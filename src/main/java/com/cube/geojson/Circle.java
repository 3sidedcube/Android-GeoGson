package com.cube.geojson;

/**
 * (Unofficial) circle support with the format
 *
 * <pre>
 *     {
 *         "type": "Circle",
 *         "coordinates": [
 *              125.6, 10.1
 *         ],
 *         "radius": 1000.0
 *     }
 * </pre>
 */
public class Circle extends Point
{
	/**
	 * The radius in metres
	 */
	protected double radius;

	/**
	 * Class constructor
	 *
	 * @param coordinates The coordinate for the point
	 * @param radius The radius (in metres) of the circle
	 */
	public Circle(LngLatAlt coordinates, double radius)
	{
		super(coordinates);
		this.radius = radius;
	}

	/**
	 * Class constructor
	 *
	 * @param point The base point coordinates to use
	 * @param radius The radius (in metres) of the circle
	 */
	public Circle(Point point, double radius)
	{
		super(point);
		this.radius = radius;
	}

	/**
	 * Class constructor
	 *
	 * @param longitude The longitude coordinate for the point
	 * @param latitude The longitude coordinate for the point
	 * @param radius The radius (in metres) of the circle
	 */
	public Circle(double longitude, double latitude, double radius)
	{
		super(longitude, latitude);
		this.radius = radius;
	}

	/**
	 * Class constructor
	 *
	 * @param longitude The longitude coordinate for the point
	 * @param latitude The longitude coordinate for the point
	 * @param altitude The altitude (in metres) for the point
	 * @param radius The radius (in metres) of the circle
	 */
	public Circle(double longitude, double latitude, double altitude, double radius)
	{
		super(longitude, latitude, altitude);
		this.radius = radius;
	}

	/**
	 * Gets the radius of the circle (in metres)
	 *
	 * @return The radius of the circle (in metres)
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * Sets the radius of the circle (in metres)
	 */
	public void setRadius(double radius) {
		this.radius = radius;
	}

	@Override public String toString()
	{
		return "[com.cube.geojson.Circle type: " + type + " coordinates: " + coordinates + " radius: " + radius + "]";
	}
}
