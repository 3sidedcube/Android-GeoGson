package com.cube.geojson.model;

/**
 * Basic 2D coordinate as the base Coordinate Structure
 */
public class Coordinate extends CoordinateGeometry
{
	private double longitude;
	private double latitude;

	public Coordinate(double longitude, double latitude)
	{
		this.longitude = longitude;
		this.latitude = latitude;

		add(this);
	}

	@Override public boolean isCoordinate()
	{
		return true;
	}

	@Override public int size()
	{
		return 2;
	}

	public double getLongitude()
	{
		return longitude;
	}

	public double getLatitude()
	{
		return latitude;
	}
}
