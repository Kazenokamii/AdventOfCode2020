/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kaz.aoc.day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author dhc10
 */
public class Day19 {
    public static void main(String args[]) {
        ArrayList<String> puzzleRules = new ArrayList<>();
        ArrayList<String> puzzleMessages = new ArrayList<>();
        HashMap<Integer, String> puzzleFinalRules = new HashMap<>();
        HashMap<Integer, String> puzzleRuleList = new HashMap<>();
        ArrayList<String> validMessages = new ArrayList<>();
        ArrayList<String> validMessagesFinal = new ArrayList<>();
        ArrayList<String> validMessagesReplaced = new ArrayList<>();
        Boolean changed = true;
        int previousArraySize = 0;
        int count = 0;
        int maxMessageLength = 0;
        try {
            //Read in Passport Data and separate passports based on blank lines
            //Note - Had to add in two New Lines at the end of the file to pick up last passport
            File input = new File("Day19Sample2.txt");
            try (Scanner myReader = new Scanner(input)) {
                while(myReader.hasNextLine()) {
                    try {
                        String data = myReader.nextLine();
                        if(Character.isDigit(data.charAt(0))) {
                            puzzleRules.add(data);
                        } else {
                            puzzleMessages.add(data);
                        }
                    } catch (StringIndexOutOfBoundsException e) {}
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No File Found");
        }
        for(int i = 0;i < puzzleMessages.size();i++) {
            if(puzzleMessages.get(i).length() > maxMessageLength) {
                maxMessageLength = puzzleMessages.get(i).length();
            }
        }
        for(String puzzleRule : puzzleRules) {
            if(puzzleRule.substring(puzzleRule.indexOf(":")+2, puzzleRule.indexOf(":")+3).equals("\"")) {
                puzzleRuleList.put(Integer.parseInt(puzzleRule.substring(0, puzzleRule.indexOf(":"))), puzzleRule.substring(puzzleRule.indexOf(":")+3, puzzleRule.indexOf(":")+4));
                puzzleFinalRules.put(Integer.parseInt(puzzleRule.substring(0, puzzleRule.indexOf(":"))), puzzleRule.substring(puzzleRule.indexOf(":")+3, puzzleRule.indexOf(":")+4));
            } else {
                puzzleRuleList.put(Integer.parseInt(puzzleRule.substring(0, puzzleRule.indexOf(":"))), puzzleRule.substring(puzzleRule.indexOf(":")+2));
            }
        }
        validMessages.add(puzzleRuleList.get(0));
        //while(previousArraySize != validMessages.size() || changed) {
        while(validMessages.size() > 0 || changed) {
            changed = false;
            try {
                previousArraySize = validMessages.size();
                for(String puzzleChar : validMessages) {
                    String originalString = puzzleChar;
                    String[] array = puzzleChar.split(" ");
                    String newString = "";
                    String newStringTwo = "";
                    int checkedTimes = 0;
                    for(int i = 0;i < array.length;i++) {
                        if(puzzleFinalRules.keySet().contains(Integer.parseInt(array[i]))) {
                            newString += array[i] + " ";
                            newStringTwo += array[i] + " ";
                        } else {
                            int puzzleRuleNumber = Integer.parseInt(array[i]);
                            if(puzzleRuleList.get(puzzleRuleNumber).contains(" | ")) {
                                int pipeIndex = puzzleRuleList.get(puzzleRuleNumber).indexOf("|");
                                if(checkedTimes < 1) {
                                    checkedTimes++;
                                    newString += puzzleRuleList.get(puzzleRuleNumber).substring(0, pipeIndex);
                                    newStringTwo += puzzleRuleList.get(puzzleRuleNumber).substring(pipeIndex + 2) + " ";
                                } else {
                                    newString += array[i] + " ";
                                    newStringTwo += array[i] + " ";
                                }
                            } else {
                                newString += puzzleRuleList.get(puzzleRuleNumber) + " ";
                                newStringTwo += puzzleRuleList.get(puzzleRuleNumber) + " ";
                            }
                        }
                    }
                    if(!newString.equals(newStringTwo)) {
                        validMessages.remove(puzzleChar);
                        validMessages.add(newString);
                        validMessages.add(newStringTwo);
                    } else if(!originalString.equals(newString)) {
                        validMessages.remove(puzzleChar);
                        validMessages.add(newString);
                        changed = true;
                    } else {
                        validMessages.remove(puzzleChar);
                        validMessagesFinal.add(newString);
                    }
                }
            } catch (ConcurrentModificationException e) {}
        }
        for(String oldString : validMessagesFinal) {
            String[] array = oldString.split(" ");
            String newString = "";
            for(int i = 0;i < array.length;i++) {
                newString += puzzleRuleList.get(Integer.parseInt(array[i]));
            }
            validMessagesReplaced.add(newString);
        }
        for(String puzzleMessage : puzzleMessages) {
            if(validMessagesReplaced.contains(puzzleMessage)) {
                count++;
            }
        }
        System.out.print("Puzzle Answer: " + count);
    }
}
