package com.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.geometry.Angle;
import com.geometry.FactorGeoRelation;
import com.geometry.GeoItem;
import com.geometry.GeoRelation;
import com.geometry.GeoValue;
import com.geometry.Line;
import com.geometry.Relation;
import com.geometry.RelationType;
import com.geometry.Triangle;

public class AnswerGetter {
	
	public ArrayList<Integer> subquetionPositions = new ArrayList<Integer>();
	private BufferedReader br;

	public ArrayList<GeoRelation> readFile(String filename) throws IOException {

		ArrayList<GeoRelation> relations = new ArrayList<GeoRelation>();
		br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filename)), "UTF-8"));

		int counter = 0;
		String line = null;
		// if no more lines the readLine() returns null
		while ((line = br.readLine()) != null) {
			// reading lines until the end of the file
			GeoRelation relation = splitScript(line);
			
			if (relation != null){
				relations.add(relation);
				counter++;
			}
			if(line != null && line.contains("<sub>")){
				subquetionPositions.add(counter);
			}
		}
		return relations;

	}

	public GeoRelation splitScript(String step) {
		step = step.trim();
		if (step.matches(".*\\d")) {
			
		}
		else if (step.matches(".*\\d.*")) {
			return getFactGeoRelation(step);
		}


		String[] spiltStepAndReason = step.split("\\(");
		String reasonPhrase = "";
		String reasonRelation = "";
		try {
			byte[] utf8Bytes = spiltStepAndReason[0].getBytes("UTF-8");
			step = new String(utf8Bytes, "UTF-8");

			utf8Bytes = spiltStepAndReason[1].getBytes("UTF-8");
			reasonPhrase = new String(utf8Bytes, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ArrayIndexOutOfBoundsException e) {
			reasonPhrase = "";
		}

		reasonRelation = reasonPhrase;// To determine complex reason

		// Reason is mined by checking for sinhala characters
		// Unicode character set for Sinhala letters
		Pattern unicodeOutliers = Pattern.compile("[^\\u0D80-\\u0DFF || . || \\u200D]",
				Pattern.UNICODE_CASE | Pattern.CANON_EQ | Pattern.CASE_INSENSITIVE);
		Matcher unicodeOutlierMatcher = unicodeOutliers.matcher(reasonPhrase);
		reasonPhrase = unicodeOutlierMatcher.replaceAll(" ");
		reasonPhrase = reasonPhrase.trim();
		
		
		// Determine Complex Reason exist or not
		// If complex reasonRelation will hold the GeoRelation e.g.: AB = AC
		unicodeOutliers = Pattern.compile("[\\u0D80-\\u0DFF || \\( || \\) || \\uFEFF || .]",
				Pattern.UNICODE_CASE | Pattern.CANON_EQ | Pattern.CASE_INSENSITIVE);
		unicodeOutlierMatcher = unicodeOutliers.matcher(reasonRelation);
		reasonRelation = unicodeOutlierMatcher.replaceAll(" ");
		reasonRelation = reasonRelation.trim();

		if (reasonRelation.length() > 0){
			
			//System.out.println(reasonRelation);
			
		}
		// Mining step relation
		unicodeOutliers = Pattern.compile("[\\u0D80-\\u0DFF || \\( || \\) || \\uFEFF]",
				Pattern.UNICODE_CASE | Pattern.CANON_EQ | Pattern.CASE_INSENSITIVE);
		unicodeOutlierMatcher = unicodeOutliers.matcher(step);
		step = unicodeOutlierMatcher.replaceAll(" ");
		step = step.trim();

		GeoRelation relation = null;
		String[] spiltStep = step.split("=");
		String part1;
		String part2;
		GeoItem item1 = null;
		GeoItem item2 = null;
		// ABC = BCD (ඒකාන්තර කෝණ)
		if (step.contains("=")) {
			spiltStep = step.split("=");
			part1 = spiltStep[0].replaceAll("\\s", "");
			part2 = spiltStep[1].replaceAll("\\s", "");
			item1 = createGeoItem(part1);
			if(part2.matches(".*\\d.*")){
				item2 = new GeoValue(part2);
				relation = new GeoRelation(item1, item2, Relation.EQUALS);
				relation.relationType = RelationType.VALUE_GEO_RELATION;
				
			}
			else{
				item2 = createGeoItem(part2);
				relation = new GeoRelation(item1, item2, Relation.EQUALS);
			}
			relation.setReason(reasonRelation + " " + reasonPhrase);
			
		} else if (step.contains("//")) {
			spiltStep = step.split("//");
			part1 = spiltStep[0].replaceAll("\\s", "");
			part2 = spiltStep[1].replaceAll("\\s", "");
			item1 = createGeoItem(part1);
			item2 = createGeoItem(part2);
			relation = new GeoRelation(item1, item2, Relation.PARALLEL_LINES);
			relation.setReason(reasonRelation + " " + reasonPhrase);
		} else if (step.contains("≡")) {
			spiltStep = step.split("≡");
			part1 = spiltStep[0].replaceAll("\\s", "");
			part2 = spiltStep[1].replaceAll("\\s", "");
			item1 = createGeoItem(part1);
			item2 = createGeoItem(part2);
			relation = new GeoRelation(item1, item2, Relation.CONGRUENT);
			relation.setReason(reasonRelation + " " + reasonPhrase);
		} else if (step.contains("⟂")) {
			spiltStep = step.split("⟂");
			part1 = spiltStep[0].replaceAll("\\s", "");
			part2 = spiltStep[1].replaceAll("\\s", "");
			item1 = createGeoItem(part1);
			item2 = createGeoItem(part2);
			relation = new GeoRelation(item1, item2, Relation.PERPENDICULAR);
			relation.setReason(reasonRelation + " " + reasonPhrase);
		}

		if(item1 == null || item2 == null)
			relation = null;
		return relation;
	}

	private GeoItem createGeoItem(String part) {
		GeoItem item = null;
		if (isGeoItem(part.toCharArray())) {
			if (part.contains("∠")) {
				part = part.replace("∠", "");
				item = new Angle(part);
			} else if (part.contains("△")) {
				part = part.replace("△", "");
				item = new Triangle(part);
			} else if (part.length() == 2) {
				item = new Line(part);
			}
		}

		return item;
	}

	private boolean isGeoItem(char[] part) {
		for (int i = 0; i < part.length; i++) {
			if (part[i] >= '0' && part[i] <= '9') {

				return false;
			}
		}
		return true;
	}

	public GeoRelation getFactGeoRelation(String step) {

		//Check for valid step which can be handled
		if(!(step.contains("=") || step.contains("//"))){
			return null;
		}
		
		String pattern1 = "([A-Za-z])|(=)|(\\s)|([\\u0D80-\\u0DFF])|(\\u2220)";
		// Create a Pattern object
		Pattern r = Pattern.compile(pattern1);
		// Now create matcher object.
		Matcher m = r.matcher(step);
		StringBuffer num = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(num, "");
		}
		m.appendTail(num);

		//System.out.println(step);
		char[] numstep = num.toString().toCharArray();
		for (int i = 0; i < numstep.length - 1; i++) {
			if (numstep[i] == '(' && numstep[i + 1] == ')') {
				numstep[i] = ' ';
				numstep[i + 1] = ' ';
			}

		}
		String factor = new String(numstep);
		// System.out.println(numaricstep);
		String pattern2 = "\\(?(\\d)\\)?|(\\/)|(\\*)|(\\()|(\\))";
		r = Pattern.compile(pattern2);
		m = r.matcher(step);
		StringBuffer rela = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(rela, "");
		}
		m.appendTail(rela);

		String tempstep = rela.toString();

		int size = tempstep.length();
		int equalsign = 0;
		for (int i = 0; i < size; i++) {
			if (tempstep.charAt(i) == '=') {
				equalsign = i;

				break;
			}
		}
		String firstItem = tempstep.substring(0, equalsign);
		//System.out.println(firstItem);

		String rest = tempstep.substring(equalsign + 1, tempstep.length());
		size = rest.length();
		equalsign = 0;
		for (int i = 0; i < size; i++) {
			if (rest.charAt(i) == ' ') {
				equalsign = i;

				break;
			} else {
				equalsign = i + 1;
			}
		}

		String seconditem = rest.substring(0, equalsign);
		String reason = "";
		if (size != (equalsign)) {
			reason = rest.substring(equalsign + 1, size);
		}
		
		GeoItem first = createGeoItem(firstItem);
		GeoItem secondI = createGeoItem(seconditem);

		GeoRelation gRelation = null;
		//System.out.println("Factor : " + factor);
		gRelation = new FactorGeoRelation(first, secondI, Relation.EQUALS, factor);
		gRelation.setReason(reason);
		return gRelation;

	}

}
