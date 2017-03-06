package com.test;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.geometry.GeoRelation;
import com.geometry.Line;
import com.geometry.Point;
import com.geometry.Quadrangle;
import com.geometry.Relation;
import com.geometry.Triangle;

public class Theorem23 {

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
        	Point X=new Point('X');
        	
        	
        	Line AB=new Line(A,B);
        	Line BC=new Line(B,C);
        	
        	Line AD=new Line(A,D);
        	Line CD=new Line(C,D);
        	
        	
        	
        	
        	
        	Quadrangle ABCD=new Quadrangle(A,B,C,D);
        	ABCD.isAParalleologram=true;
        	
        	Triangle ABX=new Triangle(A,B,X); 
        	
        	GeoRelation DXC= new GeoRelation(CD, X, Relation.ON_THE_LINE);
        	kSession.insert(ABCD);
        	kSession.insert(DXC);
        	kSession.insert(ABX);
        	//kSession.insert(DEF);
        	kSession.fireAllRules();
        	
        } catch (Throwable t) {
            t.printStackTrace();
        }

	}

}
