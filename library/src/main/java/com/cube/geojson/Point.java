package com.cube.geojson;

/**
 * A simple geometry point with a Longitude and Latitude coordinate
 *
 * <pre>
 *     {
 *         "type": "Point",
 *         "coordinates": [
 *              125.6, 10.1
 *         ]
 *     }
 * </pre>
 */
public class Point extends GeoJsonObject
{
	/**
	 * The coordinates of the point
	 */
	protected LngLatAlt coordinates;

	/**
	 * Class constructor
	 *
	 * @param point The base point coordinates to copy from (PBV)
	 */
	public Point(Point point)
	{
		this.coordinates = point.getCoordinates();
	}

	/**
	 * Class constructor
	 *
	 * @param coordinates The lat, lng, alt coordianes to use
	 */
	public Point(LngLatAlt coordinates)
	{
		this.coordinates = coordinates;
	}

	/**
	 * Class constructor
	 *
	 * @param longitude The longitude coordinate for the point
	 * @param latitude The latitude coordinate for the point
	 */
	public Point(double longitude, double latitude)
	{
		coordinates = new LngLatAlt(longitude, latitude);
	}

	/**
	 * Class constructor
	 *
	 * @param longitude The longitude coordinate for the point
	 * @param latitude The latitude coordinate for the point
	 * @param altitude The altitude (in metres) for the point
	 */
	public Point(double longitude, double latitude, double altitude)
	{
		coordinates = new LngLatAlt(longitude, latitude, altitude);
	}

	/**
	 * Gets the coordinates of the point
	 *
	 * @return The coordinates of the point
	 */
	public LngLatAlt getCoordinates()
	{
		return coordinates;
	}

	/**
	 * Sets the coordinates of the point
	 *
	 * @param coordinates The new coordinates of the point
	 */
	public void setCoordinates(LngLatAlt coordinates)
	{
		this.coordinates = coordinates;
	}

	@Override public String toString()
	{
		return "[com.cube.geojson.Point type: " + type + " coordinates: " + coordinates + "]";
	}
}
