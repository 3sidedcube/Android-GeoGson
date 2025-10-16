package com.cube.geojson;

import com.google.gson.annotations.SerializedName;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

public abstract class Geometry<T> extends GeoJsonObject
{
	@Serial
	private static final long serialVersionUID = -6481665000987288570L;

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
