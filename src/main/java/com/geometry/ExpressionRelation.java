package com.geometry;

import java.util.ArrayList;
import java.util.List;

public class ExpressionRelation extends GeoRelation{
	
	public int size=0;
	public List<GeoItem> items = new ArrayList<>();
	public List<Relation> operations = new ArrayList<>();
	
	public ExpressionRelation(GeoItem first, GeoItem second, Relation relation) {
		super(first, second, relation);
	}
	
	public ExpressionRelation(RelationType type){
		super(type);
	}
	
	public void addItem(GeoItem item){
		items.add(item);
		size++;
	}
	public void addOperation(Relation relation){
		operations.add(relation);
	}
	public void addAllItems( GeoItem ...items){
		for(GeoItem item:items){
			this.items.add(item);
			//System.out.println(item);
			size++;
		}
	}
	public void addAllOperations(Relation ...relations ){
		for(Relation relation:relations){
			this.operations.add(relation);
		}
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String rel="";	
		return rel;
	}
	

}
