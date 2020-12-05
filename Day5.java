/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kaz.aoc.day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author dhc10
 */

public class Day5 {
	
    public static void main(String args[]) {
        long highestSeatValue = 0;
        ArrayList<String> listOfSeats = new ArrayList<String>();
        ArrayList<Integer[]> seatInformation = new ArrayList<Integer[]>();
        try {
            //Read in Passport Data and separate passports based on blank lines
            //Note - Had to add in two New Lines at the end of the file to pick up last passport
            File passports = new File("Day5Seats.txt");
            Scanner myReader = new Scanner(passports);
            while(myReader.hasNextLine()) {
                listOfSeats.add(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("No File Found");
            e.printStackTrace();
        }
        for (String seat : listOfSeats) {
            seatInformation.add(getSeatInformation(seat));
            System.out.println("Seat information for seat: " + seat + ". Row: " + seatInformation.get(seatInformation.size()-1)[0]
                    + ". Column: " + seatInformation.get(seatInformation.size()-1)[1]);
        }
        seatInformation.sort((x,y) -> Integer.compare(x[0], y[0]));
        int currentrow = seatInformation.get(0)[0];
        ArrayList<Integer> columns = new ArrayList<Integer>();
        for (Integer[] seatInfo : seatInformation) {
            if(seatInfo[0] == currentrow) {
                columns.add(seatInfo[1]);
            } else {
                if(columns.size() == 8) {
                    currentrow++;
                    columns.clear();
                    columns.add(seatInfo[1]);
                } else {
                    System.out.println("Seat is at Row: " + currentrow + ".");
                    for (int i = 0; i < 8; i++) {
                        if(!columns.contains(i)){
                            System.out.println("Seat is at Column: " + i);
                        }
                    }
                    currentrow++;
                    columns.clear();
                    columns.add(seatInfo[1]);
                }
            }
        }
        for (Integer[] seatInfo : seatInformation) {
            long seatValue = seatInfo[0] * 8 + seatInfo[1];
            if(seatValue > highestSeatValue) {
                highestSeatValue = seatValue;
            }
        }
        System.out.println("Highest Seat Value: " + highestSeatValue);
    }
    
    public static Integer[] getSeatInformation(String seat) {
        int toprow = 127;
        int bottomrow = 0;
        int topcolumn = 7;
        int bottomcolumn = 0;
        for (int i = 0; i < seat.length(); i++) {
            if(seat.charAt(i) == 'F') {
                toprow = toprow - (int) Math.ceil((toprow - bottomrow)/2.0);
            } else if(seat.charAt(i) == 'B') {
                bottomrow = bottomrow + (int) Math.ceil((toprow-bottomrow)/2.0);
            } else if(seat.charAt(i) == 'R') {
                bottomcolumn = bottomcolumn + (int) Math.ceil((topcolumn-bottomcolumn)/2.0);
            } else {
                topcolumn = topcolumn - (int) Math.ceil((topcolumn-bottomcolumn)/2.0);
            }
        }
        return new Integer[] {toprow, topcolumn};
    }
}