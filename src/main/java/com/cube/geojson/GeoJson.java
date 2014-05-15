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
				.registerTypeHierarchyAdapter(Map.class, new JsonSerializer<Map<?, ?>>()
				{
					@Override public JsonElement serialize(Map<?, ?> src, Type typeOfSrc, JsonSerializationContext context)
					{
						if (src == null || src.isEmpty())
							return null;

						return context.serialize(this);
					}
				})
				.create();
	}

	/**
	 * Add the required serialization adapters to the Gson builder
	 */
	public static void registerAdapters(GsonBuilder builder)
	{
		builder.registerTypeAdapter(GeoJsonObject.class, new GeoJsonObjectAdapter());
		builder.registerTypeAdapter(LngLatAlt.class, new LngLatAltAdapter());
		builder.registerTypeHierarchyAdapter(Map.class, new JsonSerializer<Map<?, ?>>()
		{
			@Override public JsonElement serialize(Map<?, ?> src, Type typeOfSrc, JsonSerializationContext context)
			{
				if (src == null || src.isEmpty())
					return null;

				return context.serialize(this);
			}
		});
	}

	public static void useLowerCaseTypes(boolean lowerCase)
	{
		GeoJson.isUsingLowerCaseTypes = lowerCase;
	}
}
