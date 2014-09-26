#GeoGson

GeoGson is a Gson implementation of the GeoJson specification (with some unofficial object support)

##Types supported

1. Circle (unofficial support)
1. Feature
1. LineString
1. MultiLineString
1. Polygon
1. MultiPolygon
1. Point
1. MultiPoint

##Usage with custom gson parsers

In order for the gson to correctly inflate into geojson objects, you must make sure to include the type adapter so that gson can detect and inflate the geojson objects correctly.

```
GsonBuilder builder = new GsonBuilder();
builder.registerTypeAdapter(GeoJsonObject.class, new GeoJsonObjectAdapter());
builder.registerTypeAdapter(LngLatAlt.class, new LngLatAltAdapter());
builder.registerTypeHierarchyAdapter(Map.class, new JsonSerializer<Map<?, ?>>()
{
	@Override public JsonElement serialize(Map<?, ?> src, Type typeOfSrc, JsonSerializationContext context)
	{
		if (src == null || src.isEmpty())
		{
			return null;
		}

		return context.serialize(this);
	}
});
```

You can use the methods found in `GeoGson` to automatically add these adapters to your builder object, or create a new gson instance with the adapters already added

```
GeoGson.getGson()
GeoGson.registerAdapters(GsonBuilder builder)
```
