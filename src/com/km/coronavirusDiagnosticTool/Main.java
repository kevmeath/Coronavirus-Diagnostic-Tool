package com.km.coronavirusDiagnosticTool;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Coronavirus Diagnostic Tool uses machine learning to determine
 * the probability of coronavirus given a patient's symptoms.
 * 
 * @author Kevin Meath
 *
 */
public class Main {	
	public static void main(String[] args) {		
		Data data = new Data();
		data.train(new File("MLdata.csv"));
	}
}
