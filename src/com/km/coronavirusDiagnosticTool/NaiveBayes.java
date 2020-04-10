package com.km.coronavirusDiagnosticTool;

public class NaiveBayes {

	/**
	 * Get the probability of covid-19 with the given conditions
	 * 
	 * @param data - trained data
	 * @param conditions - array of conditions to test
	 * @return the probability that a patient with the given conditions has the diagnosis to test
	 */
	public static double getProbability(Data data, String[] conditions) {
		double sumProporional = 0;
		
		// get the proportional probability for each possible diagnosis
		for (String diagnosis : data.getDiagnoses()) {
			sumProporional += getProportionalProbability(data, conditions, diagnosis);
		}
		return getProportionalProbability(data, conditions, "yes") / sumProporional;
	}
	
	/**
	 * Get the proportional probability of covid-19 with the given conditions
	 * 
	 * @param data - trained data
	 * @param conditions - array of conditions to test for
	 * @param diagnosis - the diagnosis to test for
	 * @return proportional probability
	 */
	private static double getProportionalProbability(Data data, String[] conditions, String diagnosis) {
		double probability = 0;
		
		// calculate the probability of each symptom condition and multiply them
		for (int i = 0; i < conditions.length; i++) {
			Symptom symptom = data.getSymptoms().get(i);
			int count = symptom.getCount(conditions[i], diagnosis);
			int total = data.getCount(diagnosis);
			
			probability = probability > 0 ? probability * ((double)count / (double)total) : ((double)count / (double)total);
		}
		// multiply product of probabilities by the overall probability of the given diagnosis
		return probability *= (double) data.getCount(diagnosis) / (double) data.getTotal();
	}
}