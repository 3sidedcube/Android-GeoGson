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
import java.util.Locale;
import java.util.Map;

/**
 * Adapter for GeoJson objects
 */
public class GeoJsonObjectAdapter implements JsonSerializer<GeoJsonObject>, JsonDeserializer<GeoJsonObject>
{
	private static final HashMap<String, Class<?>> classMap;
	private static final HashMap<String, Class<?>> lowercaseClassMap;

	private static void registerType(String type, Class<?> clazz)
	{
		classMap.put(type, clazz);
		lowercaseClassMap.put(type.toLowerCase(Locale.ENGLISH), clazz);
	}

	static
	{
		classMap = new HashMap<>();
		lowercaseClassMap = new HashMap<>();
		registerType("Crs", Crs.class);
		registerType(Feature.TYPE_NAME, Feature.class);
		registerType(FeatureCollection.TYPE_NAME, FeatureCollection.class);
		registerType(GeometryCollection.TYPE_NAME, GeometryCollection.class);
		registerType(LineString.TYPE_NAME, LineString.class);
		registerType(MultiLineString.TYPE_NAME, MultiLineString.class);
		registerType(MultiPoint.TYPE_NAME, MultiPoint.class);
		registerType(MultiPolygon.TYPE_NAME, MultiPolygon.class);
		registerType(Point.TYPE_NAME, Point.class);
		registerType(Polygon.TYPE_NAME, Polygon.class);
		registerType(Circle.TYPE_NAME, Circle.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public JsonElement serialize(GeoJsonObject src, Type typeOfSrc, JsonSerializationContext context)
	{
		Map<String, Class<?>> map = classMap;
		if (GeoJson.isUsingLowerCaseTypes)
		{
			map = lowercaseClassMap;
		}

		Class<GeoJsonObject> cls;
		try
		{
			cls = (Class<GeoJsonObject>) map.get(src.getType());
		}
		catch (ClassCastException e)
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

		Map<String, Class<?>> map = classMap;
		if (GeoJson.isUsingLowerCaseTypes)
		{
			map = lowercaseClassMap;
		}

		Class<GeoJsonObject> cls;
		try
		{
			cls = (Class<GeoJsonObject>) map.get(type);
		}
		catch (ClassCastException e)
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
