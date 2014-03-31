package com.cube.geojson.adapter;

import com.cube.geojson.model.Coordinate;
import com.cube.geojson.model.CoordinateGeometry;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Gson de-serialization adapter for GeoJson shape
 */
public class CoordinateGeometryAdapter implements JsonDeserializer<CoordinateGeometry>
{
	@Override public CoordinateGeometry deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
	{
		CoordinateGeometry geom = new CoordinateGeometry();

		JsonArray jsonArray = json.getAsJsonArray();

		for (int i = 0; i < jsonArray.size(); i++)
		{
			JsonElement arrayElem = jsonArray.get(i);

			if (arrayElem.isJsonPrimitive())
			{
				double lon = arrayElem.getAsDouble();
				double lat = jsonArray.get(i + 1).getAsDouble();

				geom = new Coordinate(lon, lat);

				break;
			}
			else
			{
				geom.add(deserialize(arrayElem, typeOfT, context));
			}
		}

		return geom;
	}
}
