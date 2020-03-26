package com.km.coronavirusDiagnosticTool;

import java.io.File;

import com.km.coronavirusDiagnosticTool.gui.Menu;

public class Main {	
	public static void main(String[] args) {
		//Menu menu = new Menu();
		
		Data data = new Data();
		data.train(new File("MLdata.csv"));
	}
}
