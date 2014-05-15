package com.cube.geojson.test;

import com.google.gson.Gson;

import com.cube.geojson.GeoJson;
import com.cube.geojson.LngLatAlt;
import com.cube.geojson.MultiPoint;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class MultiPointTest {

	private Gson mapper = GeoJson.getGson();

	@Test
	public void itShouldSerializeMultiPoint() throws Exception {
		MultiPoint multiPoint = new MultiPoint(new LngLatAlt(100, 0), new LngLatAlt(101, 1));
		Assert.assertEquals("{\"coordinates\":[[100.0,0.0],[101.0,1.0]],\"type\":\"MultiPoint\"}",
				mapper.toJson(multiPoint));
	}

	@Test
	public void itShouldDeserializeMultiPoint() throws Exception {
		MultiPoint multiPoint = mapper.fromJson("{\"coordinates\":[[100.0,0.0],[101.0,1.0]],\"type\":\"MultiPoint\"}",
				MultiPoint.class);
		assertNotNull(multiPoint);
		List<LngLatAlt> coordinates = multiPoint.getCoordinates();
		PointTest.assertLngLatAlt(100, 0, Double.NaN, coordinates.get(0));
		PointTest.assertLngLatAlt(101, 1, Double.NaN, coordinates.get(1));
	}
}
