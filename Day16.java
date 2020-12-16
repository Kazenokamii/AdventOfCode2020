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
public class Day16 {
    public static void main(String args[]) {
        ArrayList<String> puzzleInput = new ArrayList<>();
        ArrayList<String> nearbyTickets = new ArrayList<>();
        ArrayList<Integer> nonValidNumbers = new ArrayList<>();
        ArrayList<String> invalidTickets = new ArrayList();
        HashMap<Integer, ArrayList<String>> ticketInformationLocation = new HashMap<>();
        HashMap<String, String> ticketInformation = new HashMap<>();
        ArrayList ticketInformationByLocation = new ArrayList<>();
        String objectToRemove = "";
        String myTicket = "";
        
        long nonValidTotal = 0;
        int maxValue;
        int minValue;
        try {
            //Read in Passport Data and separate passports based on blank lines
            //Note - Had to add in two New Lines at the end of the file to pick up last passport
            File input = new File("Day16Full.txt");
            try (Scanner myReader = new Scanner(input)) {
                while(myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    if(data.equals("your ticket:")) {
                        break;
                    } else {
                        puzzleInput.add(data);
                    }
                }
            }
            try (Scanner myReader = new Scanner(input)) {
                while(myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    if(data.equals("nearby tickets:")) {
                        while(myReader.hasNextLine()) {
                            data = myReader.nextLine();
                            nearbyTickets.add(data);
                        }
                    } 
                }
            }
            try (Scanner myReader = new Scanner(input)) {
                while(myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    if(data.equals("your ticket:")) {
                        myTicket = myReader.nextLine();
                        break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No File Found");
        }
        for (int i = 0; i < puzzleInput.size()-1; i++) {
            ticketInformationByLocation.add(puzzleInput.get(i).substring(0, puzzleInput.get(i).indexOf(":")));
        }
        for (int i = 0; i < puzzleInput.size()-1; i++) {
            ticketInformationLocation.put(i, (ArrayList<String>) ticketInformationByLocation.clone());
        }
        for (String ticketValues : puzzleInput) {
            try {
                ticketInformation.put(ticketValues.split(": ")[0], ticketValues.split(": ")[1]);
            } catch (IndexOutOfBoundsException e) {
                continue;
            }
        }
        for (String nearbyTicket : nearbyTickets) {
            int j = 0;
            for(String ticketValidValue : nearbyTicket.split(",")) {
                Boolean valid = false;
                int value = Integer.parseInt(ticketValidValue);
                for(String ticketValidValues : ticketInformation.keySet()) {
                    String ticketValues = ticketInformation.get(ticketValidValues);
                    int i = 0;
                    for (String minMaxValues : ticketValues.split(" or ")) {
                        int indexOfDash = minMaxValues.indexOf("-");
                        minValue = Integer.parseInt(minMaxValues.substring(0, indexOfDash));
                        maxValue = Integer.parseInt(minMaxValues.substring(indexOfDash + 1));
                        if(value <= maxValue && value >= minValue) {
                            valid = true;
                        }
                    }
                }
                if(!valid) {
                    nonValidNumbers.add(value);
                    invalidTickets.add(nearbyTicket);
                }
                j++;
            }
        }
        for (String invalidTicket : invalidTickets) {
            if(nearbyTickets.contains(invalidTicket)) {
                nearbyTickets.remove(invalidTicket);
            }
        }
        for (String nearbyTicket : nearbyTickets) {
            int j = 0;
            for(String ticketValidValue : nearbyTicket.split(",")) {
                Boolean valid = false;
                int value = Integer.parseInt(ticketValidValue);
                for(String ticketValidValues : ticketInformation.keySet()) {
                    String ticketValues = ticketInformation.get(ticketValidValues);
                    int i = 0;
                    for (String minMaxValues : ticketValues.split(" or ")) {
                        int indexOfDash = minMaxValues.indexOf("-");
                        minValue = Integer.parseInt(minMaxValues.substring(0, indexOfDash));
                        maxValue = Integer.parseInt(minMaxValues.substring(indexOfDash + 1));
                        if(value <= maxValue && value >= minValue) {
                            valid = true;
                        } else {
                            i++;
                            if(i == 2) {
                                if(ticketInformationLocation.get(j).contains(ticketValidValues)) {
                                    for (int k = 0; k < ticketInformationLocation.get(j).size(); k++) {
                                        if(ticketInformationLocation.get(j).get(k).equals(ticketValidValues)) {
                                            ticketInformationLocation.get(j).remove(k);
                                            k--;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if(!valid) {
                    nonValidNumbers.add(value);
                    invalidTickets.add(nearbyTicket);
                }
                j++;
            }
        }
        for(Integer nonValid : nonValidNumbers) {
            nonValidTotal += nonValid;
        }
        Boolean changed = true;
        while(changed) {
            changed = false;
            for (int i = 0; i < ticketInformationLocation.size();i++) {
                if(ticketInformationLocation.get(i).size() == 1) {
                    for(int k = 0; k < ticketInformation.size();k++) {
                        if(k != i) {
                            for(int j = 0;j < ticketInformationLocation.get(k).size(); j++) {
                                if(ticketInformationLocation.get(k).get(j).equals(ticketInformationLocation.get(i).get(0))){
                                    ticketInformationLocation.get(k).remove(j);
                                    j--;
                                    changed = true;
                                }
                            }
                        }
                    }
                }
            }
        }
        String[] myTicketArray = myTicket.split(",");
        for(int i : ticketInformationLocation.keySet()) {
            System.out.println("Information: " + ticketInformationLocation.get(i) + ". My Ticket: " + myTicketArray[i]);
        }
        long puzzleAnswer = 1;
        for(int i : ticketInformationLocation.keySet()) {
            if(ticketInformationLocation.get(i).get(0).contains("departure")) {
                puzzleAnswer = puzzleAnswer * Integer.parseInt(myTicketArray[i]);
            }
        }
        System.out.println("Puzzle Answer: " + puzzleAnswer);
    }
}
