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
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author dhc10
 */
public class Day11 {
    public static void main(String args[]) {
        ArrayList<String> puzzleInput = new ArrayList<>();
        ArrayList<char[]> baseArray = new ArrayList<>();
        ArrayList<char[]> newArray = new ArrayList<>();
        Boolean seatingChanged = true;
        int seatingChanges = 0;
        try {
            //Read in Passport Data and separate passports based on blank lines
            //Note - Had to add in two New Lines at the end of the file to pick up last passport
            File input = new File("Day11Full.txt");
            try (Scanner myReader = new Scanner(input)) {
                while(myReader.hasNextLine()) {
                        String data = myReader.nextLine();
                    puzzleInput.add(data);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No File Found");
        }
        for(int i = 0; i < puzzleInput.size(); i++) {
            baseArray.add(puzzleInput.get(i).toCharArray());
            newArray.add(puzzleInput.get(i).toCharArray());
        }
        while(seatingChanged) {
            seatingChanged = false;
            for(int i = 0; i < baseArray.size(); i++) {
                for(int j = 0; j < baseArray.get(i).length; j++) {
                    if(baseArray.get(i)[j] != '.') {
                        int seatsOccupied = 0;
                        int k;
                        int l;
                        try {
                            k = i+1;
                            while(baseArray.get(k)[j] == '.'){
                                k++;
                            }
                            if(baseArray.get(k)[j] == '#') {
                                seatsOccupied++;
                            }
                        } catch (IndexOutOfBoundsException e) {}
                        try {
                            k = i-1;
                            while(baseArray.get(k)[j] == '.') {
                                k--;
                            }
                            if(baseArray.get(k)[j] == '#') {
                                seatsOccupied++;
                            }
                        } catch (IndexOutOfBoundsException e) {}
                        try {
                            k = j+1;
                            while(baseArray.get(i)[k] == '.') {
                                k++;
                            }
                            if(baseArray.get(i)[k] == '#') {
                                seatsOccupied++;
                            }
                        } catch (IndexOutOfBoundsException e) {}
                        try {
                            k = j-1;
                            while(baseArray.get(i)[k] == '.') {
                                k--;
                            }
                            if(baseArray.get(i)[k] == '#') {
                                seatsOccupied++;
                            }
                        } catch (IndexOutOfBoundsException e) {}
                        try {
                            k = i+1;
                            l = j+1;
                            while(baseArray.get(k)[l] == '.') {
                                k++;
                                l++;
                            }
                            if(baseArray.get(k)[l] == '#') {
                                seatsOccupied++;
                            }
                        } catch (IndexOutOfBoundsException e) {}
                        try {
                            k = i+1;
                            l = j-1;
                            while(baseArray.get(k)[l] == '.') {
                                k++;
                                l--;
                            }
                            if(baseArray.get(k)[l] == '#') {
                                seatsOccupied++;
                            }
                        } catch (IndexOutOfBoundsException e) {}
                        try {
                            k = i-1;
                            l = j+1;
                            while(baseArray.get(k)[l] == '.') {
                                k--;
                                l++;
                            }
                            if(baseArray.get(k)[l] == '#') {
                                seatsOccupied++;
                            }
                        } catch (IndexOutOfBoundsException e) {}
                        try {
                            k = i-1;
                            l = j-1;
                            while(baseArray.get(k)[l] == '.') {
                                k--;
                                l--;
                            }
                            if(baseArray.get(k)[l] == '#') {
                                seatsOccupied++;
                            }
                        } catch (IndexOutOfBoundsException e) {}
                        if(seatsOccupied > 4) {
                            newArray.get(i)[j] = 'L';
                        } else if(seatsOccupied == 0) {
                            newArray.get(i)[j] = '#';
                        }
                    }
                }
            }
            int rowNumber = 0;
            for(char[] row : baseArray) {
                int columnNumber = 0;
                for(char seat : row) {
                    if(!(newArray.get(rowNumber)[columnNumber] == seat)) {
                        seatingChanged = true;
                    }
                    columnNumber++;
                }
                rowNumber++;
            }
            System.out.println();
            for (int i = 0; i < baseArray.size(); i++) {
                for(int j = 0; j < baseArray.get(i).length; j++) {
                    baseArray.get(i)[j] = newArray.get(i)[j];
                }
            }
            seatingChanges++;
        }
        int occupiedSeats = 0;
        for(int i = 0; i < newArray.size(); i++) {
            for (char j : newArray.get(i)) {
                System.out.print(j);
                if(j == '#') {
                    occupiedSeats++;
                }
            }
            System.out.println();
        }
        System.out.println("Number of Times Seating Changed: " + seatingChanges);
        System.out.println("Number of Occupied Seats: " + occupiedSeats);
    }
}
