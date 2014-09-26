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
public class Point extends Geometry<LngLatAlt>
{
	/**
	 * Class constructor
	 *
	 * @param point The base point coordinates to copy from (PBV)
	 */
	public Point(Point point)
	{
		addCoordinates(point.getCoordinates().get(0));
	}

	/**
	 * Class constructor
	 *
	 * @param coordinates The lat, lng, alt coordianes to use
	 */
	public Point(LngLatAlt coordinates)
	{
		addCoordinates(coordinates);
	}

	/**
	 * Class constructor
	 *
	 * @param longitude The longitude coordinate for the point
	 * @param latitude The latitude coordinate for the point
	 */
	public Point(double longitude, double latitude)
	{
		addCoordinates(new LngLatAlt(longitude, latitude));
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
		addCoordinates(new LngLatAlt(longitude, latitude, altitude));
	}

	@Override public String toString()
	{
		return "[com.cube.geojson.Point type: " + type + " coordinates: " + coordinates + "]";
	}
}
