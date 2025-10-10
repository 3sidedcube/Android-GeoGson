package com.cube.geojson;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GeometryCollection extends GeoJsonObject implements Iterable<GeoJsonObject>
{
	public static String TYPE_NAME = "GeometryCollection";

	public GeometryCollection()
	{
		super(TYPE_NAME);
	}

	@SerializedName("geometries") private List<GeoJsonObject> geometries = new ArrayList<>();

	public List<GeoJsonObject> getGeometries()
	{
		return geometries;
	}

	public void setGeometries(List<GeoJsonObject> geometries)
	{
		this.geometries = geometries;
	}

	@Override public Iterator<GeoJsonObject> iterator()
	{
		return geometries.iterator();
	}

	public GeometryCollection add(GeoJsonObject geometry)
	{
		geometries.add(geometry);
		return this;
	}

	@Override public void finishPopulate()
	{

	}
}
