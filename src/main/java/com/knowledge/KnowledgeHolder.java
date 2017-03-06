package com.knowledge;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.geometry.FactorGeoRelation;
import com.geometry.GeoItem;
import com.geometry.GeoRelation;
import com.geometry.RelationType;

/*this class does not accept the same geo-relation twice. 

*NOTE*
two relations are different if there reasons are also different.

*/

public class KnowledgeHolder {

	protected List<GeoRelation> knowledge = new ArrayList<GeoRelation>();

	public KnowledgeHolder(List<GeoRelation> knowledge) {

		this.knowledge = knowledge;

	}

	public KnowledgeHolder() {

	}

	// to get full knowledge up to now
	public List<GeoRelation> getFullKnowledge() {

		return knowledge;

	}

	public int getSize() {

		return knowledge.size();

	}

	public boolean isEmpty() {

		return getSize() == 0;
	}

	public boolean insertGeoRelation(GeoRelation relation) {

		if (relationExistsWithName(relation))
			return false;
		else {
			knowledge.add(relation);
			return true;
		}

	}

	public boolean relationExistsWithName(GeoRelation comparingRelation) {

		for (Iterator<GeoRelation> iterator = knowledge.iterator(); iterator.hasNext();) {
			GeoRelation geoRelation = (GeoRelation) iterator.next();

			if (geoRelation.getName().compareTo(comparingRelation.getName()) == 0)
				return true;
		}

		return false;
	}

	/*
	 * return whether a exact geo-relation is currently exists
	 */
	public boolean relationExists(GeoRelation comparingRelation) {

		for (Iterator<GeoRelation> iterator = knowledge.iterator(); iterator.hasNext();) {

			GeoRelation geoRelation = (GeoRelation) iterator.next();

			// System.out.println(geoRelation.getName());

			// if two relation types are not matching
			if (geoRelation.relation != comparingRelation.relation)
				continue;

			// if two first items are miss match
			if (geoRelation.firstItem.type != comparingRelation.firstItem.type)
				continue;

			// if two second items are miss match
			if (geoRelation.secondItem.type != comparingRelation.secondItem.type)
				continue;

			// if relation1 item1 == relation2.item1
			// and relation1 item2 == relation2.item2
			if ((geoItemsAreSame(geoRelation.firstItem, comparingRelation.firstItem))
					&& (geoItemsAreSame(geoRelation.secondItem, comparingRelation.secondItem))
			// && ( relationReasonsAreSame(geoRelation, comparingRelation))

			)
				return true;

			// if relation1 item2 == relation2.item1
			// and relation1 item1 == relation2.item2
			if ((geoItemsAreSame(geoRelation.secondItem, comparingRelation.firstItem))
					&& (geoItemsAreSame(geoRelation.firstItem, comparingRelation.secondItem))
			// && ( ( relationReasonsAreSame(geoRelation, comparingRelation) ))
			)
				return true;

		}

		return false;
	}

	public boolean relationExistsExtend(GeoRelation comparingRelation, int count) {

		for (int i = count - 1; i < knowledge.size(); i++) {

			GeoRelation geoRelation = (GeoRelation) knowledge.get(i);

			// if two relation types are not matching
			if (geoRelation.relationType != comparingRelation.relationType)
				continue;
			
			// if two relation are not matching
			if (geoRelation.relation != comparingRelation.relation)
				continue;

			// if two first items are miss match
			if (geoRelation.firstItem.type != comparingRelation.firstItem.type)
				continue;

			// if two second items are miss match
			if (geoRelation.secondItem.type != comparingRelation.secondItem.type)
				continue;

			boolean factorSame = true;
			if(geoRelation.relationType == RelationType.FACTOR_GEO_RELATION){
				String inferredFactor = ((FactorGeoRelation)geoRelation).getFactor();
				String studentFactor = ((FactorGeoRelation)comparingRelation).getFactor();
				
				ScriptEngineManager mgr = new ScriptEngineManager();
			    ScriptEngine engine = mgr.getEngineByName("JavaScript");
			    try {
					inferredFactor = String.valueOf(engine.eval(inferredFactor));
					studentFactor = String.valueOf(engine.eval(studentFactor));
				} catch (ScriptException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    
			    factorSame = (Double.parseDouble(inferredFactor) == Double.parseDouble(studentFactor));
				
			}
			// if relation1 item1 == relation2.item1
			// and relation1 item2 == relation2.item2
			if ((geoItemsAreSame(geoRelation.firstItem, comparingRelation.firstItem))
					&& (geoItemsAreSame(geoRelation.secondItem, comparingRelation.secondItem) && factorSame)
			// && ( relationReasonsAreSame(geoRelation, comparingRelation))

			)
				return true;

			// if relation1 item2 == relation2.item1
			// and relation1 item1 == relation2.item2
			if ((geoItemsAreSame(geoRelation.secondItem, comparingRelation.firstItem))
					&& (geoItemsAreSame(geoRelation.firstItem, comparingRelation.secondItem) && factorSame)
			// && ( ( relationReasonsAreSame(geoRelation, comparingRelation) ))
			)
				return true;

		}

		return false;
	}

	private boolean relationReasonsAreSame(GeoRelation relationOne, GeoRelation relationTwo) {

		if (relationOne.getReason() == relationTwo.getReason()) {
			return true;
		}

		if ((relationOne.getReason() == null) && (relationTwo.getReason() == null)) {
			return true;
		}

		if (relationOne.getReason() == null) {
			return false;
		}

		// if(relationTwo.getStudentReason() == null) { return false; }

		return relationOne.getReason().compareToIgnoreCase(relationTwo.getReason()) == 0;

	}

	public boolean geoItemsAreSame(GeoItem item1, GeoItem item2) {

		// System.out.println("items" + item1 + item2);
		return item1.sameItem(item2);

	}
	
	public void printdata() {

		System.out.println("--------------items in knowledge Holder-------------");
		for (Iterator<GeoRelation> iterator = knowledge.iterator(); iterator.hasNext();) {

			GeoRelation geoRelation = (GeoRelation) iterator.next();

			System.out.println(geoRelation.getName());

		}
		System.out.println("--------------end---------------");
	}

}
