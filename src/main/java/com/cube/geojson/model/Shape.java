package com.cube.geojson.model;

/**
 * Dynamic class for holding different types for GeoJson shapes
 */
public class Shape
{
	public static final String POINT = "point";
	public static final String ENVELOPE = "envelope";
	public static final String POLYGON = "polygon";
	public static final String MULTIPOLYGON = "multipolygon";
	public static final String CIRCLE = "circle";

	private String type;
	private CoordinateGeometry coordinates;
	private double radius;

	public String getType()
	{
		return type;
	}

	/**
	 * Returns the dynamic geometry of this particular shape
	 * @return The geometry of this shape.
	 */
	public CoordinateGeometry getCoordinates()
	{
		return coordinates;
	}

	/**
	 * If this shape is a Circle, this returns the associated radius, else 0.0
	 * @return The radius of the circle, if applicable.
	 */
	public double getRadius()
	{
		return radius;
	}
}
