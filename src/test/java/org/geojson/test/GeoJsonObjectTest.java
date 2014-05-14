package org.geojson.test;

import com.google.gson.Gson;

import org.geojson.GeoJson;
import org.geojson.GeoJsonObject;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class GeoJsonObjectTest
{
	private Gson mapper = GeoJson.getGson();

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
}
