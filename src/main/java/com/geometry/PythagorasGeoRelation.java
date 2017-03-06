package com.geometry;

public class PythagorasGeoRelation extends GeoRelation{
	public GeoItem thirdItem;
	public PythagorasGeoRelation(GeoItem first, GeoItem second,GeoItem third,RelationType type) {
		super(type);
		// TODO Auto-generated constructor stub
		this.firstItem = first;
		this.secondItem = second;
		this.thirdItem = third;
		this.relationType = RelationType.PYTHAGORAS;
	}
	

}
