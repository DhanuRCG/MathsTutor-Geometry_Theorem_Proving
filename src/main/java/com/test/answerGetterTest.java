package com.test;

import java.io.IOException;

import com.geometry.Angle;
import com.geometry.GeoRelation;
import com.geometry.Line;
import com.geometry.Point;
import com.io.AnswerGetter;

public class answerGetterTest {

	public static void main(String args[]) {
		AnswerGetter ag = new AnswerGetter();
		
		String filename = "E:\\eclipse\\data\\testdata1.txt";
		
		try {
			GeoRelation georelation = ag.readFile(filename).get(0);
			
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
			Angle DCE = new Angle(CD, CE);
			
			System.out.println("asdsa"+ABC.getName());
			
			//System.out.println(((Angle)georelation.firstItem).middle.getName());
			//System.out.println(((Angle)georelation.firstItem).pointOne.getName());
			//System.out.println(((Angle)georelation.firstItem).pointThree.getName());
			
			
			System.out.println(ag.readFile(filename).get(0).firstItem.getName());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
