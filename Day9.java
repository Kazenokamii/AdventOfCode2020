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
import java.util.Scanner;

/**
 *
 * @author dhc10
 */
public class Day9 {
    public static void main(String args[]) {
        ArrayList<Long> puzzleInput = new ArrayList<>();
        int preamble = 25;
        int nextNumber = preamble + 1;
        Boolean nextNumberIsValid = true;
        int loops = 0;
        long numberToPass;
        ArrayList<Long> listToPass = new ArrayList<>();
        Boolean contiguousSetFound = false;
        ArrayList<Long> numbersToAdd = new ArrayList<>();
        try {
            //Read in Passport Data and separate passports based on blank lines
            //Note - Had to add in two New Lines at the end of the file to pick up last passport
            File input = new File("Day9Full.txt");
            try (Scanner myReader = new Scanner(input)) {
                while(myReader.hasNextLine()) {
                    long data = Long.parseLong(myReader.nextLine());
                    puzzleInput.add(data);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No File Found");
        }
        while(nextNumberIsValid){
            nextNumber = preamble + loops;
            for(int i = 0; i < preamble; i++) {
                listToPass.add(puzzleInput.get(loops + i));
            }
            numberToPass = puzzleInput.get(nextNumber);
            nextNumberIsValid = isNextValid(listToPass, numberToPass);
            listToPass.clear();
            loops++;
        }
        long invalidNumber = puzzleInput.get(nextNumber);
        System.out.println("First invalid number: " + invalidNumber);
        while(!contiguousSetFound) {
            long numberCheckingAgainst = 0;
            for (int i = 0; i < puzzleInput.size(); i++) {
                if ((numberCheckingAgainst + puzzleInput.get(i)) > invalidNumber) {
                    numberCheckingAgainst -= numbersToAdd.get(0);
                    numbersToAdd.remove(0);
                    i--;
                } else if ((numberCheckingAgainst + puzzleInput.get(i)) < invalidNumber) {
                    numbersToAdd.add(puzzleInput.get(i));
                    numberCheckingAgainst += puzzleInput.get(i);
                } else {
                    System.out.println("Minimum Number: " + Collections.min(numbersToAdd));
                    System.out.println("Maximum Number: " + Collections.max(numbersToAdd));
                    long sum = Collections.min(numbersToAdd) + Collections.max(numbersToAdd);
                    System.out.println("Sum of Min and Max: " + sum);
                    break;
                }
            }
            contiguousSetFound = true;
        }
    }
    
    public static Boolean isNextValid(ArrayList<Long> listToPass, long numberToPass) {
        for (int i = 0; i < listToPass.size(); i++) {
            for (int j = 1; j < listToPass.size(); j++) {
                if ((listToPass.get(i) + listToPass.get(j)) == numberToPass) {
                    return true;
                }
            }
        }
        return false;
    }
}
