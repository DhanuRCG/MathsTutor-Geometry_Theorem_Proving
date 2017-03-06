package com.geometry;

public class SumOfAnglesInATriangleRelation extends GeoRelation{
	
	public GeoItem thirdItem;
	public SumOfAnglesInATriangleRelation(GeoItem first, GeoItem second, GeoItem third) {
		super(RelationType.SUM_OF_ANGLES_IN_A_TRIANGLE_RELATION);
		super.relation = Relation.EQUALS;
		super.firstItem = first;
		super.secondItem = second;
		this.thirdItem = third;
	}

	@Override
	public String getName() {

		return firstItem.getName() + " + " + secondItem.getName() + " + " + thirdItem.getName()+ relation + " 180" ;

	}

	public boolean sameRelation(GeoRelation relation) {

		SumOfAnglesInATriangleRelation tempGeorelation;
		try {
			tempGeorelation = (SumOfAnglesInATriangleRelation) relation;
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
