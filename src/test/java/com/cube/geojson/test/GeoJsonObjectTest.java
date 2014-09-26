package com.cube.geojson.test;

import com.cube.geojson.GeoGson;
import com.google.gson.Gson;

import com.cube.geojson.GeoJsonObject;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class GeoJsonObjectTest
{
	private Gson mapper = GeoGson.getGson();

	private class TestGeoJsonObject extends GeoJsonObject
	{
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

	@Test
	public void itShouldSerializeTypeToLowerCaseWhenRequested()
	{
		TestGeoJsonObject testObject = new TestGeoJsonObject();

		GeoGson.useLowerCaseTypes(true);

		String expected = "{\"type\":\"testgeojsonobject\"}";
		String produced = mapper.toJson(testObject);

		GeoGson.useLowerCaseTypes(false);

		Assert.assertEquals(expected, produced);
	}
}
