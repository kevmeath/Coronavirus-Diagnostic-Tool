package com.km.coronavirusDiagnosticTool;

public class NaiveBayes {
	
	/**
	 * Get the probability of covid-19 with the given conditions
	 * 
	 * @param data - trained data
	 * @param conditions - array of conditions to test
	 * @param diagnosis - the result to test for
	 * @return the probability that a patient with the given conditions has covid-19
	 */
	public static double getProbability(Data data, String[] conditions, String diagnosis) {
		double sumProporional = 0;
		for (String diagnosis1 : data.getDiagnoses()) {
			sumProporional += getProportionalProbability(data, conditions, diagnosis1);
		}
		return getProportionalProbability(data, conditions, diagnosis) / sumProporional;
	}
	
	/**
	 * Get the proportional probability of covid-19 with the given conditions
	 * 
	 * @param data - trained data
	 * @param conditions - array of conditions to test for
	 * @param diagnosis - the result to test for
	 * @return proportional probability
	 */
	private static double getProportionalProbability(Data data, String[] conditions, String diagnosis) {
		double probability = 0;
		for (int i = 0; i < conditions.length - 1; i++) {
			Symptom symptom = data.getSymptoms().get(i);
			int count = symptom.getCount(conditions[i], diagnosis);
			int total = data.getCount(diagnosis);
			
			probability = probability > 0 ? probability * ((double)count / (double)total) : ((double)count / (double)total);
		}
		probability *= (double) data.getCount(diagnosis) / (double) data.getTotal();
		return probability;
	}
}
