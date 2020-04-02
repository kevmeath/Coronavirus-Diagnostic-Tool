package com.km.coronavirusDiagnosticTool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Symptom {
	private String name;
	private HashMap<ArrayList<String>, Integer> count = new HashMap<ArrayList<String>, Integer>();
	
	/**
	 * Symptom class is used to record the count for each symptom condition and diagnosis
	 * 
	 * @param name - name of the symptom.
	 * @param key - 2 element list containing the symptom condition and the diagnosis
	 */
	public Symptom(String name, ArrayList<String> key) {
		this.name = name;
		count.put(key, 1);
	}
	
	/**
	 * Increment the count for the given symptom condition and diagnosis
	 * 
	 * @param key - 2 element list containing the symptom condition and the diagnosis
	 */
	public void addValue(ArrayList<String> key) {
		if (count.containsKey(key)) {
			count.put(key, count.get(key) + 1);
		}
		else {
			count.put(key, 1);
		}
	}
	
	/**
	 * Get the number of patients with the given condition of the symptom and the diagnosis
	 * 
	 * @param key - 2 element list containing the symptom condition and the diagnosis
	 * @return number of data entries for this condition and diagnosis combination
	 */
	public int getCount(String condition, String diagnosis) {
		ArrayList<String> key = new ArrayList<String>(Arrays.asList(condition, diagnosis));
		if (count.containsKey(key)) {
			return count.get(key);
		}
		else {
			return 0;
		}
	}
	
	/**
	 * Get the name of this symptom
	 * 
	 * @return the name of this symptom
	 */
	public String getName() {
		return name;
	}
}
