package org.geojson;

import java.util.HashMap;
import java.util.Map;

public abstract class GeoJsonObject
{
	protected String type;

	private Crs crs;
	private double[] bbox;

	private Map<String, Object> properties = new HashMap<String, Object>();

	public GeoJsonObject()
	{
		type = getClass().getSimpleName();
	}

	public Crs getCrs() {
		return crs;
	}

	public void setCrs(Crs crs) {
		this.crs = crs;
	}

	public double[] getBbox() {
		return bbox;
	}

	public void setBbox(double[] bbox) {
		this.bbox = bbox;
	}

	public void setProperty(String key, Object value) {
		properties.put(key, value);
	}

	@SuppressWarnings("unchecked")
	public <T> T getProperty(String key) {
		return (T)properties.get(key);
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}
}
