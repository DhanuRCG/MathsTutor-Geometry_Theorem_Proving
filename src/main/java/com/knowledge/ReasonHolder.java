package com.knowledge;

import java.util.ArrayList;
import java.util.Collection;

import com.geometry.GeoRelation;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class ReasonHolder {
	public Multimap<String,String> reasonsMultimap;
	
	private Multimap<String,String> packs; 
	public ReasonHolder() {
		// TODO Auto-generated constructor stub
		this.reasonsMultimap = HashMultimap.create();
	}
	public void setPacks(Multimap<String,String> packs){
		this.packs = packs;
	}
	public void addReason(GeoRelation relation,String reason){
		this.reasonsMultimap.put(relation.getName(), reason);
	}
	
	public Multimap<String, String> getReasonsMultimap() {
		return reasonsMultimap;
	}
	public Collection<String> getReasonCollection(GeoRelation relation){
		
		return this.reasonsMultimap.get(relation.getName());
	}
	public void printKnowledge(){
		System.out.println(this.reasonsMultimap);
		
		
	}
	
}
