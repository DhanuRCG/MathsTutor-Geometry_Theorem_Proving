package com.engine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

import com.geometry.GeoItem;
import com.geometry.GeoRelation;
import com.geometry.GeoType;
import com.geometry.Relation;
import com.graph.Graph;
import com.graph.GraphNode;
import com.io.AnswerGetter;
import com.io.ProblemGetter;
import com.knowledge.GeoDataHolder;
import com.knowledge.HashTestKnowledge;
import com.knowledge.KnowledgeHolder;
import com.knowledge.MarkingSchemaHolder;
import com.knowledge.ReasonHolder;
import com.knowledge.StepStatus;
import com.knowledge.StudentAnswerHolder;
import com.knowledge.TransitionKnowledgeHolder;

public class CheckingEngine {
	public static StudentAnswerHolder answerHolder;
	public static GeoDataHolder problemDataHolder;
	public KnowledgeHolder initialGivenKnowledge;
	public static TransitionKnowledgeHolder transitionKnowledgeHolder;
	public static HashTestKnowledge hashTestKnowledge;
	public static KnowledgeHolder studentsMarkedStepHolder;
	public static ReasonHolder reasonHolder;
	public static MarkingSchemaHolder markingSchemaHolder;
	public static Graph graph;

	static KieServices ks;
	static KieContainer kContainer;
	static KieSession kSession;

	static{
		transitionKnowledgeHolder = new TransitionKnowledgeHolder();
		studentsMarkedStepHolder = new KnowledgeHolder();
		problemDataHolder = new GeoDataHolder();
		hashTestKnowledge = new HashTestKnowledge();
		reasonHolder = new ReasonHolder();
		graph = new Graph();
		
	}
	
	
	public ArrayList<Integer> loadStudentAnswer(String fileName) {
		AnswerGetter studentAnswerGetter = new AnswerGetter();

		try {
			answerHolder = new StudentAnswerHolder(studentAnswerGetter.readFile(fileName));
			return studentAnswerGetter.subquetionPositions;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Student answer is not found !!!");
			e.printStackTrace();
			return null;
		}
		
	}

	public void loadProblem(String fileName) {
		// Initialize initial given data
		ProblemGetter problemDetailsGetter = new ProblemGetter(fileName);
		problemDataHolder = new GeoDataHolder(problemDetailsGetter.getProblemFigureData());
		
		// Initialize initialGivenKnowledge
		initialGivenKnowledge = new KnowledgeHolder(problemDetailsGetter.getProblemGivenRelations());
		
	}

	public void buildGraph(ArrayList<String[]> rules) {
		
		int count = 0;

		transitionKnowledgeHolder.insertKnowledgeBulk(initialGivenKnowledge);

		fireInfirenceEngineWithCommon(transitionKnowledgeHolder);
		do {

			count++;
			//System.out.println(" Iteration ::" + count + " Relations Found ::" + currentRelationCountInTemp);
			fireInfirenceEngineWithCommon(transitionKnowledgeHolder);
			for (String[] rule : rules) {
				fireSelectedRules(transitionKnowledgeHolder, rule);
				fireInfirenceEngineWithCommon(transitionKnowledgeHolder);
			}
			
	} while (!(
			  (count==2)));
		fireSelectedRules(transitionKnowledgeHolder, new String[]{"Theorem06.drl"});
		fireInfirenceEngineWithCommon(transitionKnowledgeHolder);
		
	}
	public void markAnswer() {
		
		int currentRelationCountInTemp, count;

		boolean stepFound;
		
		while (!answerHolder.endOfAnswer()) {
			stepFound = false;

			GeoRelation step = answerHolder.getNextAnswerStep();
			
			System.out.println();

			System.out.print(step.getName() + "  ");
			if (initialGivenKnowledge.relationExists(step)) {
				studentsMarkedStepHolder.insertGeoRelation(step);
				answerHolder.markCurrentAnswer(StepStatus.CORRECT);
				System.out.print("a Given data");
			} else {
				System.out.println("Not a Given data");
				fireInfirenceEngine(studentsMarkedStepHolder);
				
				if (transitionKnowledgeHolder.relationExists(step)) {
					System.out.print(" Comes from previously Marked data");

					studentsMarkedStepHolder.insertGeoRelation(step);

					answerHolder.markCurrentAnswer(StepStatus.CORRECT);
				} else {
					System.out.print(" Not Coming from prevously Marked data");														// location

					count = 0;
					
					transitionKnowledgeHolder.insertKnowledgeBulk(initialGivenKnowledge);

					do {

						count++;
						currentRelationCountInTemp = transitionKnowledgeHolder.getSize();
						//transitionKnowledgeHolder.printdata();
						fireInfirenceEngine(transitionKnowledgeHolder); // get
																		// results
																		// from
																		// given
																		// data

						System.out.println(" loop Count ::" + count + " Count ::" + currentRelationCountInTemp);
						if (transitionKnowledgeHolder.relationExistsExtend(step,currentRelationCountInTemp)) {

							System.out.print(" Infered from given data | Count" + count);

							studentsMarkedStepHolder.insertGeoRelation(step);

							answerHolder.markCurrentAnswer(StepStatus.CORRECT_BUT_PREVIOUS_STEP_MISSING);

							stepFound = true;
						}


					} while (!((currentRelationCountInTemp == transitionKnowledgeHolder.getSize()) 
							|| (stepFound) || (count==30)));
					
					if (stepFound) {
					} 
					else {
						answerHolder.markCurrentAnswer(StepStatus.INCORRECT);
						System.out.print(" -> Incorrect" + stepFound);
					}

				}
			}
		}
		
		answerHolder.printMarkedAnswer();
	}

	public boolean fireInfirenceEngine(KnowledgeHolder knowledgeHolder) {

		ks = KieServices.Factory.get();

		kContainer = ks.getKieClasspathContainer();

		kSession = kContainer.newKieSession("ksession-rule");

		List<GeoItem> geoData = problemDataHolder.getAllGeoItemKnowledge();

		for (Iterator<GeoItem> iterator = geoData.iterator(); iterator.hasNext();) {
			GeoItem geoItem = (GeoItem) iterator.next();
			kSession.insert(geoItem);

		}
	
		List<GeoRelation> allknowledge = knowledgeHolder.getFullKnowledge();

		for (Iterator<GeoRelation> iterator = allknowledge.iterator(); iterator.hasNext();) {

			GeoRelation geoRelation = (GeoRelation) iterator.next();

			// System.out.println(geoRelation.firstItem.getName() +
			// geoRelation.secondItem.getName() + geoRelation.relation);

			kSession.insert(geoRelation);

		}

		kSession.fireAllRules();

		kSession.dispose();

		return true;
	}
	
	public boolean fireInfirenceEngineWithCommon(KnowledgeHolder knowledgeHolder) {

		ks = KieServices.Factory.get();

		kContainer = ks.getKieClasspathContainer();

		kSession = kContainer.newKieSession("common-rules");

		List<GeoItem> geoData = problemDataHolder.getAllGeoItemKnowledge();

		for (Iterator<GeoItem> iterator = geoData.iterator(); iterator.hasNext();) {
			GeoItem geoItem = (GeoItem) iterator.next();
			kSession.insert(geoItem);

		}
	
		List<GeoRelation> allknowledge = knowledgeHolder.getFullKnowledge();

		for (Iterator<GeoRelation> iterator = allknowledge.iterator(); iterator.hasNext();) {

			GeoRelation geoRelation = (GeoRelation) iterator.next();

			// System.out.println(geoRelation.firstItem.getName() +
			// geoRelation.secondItem.getName() + geoRelation.relation);

			kSession.insert(geoRelation);

		}

		kSession.fireAllRules();

		kSession.dispose();

		return true;
	}
	
	public void fireSelectedRules(KnowledgeHolder knowledgeHolder, String[] rules){
		try {
			kSession = new RuleRunner().getSession( rules );
			
			
			List<GeoItem> geoData = problemDataHolder.getAllGeoItemKnowledge();

			for (Iterator<GeoItem> iterator = geoData.iterator(); iterator.hasNext();) {
				GeoItem geoItem = (GeoItem) iterator.next();
				kSession.insert(geoItem);

			}
		
			List<GeoRelation> allknowledge = knowledgeHolder.getFullKnowledge();

			for (Iterator<GeoRelation> iterator = allknowledge.iterator(); iterator.hasNext();) {

				GeoRelation geoRelation = (GeoRelation) iterator.next();

				// System.out.println(geoRelation.firstItem.getName() +
				// geoRelation.secondItem.getName() + geoRelation.relation);

				kSession.insert(geoRelation);

			}

			kSession.fireAllRules();

			kSession.dispose();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public  static void setGraph(Graph graph){
		CheckingEngine.graph = graph;
	}
	
	public  static void setHashTestKnowledge(HashTestKnowledge hashTestKnowledge){
		CheckingEngine.hashTestKnowledge = hashTestKnowledge;
	}

	public boolean isParentsExistsInAnswerAndValid(GeoRelation answer) {
		
		boolean isParentsExistsInAnswerAndValid = true;
		
		if(graph.findNode(answer) == null) return false;
		
		if(initialGivenKnowledge.relationExists(answer)){
			return true;
		}
		
		if("ROOT".equalsIgnoreCase(graph.findNode(answer).getPredesisorNames())){
			return true;
		}

		ArrayList<GraphNode> predisissors = graph.findNode(answer).getPredesisors();
		
		if(predisissors == null){
			return true;
		}

		if (isSameItemEqualsRelation(answer)){ return true; }
		
		
		if(answer.relation == Relation.CONGRUENT){
			int cogurantRelationCount = 0;
			for (Iterator iterator = predisissors.iterator(); iterator.hasNext();) {
				GraphNode parentNode = (GraphNode) iterator.next();
				
				if(studentsMarkedStepHolder.relationExists(parentNode.getRelation())){
					cogurantRelationCount++;
				}
			}
			if(cogurantRelationCount >= 3){
				return true;
			}
		}
		
		if(predisissors == null){
			
		}
		
//		if(hashTestKnowledge.isSameAngleWithDifferentName(
//				geoRelation.firstItem.getName() , geoRelation.secondItem.getName()))){
//			
//		}
				
		for (Iterator iterator = predisissors.iterator(); iterator.hasNext();) {
			GraphNode parentNode = (GraphNode) iterator.next();
			
			if(parentNode.getRelation().relation == Relation.ON_THE_LINE){
				continue;
			}
			
			if(!answerHolder.isGeorelationExists(parentNode.getRelation())){
				if(initialGivenKnowledge.relationExists(parentNode.getRelation())){
//					System.out.println("Parent has not written but data:" + parentNode.getRelation().getName());					
					
					
					if(foundInReason(answer,parentNode.getRelation())){
						//System.out.println("Found in reason");
						continue;
					}
					else{
						isParentsExistsInAnswerAndValid = false;
					}
				}
				else{
	//				System.out.println("Parent has not written not in data:" + parentNode.getRelation().getName());

					GeoRelation geoRelation = parentNode.getRelation();
					
					if(hashTestKnowledge.isSameRelationWithOtherName(answer, geoRelation)){
		//				System.out.println("Parent same as the answer:" + parentNode.getRelation().getName());
						continue;
					}
					else if(foundInReason(answer,geoRelation)){
			//			System.out.println("Found in reason");
						continue;
					}
					
					else if(geoRelation.relation == Relation.EQUALS){
						//System.out.println("relation ok");
						if(geoRelation.firstItem.type == GeoType.ANGLE && geoRelation.secondItem.type == GeoType.ANGLE){
							//System.out.println("angle ok ok");
							
							if(hashTestKnowledge.isSameAngleWithDifferentName(
									geoRelation.firstItem.getName() , geoRelation.secondItem.getName()))
							{
//								System.out.println("Parent has not written not in data but same:" + parentNode.getRelation().getName());
								continue;
							}
							else{
								// the required parent must be written in the answer
								//System.out.println("not yet found:" + parentNode.getRelation().getName());
								if(studentsMarkedStepHolder.relationExists(parentNode.getRelation())){
								//	System.out.println("found in marked :" + parentNode.getRelation().getName());
									
								}
								else{
									//System.out.println("not found in marked :" + parentNode.getRelation().getName());
									
									boolean isLoopfalse = false;
									
									List<GeoRelation> markedSteps = studentsMarkedStepHolder.getFullKnowledge();
									for (Iterator iterator2 = markedSteps.iterator(); iterator2.hasNext();) {
										GeoRelation geoRelation2 = (GeoRelation) iterator2.next();
											if(hashTestKnowledge.isSameRelationWithOtherName(parentNode.getRelation(), geoRelation2)){
												isLoopfalse = true;
												//System.out.println("found OK");
											}
										
									}
									if(isLoopfalse){
										continue;
									}
									else{
										isParentsExistsInAnswerAndValid = false;
									}
									
								}
							}
						}
						else{ isParentsExistsInAnswerAndValid = false; }
					}
					else{ 
						
					}
				}
					
			}
			
		}
		return isParentsExistsInAnswerAndValid;
	}
	
	public boolean isSameItemEqualsRelation(GeoRelation relation){

		if(relation.relation != Relation.EQUALS) { return false; }
		
		if(relation.getFirstItem() != null && relation.getSecondItem() != null && relation.getFirstItem().sameItem(relation.getSecondItem())){
			List<GeoItem> geoData = problemDataHolder.getAllGeoItemKnowledge();
			for (Iterator iterator = geoData.iterator(); iterator.hasNext();) {
				GeoItem geoItem = (GeoItem) iterator.next();
				
				if(geoItem.sameItem(relation.getFirstItem())){
					return true;
				}
			}
		}
		return false;
	}
	public boolean foundInReason(GeoRelation answer, GeoRelation geoRelation){
		
		if(geoRelation.relation == Relation.MIDPOINT){
			
			
			String pattern = "(" + geoRelation.getFirstItem().getName() + "|" 
					+ new StringBuilder(geoRelation.getFirstItem().getName()).reverse().toString() + ") *";
					
					if(geoRelation.relation == Relation.EQUALS){
						pattern += " මද්‍ය";
					}
					
					
					pattern = pattern +" *(" + geoRelation.getSecondItem().getName() + "|"
							+ new StringBuilder(geoRelation.getSecondItem().getName()).reverse().toString() + ")";

					
					Pattern p = Pattern.compile(pattern);   // the pattern to search for
				    Matcher m = p.matcher(answer.getReason());

				     if(m.find()) return true;

		}
		
		
		if("දත්තය".contains(answer.getReason()) || "දත්".contains(answer.getReason())){
			ArrayList<GraphNode> parents = graph.findNode(answer).getPredesisors();
			boolean allDataOk = true;
			
			for (Iterator iterator = parents.iterator(); iterator.hasNext();) {
				GraphNode graphNode = (GraphNode) iterator.next();
				
				if(!initialGivenKnowledge.relationExists(graphNode.getRelation())){
					allDataOk = false;
				}
				
			}
			
			if(allDataOk) return true;
		}
		
		String pattern = "(" + geoRelation.getFirstItem().getName() + "|" 
		+ new StringBuilder(geoRelation.getFirstItem().getName()).reverse().toString() + ") *";
		
		if(geoRelation.relation == Relation.EQUALS){
			pattern += "=";
		}
		
		
		pattern = pattern +" *(" + geoRelation.getSecondItem().getName() + "|"
				+ new StringBuilder(geoRelation.getSecondItem().getName()).reverse().toString() + ")";

		
		Pattern p = Pattern.compile(pattern);   // the pattern to search for
	    Matcher m = p.matcher(answer.getReason());
	    
	    //System.out.println(pattern);
	    
	    return m.find();
	} 

}
