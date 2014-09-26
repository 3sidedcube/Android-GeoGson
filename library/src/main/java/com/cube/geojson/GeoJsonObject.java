package com.cube.geojson;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Base geo json object. This has the common members and methods that apply to all of the different types of objects.
 */
public abstract class GeoJsonObject implements Serializable
{
	/**
	 * The type of geo json object, can be one of
	 *
	 * <ol>
	 * 	<li>{@link Circle}</li>
	 * 	<li>{@link LineString}</li>
	 * 	<li>{@link MultiLineString}</li>
	 * 	<li>{@link Point}</li>
	 * 	<li>{@link MultiPoint}</li>
	 * 	<li>{@link Polygon}</li>
	 * 	<li>{@link MultiPolygon}</li>
	 * 	<li>{@link Envelope}</li>
	 * </ol>
	 */
	protected String type;

	/**
	 * The bounding box coordinates of the object
	 */
	protected double[] boundingBox;

	/**
	 * The map of properties as a KVP
	 */
	protected Map<String, Object> properties = new HashMap<String, Object>();

	public GeoJsonObject()
	{
		type = getClass().getSimpleName();
	}

	/**
	 * Sets the type of object  can be one of
	 *
	 * <ol>
	 * 	<li>{@link Circle}</li>
	 * 	<li>{@link LineString}</li>
	 * 	<li>{@link MultiLineString}</li>
	 * 	<li>{@link Point}</li>
	 * 	<li>{@link MultiPoint}</li>
	 * 	<li>{@link Polygon}</li>
	 * 	<li>{@link MultiPolygon}</li>
	 * 	<li>{@link Envelope}</li>
	 * </ol>
	 *
	 * @param type The type of object
	 */
	public void setType(String type)
	{
		this.type = type;
	}

	/**
	 * Gets the type of object
	 *
	 * @return The type of object
	 */
	public String getType()
	{
		return this.type;
	}

	/**
	 * Sets the bounding box of the object
	 *
	 * @param boundingBox The bounding box of the object in the format {lng, lat}
	 */
	public void setBoundingBox(double[] boundingBox)
	{
		this.boundingBox = boundingBox;
	}

	/**
	 * Gets the bounding box of the object
	 *
	 * @return The bounding box in the format {lng, lat}
	 */
	public double[] getBoundingBox()
	{
		return boundingBox;
	}

	/**
	 * Sets a property in the {@link #properties} map
	 *
	 * @param key The key of the property
	 * @param value The valud of the property
	 */
	public void setProperty(String key, Object value)
	{
		properties.put(key, value);
	}

	/**
	 * Gets a property from the {@link #properties} map
	 *
	 * @param key They key of the value to find
	 *
	 * @return The found value or null
	 */
	public Object getProperty(String key)
	{
		return properties.get(key);
	}

	/**
	 * Sets the properties of the object
	 *
	 * @param properties The new map of properties (PBR)
	 */
	public void setProperties(Map<String, Object> properties)
	{
		this.properties = properties;
	}

	/**
	 * Gets the map of {@link #properties}
	 *
	 * @return The map of properties associated with the object
	 */
	public Map<String, Object> getProperties()
	{
		return properties;
	}

	public abstract void finishPopulate();
}
