package com.cube.geojson;


public class MultiPoint extends Geometry<LngLatAlt> {

	public MultiPoint() {
	}

	@Override public void finishPopulate()
	{

	}

	public MultiPoint(LngLatAlt... points) {
		super(points);
	}
}
