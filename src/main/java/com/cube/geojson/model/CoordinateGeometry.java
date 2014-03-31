package com.cube.geojson.model;

import java.util.ArrayList;

/**
 * CoordinateGeometry type for holding dynamic coordinates
 */
public class CoordinateGeometry extends ArrayList<CoordinateGeometry>
{
	public boolean isCoordinate()
	{
		return false;
	}
}
