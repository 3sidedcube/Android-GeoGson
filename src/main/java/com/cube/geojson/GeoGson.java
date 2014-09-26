package com.cube.geojson;

import com.cube.geojson.gson.GeoJsonObjectAdapter;
import com.cube.geojson.gson.LngLatAltAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Entrypoint for generating Gson parser with required overrides
 */
public final class GeoGson
{
	public static boolean isUsingLowerCaseTypes = false;

	/**
	 * Gets a new gson object. You should try to use {@link GeoGson#registerAdapters(com.google.gson.GsonBuilder)} instead
	 */
	public static Gson getGson()
	{
		return registerAdapters(new GsonBuilder()).create();
	}

	/**
	 * Add the required serialization adapters to the Gson builder
	 */
	public static GsonBuilder registerAdapters(GsonBuilder builder)
	{
		builder.registerTypeAdapter(GeoJsonObject.class, new GeoJsonObjectAdapter());
		builder.registerTypeAdapter(LngLatAlt.class, new LngLatAltAdapter());
		builder.registerTypeHierarchyAdapter(Map.class, new JsonSerializer<Map<?, ?>>()
		{
			@Override public JsonElement serialize(Map<?, ?> src, Type typeOfSrc, JsonSerializationContext context)
			{
				if (src == null || src.isEmpty())
				{
					return null;
				}

				return context.serialize(this);
			}
		});

		return builder;
	}

	public static void useLowerCaseTypes(boolean lowerCase)
	{
		GeoGson.isUsingLowerCaseTypes = lowerCase;
	}
}
