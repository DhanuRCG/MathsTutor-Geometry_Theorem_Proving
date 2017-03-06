package com.test;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;

import com.geometry.Angle;
import com.geometry.ExpressionRelation;
import com.geometry.GeoRelation;
import com.geometry.Line;
import com.geometry.Point;
import com.geometry.Relation;
import com.geometry.RelationType;
import com.geometry.Triangle;

public class Theorem01 {
	public static void main(String args[]) {
		try {
			// load up the knowledge base
			KieServices ks = KieServices.Factory.get();
			KieContainer kContainer = ks.getKieClasspathContainer();
			KieSession kSession = kContainer.newKieSession("ksession-rules");

			// go !
			Point A = new Point('A');
			Point B = new Point('B');
			Point C = new Point('C');
			Point D = new Point('D');

			ExpressionRelation relation1 = new ExpressionRelation(RelationType.EXPRESSION_RELATION);
			Triangle ABC = new Triangle(A, B, C);

			Line AB = new Line(A, B);
			Line BC = new Line(B, C);
			Line BD = new Line(B, D);
			Line AC = new Line(A, C);

			Angle ABD = new Angle(AB, BD);
			Angle CBD = new Angle(BD, BC);
			Angle val = new Angle(180);
			GeoRelation AConB = new GeoRelation(AC, B, Relation.ON_THE_LINE);

			relation1.addItem(ABD);
			relation1.addOperation(Relation.PLUS);
			relation1.addItem(CBD);
			relation1.addOperation(Relation.EQUALS);
			relation1.addItem(val);
			// Test 1

			// kSession.insert(relation1);
			kSession.insert(BD);
			kSession.insert(AConB);
			kSession.insert(ABD);
			kSession.insert(CBD);
			// Test2
			/*
			 * Line DF=new Line(D,F); Line ED=new Line(E,D); Angle EDF=new
			 * Angle(DF,ED); GeoRelation relation4 =new
			 * GeoRelation(ABCangle,EDF,Relation.EQUALS);
			 * kSession.insert(relation4); kSession.insert(relation2);
			 * kSession.insert(relation3);
			 * 
			 * kSession.insert(ABC); kSession.insert(DEF);
			 */
			kSession.fireAllRules();

		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
}
