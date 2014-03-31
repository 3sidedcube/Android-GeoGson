package com.cube.geojson;

import com.cube.geojson.adapter.CoordinateGeometryAdapter;
import com.cube.geojson.model.CoordinateGeometry;
import com.cube.geojson.model.GeoJsonLocation;
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
	public static GeoJsonLocation fromJson(String json)
	{
		JsonParser parser = new JsonParser();
		JsonElement jsonElement = parser.parse(json);
		if (!jsonElement.isJsonObject())
		{
			throw new JsonParseException("JSON must be an object to match GeoJson format!");
		}

		return fromJson(jsonElement.getAsJsonObject());
	}

	public static GeoJsonLocation fromJson(JsonObject jsonObject)
	{
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(CoordinateGeometry.class, new CoordinateGeometryAdapter());
		Gson processor = builder.create();

		return processor.fromJson(jsonObject, GeoJsonLocation.class);
	}
}
