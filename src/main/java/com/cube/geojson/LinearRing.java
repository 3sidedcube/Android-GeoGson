package com.cube.geojson;

import java.util.List;

/**
 * A simple geometry point with a minimum of 4 Longitude and Latitude coordinates. Start and end coordinates *must* be the same
 *
 * <pre>
 *     {
 *         "type": "LinearRing",
 *         "coordinates": [
 *              [ 125.6, 10.1 ], [ 125.6, 10.1 ], [ 125.6, 10.1 ], [ 125.6, 10.1 ]
 *         ]
 *     }
 * </pre>
 */
public class LinearRing extends Geometry<List<LngLatAlt>>
{
	public LinearRing(List<LngLatAlt> points)
	{
		addCoordinates(points);
	}

	@Override public String toString()
	{
		return "[com.cube.geojson.LinearRing type: " + type + " coordinates: " + coordinates + "]";
	}
}
