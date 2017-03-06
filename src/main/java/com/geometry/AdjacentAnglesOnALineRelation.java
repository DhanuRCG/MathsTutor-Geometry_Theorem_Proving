package com.geometry;

public class AdjacentAnglesOnALineRelation extends GeoRelation {

	public GeoItem thirdItem;

	public AdjacentAnglesOnALineRelation(GeoItem first, GeoItem second, GeoItem third) {
		super(RelationType.ADJACENT_ANGLES_ON_A_LINE_RELATION);
		super.relation = Relation.EQUALS;
		super.firstItem = first;
		super.secondItem = second;
		this.thirdItem = third;
	}

	public AdjacentAnglesOnALineRelation(GeoItem first, GeoItem second) {
		super(RelationType.ADJACENT_ANGLES_ON_A_LINE_RELATION);
		super.relation = Relation.EQUALS;
		super.firstItem = first;
		super.secondItem = second;
	}

	public AdjacentAnglesOnALineRelation(Angle first, Angle second) {
		super(RelationType.ADJACENT_ANGLES_ON_A_LINE_RELATION);
		super.relation = Relation.EQUALS;
		super.firstItem = first;
		super.secondItem = second;
	}
	
	@Override
	public String getName() {

		return firstItem.getName() + " + " + secondItem.getName() + " " + relation + " 180 ";

	}

	public boolean sameRelation(GeoRelation relation) {

		AdjacentAnglesOnALineRelation tempGeorelation;
		try {
			tempGeorelation = (AdjacentAnglesOnALineRelation) relation;
		} catch (Exception e) {
			return false;
		}

		// if relation1 item1 == relation2.item1
		// and relation1 item2 == relation2.item2
		// and factor matches
		if ((geoItemsAreSame(this.firstItem, tempGeorelation.firstItem))
				&& (geoItemsAreSame(this.secondItem, tempGeorelation.secondItem))
						)
			return true;

		return false;

	}
}
