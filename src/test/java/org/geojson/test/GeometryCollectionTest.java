package org.geojson.test;

import com.google.gson.Gson;

import org.geojson.GeoJson;
import org.geojson.GeometryCollection;
import org.geojson.LineString;
import org.geojson.LngLatAlt;
import org.geojson.Point;
import org.junit.Assert;
import org.junit.Test;

public class GeometryCollectionTest {

	private Gson mapper = GeoJson.getGson();

	@Test
	public void itShouldSerialize() throws Exception {
		GeometryCollection gc = new GeometryCollection();
		gc.add(new Point(100, 0));
		gc.add(new LineString(new LngLatAlt(101, 0), new LngLatAlt(102, 1)));

		String expected = "{\"geometries\":[{\"coordinates\":[100.0,0.0],\"type\":\"Point\"},"
				+ "{\"coordinates\":[[101.0,0.0],[102.0,1.0]],\"type\":\"LineString\"}],"
				+ "\"type\":\"GeometryCollection\"}";

		String generated = mapper.toJson(gc);

		Assert.assertEquals(expected, generated);
	}
}
