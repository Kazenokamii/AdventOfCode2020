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
public class Day12 {
    public static Integer shipNSValue = 0;
    public static Integer shipEWValue = 0;
    public static HashMap directionMap = new HashMap<Integer, String>();
    public static Integer shipFacing = 90;
    public static Integer[] waypointPosition = new Integer[]{10,1};
    public static void main(String args[]) {
        ArrayList<String> puzzleInput = new ArrayList<>();
        directionMap.put(90, "E");
        directionMap.put(180, "S");
        directionMap.put(270, "W");
        directionMap.put(0, "N");
        try {
            //Read in Passport Data and separate passports based on blank lines
            //Note - Had to add in two New Lines at the end of the file to pick up last passport
            File input = new File("Day12Full.txt");
            try (Scanner myReader = new Scanner(input)) {
                while(myReader.hasNextLine()) {
                        String data = myReader.nextLine();
                    puzzleInput.add(data);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No File Found");
        }
        for (String instruction : puzzleInput) {
            moveShip(instruction);
        }
        System.out.println("Ship's nsValue: " + shipNSValue + ". Ship's ewValue: " + shipEWValue);
        System.out.println("Puzzle Answer: " + (Math.abs(shipNSValue) + Math.abs(shipEWValue)));
    }
    
    public static void moveShip(String instruction) {
        String direction = instruction.substring(0, 1);
        Integer movementAmount = Integer.parseInt(instruction.substring(1));
        int waypointEWValue = waypointPosition[0];
        int waypointNSValue = waypointPosition[1];
        switch (direction) {
            case "N":
                waypointPosition[1]+=movementAmount;
                break;
            case "S":
                waypointPosition[1]-=movementAmount;
                break;
            case "E":
                waypointPosition[0]+=movementAmount;
                break;
            case "W":
                waypointPosition[0]-=movementAmount;
                break;
            case "F":
                shipNSValue+=waypointPosition[1]*movementAmount;
                shipEWValue+=waypointPosition[0]*movementAmount;
                break;
            case "R":
                if(movementAmount == 90) {
                    waypointPosition[0] = waypointNSValue;
                    waypointPosition[1] = waypointEWValue*-1;
                } else if(movementAmount == 180) {
                    waypointPosition[0] = waypointEWValue*-1;
                    waypointPosition[1] = waypointNSValue*-1;
                } else if(movementAmount == 270) {
                    waypointPosition[0] = waypointNSValue*-1;
                    waypointPosition[1] = waypointEWValue;
                }
                break;
            case "L": 
                if(movementAmount == 90) {
                    waypointPosition[0] = waypointNSValue*-1;
                    waypointPosition[1] = waypointEWValue;
                } else if(movementAmount == 180) {
                    waypointPosition[0] = waypointEWValue*-1;
                    waypointPosition[1] = waypointNSValue*-1;
                } else if(movementAmount == 270) {
                    waypointPosition[0] = waypointNSValue;
                    waypointPosition[1] = waypointEWValue*-1;
                }
                break;
        }
    }
}
