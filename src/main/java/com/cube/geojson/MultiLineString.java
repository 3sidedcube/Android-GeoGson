package com.cube.geojson;

import java.util.List;

public class MultiLineString extends Geometry<List<LngLatAlt>> {

	public MultiLineString() {
	}

	@Override public void finishPopulate()
	{

	}

	public MultiLineString(List<LngLatAlt> line) {
		add(line);
	}
}
