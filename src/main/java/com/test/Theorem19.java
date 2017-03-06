package com.test;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.geometry.GeoRelation;
import com.geometry.Line;
import com.geometry.Point;
import com.geometry.Quadrangle;
import com.geometry.Relation;

public class Theorem19 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
        	
        	Point E= new Point('E');
        	
        	Line AB=new Line(A,B);
        	Line BC=new Line(B,C);
        	
        	Line AD=new Line(A,D);
        	Line CD=new Line(C,D);
        	
        	Line AC=new Line(A,C);
        	Line BD=new Line(B,D);
        	
        	Line BE=new Line(B,E);
        	Line ED=new Line(E,D);
        	
        	Line AE=new Line(A,E);
        	Line EC=new Line(E,C);
        	
        	Quadrangle ABCD=new Quadrangle(A,B,C,D);
        	ABCD.isAParalleologram=false;
        	
        	;
        	
        	
        	GeoRelation ABlineCD= new GeoRelation(AB, CD, Relation.EQUALS);
        	GeoRelation ABparaCD= new GeoRelation(AB, CD, Relation.PARALLEL_LINES);
        	kSession.insert(ABCD);
        	kSession.insert(ABlineCD);
        	kSession.insert(ABparaCD);
        	
        	//kSession.insert(DEF);
        	kSession.fireAllRules();
        	
        } catch (Throwable t) {
            t.printStackTrace();
        }

	}
}
