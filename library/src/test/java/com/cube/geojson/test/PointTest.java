package com.cube.geojson.test;

import com.google.gson.Gson;

import com.cube.geojson.GeoJson;
import com.cube.geojson.GeoJsonObject;
import com.cube.geojson.LngLatAlt;
import com.cube.geojson.Point;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class PointTest {

	private Gson mapper = GeoJson.getGson();

	@Test
	public void itShouldSerializeAPoint() throws Exception {
		Point point = new Point(100, 0);
		assertEquals("{\"coordinates\":[100.0,0.0],\"type\":\"Point\"}", mapper.toJson(point));
	}

	@Test
	public void itShouldDeserializeAPoint() throws Exception {
		GeoJsonObject value = mapper.fromJson("{\"type\":\"Point\",\"coordinates\":[100.0,5.0]}", GeoJsonObject.class);
		assertNotNull(value);
		assertTrue(value instanceof Point);
		Point point = (Point)value;
		assertLngLatAlt(100, 5, Double.NaN, point.getCoordinates());
	}

	public static void assertLngLatAlt(double expectedLongitue, double expectedLatitude, double expectedAltitude,
			LngLatAlt point) {
		assertEquals(expectedLongitue, point.getLongitude(), 0.00001);
		assertEquals(expectedLatitude, point.getLatitude(), 0.00001);
		if (Double.isNaN(expectedAltitude))
			assertFalse(point.hasAltitude());
		else
			assertEquals(expectedAltitude, point.getAltitude(), 0.00001);
	}

	@Test
	public void itShouldDeserializeAPointWithAltitude() throws Exception {
		GeoJsonObject value = mapper.fromJson("{\"coordinates\":[100.0,5.0,123],\"type\":\"Point\"}",
				GeoJsonObject.class);
		Point point = (Point)value;
		assertLngLatAlt(100, 5, 123, point.getCoordinates());
	}

	@Test
	public void itShouldSerializeAPointWithAltitude() throws Exception {
		Point point = new Point(100, 0, 256);
		assertEquals("{\"coordinates\":[100.0,0.0,256.0],\"type\":\"Point\"}", mapper.toJson(point));
	}
}
