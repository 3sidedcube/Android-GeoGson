package com.cube.geojson.model;

import com.google.gson.annotations.SerializedName;

/**
 * A final location type for GeoJson
 */
public class GeoJsonLocation
{
	@SerializedName("location") private Shape shape;

	public Shape getShape()
	{
		return shape;
	}
}
