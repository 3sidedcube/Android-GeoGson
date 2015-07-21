package com.cube.geojson.test;

import com.google.gson.Gson;

import com.cube.geojson.GeoJson;
import com.cube.geojson.LngLatAlt;
import com.cube.geojson.Polygon;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class PolygonTest {

	private Gson mapper = GeoJson.getGson();

	@Test
	public void itShouldSerialize() throws Exception {
		Polygon polygon = new Polygon(MockData.EXTERNAL);
		assertEquals("{\"coordinates\":"
				+ "[[[100.0,0.0],[101.0,0.0],[101.0,1.0],[100.0,1.0],[100.0,0.0]]],\"type\":\"Polygon\"}",
				mapper.toJson(polygon));
	}

	@Test
	public void itShouldSerializeWithHole() throws Exception {
		Polygon polygon = new Polygon(MockData.EXTERNAL);
		polygon.addInteriorRing(MockData.INTERNAL);
		assertEquals("{\"coordinates\":"
				+ "[[[100.0,0.0],[101.0,0.0],[101.0,1.0],[100.0,1.0],[100.0,0.0]],"
				+ "[[100.2,0.2],[100.8,0.2],[100.8,0.8],[100.2,0.8],[100.2,0.2]]],\"type\":\"Polygon\"}", mapper.toJson(polygon));
	}

	@Test(expected = RuntimeException.class)
	public void itShouldFailOnAddInteriorRingWithoutExteriorRing() throws Exception {
		Polygon polygon = new Polygon();
		polygon.addInteriorRing(MockData.EXTERNAL);
	}

	@Test
	public void itShouldDeserialize() throws Exception
	{
		Polygon polygon = mapper.fromJson("{\"coordinates\":"
				+ "[[[100.0,0.0],[101.0,0.0],[101.0,1.0],[100.0,1.0],[100.0,0.0]],"
				+ "[[100.2,0.2],[100.8,0.2],[100.8,0.8],[100.2,0.8],[100.2,0.2]]],\"type\":\"Polygon\"}", Polygon.class);
		assertListEquals(MockData.EXTERNAL, polygon.getExteriorRing());
		assertListEquals(MockData.INTERNAL, polygon.getInteriorRing(0));
		assertListEquals(MockData.INTERNAL, polygon.getInteriorRings().get(0));
	}

	private void assertListEquals(List<LngLatAlt> expectedList, List<LngLatAlt> actualList) {
		for (int x = 0; x < actualList.size(); x++) {
			LngLatAlt expected = expectedList.get(x);
			LngLatAlt actual = actualList.get(x);
			PointTest.assertLngLatAlt(expected.getLongitude(), expected.getLatitude(), expected.getAltitude(), actual);
		}
	}
}
