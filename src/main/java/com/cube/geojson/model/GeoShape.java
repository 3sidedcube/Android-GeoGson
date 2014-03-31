package com.cube.geojson.model;

/**
 * Dynamic class for holding different types for GeoJson shapes
 */
public abstract class GeoShape
{
	public static final String TYPE_POINT = "point";
	public static final String TYPE_ENVELOPE = "envelope";
	public static final String TYPE_POLYGON = "polygon";
	public static final String TYPE_MULTIPOLYGON = "multipolygon";
	public static final String TYPE_CIRCLE = "circle";

	private String type;
	private CoordinateGeometry coordinates;

	public String getType()
	{
		return type;
	}
	public CoordinateGeometry getCoordinates()
	{
		return coordinates;
	}
}
