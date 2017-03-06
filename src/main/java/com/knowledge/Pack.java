package com.knowledge;

import java.util.HashMap;



public class Pack implements java.io.Serializable{
	private HashMap<String,Boolean> pack;
	
	public Pack() {
		// TODO Auto-generated constructor stub
		pack = new HashMap<String,Boolean>();
	}
	public void addItem(String key){
		pack.put(key, true);
	}
	public boolean isAItem(String key){
		
		if(pack.get(key)== null){
			return false;
		}
		return true;
	}
	public HashMap<String, Boolean> getMap() {
		return pack;
	}
	
}
