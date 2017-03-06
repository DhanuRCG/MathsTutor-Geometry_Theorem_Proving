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

public class Theorem08 {
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
	        	Point P= new Point('P');
	        	Point Q=new Point('Q');
	        	Point R=new Point('R');
	        	
	        	
	        	
	        	
	        	
	        	
	        	Line AB=new Line(A,B);
	        	Line PQ=new Line(P,Q);
	        	//line
	        	Line BC=new Line(B,C);
	        	Line QR=new Line(Q,R);
	        	//hypotenuse
	        	Line AC=new Line(A,C);
	        	Line PR=new Line(P,R);
	        	GeoRelation AC_PR = new GeoRelation(AC,PR,Relation.EQUALS); 
	        	GeoRelation BC_QR = new GeoRelation(BC,QR,Relation.EQUALS);
	        	
	        	Angle ABCangle=new Angle(AB,AC);
	        	Angle PQRangle=new Angle(PQ,PR);
	        	ABCangle.value=90;
	        	PQRangle.value=90;	
	        	
	        	Triangle ABC=new Triangle(A,B,C);
	        	Triangle PQR=new Triangle(P, Q, R);
	        	ABC.setValueToAngle(ABCangle);
	        	PQR.setValueToAngle(PQRangle);
	        	
	        	
	        	kSession.insert(ABC);
	        	kSession.insert(PQR);
	        	kSession.insert(BC_QR);
	        	kSession.insert(AC_PR);
	        
	        	kSession.fireAllRules();
	        	
	        	
	        } catch (Throwable t) {
	            t.printStackTrace();
	        }
	}
}
