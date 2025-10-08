package com.cube.geojson;


public class LineString extends MultiPoint
{
	public static String TYPE_NAME = "LineString";

	public LineString()
	{
		super(TYPE_NAME);
	}

	public LineString(LngLatAlt... points)
	{
		super(TYPE_NAME, points);
	}
}
