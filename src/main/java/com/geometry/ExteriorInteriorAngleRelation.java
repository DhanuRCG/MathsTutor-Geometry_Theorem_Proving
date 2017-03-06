package com.geometry;

public class ExteriorInteriorAngleRelation extends GeoRelation{

	public GeoItem thirdItem;
	public ExteriorInteriorAngleRelation(GeoItem first, GeoItem second, GeoItem third) {
		super(RelationType.EXTERIOR_INTERIOR_ANGLE_RELATION);
		super.relation = Relation.EQUALS;
		super.firstItem = first;
		super.secondItem = second;
		this.thirdItem = third;
	}

	@Override
	public String getName() {

		return firstItem.getName() + " + " + secondItem.getName() + " " + relation + " " + thirdItem.getName();

	}

	public boolean sameRelation(GeoRelation relation) {

		ExteriorInteriorAngleRelation tempGeorelation;
		try {
			tempGeorelation = (ExteriorInteriorAngleRelation) relation;
		} catch (Exception e) {
			return false;
		}

		// if relation1 item1 == relation2.item1
		// and relation1 item2 == relation2.item2
		// and factor matches
		if ((geoItemsAreSame(this.firstItem, tempGeorelation.firstItem))
				&& (geoItemsAreSame(this.secondItem, tempGeorelation.secondItem))
						&& (geoItemsAreSame(this.thirdItem, tempGeorelation.thirdItem)))
			return true;

		return false;

	}

}
