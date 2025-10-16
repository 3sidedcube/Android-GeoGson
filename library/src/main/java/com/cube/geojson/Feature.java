package com.cube.geojson;

import com.google.gson.annotations.SerializedName;

import java.io.Serial;

public class Feature extends GeoJsonObject
{
	@Serial
	private static final long serialVersionUID = 5351451912626812523L;

	public static String TYPE_NAME = "Feature";
	@SerializedName("geometry") private GeoJsonObject geometry;
	@SerializedName("id") private String id;

	public Feature() {
		super(TYPE_NAME);
	}

	public GeoJsonObject getGeometry()
	{
		return geometry;
	}

	public void setGeometry(GeoJsonObject geometry)
	{
		this.geometry = geometry;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	@Override public void finishPopulate()
	{

	}
}
