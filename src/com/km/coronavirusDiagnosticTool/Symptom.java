package com.km.coronavirusDiagnosticTool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Symptom {
	private String name;
	private HashMap<ArrayList<String>, Integer> count = new HashMap<ArrayList<String>, Integer>();
	
	/**
	 * Initialize with a name and symptom condition
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
	
	/**
	 * Get the recorded conditions for this symptom
	 * 
	 * @return string list of conditions
	 */
	public ArrayList<String> getConditions() {
		ArrayList<String> conditions = new ArrayList<String>();
		for (ArrayList<String> key : count.keySet()) {
			if (conditions.contains(key.get(0))) {
				continue;
			}
			else {
				conditions.add(key.get(0));
			}
		}
		return conditions;
	}
}
