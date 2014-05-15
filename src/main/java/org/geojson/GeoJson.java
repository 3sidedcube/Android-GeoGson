package org.geojson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import org.geojson.gson.GeoJsonObjectAdapter;
import org.geojson.gson.LngLatAltAdapter;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Entrypoint for generating Gson parser with required overrides
 */
public final class GeoJson
{
	public static boolean isUsingLowerCaseTypes = false;

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

	public static void useLowerCaseTypes(boolean lowerCase)
	{
		GeoJson.isUsingLowerCaseTypes = lowerCase;
	}
}
