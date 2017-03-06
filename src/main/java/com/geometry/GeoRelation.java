package com.geometry;

import java.util.ArrayList;

public class GeoRelation  implements java.io.Serializable {
	public GeoItem firstItem;
	public GeoItem secondItem;
	public Relation relation;
	public ArrayList<Reason> reasons;
	public int markforrelation;
	public GeoItem getFirstItem() {
		return firstItem;
	}

	public void setFirstItem(GeoItem firstItem) {
		this.firstItem = firstItem;
	}

	public GeoItem getSecondItem() {
		return secondItem;
	}

	public void setSecondItem(GeoItem secondItem) {
		this.secondItem = secondItem;
	}

	public int markforreason;
	public String reason;
	public RelationType relationType;
	
	
	public GeoRelation(GeoItem first, GeoItem second, Relation relation){
		this.firstItem = first;
		this.secondItem = second;
		this.relation = relation;
		this.relationType = RelationType.GEO_RELATION;
		this.reasons = new ArrayList<>();
	}
	
	public GeoRelation(GeoItem first, GeoItem second, Relation relation,Reason reason){
		this.firstItem = first; 
		this.secondItem = second;
		this.relation = relation;
		this.relationType = RelationType.GEO_RELATION;
		this.reasons = new ArrayList<>();
		this.reasons.add(reason);
	}
	
	public GeoRelation(GeoItem first, GeoItem second, Relation relation,ArrayList<Reason> reasons){
		this.firstItem = first;
		this.secondItem = second;
		this.relation = relation;
		this.reasons = reasons;
		this.relationType = RelationType.GEO_RELATION;
	}
	
	public GeoRelation(RelationType type){
		this.relationType=type;
	}
	
	public String getName(){
		
		return firstItem.getName() + " " + relation +" "+ secondItem.getName();
	}
	
	public void setReason(Reason reason){
		this.reasons.add(reason);
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getMarkforrelation() {
		return markforrelation;
	}

	public void setMarkforrelation(int markrelation) {
		this.markforrelation = markrelation;
	}

	public int getMarkforreason() {
		return markforreason;
	}

	public void setMarkforreason(int markreason) {
		this.markforreason = markreason;
	}
	
public boolean sameRelation(GeoRelation relation){
		
		//if two relation types are not matching
		if( this.relation != relation.relation)
			return false;
		
		//if two first items are miss match
		if( this.firstItem.type != relation.firstItem.type)
			return false;

		//if two second items are miss match
		if( this.secondItem.type != relation.secondItem.type)
			return false;

		//if relation1 item1 == relation2.item1
		// and relation1 item2 == relation2.item2
		if(( geoItemsAreSame(this.firstItem, relation.firstItem) )
				&& ( geoItemsAreSame(this.secondItem, relation.secondItem) ) 
				//&& ( relationReasonsAreSame(geoRelation, comparingRelation))
				
				)
			return true;

		//if relation1 item2 == relation2.item1
		// and relation1 item1 == relation2.item2
		if(( geoItemsAreSame(this.secondItem, relation.firstItem) )
				&& ( geoItemsAreSame(this.firstItem, relation.secondItem) )
				//&& ( (  relationReasonsAreSame(geoRelation, comparingRelation) ))
				)
			return true;
		
		return false;	
	}

	public boolean geoItemsAreSame(GeoItem item1,GeoItem item2){
		return item1.sameItem(item2);
	}
}
