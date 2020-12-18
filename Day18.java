/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kaz.aoc.day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 *
 * @author dhc10
 */
public class Day18 {
    public static void main(String args[]) {
        ArrayList<String> puzzleInput = new ArrayList<>();
        try {
            //Read in Passport Data and separate passports based on blank lines
            //Note - Had to add in two New Lines at the end of the file to pick up last passport
            File input = new File("Day18Full.txt");
            try (Scanner myReader = new Scanner(input)) {
                while(myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    puzzleInput.add(data);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No File Found");
        }
        long evalTotalString = 0;
        for(String mathEquation : puzzleInput) {
            evalTotalString += Long.parseLong(doBadMath(mathEquation));
        }
        System.out.println("Puzzle Answer: " + evalTotalString);
    }

    public static String doBadMath(String mathEquation) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        String partialEvalString = "";
        int timeToEvaluate = 0;
        for(int i = 0;i < mathEquation.length();i++) {
            if(Character.isDigit(mathEquation.charAt(i))) {
                partialEvalString += mathEquation.charAt(i);
                timeToEvaluate++;
            } else if(Character.compare(mathEquation.charAt(i), '(') == 0) {
                i++;
                partialEvalString += doBadMath(mathEquation.substring(i));
                while(!(Character.compare(mathEquation.charAt(i), ')') == 0)) {
                    if(Character.compare(mathEquation.charAt(i), '(') == 0) { 
                        while(!(Character.compare(mathEquation.charAt(i), ')') == 0)) {
                            i++;
                        }
                    }
                    i++;
                }
                timeToEvaluate++;
            } else if(Character.compare(mathEquation.charAt(i), ')') == 0) {
                return partialEvalString;
            } else if(Character.isSpaceChar(mathEquation.charAt(i))) {
                partialEvalString += mathEquation.charAt(i);
            } else if(Character.compare(mathEquation.charAt(i), '*') == 0) {
                partialEvalString += " * ";
                i += 2;
                partialEvalString += doBadMath(mathEquation.substring(i));
                break;
            }else {
                partialEvalString += mathEquation.charAt(i);
                timeToEvaluate++;
            }
            if(timeToEvaluate == 3) {
                try {
                    partialEvalString = (String) engine.eval(partialEvalString).toString();
                } catch (ScriptException ex) {
                    Logger.getLogger(Day18.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NumberFormatException ex) {
                    System.out.println(partialEvalString + " is too large to compute.");
                }
                timeToEvaluate = 1;
                if(partialEvalString.contains("" + 'E')) {
                    BigDecimal tempEvalString = new BigDecimal(partialEvalString);
                    partialEvalString = tempEvalString.toPlainString();
                }
            }
        }
        try {
            partialEvalString = (String) engine.eval(partialEvalString).toString();
        } catch (ScriptException ex) {
            Logger.getLogger(Day18.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException ex) {
            System.out.println(partialEvalString + " is too large to compute.");
        }
        if(partialEvalString.contains("" + 'E')) {
            BigDecimal tempEvalString = new BigDecimal(partialEvalString);
            partialEvalString = tempEvalString.toPlainString();
        }
        return partialEvalString;
    }
}
