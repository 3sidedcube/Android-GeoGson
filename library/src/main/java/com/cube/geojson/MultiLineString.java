package com.cube.geojson;

import java.util.List;

public class MultiLineString extends Geometry<List<LngLatAlt>>
{
	public static String TYPE_NAME = "MultiLineString";
	
	public MultiLineString()
	{
		super(TYPE_NAME);
	}

	@Override public void finishPopulate()
	{

	}

	public MultiLineString(List<LngLatAlt> line)
	{
		super(TYPE_NAME);
		add(line);
	}
}
