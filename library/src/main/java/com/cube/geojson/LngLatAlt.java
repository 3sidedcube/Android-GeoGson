package com.cube.geojson;

import java.io.Serializable;

public class LngLatAlt implements Serializable
{
	private double longitude;
	private double latitude;
	private double altitude = Double.NaN;

	public LngLatAlt() {
	}

	public LngLatAlt(double longitude, double latitude) {
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public LngLatAlt(double longitude, double latitude, double altitude) {
		this.longitude = longitude;
		this.latitude = latitude;
		this.altitude = altitude;
	}

	public boolean hasAltitude() {
		return !Double.isNaN(altitude);
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getAltitude() {
		return altitude;
	}

	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}

	@Override public String toString()
	{
		return "[com.cube.geojson.LngLatAlt lng: " + longitude + " lat: " + latitude + " alt: " + altitude + "]";
	}
}
