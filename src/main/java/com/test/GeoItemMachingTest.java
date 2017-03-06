package com.test;

import java.util.ArrayList;

import com.geometry.GeoRelation;
import com.geometry.Line;
import com.geometry.Point;
import com.geometry.Relation;
import com.knowledge.KnowledgeHolder;

public class GeoItemMachingTest {

	public static void main(String args[]){


		
		Point p1 = new Point('A');
		Point p2 = new Point('B');
		Point pc = new Point('C');
		Point pd = new Point('D');
		
		Line ab = new Line(p1, p2);
		Line cd = new Line(pc, pd);
		
		GeoRelation rel = new GeoRelation(ab, cd, Relation.PARALLEL_LINES);
		GeoRelation rel2 = new GeoRelation(cd, ab, Relation.PARALLEL_LINES);
		
		ArrayList<GeoRelation> knowledge = new ArrayList<GeoRelation>();

		knowledge.add(rel);
		
		KnowledgeHolder kh = new KnowledgeHolder(knowledge);
		
		
		
		
		
		System.out.println(kh.relationExists(rel));

	}
	
}
