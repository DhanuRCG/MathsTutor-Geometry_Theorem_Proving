package com.geometry;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class FactorGeoRelation extends GeoRelation{
	
	public String factor;

	public FactorGeoRelation(GeoItem first, GeoItem second, Relation relation,String factor) {

		super(first, second, relation);
		super.relationType = RelationType.FACTOR_GEO_RELATION;
		this.factor = factor;
		

	}
	
	@Override
	public String getName(){
		return firstItem.getName() + " " + relation +" "+ factor +" "+ secondItem.getName();	
	}
	
public boolean sameRelation(GeoRelation relation){
	
	FactorGeoRelation tempGeorelation;
	try {
		 tempGeorelation = (FactorGeoRelation) relation;
	} catch (Exception e) {
		return false;
	}
		
		//if two relation types are not matching
		if( this.relation != relation.relation)
			return false;
		
		//if two first items are miss match
		if( this.firstItem.type != relation.firstItem.type)
			return false;

		//if two second items are miss match
		if( this.secondItem.type != relation.secondItem.type)
			return false;

		if(!sameFactor(relation)){
			return false;
		}
		//if relation1 item1 == relation2.item1
		// and relation1 item2 == relation2.item2
		//and factor matches
		if(( geoItemsAreSame(this.firstItem, relation.firstItem) )
				&& ( geoItemsAreSame(this.secondItem, relation.secondItem) 
						))
			return true;

		
		return false;

		
	}

	public boolean sameFactor(GeoRelation compare){
		boolean factorSame = true;
		String relation1Factor = ((FactorGeoRelation)compare).getFactor();
		String relation2Factor = this.getFactor();
	
		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByName("JavaScript");
		try {
			relation1Factor = String.valueOf(engine.eval(relation1Factor));
			relation2Factor = String.valueOf(engine.eval(relation2Factor));
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		factorSame = (Double.parseDouble(relation1Factor) == Double.parseDouble(relation2Factor));
		
		return factorSame;
	}

	public String getFactor(){
		return factor;
	}

}
