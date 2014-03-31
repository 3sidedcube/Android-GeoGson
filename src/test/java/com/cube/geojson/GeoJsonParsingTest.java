package com.cube.geojson;

import com.cube.geojson.model.GeoCircle;
import com.cube.geojson.model.GeoEnvelope;
import com.cube.geojson.model.GeoMultipolygon;
import com.cube.geojson.model.GeoPoint;
import com.cube.geojson.model.GeoPolygon;
import com.cube.geojson.model.GeoShape;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Test for making sure parsing works
 */
@RunWith(JUnit4.class)
public class GeoJsonParsingTest
{
	private static final String testPointJson = "{\"location\":{\"type\":\"point\",\"coordinates\":[45.0, -45.0]}}";
	private static final String testEnvelopeJson = "{\"location\":{\"type\":\"envelope\",\"coordinates\":[[-45,45],[45,-45]]}}";
	private static final String testPolygonJson = "{\"location\":{\"type\":\"polygon\",\"coordinates\":[[[100,0],[101,0],[101,1],[100,1],[100,0]]]}}";
	private static final String testMultipolygonJson = "{\"location\":{\"type\":\"multipolygon\",\"coordinates\":[[[[102,2],[103,2],[103,3],[102,3],[102,2]]],[[[100,0],[101,0],[101,1],[100,1],[100,0]],[[100.2,0.2],[100.8,0.2],[100.8,0.8],[100.2,0.8],[100.2,0.2]]]]}}";
	private static final String testCircleJson = "{\"location\":{\"type\":\"circle\",\"coordinates\":[45.0, -45.0],\"radius\":35.0}}";

	@Test
	public void testPointParsingCompletesSuccessfully()
	{
		GeoPoint point = GeoJson.fromJson(testPointJson, GeoPoint.class);

		Assert.assertNotNull(point);

		Assert.assertEquals(GeoShape.TYPE_POINT, point.getType());

		Assert.assertTrue(point.getCoordinates().isCoordinate());
	}

	@Test
	public void testEnvelopeParsingCompletesSuccessfully()
	{
		GeoEnvelope envelope = GeoJson.fromJson(testEnvelopeJson, GeoEnvelope.class);

		Assert.assertNotNull(envelope);

		Assert.assertNotNull(envelope.getCoordinates());

		Assert.assertEquals(GeoShape.TYPE_ENVELOPE, envelope.getType());

		Assert.assertFalse(envelope.getCoordinates().isCoordinate());
		Assert.assertTrue(envelope.getCoordinates().size() == 2);

		Assert.assertTrue(envelope.getCoordinates().get(0).isCoordinate());
	}

	@Test
	public void testPolygonParsingCompletesSuccessfully()
	{
		GeoPolygon polygon = GeoJson.fromJson(testPolygonJson, GeoPolygon.class);

		Assert.assertNotNull(polygon);

		Assert.assertEquals(GeoShape.TYPE_POLYGON, polygon.getType());

		Assert.assertFalse(polygon.getCoordinates().isCoordinate());
		Assert.assertTrue(polygon.getCoordinates().size() == 1);

		Assert.assertTrue(polygon.getCoordinates().get(0).get(0).isCoordinate());

		Assert.assertTrue(polygon.getCoordinates().get(0).size() == 5);
	}

	@Test
	public void testMultipolygonParsingCompletesSuccessfully()
	{
		GeoMultipolygon location = GeoJson.fromJson(testMultipolygonJson, GeoMultipolygon.class);

		Assert.assertNotNull(location);

		Assert.assertEquals(GeoShape.TYPE_MULTIPOLYGON, location.getType());

		Assert.assertFalse(location.getCoordinates().isCoordinate());
		Assert.assertTrue(location.getCoordinates().size() == 2);

		Assert.assertFalse(location.getCoordinates().get(0).get(0).isCoordinate());
		Assert.assertTrue(location.getCoordinates().get(0).get(0).size() == 5);

		Assert.assertTrue(location.getCoordinates().get(1).size() == 2);
	}

	@Test
	public void testCircleParsingCompletesSuccessfully()
	{
		GeoCircle location = GeoJson.fromJson(testCircleJson, GeoCircle.class);

		Assert.assertNotNull(location);

		Assert.assertEquals(GeoShape.TYPE_CIRCLE, location.getType());

		Assert.assertTrue(location.getCoordinates().isCoordinate());

		Assert.assertEquals(35.0, location.getRadius(), 0.001);
	}
}
