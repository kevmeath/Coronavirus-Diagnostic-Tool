package com.km.coronavirusDiagnosticTool;

import java.io.File;

import com.km.coronavirusDiagnosticTool.gui.Menu;

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
