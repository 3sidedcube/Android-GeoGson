package com.cube.geojson;

import java.util.List;

public class MultiPolygon extends Geometry<List<List<LngLatAlt>>> {

	public MultiPolygon() {
	}

	@Override public void finishPopulate()
	{
		double top=0,btm=0,left=0,right=0;
		if (getCoordinates() != null && getCoordinates().size() > 0)
		{
			top = getCoordinates().get(0).get(0).get(0).getLatitude();
			btm = getCoordinates().get(0).get(0).get(0).getLatitude();
			left = getCoordinates().get(0).get(0).get(0).getLongitude();
			right = getCoordinates().get(0).get(0).get(0).getLongitude();
			for (List<List<LngLatAlt>> lists : getCoordinates())
			{
				for (List<LngLatAlt> list : lists)
				{
					for (LngLatAlt lngLatAlt : list)
					{
						if (top < lngLatAlt.getLatitude()) top = lngLatAlt.getLatitude();
						if (btm > lngLatAlt.getLatitude()) btm = lngLatAlt.getLatitude();
						if (left > lngLatAlt.getLongitude()) left = lngLatAlt.getLongitude();
						if (right < lngLatAlt.getLongitude()) right = lngLatAlt.getLongitude();
					}
				}
			}
		}
		double[] bbox = new double[]{left,btm,right,top};
		setBbox(bbox);
	}

	public MultiPolygon(Polygon polygon) {
		add(polygon);
	}

	public MultiPolygon add(Polygon polygon) {
		coordinates.add(polygon.getCoordinates());
		return this;
	}
}
