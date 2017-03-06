package com.test;

import java.util.ArrayList;

import com.geometry.Angle;
import com.geometry.GeoRelation;
import com.geometry.Line;
import com.geometry.Point;
import com.geometry.Relation;
import com.knowledge.KnowledgeHolder;

public class RelationExistTest {
	public static void main(String args[]){
		
		final  KnowledgeHolder inferedKnowledge;
		
		Point A = new Point('A');
		Point B = new Point('B');
		Point C = new Point('C');
		Point D = new Point('D');
		Point E = new Point('E');
		
		Line AB = new Line(A, B);
		Line BC = new Line(B, C);
		Line CD = new Line(C, D);
		Line CE = new Line(C, E);
		
		Angle ABC = new Angle(BC, AB);
		Angle test = new Angle(AB, BC);
		Angle DCE = new Angle(CD, CE);
		
		GeoRelation relData = new GeoRelation(ABC, DCE, Relation.EQUALS);
		GeoRelation reltest = new GeoRelation(DCE, test, Relation.EQUALS);
		
		GeoRelation relParrData = new GeoRelation(AB, CD, Relation.PARALLEL_LINES);
		
		ArrayList<GeoRelation> initRelations = new ArrayList<GeoRelation>();
		
		initRelations.add(relData);

		initRelations.add(relParrData);

		inferedKnowledge = new KnowledgeHolder(initRelations);
		
		System.out.println(ABC.sameItem(test));
		System.out.println(inferedKnowledge.relationExists(reltest));
		
		
	}

}
