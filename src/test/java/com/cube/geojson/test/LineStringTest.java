package com.cube.geojson.test;

import com.google.gson.Gson;

import com.cube.geojson.GeoJson;
import com.cube.geojson.LineString;
import com.cube.geojson.LngLatAlt;
import com.cube.geojson.MultiPoint;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class LineStringTest {

	private Gson mapper = GeoJson.getGson();

	@Test
	public void itShouldSerializeMultiPoint() throws Exception {
		MultiPoint lineString = new LineString(new LngLatAlt(100, 0), new LngLatAlt(101, 1));
		Assert.assertEquals("{\"coordinates\":[[100.0,0.0],[101.0,1.0]],\"type\":\"LineString\"}",
				mapper.toJson(lineString));
	}

	@Test
	public void itShouldDeserializeLineString() throws Exception {
		LineString lineString = mapper.fromJson("{\"coordinates\":[[100.0,0.0],[101.0,1.0]],\"type\":\"LineString\"}",
				LineString.class);
		assertNotNull(lineString);
		List<LngLatAlt> coordinates = lineString.getCoordinates();
		PointTest.assertLngLatAlt(100, 0, Double.NaN, coordinates.get(0));
		PointTest.assertLngLatAlt(101, 1, Double.NaN, coordinates.get(1));
	}
}
