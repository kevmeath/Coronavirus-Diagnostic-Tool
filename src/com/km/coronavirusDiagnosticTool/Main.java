package com.km.coronavirusDiagnosticTool;

public class Main {	
	public static void main(String[] args) {
		Data data = new Data("MLdata.csv");
		System.out.println(data.getSymptom("Tempeature")+"\n");
		System.out.println(data.getSymptom("Aches")+"\n");
		System.out.println(data.getSymptom("Cough")+"\n");
		System.out.println(data.getSymptom("Sore Throat")+"\n");
		System.out.println(data.getSymptom("Recently Travelled from Danger Zone?")+"\n");
	}
}
