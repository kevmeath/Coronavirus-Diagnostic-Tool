package com.km.coronavirusDiagnosticTool;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Data {
	private ArrayList<Symptom> symptoms = new ArrayList<Symptom>();
	
	public void train(File file) {
		try {
			Scanner scanner = new Scanner(file);
			String[] symptomNames = new String[]{};
			String[] row;
			scan:
			while (scanner.hasNextLine()) {
				row = scanner.nextLine().split(",");
				for (int i = 0; i < row.length - 1; i++) {
					if (row[i].strip().equals("")) {
						//empty cell
						continue scan;
					}
					else if (i == row.length - 2){
						//row of column headers found
						symptomNames = row;
						break scan;
					}
				}
			}
			while (scanner.hasNextLine()) {
				row = scanner.nextLine().split(",");
				if (row.length == 0) {
					continue;
				}
				for (int i = 0; i < row.length - 1; i++) {
					addSymptom(symptomNames[i].toLowerCase().strip(), row[i].toLowerCase().strip(), row[row.length - 1].toLowerCase().strip());
				}
				//addDiagnosis(row[row.length - 1].toLowerCase().strip());
			}
			scanner.close();
		} catch (FileNotFoundException exception) {
			System.out.println("File not found");
		}
	}
	
	private void addSymptom(String name, String response, String result) {
		
		for (Symptom symptom : symptoms) {
			if (symptom.getName().equals(name)) {
				symptom.addValue(new ArrayList<String>(Arrays.asList(response, result)));
				return;
			}
		}
		symptoms.add(new Symptom(name, new ArrayList<String>(Arrays.asList(response, result))));
	}
	
	public ArrayList<Symptom> getSymptoms() {
		return symptoms;
	}
}
