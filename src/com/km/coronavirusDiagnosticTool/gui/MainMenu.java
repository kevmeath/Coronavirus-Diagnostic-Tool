package com.km.coronavirusDiagnosticTool.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.km.coronavirusDiagnosticTool.Data;
import com.km.coronavirusDiagnosticTool.NaiveBayes;
import com.km.coronavirusDiagnosticTool.Symptom;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenu extends JFrame implements ActionListener {
	private Data data = new Data();
	private GridBagConstraints gbc = new GridBagConstraints();
	private JLabel instruction;
	private JPanel inputPanel;
	private JButton train, predict;
	private JComboBox[] options;
	
	
	public MainMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 300);
		setTitle("Coronavirus Diagnostic Tool");
		setLayout(new GridBagLayout());
		
		instruction = new JLabel("<html><div style='text-align:center'>Use a comma delimited csv table to<br>train the machine learning model<html>");
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(2, 0, 0, 0);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		add(instruction, gbc);
		
		inputPanel = new JPanel(new GridLayout(0, 2));
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.weighty = 1;
		add(inputPanel, gbc);
		
		train = new JButton("Train");
		train.setPreferredSize(new Dimension(100, 26));
		train.addActionListener(this);
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 0, 4, 2);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 0.5;
		gbc.anchor = GridBagConstraints.SOUTHEAST;
		add(train, gbc);
		
		predict = new JButton("Predict");
		predict.setPreferredSize(new Dimension(100, 26));
		predict.addActionListener(this);
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 2, 4, 0);
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.weightx = 0.5;
		gbc.anchor = GridBagConstraints.SOUTHWEST;
		add(predict, gbc);
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	/**
	 * Update the panel to add any new symptoms or input options
	 */
	private void updateInputPanel() {
		inputPanel.removeAll();
		int rows = data.getSymptoms().size();
		inputPanel.setLayout(new GridBagLayout());
		
		GridBagConstraints gbcLabel = new GridBagConstraints();
		gbcLabel.insets = new Insets(2, 2, 2, 2);
		gbcLabel.gridx = 0;
		gbcLabel.weightx = 0.6;
		gbcLabel.anchor = GridBagConstraints.WEST;

		GridBagConstraints gbcInput = new GridBagConstraints();
		gbcInput.insets = new Insets(2, 2, 2, 2);
		gbcInput.gridx = 1;
		gbcInput.weightx = 0.4;
		gbcInput.anchor = GridBagConstraints.WEST;
		gbcInput.fill = GridBagConstraints.HORIZONTAL;
		
		options = new JComboBox[rows];
		
		// Add all symptoms and options to the panel
		for (int i = 0; i < rows; i++) {
			Symptom symptom = data.getSymptoms().get(i);
			options[i] = new JComboBox(symptom.getConditions().toArray());
			
			gbcLabel.gridy = i + 1;
			inputPanel.add(new JLabel(symptom.getName()), gbcLabel);
			
			gbcInput.gridy = i + 1;
			inputPanel.add(options[i], gbcInput);
		}

		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		add(inputPanel, gbc);
		
		if (options.length > 0) {
			instruction.setText("Enter symptoms");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == train) {
			JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
			int option = fileChooser.showOpenDialog(this);
			if (option == JFileChooser.APPROVE_OPTION) {
				data.train(fileChooser.getSelectedFile());
			}
			updateInputPanel();
			revalidate();
		}
		else if (e.getSource() == predict) {
			if (options == null) {
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(this, "No data", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			String[] conditions = new String[options.length];
			for (int i = 0; i < options.length; i++) {
				conditions[i] = (String) options[i].getSelectedItem();
			}
			double probability = NaiveBayes.getProbability(data, conditions);
			JOptionPane.showMessageDialog(this, String.format("%.2f%% chance of COVID-19.", probability * 100));
		}
	}
}
