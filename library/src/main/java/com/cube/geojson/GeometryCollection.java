package com.cube.geojson;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A collection of {@link com.cube.geojson.Geometry} objects. See http://geojson.org/geojson-spec.html#geometrycollection for more information
 */
public class GeometryCollection extends GeoJsonObject implements Iterable<GeoJsonObject>
{
	/**
	 * The collection of geometries
	 */
	protected List<GeoJsonObject> geometries = new ArrayList<GeoJsonObject>();

	/**
	 * Adds a new geometry object to the list of geometries
	 *
	 * @param geometry The new geometry object to add
	 *
	 * @return The class to allow for chaining
	 */
	public GeometryCollection add(GeoJsonObject geometry)
	{
		geometries.add(geometry);
		return this;
	}

	/**
	 * Sets the list of geometries
	 *
	 * @param geometries The new list of geometry objects
	 */
	public void setGeometries(List<GeoJsonObject> geometries)
	{
		this.geometries.clear();
		this.geometries.addAll(geometries);
	}

	/**
	 * Gets the list of geometries in the object
	 *
	 * @return The list of geometries
	 */
	public List<GeoJsonObject> getGeometries()
	{
		return geometries;
	}

	@Override public Iterator<GeoJsonObject> iterator()
	{
		return geometries.iterator();
	}

	@Override public void finishPopulate()
	{

	}
}
