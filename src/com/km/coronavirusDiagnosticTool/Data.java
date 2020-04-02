package com.km.coronavirusDiagnosticTool;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Data {
	private ArrayList<Symptom> symptoms = new ArrayList<Symptom>();
	private HashMap<String, Integer> diagnoses = new HashMap<String, Integer>();
	
	/** 
	 * Train the machine learning model using a csv file
	 * 
	 * @param file - csv file containing the training dataset
	 */
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
						continue scan;
					}
					else if (i == row.length - 2){
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
				String diagnosis = row[row.length - 1].toLowerCase().strip();
				for (int i = 0; i < row.length - 1; i++) {
					addSymptom(symptomNames[i].toLowerCase().strip(), row[i].toLowerCase().strip(), diagnosis);
				}
				if (!diagnoses.containsKey(diagnosis)) {
					diagnoses.put(diagnosis, diagnoses.get(diagnosis) + 1);
				}
			}
			scanner.close();
		} catch (FileNotFoundException exception) {
			System.out.println("File not found");
		}
	}
	
	/**
	 * Add a symptom or condition of a symptom to the list of learned symptoms.
	 * Increment the count for the condition of this symptom if it already exists.
	 * 
	 * @param name - name of the symptom
	 * @param response - condition of the symptom
	 * @param result - diagnosis for this patient
	 */
	private void addSymptom(String name, String response, String result) {
		for (Symptom symptom : symptoms) {
			if (symptom.getName().equals(name)) {
				symptom.addValue(new ArrayList<String>(Arrays.asList(response, result)));
				return;
			}
		}
		symptoms.add(new Symptom(name, new ArrayList<String>(Arrays.asList(response, result))));
	}
	
	/**
	 * Get data for an individual symptom
	 * 
	 * @param name - name of the symptom
	 * @return Symptom
	 */
	public Symptom getSymptom(String name) {
		for (Symptom symptom : symptoms) {
			if (symptom.getName().equals(name)) {
				return symptom;
			}
		}
		return null;
	}
	
	/**
	 * Get the number of cases that resulted in the given diagnosis
	 * 
	 * @param diagnosis - the diagnosis to count
	 * @return count of the given diagnosis
	 */
	public int getCount(String diagnosis) {
		return diagnoses.get(diagnosis);
	}
	
	/**
	 * Get the total number of data entries
	 * 
	 * @return count of entries
	 */
	public int getTotal() {
		int total = 0;
		for (int count : diagnoses.values()) {
			total +=count;
		}
		return total;
	}
	
	/**
	 * Get a list the learned symptoms
	 * 
	 * @return list of symptoms
	 */
	public ArrayList<Symptom> getSymptoms() {
		return symptoms;
	}
}
