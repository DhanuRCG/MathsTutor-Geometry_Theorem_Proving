package com.knowledge;

import java.util.ArrayList;

import com.geometry.GeoItem;
import com.geometry.GeoType;

public class SameItemPackes {
	static Pack current_pack;
	private ArrayList<Pack> pack_angle;

	public void createNewPack() {
		current_pack = new Pack();
		
	}

	public void addPack() {
		
			pack_angle.add(current_pack);
		

	}

	public void addItemToPack(String item) {
			current_pack.addItem(item);
	}

}
