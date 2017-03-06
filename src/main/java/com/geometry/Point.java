package com.geometry;

public class Point extends GeoItem {
	public char name;

	public Point(char name) {
		super(GeoType.POINT);
		super.name = name + "";
		this.name = name;
		// TODO Auto-generated constructor stub
	}

	public Boolean sameItem(GeoItem item) {
		if (item.type != GeoType.POINT)
			return false;
		String thisName = String.valueOf(name).toUpperCase();
		String itemName = String.valueOf(((Point) item).name).toUpperCase();
		if (thisName.matches(itemName))
			return true;
		return false;
	}

	public String getName() {
		return String.valueOf(name);
	}

}
