package com.cube.geojson;

import com.cube.geojson.model.GeoJsonLocation;
import com.cube.geojson.model.Shape;

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
		GeoJsonLocation location = GeoJson.fromJson(testPointJson);

		Assert.assertNotNull(location);

		Assert.assertNotNull(location.getShape());

		Assert.assertEquals(Shape.POINT, location.getShape().getType());

		Assert.assertTrue(location.getShape().getCoordinates().isCoordinate());

		Assert.assertEquals(0.0, location.getShape().getRadius(), 0.001);
	}

	@Test
	public void testEnvelopeParsingCompletesSuccessfully()
	{
		GeoJsonLocation location = GeoJson.fromJson(testEnvelopeJson);

		Assert.assertNotNull(location);

		Assert.assertNotNull(location.getShape());

		Assert.assertEquals(Shape.ENVELOPE, location.getShape().getType());

		Assert.assertFalse(location.getShape().getCoordinates().isCoordinate());
		Assert.assertTrue(location.getShape().getCoordinates().size() == 2);

		Assert.assertTrue(location.getShape().getCoordinates().get(0).isCoordinate());

		Assert.assertEquals(0.0, location.getShape().getRadius(), 0.001);
	}

	@Test
	public void testPolygonParsingCompletesSuccessfully()
	{
		GeoJsonLocation location = GeoJson.fromJson(testPolygonJson);

		Assert.assertNotNull(location);

		Assert.assertNotNull(location.getShape());

		Assert.assertEquals(Shape.POLYGON, location.getShape().getType());

		Assert.assertFalse(location.getShape().getCoordinates().isCoordinate());
		Assert.assertTrue(location.getShape().getCoordinates().size() == 1);

		Assert.assertTrue(location.getShape().getCoordinates().get(0).get(0).isCoordinate());

		Assert.assertTrue(location.getShape().getCoordinates().get(0).size() == 5);

		Assert.assertEquals(0.0, location.getShape().getRadius(), 0.001);
	}

	@Test
	public void testMultipolygonParsingCompletesSuccessfully()
	{
		GeoJsonLocation location = GeoJson.fromJson(testMultipolygonJson);

		Assert.assertNotNull(location);

		Assert.assertNotNull(location.getShape());

		Assert.assertEquals(Shape.MULTIPOLYGON, location.getShape().getType());

		Assert.assertFalse(location.getShape().getCoordinates().isCoordinate());
		Assert.assertTrue(location.getShape().getCoordinates().size() == 2);

		Assert.assertFalse(location.getShape().getCoordinates().get(0).get(0).isCoordinate());
		Assert.assertTrue(location.getShape().getCoordinates().get(0).get(0).size() == 5);

		Assert.assertTrue(location.getShape().getCoordinates().get(1).size() == 2);

		Assert.assertEquals(0.0, location.getShape().getRadius(), 0.001);
	}

	@Test
	public void testCircleParsingCompletesSuccessfully()
	{
		GeoJsonLocation location = GeoJson.fromJson(testCircleJson);

		Assert.assertNotNull(location);

		Assert.assertNotNull(location.getShape());

		Assert.assertEquals(Shape.CIRCLE, location.getShape().getType());

		Assert.assertTrue(location.getShape().getCoordinates().isCoordinate());

		Assert.assertEquals(35.0, location.getShape().getRadius(), 0.001);
	}
}
