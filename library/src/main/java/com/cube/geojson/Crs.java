package com.cube.geojson;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class Crs
{
	@SerializedName("type") private String type = "name";
	@SerializedName("properties") private Map<String, Object> properties = new HashMap<>();

	public String getType()
	{
		return type;
	}

	public Map<String, Object> getProperties()
	{
		return properties;
	}

	public void setProperties(Map<String, Object> properties)
	{
		this.properties = properties;
	}
}
