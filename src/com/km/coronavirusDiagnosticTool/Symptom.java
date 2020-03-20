package com.km.coronavirusDiagnosticTool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Symptom {
	private String name;
	private List<String> categories = new ArrayList<String>();
	private List<String> results = new ArrayList<String>();
	private int[][] count;
	
	public Symptom(String name, String category, String result) {
		this.name = name;
		categories.add(category);
		results.add(result);
		count = new int[][] {{1}};
	}
	
	public void addCategory(String category) {
		categories.add(category);
		int[][] newCount = new int[count.length + 1][count[0].length];
		for (int i = 0; i < count.length; i++) {
			for (int j = 0; j < count[0].length; j++) {
				newCount[i][j] = count[i][j];
			}
		}
		count = newCount;
	}
	
	public void addResult(String result) {
		results.add(result);
		int[][] newCount = new int[count.length][count[0].length + 1];
		for (int i = 0; i < count.length; i++) {
			for (int j = 0; j < count[0].length; j++) {
				newCount[i][j] = count[i][j];
			}
		}
		count = newCount;
	}
	
	public void addValue(String category, String result) {
		if (!categories.contains(category)) {
			addCategory(category);
		}
		if (!results.contains(result)) {
			addResult(result);
		}
		count[categories.indexOf(category)][results.indexOf(result)]++;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		String table = name + "\n";
		for (String result : results) {
			table = table + "\t" + result;
		}
		for (int i = 0; i < count.length; i++) {
			table = table + "\n" + categories.get(i) + "\t";
			for (int j = 0; j < count[0].length; j++) {
				table = table + count[i][j] + "\t";
			}
		}
		return table;
	}
}
