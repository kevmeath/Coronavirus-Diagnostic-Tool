package com.km.coronavirusDiagnosticTool;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Data {
	private Map<String, Symptom> symptoms = new HashMap<String, Symptom>();
	
	public Data(String filePath) {
		addData(new File(filePath));
	}
	
	public void addData(File file) {
		String[] symptomNames = new String[] {};
		try {
			Scanner scanner = new Scanner(file);
			String[] row;
			scan:
			while (scanner.hasNextLine()) {
				row = scanner.nextLine().split(",");
				for (int i = 0; i < row.length; i++) {
					if (row[i].strip().equals("")) {
						//empty cell
						continue scan;
					}
					else if (i == row.length - 1){
						//row of column headers found
						symptomNames = row;
						break scan;
					}
				}
			}
			while (scanner.hasNextLine()) {
				row = scanner.nextLine().split(",");
				for (int i = 0; i < row.length - 1; i++) {
					addSymptom(symptomNames[i].toLowerCase().strip(), row[i].toLowerCase().strip(), row[row.length - 1].toLowerCase().strip());
				}
			}
			scanner.close();
		} catch (FileNotFoundException exception) {
			System.out.println("File not found");
		}
	}
	
	public void addSymptom(String name, String category, String result) {	
		if (symptoms.containsKey(name)) {
			symptoms.get(name).addValue(category, result);
		}
		else {
			symptoms.put(name, new Symptom(name, category, result));
		}
	}
	
	public Symptom getSymptom(String name) {
		return symptoms.get(name.toLowerCase());
	}
}
