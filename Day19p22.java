/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kaz.aoc.day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author dhc10
 */
public class Day19p22 {
    public static void main(String args[]) {
        ArrayList<String> puzzleRules = new ArrayList<>();
        ArrayList<String> puzzleMessages = new ArrayList<>();
        HashMap<Integer, String> puzzleFinalRules = new HashMap<>();
        HashMap<Integer, String> puzzleRuleList = new HashMap<>();
        ArrayList<String> validMessages = new ArrayList<>();
        ArrayList<String> validMessagesFinal = new ArrayList<>();
        HashMap<Integer, ArrayList<String>> puzzleSubLists = new HashMap<>();
        int count = 0;
        int maxMessageLength = 0;
        try {
            //Read in Passport Data and separate passports based on blank lines
            //Note - Had to add in two New Lines at the end of the file to pick up last passport
            File input = new File("Day19Full.txt");
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
        for(int i = 0;i < maxMessageLength;i++) {
            ArrayList<String> messagesToAdd = new ArrayList<>();
            for(String puzzleMessage : puzzleMessages) {
                if(puzzleMessage.length() > i) {
                    messagesToAdd.add(puzzleMessage.substring(0,i+1));
                }
            }
            puzzleSubLists.put(i+1, messagesToAdd);
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
        while(validMessages.size() > 0) {
            try {
                for(String puzzleChar : validMessages) {
                    String originalString = puzzleChar;
                    String[] array = puzzleChar.split(" ");
                    String newString = "";
                    String newStringTwo = "";
                    int checkedTimes = 0;
                    int indexOfFirstNonFinal = 0;
                    if(array[0].length() > maxMessageLength) {
                        validMessages.remove(puzzleChar);
                        break;
                    } else if(Character.isAlphabetic(array[0].charAt(0))){
                        if(!puzzleSubLists.get(array[0].length()).contains(array[0])) {
                            validMessages.remove(puzzleChar);
                            break;
                        }
                    }
                    for(int i = 0;i < array.length;i++) {
                        if(Character.isAlphabetic(array[i].charAt(0))) {
                            newString += array[i];
                            newStringTwo += array[i];
                            i++;
                            if(array.length == 1) {
                                indexOfFirstNonFinal = i+1;
                                break;
                            }
                        }
                        if(!puzzleFinalRules.keySet().contains(Integer.parseInt(array[i]))) {
                            indexOfFirstNonFinal = i;
                            if(i != 0) {
                                newString += " ";
                                newStringTwo += " ";
                            }
                            break;
                        } else {
                            newString += puzzleRuleList.get(Integer.parseInt(array[i]));
                            newStringTwo += puzzleRuleList.get(Integer.parseInt(array[i]));
                        }
                        if(i == array.length-1) {
                            indexOfFirstNonFinal = i+1;
                        }
                    }
                    for(int i = indexOfFirstNonFinal;i < array.length;i++) {
                        if(array[i].equals("")) {
                        } else if(puzzleFinalRules.keySet().contains(Integer.parseInt(array[i]))) {
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
                    } else {       
                        validMessages.remove(puzzleChar);
                        validMessagesFinal.add(newString);
                    }
                }
            } catch (ConcurrentModificationException e) {}
        }
//        for(String oldString : validMessagesFinal) {
//            String[] array = oldString.split(" ");
//            String newString = "";
//            for(int i = 0;i < array.length;i++) {
//                newString += puzzleRuleList.get(Integer.parseInt(array[i]));
//            }
//            validMessagesReplaced.add(newString);
//        }
        for(String puzzleMessage : puzzleMessages) {
            if(validMessagesFinal.contains(puzzleMessage)) {
                count++;
            }
        }
        System.out.print("Puzzle Answer: " + count);
    }
}
