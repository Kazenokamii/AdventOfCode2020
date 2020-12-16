/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kaz.aoc.day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author dhc10
 */
public class Day15 {
    public static void main(String args[]) {
        ArrayList<String> puzzleInput = new ArrayList<>();
        HashMap<Integer, Integer> spokenValues = new HashMap<>();
        ArrayList<Integer> spokenValuesInOrder = new ArrayList<>();
        try {
            //Read in Passport Data and separate passports based on blank lines
            //Note - Had to add in two New Lines at the end of the file to pick up last passport
            File input = new File("Day15Sample.txt");
            try (Scanner myReader = new Scanner(input)) {
                while(myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    puzzleInput.add(data);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No File Found");
        }
        for (String startingValues : puzzleInput.get(0).split(",")) {
            spokenValuesInOrder.add(Integer.parseInt(startingValues));
            spokenValues.put(Integer.parseInt(startingValues),spokenValues.size());
        }
        spokenValues.remove(spokenValuesInOrder.get(spokenValuesInOrder.size()-1));
        for(int i = spokenValuesInOrder.size();i < 30000000;i++) {
            if(spokenValues.keySet().contains(spokenValuesInOrder.get(i-1))) {
                spokenValuesInOrder.add(i-spokenValues.get(spokenValuesInOrder.get(i-1))-1);
                spokenValues.replace(spokenValuesInOrder.get(i-1), i-1);
            } else {
                spokenValuesInOrder.add(0);
                spokenValues.put(spokenValuesInOrder.get(i-1), i-1);
            }
        }
        System.out.println("Puzzle Answer: " + spokenValuesInOrder.get(29999999));
    }
}
