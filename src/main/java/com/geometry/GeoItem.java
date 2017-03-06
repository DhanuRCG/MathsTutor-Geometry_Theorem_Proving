package com.geometry;

public abstract class GeoItem  implements java.io.Serializable{
	public GeoType type;
	public String name;
	public double value;
	
	public GeoItem(GeoType type){
		this.type = type;
	}
	
	public abstract Boolean sameItem(GeoItem item);

	public abstract String getName();

}
