package com.test;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.geometry.Angle;
import com.geometry.GeoRelation;
import com.geometry.Line;
import com.geometry.Point;
import com.geometry.Relation;

public class Theorem04 {
	public static void main(String args[]){
	      try {
	        	
	        	
	  		
	        	
	            // load up the knowledge base
		        KieServices ks = KieServices.Factory.get();
	    	    KieContainer kContainer = ks.getKieClasspathContainer();
	        	KieSession kSession = kContainer.newKieSession("ksession-rules");

	            // go !
	        	Point A= new Point('A');
	        	Point B= new Point('B');
	        	Point C= new Point('C');
	        	Point D= new Point('D');
	        	Point E=new Point('E');
	        	Point F=new Point('F');
	        	Point G=new Point('G');
	        	Point H=new Point('H');
	        	
	        	Line AB =new Line(A,B);
	        	Line CD =new Line(C,D);
	        	Line BC=new Line(B,C);
	        	Line CE=new Line(C,E);
	        	Line BE=new Line(B,E);
	        	Angle ABC=new Angle(BC, AB);
	        	Angle BCD=new Angle(CE, CD);
	        	
	        	
	        	GeoRelation online5=new GeoRelation(ABC, BCD, Relation.EQUALS);
	        	GeoRelation online6=new GeoRelation(BE, C, Relation.ON_THE_LINE);
	        	
	        	kSession.insert(online5);
	        	kSession.insert(online6);
	        	
	        	//kSession.insert(correspondingAngle);
	        	
	        	kSession.fireAllRules();
	        	
	        } catch (Throwable t) {
	            t.printStackTrace();
	        }
	}
}
