package com.test;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.geometry.Angle;
import com.geometry.GeoRelation;
import com.geometry.Line;
import com.geometry.Point;
import com.geometry.Relation;

public class TriangleTest {
	public static void main(String[] args) {
		String strWithNumber = "OC = 1/2(1/2)BC";
		String strWithoutNumber = "This string has a number";

		System.out.println(strWithNumber.matches(".*\\d.*"));
		System.out.println(strWithoutNumber.matches(".*\\d.*"));
	}
}
