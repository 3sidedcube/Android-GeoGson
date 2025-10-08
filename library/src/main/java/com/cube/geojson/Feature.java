package com.cube.geojson;

public class Feature extends GeoJsonObject
{
	public static String TYPE_NAME = "Feature";
	private GeoJsonObject geometry;
	private String id;

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
