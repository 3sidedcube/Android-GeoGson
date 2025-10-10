package com.cube.geojson;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class GeoJsonObject implements Serializable
{
	@SerializedName("type") protected String type;
	@SerializedName("crs") private Crs crs;
	@SerializedName("bbox") private double[] bbox;

	@SerializedName("properties") private Map<String, Object> properties;

	public void setType(String type)
	{
		this.type = type;
	}

	public String getType()
	{
		return this.type;
	}

	protected GeoJsonObject(String type)
	{
		this.type = type;
	}

	public Crs getCrs()
	{
		return crs;
	}

	public void setCrs(Crs crs)
	{
		this.crs = crs;
	}

	public double[] getBbox()
	{
		return bbox;
	}

	public void setBbox(double[] bbox)
	{
		this.bbox = bbox;
	}

	public void setProperty(String key, Object value)
	{
		if (properties == null)
		{
			properties = new HashMap<String, Object>();
		}

		properties.put(key, value);
	}

	@SuppressWarnings("unchecked") public <T> T getProperty(String key)
	{
		if (properties == null)
		{
			return null;
		}

		return (T)properties.get(key);
	}

	public Map<String, Object> getProperties()
	{
		return properties;
	}

	public void setProperties(Map<String, Object> properties)
	{
		this.properties = properties;
	}

	public abstract void finishPopulate();
}
