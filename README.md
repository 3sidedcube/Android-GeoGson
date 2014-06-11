#GeoJson With Gson Support

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
new GsonBuilder()
	.registerTypeAdapter(GeoJsonObject.class, new GeoJsonObjectAdapter())
.create()
```
