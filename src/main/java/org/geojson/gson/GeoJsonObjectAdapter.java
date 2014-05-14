package org.geojson.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import org.geojson.GeoJson;
import org.geojson.GeoJsonObject;

import java.lang.reflect.Type;

/**
 * Adapter for GeoJson objects
 */
public class GeoJsonObjectAdapter implements JsonDeserializer<GeoJsonObject>
{
	@Override
	@SuppressWarnings("unchecked")
	public GeoJsonObject deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
	{
		JsonObject jsonObject = json.getAsJsonObject();
		String type = jsonObject.get("type").getAsString();

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
