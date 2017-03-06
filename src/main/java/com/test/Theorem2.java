package com.test;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.geometry.Angle;
import com.geometry.GeoRelation;
import com.geometry.Line;
import com.geometry.Point;
import com.geometry.Relation;

public class Theorem2 {

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
	        	
	        	
	        	Line AB =new Line(A,B);
	        	Line CD =new Line(C,D);
	        	
	        	
	        	Line AE=new Line(A, E);
	        	Line BE=new Line(B,E);
	        	Line CE=new Line(C, E);
	        	Line DE=new Line(D,E);
	        	
	        	
	        	Angle AED=new Angle(AE, DE);
	        	Angle DEB=new Angle(DE,BE);
	        	Angle BEC=new Angle(BE, CE);
	        	Angle AEC=new Angle(CE, AE);
	        	
	        	
	        	
	        	GeoRelation online1=new GeoRelation(AB, E, Relation.ON_THE_LINE);
	        	GeoRelation online2=new GeoRelation(CD, E, Relation.ON_THE_LINE);
	        	
	        	
	        	
	        	
	        	kSession.insert(AED);
	        	kSession.insert(DEB);
	        	kSession.insert(BEC);
	        	kSession.insert(AEC);
	        	
	        	
	        	kSession.insert(online1);
	        	kSession.insert(online2);
	        	//kSession.insert(twoangle);
	        	
	        	
	        	//kSession.insert(correspondingAngle);
	        	
	        	kSession.fireAllRules();
	        	
	        } catch (Throwable t) {
	            t.printStackTrace();
	        }
	}

}
