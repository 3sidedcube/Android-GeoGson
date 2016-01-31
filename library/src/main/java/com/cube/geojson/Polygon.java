package com.cube.geojson;

import java.util.Arrays;
import java.util.List;

public class Polygon extends Geometry<List<LngLatAlt>> {

	public Polygon() {
	}

	@Override public void finishPopulate()
	{

	}

	public Polygon(List<LngLatAlt> polygon) {
		add(polygon);
	}

	public Polygon(LngLatAlt... polygon) {
		add(Arrays.asList(polygon));
	}

	public void setExteriorRing(List<LngLatAlt> points) {
		coordinates.add(0, points);
	}

	public List<LngLatAlt> getExteriorRing() {
		assertExteriorRing();
		return coordinates.get(0);
	}

	public List<List<LngLatAlt>> getInteriorRings() {
		assertExteriorRing();
		return coordinates.subList(1, coordinates.size());
	}

	public List<LngLatAlt> getInteriorRing(int index) {
		assertExteriorRing();
		return coordinates.get(1 + index);
	}

	public void addInteriorRing(List<LngLatAlt> points) {
		assertExteriorRing();
		coordinates.add(points);
	}

	public void addInteriorRing(LngLatAlt... points) {
		assertExteriorRing();
		coordinates.add(Arrays.asList(points));
	}

	private void assertExteriorRing() {
		if (coordinates.isEmpty())
			throw new RuntimeException("No exterior ring defined");
	}

	public boolean contains(Point point)
	{
		int intersections = 0;
		// Check if point matches existing point, return from here to skip intersection computation
		for (List<LngLatAlt> coordinate : coordinates)
		{
			for (int i = 1; i < coordinate.size(); i++)
			{
				LngLatAlt v1 = coordinate.get(i - 1), v2 = coordinate.get(i);
				// TODO ignore lines that could not possibly intersect with coordinates of the given point
				if (point.getCoordinates().equals(v2))
				{
					return true;
				}
				if (v1.getLatitude() == v2.getLatitude()
						&& v1.getLatitude() == point.getCoordinates().getLatitude()
						&& point.getCoordinates().getLongitude() > (v1.getLongitude() > v2.getLongitude() ? v2.getLongitude() : v1.getLongitude())
						&& point.getCoordinates().getLongitude() < (v1.getLongitude() < v2.getLongitude() ? v2.getLongitude() : v1.getLongitude()))
				{
					// Is horizontal polygon boundary
					return true;
				}
				if (point.getCoordinates().getLatitude() > (v1.getLatitude() < v2.getLatitude() ? v1.getLatitude() : v2.getLatitude())
						&& point.getCoordinates().getLatitude() <= (v1.getLatitude() < v2.getLatitude() ? v2.getLatitude() : v1.getLatitude())
						&& point.getCoordinates().getLongitude() <= (v1.getLongitude() < v2.getLongitude() ? v2.getLongitude() : v1.getLongitude())
						&& v1.getLatitude() != v2.getLatitude())
				{
					double intersection = (
							(point.getCoordinates().getLatitude() - v1.getLatitude()) *
									(v2.getLongitude() - v1.getLongitude()) /
									(v2.getLatitude() - v1.getLatitude()) +
									v1.getLongitude());
					if (intersection == point.getCoordinates().getLongitude())
					{
						// Is other boundary
						return true;
					}
					if (v1.getLongitude() == v2.getLongitude() || point.getCoordinates().getLongitude() <= intersection)
					{
						intersections++;
					}
				}
			}
		}
		return intersections % 2 != 0;
	}
}
