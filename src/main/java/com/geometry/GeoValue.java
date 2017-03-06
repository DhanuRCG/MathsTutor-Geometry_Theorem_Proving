package com.geometry;

public class GeoValue extends GeoItem{

	public GeoValue(String value) {
		super(GeoType.VALUE);
		// TODO Auto-generated constructor stub
		this.value = Double.parseDouble(value);
	}

	@Override
	public Boolean sameItem(GeoItem item) {
		// TODO Auto-generated method stub
		if(item.value == this.value){
			return true;
		}
		return false;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return ""+value;
	}

}
