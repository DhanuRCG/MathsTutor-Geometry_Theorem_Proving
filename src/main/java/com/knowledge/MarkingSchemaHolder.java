package com.knowledge;

public class MarkingSchemaHolder {
	/*List<com.markingschema.Step> schemaSteps;
	List<GeoRelation> schemaRelation;
	private int currentPosition;
	private CheckingReason nlpengine;

	public MarkingSchemaHolder() {
		super();
		// TODO Auto-generated constructor stub
		MarkingSchema markingSchema = new MarkingSchema();

		String path = "src/main/java/com/markingschema/MarkingScheme.xml";
		System.out.println("Creating Marking schema ......");
		
		// Document doc=markingSchema.readXml(path);
		AnswerScript answerscript = markingSchema.MarkingSchemaXmlMaper(path);

		AnswerToGeoRelation answerToGeoRelation = new AnswerToGeoRelation();

		schemaSteps = answerscript.getQuestion().get(0).getSubQuestion().get(0).getMarkSet().getStep();
		schemaRelation = answerToGeoRelation.getRelationStep(answerscript);
		currentPosition = 0;
		nlpengine = new CheckingReason();
		System.out.println("Marking schema Created ......");

	}

	public GeoRelation getNextMarkingSchemaStep() {

		if (endOfAnswer())
			return null;

		return schemaRelation.get(currentPosition++);

	}

	public boolean endOfAnswer() {

		return currentPosition >= schemaRelation.size();

	}

	public void initPostion() {
		currentPosition = 0;
	}

	public List<com.markingschema.Step> getSchemaSteps() {
		return schemaSteps;
	}

	public void setSchemaSteps(List<com.markingschema.Step> schemaSteps) {
		this.schemaSteps = schemaSteps;
	}

	public List<GeoRelation> getSchemaRelation() {
		return schemaRelation;
	}

	public void setSchemaRelation(List<GeoRelation> schemaRelation) {
		this.schemaRelation = schemaRelation;
	}

	public void giveMarkStep(GeoRelation step) {
		// TODO Auto-generated method stub

		initPostion();

		while (!endOfAnswer()) {
			// System.out.println(currentPosition);
			if (sameStepIdentifire(step, getNextMarkingSchemaStep())) {

				//step.setMarkforrelation(schemaSteps.get(currentPosition - 1).getExpression().getMark());
				//System.out.println(step.getStudentReason() + " student reason");
				String studentreason = step.getStudentReason();
				//System.out.println(currentPosition);
				String schemareason = null;
				if(schemaSteps.get(currentPosition - 1).getReason()!=null){
					schemareason = schemaSteps.get(currentPosition - 1).getReason().getAnswer();
				}
				if (schemareason != null) {
					if (nlpengine.isCorrectStudentAnswer(studentreason, schemareason)) {
						step.setMarkforreason(schemaSteps.get(currentPosition - 1).getReason().getMark());
					}
				}

			}
		}

	}

	public boolean sameStepIdentifire(GeoRelation step1, GeoRelation step2) {
		boolean same = false;
		if (step1.firstItem.sameItem(step2.firstItem) && step1.firstItem.sameItem(step2.secondItem)) {
			same = true;
		} else if (step1.firstItem.sameItem(step2.secondItem) && step1.firstItem.sameItem(step2.firstItem)) {
			same = true;
		} else {

		}
		return true;
	}

	
*/
}
