package com.knowledge;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.geometry.Angle;
import com.geometry.GeoItem;
import com.geometry.GeoRelation;
import com.geometry.GeoType;
import com.geometry.Relation;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;

@SuppressWarnings("restriction")
public class HashTestKnowledge implements java.io.Serializable{
	public Multimap<String,String> myHashMultimap;
	private ArrayList<Pack> packes;
	public static void main(String[] args){
		 
	}
	public HashTestKnowledge() {
		// TODO Auto-generated constructor stub
		this.myHashMultimap = HashMultimap.create();
		packes = new ArrayList<Pack>();
	}
	
	public void addGeoRelation(String item1,String item2){
		
		if(isKeyAndValue(item1, item2) || isKeyAndValue(item2, item1)){
			
		}
		else if(isValueAndValueAtSameKey(item1,item2)){
			
		}
		else if(isKey(item1) && isKey(item2)){
			
			addKeyValueSetToKeyValueSet(item1,item2);
			
		}
		else if(isKey(item1)){
			
			if(this.myHashMultimap.containsValue(item2)){
				addkeyValueSetToContainValueSet(item1, item2);
				
			}
			else{
				this.myHashMultimap.put(item1, item2);
			}
			
		}
		else if(isKey(item2)){
			
			if(this.myHashMultimap.containsValue(item1)){
				
				addkeyValueSetToContainValueSet(item2, item1);
			}
			else{
				this.myHashMultimap.put(item2, item1);
			}
			
		}
		else{
			
			
			if(this.myHashMultimap.containsValue(item1) && this.myHashMultimap.containsValue(item2)){
				
				addValueSetToValueSet(item1,item2);
				
			}
			else if(this.myHashMultimap.containsValue(item1) || this.myHashMultimap.containsValue(item2)){
				
				if(this.myHashMultimap.containsValue(item1)){
					addValueToValueSet(item1,item2);
					
				}
				else{
					addValueToValueSet(item2,item1);
					
				}
			}
			else{
				
				this.myHashMultimap.put(item1, item2);
				
			}
		}
		
	}
	
	public boolean isValueAndValueAtSameKey(String item1, String item2) {
		// TODO Auto-generated method stub
		
		String key = getKeyContainData(item1);
		
		for(String value :this.myHashMultimap.get(key)){
			
			if(item2.equals(value)){
				return true;
			}
		}
		return false;
	}
	public boolean isKey(String item){
		for(String key :this.myHashMultimap.keySet()){
			if(key.equals(item)){
				return true;
				
			}
			
		}
		return false;
		
	}
	public void printKnowledge(){
		System.out.println(this.myHashMultimap);
		
		
	}
	
	public String getKeyContainData(String item){
		
		 for(String key : this.myHashMultimap.keySet()){
			 Collection<String> values = this.myHashMultimap.get(key);
			 
			 for(String value : values){
				 
				 if(value.equals(item)){
					 
				 return key;
				 }
			 }
			 
		 }
		return null;
		
	}
	public void addKeyValueSetToKeyValueSet(String key1,String key2){
		
		Collection<String> itemsSet2 = this.myHashMultimap.get(key2);
		for(String k : itemsSet2){
			System.out.println(k);
		}
		this.myHashMultimap.putAll(key1, itemsSet2);
		this.myHashMultimap.put(key1, key2);
		this.myHashMultimap.removeAll(key2);
	}
	public void addkeyValueSetToContainValueSet(String key,String data){
		
			String keyofdata = getKeyContainData(data);
			
			addKeyValueSetToKeyValueSet(key,keyofdata);
		
		
	}
	public void addValueSetToValueSet(String value1,String value2){
		
		String key1 = getKeyContainData(value1);
		String key2 = getKeyContainData(value2);
		Collection<String> itemsSet2 = this.myHashMultimap.get(key2);
		this.myHashMultimap.putAll(key1, itemsSet2);
		this.myHashMultimap.put(key1, key2);
		this.myHashMultimap.removeAll(key2);
	}
	public void addValueToValueSet(String value1,String newValue){
		String key1 = getKeyContainData(value1);
		this.myHashMultimap.put(key1, newValue);
	}
	
	public ArrayList<Pack> generateSameItemPackege(){
		ArrayList<Pack> temps = new ArrayList<Pack>();
		Pack temp;
		for(String key : this.myHashMultimap.keySet()){
			
			temp = new Pack();
			temp.addItem(key);
			for(String value : this.myHashMultimap.get(key)){
				temp.addItem(value);
				
			}
			temps.add(temp);
		}
		this.packes = temps;
		
		return this.packes;
	}
	public void printPack(){
		for(Pack pack : this.packes){
			System.out.println("pack");
			for(String key : pack.getMap().keySet()){
				System.out.println(key +" : pack key");
			}
			
		}
		
	}
	public boolean isContainItem(String item){
		boolean contain = false;
		for(Pack pack : this.packes){
			
			if(pack.isAItem(item)){
				contain = true;
				break;
			}
		}
		
		return contain;
		
	}
	public Pack getPack(String item){
		for(Pack pack : packes){
			if(pack.getMap().get(item)!=null){
				
				return pack;
			}
		}
		
		return null;
	}
	public ArrayList<GeoRelation> getSameRelation(GeoRelation relation){
		//printPack();
		GeoItem item1 = relation.firstItem;
		GeoItem item2 = relation.secondItem;
		ArrayList<GeoRelation> relations = new ArrayList<GeoRelation>();
		if(isContainItem(item1.getName()) && isContainItem(item2.getName())){
			Pack pack1 = getPack(item1.getName());
			Pack pack2 = getPack(item2.getName());
			for(String key1 :pack1.getMap().keySet()){
				for(String key2 :pack2.getMap().keySet()){
					GeoRelation gen = new GeoRelation( new Angle(key1),new Angle(key2),relation.relation);
					
					relations.add(gen);
				}
			}
		}
		else if(isContainItem(item1.getName()) ){
			Pack pack1 = getPack(item1.getName());
			
			//System.out.println("true");
			for(String key1 :pack1.getMap().keySet()){
				
					GeoRelation gen = new GeoRelation( new Angle(key1),item2,relation.relation);
					
					relations.add(gen);
				
			}
		}
		else if(item2.type != GeoType.VALUE && isContainItem(item2.getName())){
			
			Pack pack2 = getPack(item2.getName());
			//System.out.println("true");
			
				for(String key2 :pack2.getMap().keySet()){
					GeoRelation gen = new GeoRelation( item1,new Angle(key2),relation.relation);
					
					relations.add(gen);
				}
			
		}
		
		return relations;
	}
	private boolean isKeyAndValue(String key,String value){
		for(String data : this.myHashMultimap.get(key)){
			if(data.equals(value)){
				return true;
				
			}
			
		}
		return false;
	}
	
	public boolean isSameAngleWithDifferentName(String first, String second){
		String key = null;
		
		if(isKey(first)){
			
			return myHashMultimap.get(first).contains(second);		
		}
		
		if(isKey(second)){
			
			return myHashMultimap.get(second).contains(first);		
		}
		
		if(myHashMultimap.containsValue(first)){
			key = getKeyContainData(first);
			
			return myHashMultimap.get(key).contains(second);
		}
		
		return false;
		
	}
	public boolean isSameRelationWithOtherName(GeoRelation answer, GeoRelation geoRelation) {
//		System.out.println();
//		System.out.println(answer.getName() + " " + answer.firstItem.getName() + answer.secondItem.getName());
//		System.out.println(geoRelation.getName() + " " + geoRelation.firstItem.getName() + geoRelation.secondItem.getName());
		if(geoRelation.relation == Relation.EQUALS){
			if(geoRelation.firstItem.type == GeoType.ANGLE && geoRelation.secondItem.type == GeoType.ANGLE){
				if(		
						(answer.secondItem.getName().equalsIgnoreCase(geoRelation.firstItem.getName())) &&
						(isSameAngleWithDifferentName(answer.firstItem.getName(),
								geoRelation.secondItem.getName())) ||
						
						(answer.firstItem.getName().equalsIgnoreCase(geoRelation.firstItem.getName())) &&
						(isSameAngleWithDifferentName(answer.secondItem.getName(),
								geoRelation.secondItem.getName())) ||
						
						(answer.firstItem.getName().equalsIgnoreCase(geoRelation.secondItem.getName())) &&
						(isSameAngleWithDifferentName(answer.secondItem.getName(),
								geoRelation.firstItem.getName())) ||
						
						(answer.secondItem.getName().equalsIgnoreCase(geoRelation.secondItem.getName())) &&
						(isSameAngleWithDifferentName(answer.firstItem.getName(),
								geoRelation.firstItem.getName())) ||
						
						(isSameAngleWithDifferentName(answer.secondItem.getName(),
								geoRelation.secondItem.getName())) &&
						(isSameAngleWithDifferentName(answer.firstItem.getName(),
								geoRelation.firstItem.getName())) ||
						
						(isSameAngleWithDifferentName(answer.firstItem.getName(),
								geoRelation.firstItem.getName())) &&
						(isSameAngleWithDifferentName(answer.secondItem.getName(),
								geoRelation.secondItem.getName()))
						)
				{ 
					//System.out.println("Found ok");
					return true;
				}
			}
			else{ return false; }
		}
		else{return false; }

		return false;
	}
}
