/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kaz.aoc.day1;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author dhc10
 */
public class Day23 {
    public static void main(String args[]) {
        ArrayList<Integer> numberSequence = new ArrayList<>();
        int[] sequenceList = new int[]{2,4,7,8,1,9,3,5,6};
        //int[] sequenceList = new int[]{3,8,9,1,2,5,4,6,7};
        int maxCupNumber = 1000000;
        for(int number : sequenceList) {
            numberSequence.add(number);
        }
        for(int i = 10;i < 1000001;i++) {
            numberSequence.add(i);
        }
        for(int i = 0;i < 10000000;i++) { 
            if(i % 20000 == 0) {
                System.out.println("Current Loop: " + i);
                System.out.println("Current Time: " + System.currentTimeMillis());
            }
            int destinationIndex = 0;
            int currentCup = numberSequence.get(0);
            numberSequence.remove(0);
            int destinationCup = 0;
            int currentCupToCheck = currentCup - 1;
            if(currentCupToCheck == 0) {
                currentCupToCheck = maxCupNumber;
            }
            ArrayList<Integer> pickedUpCups = new ArrayList<>();
            for(int j = 0;j < 3;j++) {
                pickedUpCups.add(numberSequence.get(0));
                numberSequence.remove(0);
            }
            while(destinationCup == 0) {
                if(currentCupToCheck > 0 && !pickedUpCups.contains(currentCupToCheck)) {
                    destinationCup = currentCupToCheck;
                    destinationIndex = numberSequence.indexOf(destinationCup);
                } else if(currentCupToCheck == 0) {
                    currentCupToCheck = maxCupNumber;
                } else {
                    currentCupToCheck--;
                }
            }
            for(int j = 1;j < 4;j++) {
                numberSequence.add(destinationIndex+j, pickedUpCups.get(0));
                pickedUpCups.remove(0);
            }
            numberSequence.add(currentCup);
        }
        String numbersToPrint = "";
        int indexOfOne = numberSequence.indexOf(1);
        for(int i = 1;i < 3;i++) {
            if(indexOfOne + i < 1000000) {
                numbersToPrint += numberSequence.get(indexOfOne + i);
                numbersToPrint += " ";
            } else {
                numbersToPrint += numberSequence.get(indexOfOne + i - 1000000);
            }
        }
        System.out.println("Puzzle Answer: " + numbersToPrint);
    }
}
