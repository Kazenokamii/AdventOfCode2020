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
public class Day13p2 {
    public static void main(String args[]) {
        ArrayList<String> puzzleInput = new ArrayList<>();
        String[] busInitialDepartures = new String[]{};
        ArrayList<Long> busFinalDepartures = new ArrayList<>();        
        ArrayList<Integer> busInitialIntegers = new ArrayList<>();
        try {
            //Read in Passport Data and separate passports based on blank lines
            //Note - Had to add in two New Lines at the end of the file to pick up last passport
            File input = new File("Day13Full.txt");
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
        for (String busInitialDeparture : busInitialDepartures) {
            if (!busInitialDeparture.equals("x")) {
                busFinalDepartures.add(Long.parseLong(busInitialDeparture));
                busInitialIntegers.add(Integer.parseInt(busInitialDeparture));
            } else {
                busFinalDepartures.add(new Long(0));
                busInitialIntegers.add(0);
            } 
        }
        long lcm = busInitialIntegers.get(0);
        for(int i = 0;i < busFinalDepartures.size()-1;i++) {
            int index = 1;
            while(busFinalDepartures.get(i) == 0) {
                i++;
            }
            while(busFinalDepartures.get(i+index) == 0) {
                index++;
            }
            busFinalDepartures.set(i+index, busFinalDepartures.get(i)+index);
            while(busFinalDepartures.get(i+index) % busInitialIntegers.get(i+index) != 0) {
                busFinalDepartures.set(i, busFinalDepartures.get(i)+lcm);
                busFinalDepartures.set(i+index, busFinalDepartures.get(i)+index);
            }
            System.out.println("First Timestamp: " + (busFinalDepartures.get(i+index)-(busFinalDepartures.size()-1)));
            if(index+i < busInitialIntegers.size() - 1) {
                lcm = Math.abs(lcm*busInitialIntegers.get(i+index))/gcd(lcm, busInitialIntegers.get(i+index));
            } else {
                break;
            }
        }
    }
    
    public static long gcd(long num1, long num2) {
        while (num1 != num2) {
            if(num1 > num2)
                num1 = num1 - num2;
            else
                num2 = num2 - num1;
        }
        return num2;
    }
}
