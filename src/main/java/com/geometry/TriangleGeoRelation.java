package com.geometry;

public class TriangleGeoRelation extends GeoRelation{
	public GeoItem thirdItem;
	public TriangleGeoRelation(GeoItem first, GeoItem second,GeoItem third,RelationType type) {
		super(type);
		// TODO Auto-generated constructor stub
		this.firstItem = first;
		this.secondItem = second;
		this.thirdItem = third;
		this.relationType = RelationType.TRIANGLESUM;
		
	}

}
