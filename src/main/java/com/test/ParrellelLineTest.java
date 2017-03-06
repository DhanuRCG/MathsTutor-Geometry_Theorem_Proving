package com.test;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.geometry.Angle;
import com.geometry.GeoRelation;
import com.geometry.Line;
import com.geometry.Point;
import com.geometry.Relation;

public class ParrellelLineTest {

	public static void main(String args[]) {
		try {

			Point A = new Point('A');
			Point B = new Point('B');
			Point C = new Point('C');
			Point D = new Point('D');
			Point E = new Point('E');

			Line AB = new Line(A, B);
			Line AC = new Line(A, C);
			Line CD = new Line(C, D);
			Line CE = new Line(C, E);
			Line BA = new Line(B, A);

			GeoRelation ABparrCD = new GeoRelation(AB, CD, Relation.PARALLEL_LINES);
			GeoRelation EonAB = new GeoRelation(AC, E, Relation.ON_THE_LINE);

			// load up the knowledge base
			KieServices ks = KieServices.Factory.get();
			KieContainer kContainer = ks.getKieClasspathContainer();
			KieSession kSession = kContainer.newKieSession("ksession-rule");

			// go !
			kSession.insert(A);
			kSession.insert(ABparrCD);
			kSession.insert(EonAB);
			kSession.fireAllRules();

		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
}
