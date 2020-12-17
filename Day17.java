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
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author dhc10
 */
public class Day17 {
    public static void main(String args[]) {
        ArrayList<String> puzzleInput = new ArrayList<>();
        HashMap<String, Character> grid = new HashMap<>();
        HashMap<String, Character> newGrid = new HashMap<>();
        for(int x = -10;x < 11;x++) {
            for(int y = -10;y < 11;y++) {
                for(int z = -10;z < 11;z++) {
                    for(int w = -10;w < 11;w++) {
                        grid.put("" + x + "," + y + "," + z + "," + w, '.');
                    }
                }
            }
        }
        try {
            //Read in Passport Data and separate passports based on blank lines
            //Note - Had to add in two New Lines at the end of the file to pick up last passport
            File input = new File("Day17Full.txt");
            try (Scanner myReader = new Scanner(input)) {
                while(myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    puzzleInput.add(data);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No File Found");
        }
        for(int x = 0;x < 8;x++) {
            for(int y = 0;y < 8;y++) {
                grid.replace("" + (x-4) + "," + (y-4) + ",0,0", puzzleInput.get(x).charAt(y));
            }
        }
        newGrid = (HashMap) grid.clone();
        for(int cycle = 0;cycle < 6;cycle++) {
            for(int z = -10;z < 11;z++) {
                for(int x = -10;x < 11;x++) {
                    for(int y = -10;y < 11;y++) {
                        for(int w = -10;w < 11;w++) {
                            int count = 0;
                            for(int a = -1;a < 2;a++) {
                                for(int b = -1;b < 2;b++) {
                                    for(int c = -1;c < 2;c++) {
                                        for(int d = -1;d < 2;d++) {
                                            try {
                                                if(a == 0 && b == 0 && c == 0 && d == 0) {}
                                                else if(grid.get("" + (x+a) + "," + (y+b) + "," + (z+c) + "," + (w+d)) == '#') {
                                                    count++;
                                                }
                                            } catch (NullPointerException e){}
                                        }
                                    }
                                }
                            }
                            if(grid.get("" + x + "," + y + "," + z + "," + w) == '#' && !(count == 2 || count == 3)) {
                                newGrid.replace("" + x + "," + y + "," + z + "," + w, '.');
                            } else if(grid.get("" + x + "," + y + "," + z + "," + w) == '.' && count == 3) {
                                newGrid.replace("" + x + "," + y + "," + z + "," + w, '#');
                            }
                        }
                    }
                }
            }
            grid = (HashMap) newGrid.clone();
        }
        int activeCount = 0;
        for(char c : grid.values()) {
            if(c == '#') {
                activeCount++;
            }
        }
        System.out.println("Puzzle Answer: " + activeCount);
    }
}
