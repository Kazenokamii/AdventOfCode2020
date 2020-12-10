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
public class Day10 {
    public static void main(String args[]) {
        ArrayList<Long> puzzleInput = new ArrayList<>();
        Map<Long, Long> breakPoints = new HashMap();
        long totalNumberOfBranches = 1;
        long previousKey = new Long(0);
        long ones = 0;
        long twos = 0;
        long threes = 0;
        puzzleInput.add(new Long(0));
        try {
            //Read in Passport Data and separate passports based on blank lines
            //Note - Had to add in two New Lines at the end of the file to pick up last passport
            File input = new File("Day10Full.txt");
            try (Scanner myReader = new Scanner(input)) {
                while(myReader.hasNextLine()) {
                        long data = Long.parseLong(myReader.nextLine());
                    puzzleInput.add(data);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No File Found");
        }
        Collections.sort(puzzleInput);
        for (int i = 0; i < puzzleInput.size()-1; i++) {
            if((puzzleInput.get(i+1) - puzzleInput.get(i)) == 1) {
                ones++;
            } else if ((puzzleInput.get(i+1) - puzzleInput.get(i)) == 2) {
                twos++;
            } else {
                threes++;
            }
        }
        threes++;
        System.out.println("Device Value: " + (puzzleInput.get(puzzleInput.size()-1) + 3));
        System.out.println("Number of One Differences: " + ones);
        System.out.println("Number of Two Differences: " + twos);
        System.out.println("Number of Three Differences: " + threes);
        System.out.println("Puzzle Answer: " + (threes * ones));
        Collections.reverse(puzzleInput);
        for (int i = 0; i < puzzleInput.size(); i++) {
            int numberOfBranches = 0;
            try {
                if((puzzleInput.get(i-1) - puzzleInput.get(i)) < 4) {
                    numberOfBranches++;
                }
                if((puzzleInput.get(i-2) - puzzleInput.get(i)) < 4) {
                    numberOfBranches++;
                }
                if((puzzleInput.get(i-3) - puzzleInput.get(i)) < 4) {
                    numberOfBranches++;
                }
            } catch (IndexOutOfBoundsException e) {
                if(numberOfBranches == 0) {
                    numberOfBranches++;
                }
            }
            if(numberOfBranches == 1) {
                breakPoints.put(puzzleInput.get(i), totalNumberOfBranches);
            }
            for(int j = 2; j <= numberOfBranches; j++) {
                if(breakPoints.keySet().contains(puzzleInput.get(i-j))) {
                    totalNumberOfBranches+=breakPoints.get(puzzleInput.get(i-j));
                }
            }
            breakPoints.put(puzzleInput.get(i), totalNumberOfBranches);
        }
        System.out.println("Total Number of Branches: " + totalNumberOfBranches);
    }
}
