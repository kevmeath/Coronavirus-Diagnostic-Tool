package com.km.coronavirusDiagnosticTool;

public class NaiveBayes {
	private Data data;
	
	public NaiveBayes(Data data) {
		this.data = data;
	}
	
	public double getDiagnosis(String[] conditions, String diagnosis) {
		return 0;
	}
	
	public double getProbability(String[] conditions, String diagnosis) {
		return 0;
	}
	
	private double getProportionalProbability(String[] conditions, String diagnosis) {
		double probability = 0;
		for (int i = 0; i < conditions.length; i++) {
			Symptom symptom = data.getSymptoms().get(i);
			int count = symptom.getCount(conditions[i], diagnosis);
			int total = data.getTotal();
			
			probability *= (count / total);
		}
		probability *= data.getCount(diagnosis) / data.getTotal();
		return probability;
	}
}
