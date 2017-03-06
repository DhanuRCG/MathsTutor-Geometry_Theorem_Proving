package com.test;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.geometry.*;

public class Theorem20 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
        	
        	
	  		
        	
            // load up the knowledge base
	        KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
        	KieSession kSession = kContainer.newKieSession("ksession-test");

            // go !
        	Point A= new Point('A');
        	Point B= new Point('B');
        	Point C= new Point('C');
        	Point D= new Point('D');
        	Point E=new Point('E');
        	
        	
        	Line AB=new Line(A,B);
        	Line BC=new Line(B,C);
        	
        	Line AD=new Line(A,D);
        	Line DB=new Line(D,B);
        	
        	Line BE=new Line(B,E);
        	Line EC=new Line(E,C);
        	
        	Line AC=new Line(A,C);
        	Triangle ABC=new Triangle(A,B,C);
        	
        	
        	GeoRelation relation1=new GeoRelation(AB,D, Relation.ON_THE_LINE);
        	
        	GeoRelation relation3=new GeoRelation(BC, E, Relation.ON_THE_LINE);
        	
        	GeoRelation relation4=new GeoRelation(AD, DB, Relation.EQUALS);
        	GeoRelation relation5=new GeoRelation(BE, EC, Relation.EQUALS);
        	//GeoRelation relation4=new GeoRelation(BACangle, EDFangle, Relation.EQUALS);
        	//System.out.println(DEFangle.sameItem(EDFangle)+"fksdfdkfjsdkjf");
        	
        	kSession.insert(relation1);
        	//kSession.insert(relation2);
        	kSession.insert(relation3);
        	kSession.insert(relation4);
        	kSession.insert(relation5);
        	//kSession.insert(relation4);
        	kSession.insert(ABC);
        	//kSession.insert(DEF);
        	kSession.fireAllRules();
        	
        } catch (Throwable t) {
            t.printStackTrace();
        }

	}

}
