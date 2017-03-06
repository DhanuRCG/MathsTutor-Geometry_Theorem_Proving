package com.test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.sentensesimilarity.core.NLP;
import org.sentensesimilarity.core.SentensePair;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByName("JavaScript");
		String relation1Factor = "(1/2)*(1/2)";
		String relation2Factor = "1/4";
		try {
			relation1Factor = String.valueOf(engine.eval(relation1Factor));
			relation2Factor = String.valueOf(engine.eval(relation2Factor));
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/
		double a = 5.0;
		String s = a+"";
		String b[] = s.split("\\.");
		for (String string : b) {
			System.out.println(string);
		}
		
	}

}
