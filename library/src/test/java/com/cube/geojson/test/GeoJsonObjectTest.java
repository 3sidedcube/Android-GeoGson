package com.cube.geojson.test;

import com.google.gson.Gson;

import com.cube.geojson.GeoJson;
import com.cube.geojson.GeoJsonObject;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class GeoJsonObjectTest
{
	private Gson mapper = GeoJson.getGson();

	private class TestGeoJsonObject extends GeoJsonObject
	{
		@Override public void finishPopulate()
		{
		}
	}

	@Test
	public void itShouldHaveProperties() throws Exception
	{
		TestGeoJsonObject testObject = new TestGeoJsonObject();
		assertNotNull(testObject.getProperties());
	}

	@Test
	public void itShouldNotSerializeEmptyProperties() throws Exception
	{
		TestGeoJsonObject testObject = new TestGeoJsonObject();
		Assert.assertEquals("{\"type\":\"TestGeoJsonObject\"}", mapper.toJson(testObject));
	}
}
