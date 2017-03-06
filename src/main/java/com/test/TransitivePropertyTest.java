package com.test;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.geometry.Angle;
import com.geometry.ExpressionRelation;
import com.geometry.GeoRelation;
import com.geometry.Line;
import com.geometry.Point;
import com.geometry.Relation;
import com.geometry.RelationType;
import com.geometry.Triangle;

public class TransitivePropertyTest {
	public static void main(String args[]) {
		try {
			// load up the knowledge base
			KieServices ks = KieServices.Factory.get();
			KieContainer kContainer = ks.getKieClasspathContainer();
			KieSession kSession = kContainer.newKieSession("ksession-rule");

			// go !
			Point A = new Point('A');
			Point B = new Point('B');
			Point C = new Point('C');
			Point D = new Point('D');

			Line AB = new Line(A, B);
			Line BC = new Line(B, C);
			Line BD = new Line(B, D);
			Line AC = new Line(A, C);

			Angle ABC = new Angle(AB, BC);
			Angle CAB = new Angle(AC, AB);
			Angle BCA = new Angle(BC, AC);
			
			
			//Test 01 a = b, b = c --> a = c
			/*GeoRelation ABeqBC = new GeoRelation(AB, BC, Relation.EQUALS);
			GeoRelation BCeqAC = new GeoRelation(BC, AC, Relation.EQUALS);
			GeoRelation ABCeqCAB = new GeoRelation(ABC, CAB, Relation.EQUALS);
			GeoRelation CABeqBCA = new GeoRelation(CAB, BCA, Relation.EQUALS);*/
			
			//Test 02 a = b, a = c --> b = c
			GeoRelation ABeqBC = new GeoRelation(AB, BC, Relation.PARALLEL_LINES);
			GeoRelation ABeqAC = new GeoRelation(AB, AC, Relation.PARALLEL_LINES);
			GeoRelation ABCeqCAB = new GeoRelation(ABC, CAB, Relation.EQUALS);
			GeoRelation ABCeqBCA = new GeoRelation(ABC, BCA, Relation.EQUALS);

			kSession.insert(ABeqBC);
			kSession.insert(ABeqAC);
			kSession.insert(ABCeqCAB);
			kSession.insert(ABCeqBCA);
			
			kSession.fireAllRules();

		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

}
