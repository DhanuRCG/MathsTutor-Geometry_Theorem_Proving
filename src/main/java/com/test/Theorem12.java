package com.test;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.geometry.Angle;
import com.geometry.GeoRelation;
import com.geometry.Line;
import com.geometry.Point;
import com.geometry.Relation;
import com.geometry.Triangle;

public class Theorem12 {
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
	        	
	        	Line AB=new Line(A,B);
	        	Line BC=new Line(B,C);
	        	Line CG=new Line(C,G);
	        	
	        	Line DE=new Line(D,E);
	        	Line EF=new Line(E,F);
	        	Line DF=new Line(D,F);
	        	Line AC=new Line(A,C);
	        	Triangle ABC=new Triangle(A,B,C);
	        	Triangle DEF=new Triangle(D, E, F);
	        	
	        	Angle ABCangle=new Angle(AB,BC);
	        	Angle DEFangle=new Angle(DE,EF);
	        	
	        	Angle BACangle=new Angle(AC, AB);
	        	Angle EDFangle=new Angle(DF, DE);
	        	
	        	Angle ACBangle=new Angle(BC, AC);
	        	
	        	//GeoRelation relation1=new GeoRelation(ABCangle,DEFangle, Relation.EQUALS);
	        	
	        	GeoRelation relation3=new GeoRelation(AB, AC, Relation.EQUALS);
	        	//GeoRelation relation4=new GeoRelation(BACangle, EDFangle, Relation.EQUALS);
	      
	        	
	        	kSession.insert(relation3);
	        	
	        	kSession.insert(ABC);
	        	kSession.insert(ABCangle);
	        	kSession.insert(ACBangle);
	        	kSession.fireAllRules();
	        	
	        } catch (Throwable t) {
	            t.printStackTrace();
	        }
	}
}
