package com.cube.geojson;

import com.cube.geojson.adapter.CoordinateGeometryAdapter;
import com.cube.geojson.adapter.GeoShapeAdapter;
import com.cube.geojson.model.CoordinateGeometry;
import com.cube.geojson.model.GeoShape;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

/**
 * Library entrypoint for processing GeoJson into recognizable shapes
 */
public class GeoJson
{
	public static <T extends GeoShape> T fromJson(String json, Class<T> cls)
	{
		JsonParser parser = new JsonParser();
		JsonElement jsonElement = parser.parse(json);
		if (!jsonElement.isJsonObject())
		{
			throw new JsonParseException("JSON must be an object to match GeoJson format!");
		}

		return fromJson(jsonElement.getAsJsonObject(), cls);
	}

	public static <T extends GeoShape> T fromJson(JsonObject jsonObject, Class<T> cls)
	{
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(CoordinateGeometry.class, new CoordinateGeometryAdapter());
		builder.registerTypeAdapter(GeoShape.class, new GeoShapeAdapter());
		Gson processor = builder.create();

		try
		{
			return cls.cast(processor.fromJson(jsonObject.get("location"), GeoShape.class));
		}
		catch (ClassCastException ex)
		{
			return null;
		}
	}
}
