package com.cube.geojson;

import java.io.Serial;
import java.util.Arrays;
import java.util.List;

public class Polygon extends Geometry<List<LngLatAlt>>
{
	@Serial
	private static final long serialVersionUID = 3863207152526191906L;

	public static String TYPE_NAME = "Polygon";

	public Polygon()
	{
		super(TYPE_NAME);
	}

	@Override public void finishPopulate()
	{

	}

	public Polygon(List<LngLatAlt> polygon)
	{
		super(TYPE_NAME);
		add(polygon);
	}

	public Polygon(LngLatAlt... polygon)
	{
		super(TYPE_NAME);
		add(Arrays.asList(polygon));
	}

	public void setExteriorRing(List<LngLatAlt> points)
	{
		coordinates.add(0, points);
	}

	public List<LngLatAlt> getExteriorRing()
	{
		assertExteriorRing();
		return coordinates.get(0);
	}

	public List<List<LngLatAlt>> getInteriorRings()
	{
		assertExteriorRing();
		return coordinates.subList(1, coordinates.size());
	}

	public List<LngLatAlt> getInteriorRing(int index)
	{
		assertExteriorRing();
		return coordinates.get(1 + index);
	}

	public void addInteriorRing(List<LngLatAlt> points)
	{
		assertExteriorRing();
		coordinates.add(points);
	}

	public void addInteriorRing(LngLatAlt... points)
	{
		assertExteriorRing();
		coordinates.add(Arrays.asList(points));
	}

	private void assertExteriorRing()
	{
		if (coordinates.isEmpty())
		{
			throw new RuntimeException("No exterior ring defined");
		}
	}

	public boolean contains(Point point)
	{
		return GeoJson.pointInPolygon(coordinates, point);
	}
}
