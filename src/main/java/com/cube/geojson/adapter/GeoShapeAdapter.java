package com.cube.geojson.adapter;

import com.cube.geojson.model.GeoCircle;
import com.cube.geojson.model.GeoEnvelope;
import com.cube.geojson.model.GeoMultipolygon;
import com.cube.geojson.model.GeoPoint;
import com.cube.geojson.model.GeoPolygon;
import com.cube.geojson.model.GeoShape;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Adapter for de-serializing Shapes
 */
public class GeoShapeAdapter implements JsonDeserializer<GeoShape>
{

	@Override public GeoShape deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
	{
		JsonObject shapeJson = json.getAsJsonObject();

		String type = shapeJson.get("type").getAsString();

		if (GeoShape.TYPE_POINT.equals(type))
		{
			return context.deserialize(json, GeoPoint.class);
		}
		else if (GeoShape.TYPE_ENVELOPE.equals(type))
		{
			return context.deserialize(json, GeoEnvelope.class);
		}
		else if (GeoShape.TYPE_POLYGON.equals(type))
		{
			return context.deserialize(json, GeoPolygon.class);
		}
		else if (GeoShape.TYPE_MULTIPOLYGON.equals(type))
		{
			return context.deserialize(json, GeoMultipolygon.class);
		}
		else if (GeoShape.TYPE_CIRCLE.equals(type))
		{
			return context.deserialize(json, GeoCircle.class);
		}

		return null;
	}
}
