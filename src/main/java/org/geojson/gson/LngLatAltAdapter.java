package org.geojson.gson;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import org.geojson.LngLatAlt;

import java.lang.reflect.Type;

/**
 * LngLatAlt de-serialization in Gson
 */
public class LngLatAltAdapter implements JsonSerializer<LngLatAlt>, JsonDeserializer<LngLatAlt>
{
	@Override public JsonElement serialize(LngLatAlt src, Type typeOfSrc, JsonSerializationContext context)
	{
		JsonArray array = new JsonArray();
		array.add(new JsonPrimitive(src.getLongitude()));
		array.add(new JsonPrimitive(src.getLatitude()));
		if (src.hasAltitude())
		{
			array.add(new JsonPrimitive(src.getAltitude()));
		}
		return array;
	}

	@Override public LngLatAlt deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
	{
		JsonArray data = json.getAsJsonArray();

		LngLatAlt node = new LngLatAlt();

		node.setLongitude(data.get(0).getAsDouble());
		node.setLatitude(data.get(1).getAsDouble());

		if (data.size() == 3)
		{
			node.setAltitude(data.get(2).getAsDouble());
		}
		else
		{
			node.setAltitude(Double.NaN);
		}

		return node;
	}
}
