package com.geometry;

public class RetioGeoRelation extends FactorGeoRelation{
	String factorRelation;
	public GeoItem thirdItem;
	public GeoItem fourthItem;
	public RetioGeoRelation(GeoItem first, GeoItem second, Relation relation,GeoItem third, GeoItem fourth){
		super(first, second, relation, third.getName()+"/"+fourth.getName());
		this.factorRelation = third.getName()+"/"+fourth.getName();
		this.thirdItem = third;
		this.fourthItem = fourth;
		super.relationType = RelationType.RATIO_GEO_RELATION;
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		
		
		return firstItem.getName()+"/"+secondItem.getName() + relation + factorRelation;
	}
	@Override
	public boolean sameRelation(GeoRelation relation) {
		// TODO Auto-generated method stub
		RetioGeoRelation re = null;
		if(relation.relationType == RelationType.RATIO_GEO_RELATION){
			re = (RetioGeoRelation) relation;
			
			if(this.firstItem.sameItem(re.firstItem) && this.secondItem.sameItem(re.secondItem) && this.thirdItem.sameItem(re.thirdItem) && this.fourthItem.sameItem(re.fourthItem)){
				return true;
			}
			else if (this.firstItem.sameItem(re.thirdItem) && this.secondItem.sameItem(re.fourthItem) && this.thirdItem.sameItem(re.firstItem) && this.fourthItem.sameItem(re.secondItem)){
				return true;
			}
			else if(this.firstItem.sameItem(re.secondItem) && this.secondItem.sameItem(re.firstItem) && this.fourthItem.sameItem(re.thirdItem) && this.fourthItem.sameItem(re.thirdItem)){
				return true;
			}
			else if (this.firstItem.sameItem(re.fourthItem) && this.secondItem.sameItem(re.thirdItem) && this.thirdItem.sameItem(re.secondItem) && this.fourthItem.sameItem(re.firstItem)){
				return true;
			}
			
			
		}
		
		
		
		
		return false;
	}
}
