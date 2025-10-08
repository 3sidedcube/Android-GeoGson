package com.cube.geojson;

public class Circle extends Point
{
	public static String TYPE_NAME = "Circle";
	private double radius;

	public Circle()
	{
		super(TYPE_NAME);
	}

	public Circle(LngLatAlt coordinates, double radius)
	{
		super(TYPE_NAME, coordinates);
		this.radius = radius;
	}

	public Circle(double longitude, double latitude, double radius)
	{
		super(TYPE_NAME, longitude, latitude);
		this.radius = radius;
	}

	public Circle(double longitude, double latitude, double altitude, double radius)
	{
		super(TYPE_NAME, longitude, latitude, altitude);
		this.radius = radius;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	@Override public String toString()
	{
		return "[com.cube.geojson.Circle type: " + type + " coordinates: " + coordinates + " radius: " + radius + "]";
	}
}
