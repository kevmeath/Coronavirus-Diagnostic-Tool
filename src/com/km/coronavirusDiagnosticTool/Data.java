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
			
			// Find the column headers for the data set 
			// assume that column headers are the first full row of occupied cells
			// assume that the diagnosis is in the last column
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
			// Read data and record it
			// assuming that the remainder of the file is all relevant data
			// assume that the diagnosis is in the last column
			while (scanner.hasNextLine()) {
				row = scanner.nextLine().split(",");
				if (row.length == 0) {
					// skip empty row
					continue;
				}
				String diagnosis = row[row.length - 1].toLowerCase().strip();
				if (diagnosis.equals("") || diagnosis == null) {
					// skip if there is no diagnosis
					continue;
				}
				for (int i = 0; i < row.length - 1; i++) {
					if (row[i].equals("") || row[i] == null) {
						// skip empty cell
						continue;
					}
					addSymptom(symptomNames[i].toLowerCase().strip(), row[i].toLowerCase().strip(), diagnosis);
				}
				// increment count of diagnosis
				if (diagnoses.containsKey(diagnosis)) {
					diagnoses.put(diagnosis, diagnoses.get(diagnosis) + 1);
				}
				else {
					diagnoses.put(diagnosis, 1);
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
	 * Get all possible diagnoses
	 * 
	 * @return list of all possible diagnoses
	 */
	public ArrayList<String> getDiagnoses() {
		return new ArrayList<String>(diagnoses.keySet());
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
