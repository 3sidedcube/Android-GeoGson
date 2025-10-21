package com.cube.geojson;

import java.io.Serial;
import java.util.List;

public class MultiLineString extends Geometry<List<LngLatAlt>>
{
	@Serial
	private static final long serialVersionUID = 5954111454087547522L;

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
