package com.km.coronavirusDiagnosticTool;

import java.util.ArrayList;
import java.util.HashMap;

public class Symptom {
	private String name;
	private HashMap<ArrayList<String>, Integer> count = new HashMap<ArrayList<String>, Integer>();
	
	public Symptom(String name, ArrayList<String> key) {
		this.name = name;
		count.put(key, 1);
	}
	
	public void addValue(ArrayList<String> key) {
		if (count.containsKey(key)) {
			count.put(key, count.get(key) + 1);
		}
		else {
			count.put(key, 1);
		}
	}
	
	public int getCount(ArrayList<String> key) {
		return count.get(key);
	}
	
	public String getName() {
		return name;
	}
}
