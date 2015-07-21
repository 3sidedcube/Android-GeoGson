package com.cube.geojson.gson;

import com.cube.geojson.Circle;
import com.cube.geojson.Crs;
import com.cube.geojson.Feature;
import com.cube.geojson.FeatureCollection;
import com.cube.geojson.GeoJson;
import com.cube.geojson.GeoJsonObject;
import com.cube.geojson.GeometryCollection;
import com.cube.geojson.LineString;
import com.cube.geojson.MultiLineString;
import com.cube.geojson.MultiPoint;
import com.cube.geojson.MultiPolygon;
import com.cube.geojson.Point;
import com.cube.geojson.Polygon;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;
import java.util.HashMap;

/**
 * Adapter for GeoJson objects
 */
public class GeoJsonObjectAdapter implements JsonSerializer<GeoJsonObject>, JsonDeserializer<GeoJsonObject>
{
	private static HashMap<String, String> lowerCaseMap;

	static
	{
		lowerCaseMap = new HashMap<String, String>();
		lowerCaseMap.put(Crs.class.getSimpleName().toLowerCase(), Crs.class.getSimpleName());
		lowerCaseMap.put(Feature.class.getSimpleName().toLowerCase(), Feature.class.getSimpleName());
		lowerCaseMap.put(FeatureCollection.class.getSimpleName().toLowerCase(), FeatureCollection.class.getSimpleName());
		lowerCaseMap.put(GeometryCollection.class.getSimpleName().toLowerCase(), GeometryCollection.class.getSimpleName());
		lowerCaseMap.put(LineString.class.getSimpleName().toLowerCase(), LineString.class.getSimpleName());
		lowerCaseMap.put(MultiLineString.class.getSimpleName().toLowerCase(), MultiLineString.class.getSimpleName());
		lowerCaseMap.put(MultiPoint.class.getSimpleName().toLowerCase(), MultiPoint.class.getSimpleName());
		lowerCaseMap.put(MultiPolygon.class.getSimpleName().toLowerCase(), MultiPolygon.class.getSimpleName());
		lowerCaseMap.put(Point.class.getSimpleName().toLowerCase(), Point.class.getSimpleName());
		lowerCaseMap.put(Polygon.class.getSimpleName().toLowerCase(), Polygon.class.getSimpleName());
		lowerCaseMap.put(Circle.class.getSimpleName().toLowerCase(), Circle.class.getSimpleName());
	}

	@Override
	@SuppressWarnings("unchecked")
	public JsonElement serialize(GeoJsonObject src, Type typeOfSrc, JsonSerializationContext context)
	{
		Class<GeoJsonObject> cls;
		try
		{
			cls = (Class<GeoJsonObject>)Class.forName(GeoJson.class.getPackage().getName().concat(".").concat(src.getType()));
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
			throw new JsonSyntaxException(e.getMessage());
		}

		GsonBuilder builder = new GsonBuilder();
		GeoJson.registerAdapters(builder);

		return builder.create().toJsonTree(src, cls);
	}

	@Override
	@SuppressWarnings("unchecked")
	public GeoJsonObject deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
	{
		JsonObject jsonObject = json.getAsJsonObject();
		String type = jsonObject.get("type").getAsString();

		if (GeoJson.isUsingLowerCaseTypes && lowerCaseMap.containsKey(type))
		{
			type = lowerCaseMap.get(type);
		}

		Class<GeoJsonObject> cls;
		try
		{
			cls = (Class<GeoJsonObject>)Class.forName(GeoJson.class.getPackage().getName().concat(".").concat(type));
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
			throw new JsonParseException(e.getMessage());
		}

		GsonBuilder builder = new GsonBuilder();
		GeoJson.registerAdapters(builder);

		GeoJsonObject geoObject = builder.create().fromJson(json, cls);
		geoObject.finishPopulate();

		return geoObject;
	}
}
