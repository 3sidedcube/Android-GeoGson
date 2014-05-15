package org.geojson.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import org.geojson.Crs;
import org.geojson.Feature;
import org.geojson.FeatureCollection;
import org.geojson.GeoJson;
import org.geojson.GeoJsonObject;
import org.geojson.GeometryCollection;
import org.geojson.LineString;
import org.geojson.MultiLineString;
import org.geojson.MultiPoint;
import org.geojson.MultiPolygon;
import org.geojson.Point;
import org.geojson.Polygon;

import java.lang.reflect.Type;
import java.util.HashMap;

/**
 * Adapter for GeoJson objects
 */
public class GeoJsonObjectAdapter implements JsonDeserializer<GeoJsonObject>
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
			cls = (Class<GeoJsonObject>) Class.forName(GeoJson.class.getPackage().getName().concat(".").concat(type));
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
			throw new JsonParseException(e.getMessage());
		}

		return GeoJson.getGson().fromJson(json, cls);
	}
}
