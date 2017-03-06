package com.knowledge;

import java.util.ArrayList;
import java.util.List;

import com.geometry.GeoItem;

public class GeoDataHolder {
	
	private List<GeoItem> knowledge = new ArrayList<GeoItem>();

	public GeoDataHolder(List<GeoItem> knowledge){

		this.knowledge = knowledge;

	}
	
	public GeoDataHolder() {
		// TODO Auto-generated constructor stub
	}

	public List<GeoItem> getAllGeoItemKnowledge(){
		
		return knowledge;

	}
	
	public boolean addGeoItem(GeoItem newItem){
		
		knowledge.add(newItem);
		
		return true;
	}
	
	

}
