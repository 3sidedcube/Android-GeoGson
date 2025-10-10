package com.cube.geojson;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public abstract class Geometry<T> extends GeoJsonObject
{
	@SerializedName("coordinates") protected List<T> coordinates = new ArrayList<>();

	protected Geometry(String type)
	{
		super(type);
	}

	protected Geometry(String type, T... elements)
	{
		super(type);
		for (T coordinate : elements)
		{
			coordinates.add(coordinate);
		}
	}

	public Geometry<T> add(T elements)
	{
		coordinates.add(elements);
		return this;
	}

	public List<T> getCoordinates()
	{
		return coordinates;
	}

	public void setCoordinates(List<T> coordinates)
	{
		this.coordinates = coordinates;
	}
}
