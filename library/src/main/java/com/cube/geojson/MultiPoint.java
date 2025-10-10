package com.cube.geojson;


public class MultiPoint extends Geometry<LngLatAlt>
{
	public static String TYPE_NAME = "MultiPoint";

	public MultiPoint()
	{
		super(TYPE_NAME);
	}

	protected MultiPoint(String type)
	{
		super(type);
	}

	@Override public void finishPopulate()
	{

	}

	public MultiPoint(LngLatAlt... points)
	{
		super(TYPE_NAME, points);
	}

	protected MultiPoint(String type, LngLatAlt... points)
	{
		super(type, points);
	}
}
