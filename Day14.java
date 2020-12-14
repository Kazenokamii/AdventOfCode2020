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
public class Day14 {
    public static void main(String args[]) {
        ArrayList<String> puzzleInput = new ArrayList<>();
        String bitmask;
        int beginIndex;
        int endIndex;
        String binaryValueBeforeBitMask;
        String binaryValue = "000000000000000000000000000000000000";
        Map<Long, Long> memValues = new HashMap<Long, Long>();
        String binaryValueAfterMask;
        Long valueAfterMask;
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
                    binaryValueAfterMask = "";
                    beginIndex = puzzleInput.get(i).indexOf("[")+1;
                    endIndex = puzzleInput.get(i).indexOf("]");
                    long memLocation = Long.parseLong(puzzleInput.get(i).substring(beginIndex, endIndex));
                    long valueToWrite = Long.parseLong(puzzleInput.get(i).substring(puzzleInput.get(i).indexOf("=")+2));
                    binaryValueBeforeBitMask = Long.toBinaryString(valueToWrite);
                    binaryValueBeforeBitMask = binaryValue.substring(binaryValueBeforeBitMask.length()) + binaryValueBeforeBitMask;
                    for(int j = 0;j < binaryValueBeforeBitMask.length();j++) {
                        if(bitmask.charAt(j) == 'X') {
                            binaryValueAfterMask = binaryValueAfterMask + Character.toString(binaryValueBeforeBitMask.charAt(j));
                        } else {
                            binaryValueAfterMask = binaryValueAfterMask + Character.toString(bitmask.charAt(j));
                        }
                    }
                    valueAfterMask = Long.parseLong(binaryValueAfterMask, 2);
                    if(memValues.keySet().contains(memLocation)) {
                        memValues.replace(memLocation, valueAfterMask);
                    } else {
                        memValues.put(memLocation, valueAfterMask);
                    }
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
}
