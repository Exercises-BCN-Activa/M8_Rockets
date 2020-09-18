package com.application;

import javax.swing.JOptionPane;

final class Utilities {
	
	Utilities() {}
	
	/**
	 * Method that invokes JOptionPane Class to jump a window 
	 * asking the user to enter information and return a string.
	 * This method does not allow the user to enter an empty input.
	 *  
	 * @param msg that tells the user what to insert
	 * @param option that define which regex will be used 
	 * to check user input (0 = Rocket Code | 1 = Amount of Propellant | 2 = Max Power)
	 * @return string upper case
	 */
	public static String input3cases(String msg, int option) {
		String userInput = "", msgError, regexCase;
		
		switch (option) {
		case 0:
			regexCase = "(?=^.{8,8}$)(?=.*\\d)(?=.*\\w).*$";
			msgError = "\neight alphanumeric characters";
			break;
		case 1:
			regexCase = "[1-9]{1}[0-9]*";
			msgError = "\ninteger number, at least one";
			break;
		case 2:
			regexCase = "[1-9]{1}[0-9]*";
			msgError = "\nvalue greater than one";
			break;
		default:
			regexCase = "[A-zÀ-ÿ]*";
			msgError = "\nonly letters";
			break;
		}
		
		try {
			
			while (!userInput.matches(regexCase)) { 
				userInput = JOptionPane.showInputDialog(msg)
						.trim().toUpperCase();
				if (!userInput.matches(regexCase)) {
					msg=msg+msgError;
				}
			}
			
		} catch (Exception e) {
			System.out.println("Inputing canceled by user");
			userInput = "NulL";
		}
		
		return userInput; 
	}

}