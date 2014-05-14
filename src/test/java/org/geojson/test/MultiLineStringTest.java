package org.geojson.test;

import com.google.gson.Gson;

import org.geojson.GeoJson;
import org.geojson.LngLatAlt;
import org.geojson.MultiLineString;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class MultiLineStringTest {

	private Gson mapper = GeoJson.getGson();

	@Test
	public void itShouldSerialize() throws Exception {
		MultiLineString multiLineString = new MultiLineString();
		multiLineString.add(Arrays.asList(new LngLatAlt(100, 0), new LngLatAlt(101, 1)));
		multiLineString.add(Arrays.asList(new LngLatAlt(102, 2), new LngLatAlt(103, 3)));
		Assert.assertEquals("{\"coordinates\":"
				+ "[[[100.0,0.0],[101.0,1.0]],[[102.0,2.0],[103.0,3.0]]],\"type\":\"MultiLineString\"}", mapper.toJson(multiLineString));
	}
}
