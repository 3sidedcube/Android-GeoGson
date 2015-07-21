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
			throw new RuntimeException("No exterior ring definied");
	}
}
