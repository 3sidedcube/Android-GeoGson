package com.cube.geojson;

import java.util.Arrays;
import java.util.List;

public class Polygon extends Geometry<List<LngLatAlt>>
{
	public Polygon()
	{
	}

	/**
	 * Class constructor
	 *
	 * @param polygon The list of coordinates to use as the exterior polygon ring
	 */
	public Polygon(List<LngLatAlt> polygon)
	{
		setExteriorRing(polygon);
	}

	/**
	 * Class constructor
	 *
	 * @param polygon The list of coordinates to use as the exterior polygon ring
	 * @param holes The list of list of coordinates of holes in the polygon
	 */
	public Polygon(List<LngLatAlt> polygon, List<List<LngLatAlt>> holes)
	{
		setExteriorRing(polygon);

		if (holes != null)
		{
			for (List<LngLatAlt> hole : holes)
			{
				addInteriorRing(hole);
			}
		}
	}

	/**
	 * Class constructor
	 *
	 * @param polygon The list of coordinates to use as the exterior polygon ring
	 */
	public Polygon(LngLatAlt... polygon)
	{
		setExteriorRing(Arrays.asList(polygon));
	}

	/**
	 * Sets the exterior ring of the polygon
	 *
	 * @param points The list of coordinates to set the exterior ring to
	 */
	public void setExteriorRing(List<LngLatAlt> points)
	{
		coordinates.add(0, points);
	}

	/**
	 * Returns the list of coordinates defining the exterior ring of the polygon
	 *
	 * @return The list of exterior points
	 */
	public List<LngLatAlt> getExteriorRing()
	{
		assertExteriorRing();
		return coordinates.get(0);
	}

	/**
	 * Adds an interior ring to the polygon
	 *
	 * @param points The list of points to add to the ring
	 */
	public void addInteriorRing(List<LngLatAlt> points)
	{
		assertExteriorRing();
		coordinates.add(points);
	}

	/**
	 * Adds an interior ring to the polygon
	 *
	 * @param points The list of points to add to the ring
	 */
	public void addInteriorRing(LngLatAlt... points)
	{
		assertExteriorRing();
		coordinates.add(Arrays.asList(points));
	}

	/**
	 * Gets the list of internal ring coordinates of the object
	 *
	 * @return The list of list of coordinates of the interior points
	 */
	public List<List<LngLatAlt>> getInteriorRings()
	{
		assertExteriorRing();
		return coordinates.subList(1, coordinates.size());
	}

	/**
	 * Gets the list of internal ring coordinates for the specific index
	 *
	 * @param index The index of the interior ring to fetch
	 *
	 * @return The list of list of coordinates of the interior points at index
	 */
	public List<LngLatAlt> getInteriorRing(int index)
	{
		assertExteriorRing();
		return coordinates.get(1 + index);
	}

	/**
	 * Ensures that the external ring is defined before proceeding
	 */
	private void assertExteriorRing()
	{
		if (coordinates.isEmpty())
		{
			throw new RuntimeException("No exterior ring definied");
		}
	}
}
