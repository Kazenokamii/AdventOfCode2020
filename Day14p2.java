/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kaz.aoc.day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author dhc10
 */
public class Day14p2 {
    public static Map<Long, Long> memValues = new HashMap<>();
    public static void main(String args[]) {
        ArrayList<String> puzzleInput = new ArrayList<>();
        String bitmask;
        int beginIndex;
        int endIndex;
        String memLocationBeforeMask;
        String binaryValue = "000000000000000000000000000000000000";
        String memLocationAfterMask;
        Long memValueAfterMask;
        Long totalSumOfMemoryValues = new Long(0);
        try {
            //Read in Passport Data and separate passports based on blank lines
            //Note - Had to add in two New Lines at the end of the file to pick up last passport
            File input = new File("Day14Full.txt");
            try (Scanner myReader = new Scanner(input)) {
                while(myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    puzzleInput.add(data);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No File Found");
        }
        for(int i = 0;i < puzzleInput.size();) {
            bitmask = puzzleInput.get(i).substring(7);
            i++;
            try {
                while(puzzleInput.get(i).substring(0,3).equals("mem")) {
                    memLocationAfterMask = "";
                    beginIndex = puzzleInput.get(i).indexOf("[")+1;
                    endIndex = puzzleInput.get(i).indexOf("]");
                    long memLocation = Long.parseLong(puzzleInput.get(i).substring(beginIndex, endIndex));
                    long valueToWrite = Long.parseLong(puzzleInput.get(i).substring(puzzleInput.get(i).indexOf("=")+2));
                    memLocationBeforeMask = Long.toBinaryString(memLocation);
                    memLocationBeforeMask = binaryValue.substring(memLocationBeforeMask.length()) + memLocationBeforeMask;
                    for(int j = 0;j < memLocationBeforeMask.length();j++) {
                        if(bitmask.charAt(j) == '0') {
                            memLocationAfterMask = memLocationAfterMask + Character.toString(memLocationBeforeMask.charAt(j));
                        } else {
                            memLocationAfterMask = memLocationAfterMask + Character.toString(bitmask.charAt(j));
                        }
                    }
                    char[] memLocationCharArray = memLocationAfterMask.toCharArray();
                    placeMemoryValues(memLocationCharArray, valueToWrite);
                    i++;
                }
            } catch (IndexOutOfBoundsException e) {
                continue;
            }
        }
        for (Long memValue : memValues.values()) {
            totalSumOfMemoryValues+=memValue;
        }
        System.out.println("Puzzle Answer: " + totalSumOfMemoryValues);
    }
    
    public static void placeMemoryValues(char[] memLocationCharArray, Long valueToWrite) {
        char[] memLocationToPass = memLocationCharArray.clone();
        for(int i = 0;i < memLocationCharArray.length;i++) {
            if(memLocationToPass[i] == 'X') {
                memLocationToPass[i] = '0';
                placeMemoryValues(memLocationToPass, valueToWrite);
                memLocationToPass[i] = '1';
                placeMemoryValues(memLocationToPass, valueToWrite);
            }
        }
        String memoryLocation = String.valueOf(memLocationToPass);
        long memoryLocationDecimal = Long.parseLong(memoryLocation, 2);
        if(memValues.keySet().contains(memoryLocation)) {
            memValues.replace(memoryLocationDecimal, valueToWrite);
        } else {
            memValues.put(memoryLocationDecimal, valueToWrite);
        }
    }
}
