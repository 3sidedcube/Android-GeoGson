package com.cube.geojson;

import com.cube.geojson.gson.GeoJsonObjectAdapter;
import com.cube.geojson.gson.LngLatAltAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

/**
 * Entrypoint for generating Gson parser with required overrides
 */
public final class GeoJson
{
	public static boolean isUsingLowerCaseTypes = false;

	/**
	 * @deprecated Use {@link com.cube.geojson.GeoJson#registerAdapters(com.google.gson.GsonBuilder)}
	 */
	public static Gson getGson()
	{
		return new GsonBuilder()
				.registerTypeAdapter(GeoJsonObject.class, new GeoJsonObjectAdapter())
				.registerTypeAdapter(LngLatAlt.class, new LngLatAltAdapter())
				.create();
	}

	/**
	 * Add the required serialization adapters to the Gson builder
	 */
	public static GsonBuilder registerAdapters(GsonBuilder builder)
	{
		builder.registerTypeAdapter(GeoJsonObject.class, new GeoJsonObjectAdapter());
		builder.registerTypeAdapter(LngLatAlt.class, new LngLatAltAdapter());
		return builder;
	}

	public static void useLowerCaseTypes(boolean lowerCase)
	{
		GeoJson.isUsingLowerCaseTypes = lowerCase;
	}

	public static boolean pointInPolygon(List<List<LngLatAlt>> polygon, Point point)
	{
		int intersections = 0;
		// Check if point matches existing point, return from here to skip intersection computation
		for (List<LngLatAlt> coordinate : polygon)
		{
			for (int i = 1; i < coordinate.size(); i++)
			{
				LngLatAlt v1 = coordinate.get(i - 1), v2 = coordinate.get(i);
				// TODO ignore lines that could not possibly intersect with coordinates of the given point
				if (point.getCoordinates().equals(v2))
				{
					return true;
				}
				if (v1.getLatitude() == v2.getLatitude()
						&& v1.getLatitude() == point.getCoordinates().getLatitude()
						&& point.getCoordinates().getLongitude() > (v1.getLongitude() > v2.getLongitude() ? v2.getLongitude() : v1.getLongitude())
						&& point.getCoordinates().getLongitude() < (v1.getLongitude() < v2.getLongitude() ? v2.getLongitude() : v1.getLongitude()))
				{
					// Is horizontal polygon boundary
					return true;
				}
				if (point.getCoordinates().getLatitude() > (v1.getLatitude() < v2.getLatitude() ? v1.getLatitude() : v2.getLatitude())
						&& point.getCoordinates().getLatitude() <= (v1.getLatitude() < v2.getLatitude() ? v2.getLatitude() : v1.getLatitude())
						&& point.getCoordinates().getLongitude() <= (v1.getLongitude() < v2.getLongitude() ? v2.getLongitude() : v1.getLongitude())
						&& v1.getLatitude() != v2.getLatitude())
				{
					double intersection = (
							(point.getCoordinates().getLatitude() - v1.getLatitude()) *
									(v2.getLongitude() - v1.getLongitude()) /
									(v2.getLatitude() - v1.getLatitude()) +
									v1.getLongitude());
					if (intersection == point.getCoordinates().getLongitude())
					{
						// Is other boundary
						return true;
					}
					if (v1.getLongitude() == v2.getLongitude() || point.getCoordinates().getLongitude() <= intersection)
					{
						intersections++;
					}
				}
			}
		}
		return intersections % 2 != 0;
	}
}
