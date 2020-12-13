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
import java.util.Scanner;

/**
 *
 * @author dhc10
 */
public class Day13 {
    public static void main(String args[]) {
        ArrayList<String> puzzleInput = new ArrayList<>();
        String[] busInitialDepartures = new String[]{};
        ArrayList<Integer> busFinalDepartures = new ArrayList<>();        
        ArrayList<Integer> busInitialIntegers = new ArrayList<>();
        try {
            //Read in Passport Data and separate passports based on blank lines
            //Note - Had to add in two New Lines at the end of the file to pick up last passport
            File input = new File("Day13Sample.txt");
            try (Scanner myReader = new Scanner(input)) {
                while(myReader.hasNextLine()) {
                        String data = myReader.nextLine();
                    puzzleInput.add(data);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No File Found");
        }
        busInitialDepartures = puzzleInput.get(1).split(",");
        for (int i = 0;i < busInitialDepartures.length;i++) {
            if(!busInitialDepartures[i].equals("x")) {
                busFinalDepartures.add(Integer.parseInt(busInitialDepartures[i]));
                busInitialIntegers.add(Integer.parseInt(busInitialDepartures[i]));
            } 
        }
        for(int i = 0;i < busFinalDepartures.size();i++) {
            int k = 1;
            while(busFinalDepartures.get(i) < Integer.parseInt(puzzleInput.get(0))) {
                k++;
                busFinalDepartures.set(i, busInitialIntegers.get(i)*k);
            }
        }
        int firstBusDeparture = Collections.min(busFinalDepartures);
        int lowestIndex = 0;
        for(int i = 0;i < busFinalDepartures.size();i++) {
            if(busFinalDepartures.get(i) == firstBusDeparture) {
                lowestIndex = i;
                break;
            }
        }
        int busId = busInitialIntegers.get(lowestIndex);
        int timeToWait = firstBusDeparture - Integer.parseInt(puzzleInput.get(0));
        System.out.println("First Available Departure: " + firstBusDeparture);
        System.out.println("Puzzle Answer: " + (busId*timeToWait));
    }
}
