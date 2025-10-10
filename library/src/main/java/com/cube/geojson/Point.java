package com.cube.geojson;

import com.google.gson.annotations.SerializedName;

public class Point extends GeoJsonObject
{
	public static String TYPE_NAME = "Point";

	@SerializedName("coordinates") protected LngLatAlt coordinates;

	public Point()
	{
		super(TYPE_NAME);
	}

	protected Point(String type)
	{
		super(type);
	}

	public Point(LngLatAlt coordinates)
	{
		super(TYPE_NAME);
		this.coordinates = coordinates;
	}

	protected Point(String type, LngLatAlt coordinates)
	{
		super(type);
		this.coordinates = coordinates;
	}

	public Point(double longitude, double latitude)
	{
		super(TYPE_NAME);
		coordinates = new LngLatAlt(longitude, latitude);
	}

	protected Point(String type, double longitude, double latitude)
	{
		super(type);
		coordinates = new LngLatAlt(longitude, latitude);
	}

	public Point(double longitude, double latitude, double altitude)
	{
		super(TYPE_NAME);
		coordinates = new LngLatAlt(longitude, latitude, altitude);
	}

	protected Point(String type, double longitude, double latitude, double altitude)
	{
		super(type);
		coordinates = new LngLatAlt(longitude, latitude, altitude);
	}

	public LngLatAlt getCoordinates()
	{
		return coordinates;
	}

	public void setCoordinates(LngLatAlt coordinates)
	{
		this.coordinates = coordinates;
	}

	@Override public void finishPopulate()
	{
		setBbox(new double[]{getCoordinates().getLongitude(), getCoordinates().getLongitude(), getCoordinates().getLatitude(), getCoordinates().getLatitude()});
	}
}
