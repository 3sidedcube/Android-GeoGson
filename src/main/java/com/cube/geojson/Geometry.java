package com.cube.geojson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Base class for geometries. See http://geojson.org/geojson-spec.html#geometrycollection for more info
 *
 * @param <T> The type of geometry
 */
public abstract class Geometry<T> extends GeoJsonObject
{
	protected List<T> coordinates = new ArrayList<T>();

	/**
	 * Class constructor
	 */
	public Geometry()
	{
	}

	/**
	 * Class constructor
	 *
	 * @param coordinates The list of coordinates to initialse the geometry with
	 */
	public Geometry(T... coordinates)
	{
		if (coordinates != null)
		{
			Collections.addAll(this.coordinates, coordinates);
		}
	}

	/**
	 * Adds a new coordinate to the list of coordinates
	 *
	 * @param coordinate The coordinate to add
	 *
	 * @return The geometry class to allow for chaining
	 */
	public Geometry<T> addCoordinates(T coordinate)
	{
		coordinates.add(coordinate);
		return this;
	}

	/**
	 * Sets the geometry coordinates
	 *
	 * @param coordinates The new coordinates (PBV)
	 */
	public void setCoordinates(List<T> coordinates)
	{
		this.coordinates.clear();
		this.coordinates.addAll(coordinates);
	}

	/**
	 * Gets the list of coordinates from the geometry
	 *
	 * @return The list of coordinates
	 */
	public List<T> getCoordinates()
	{
		return coordinates;
	}
}
