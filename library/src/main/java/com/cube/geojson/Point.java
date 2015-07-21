package com.cube.geojson;

public class Point extends GeoJsonObject
{
	protected LngLatAlt coordinates;

	public Point(LngLatAlt coordinates) {
		this.coordinates = coordinates;
	}

	public Point(double longitude, double latitude) {
		coordinates = new LngLatAlt(longitude, latitude);
	}

	public Point(double longitude, double latitude, double altitude) {
		coordinates = new LngLatAlt(longitude, latitude, altitude);
	}

	public LngLatAlt getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(LngLatAlt coordinates) {
		this.coordinates = coordinates;
	}

	@Override public void finishPopulate()
	{
		setBbox(new double[]
		{
			getCoordinates().getLongitude(),
			getCoordinates().getLongitude(),
			getCoordinates().getLatitude(),
			getCoordinates().getLatitude()
		});
	}
}
