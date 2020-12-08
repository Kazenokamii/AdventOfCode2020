/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kaz.aoc.day8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author dhc10
 */

public class Day8 {

    public static void main(String args[]) {
        ArrayList<String> instructionList = new ArrayList<String>();
        Accumulator startUp = new Accumulator(0, 0, false);
        try {
            File instructions = new File("Day8Full.txt");
            Scanner myReader = new Scanner(instructions);
            while(myReader.hasNextLine()) {
                String data = myReader.nextLine();
                instructionList.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("No File Found");
            e.printStackTrace();
        }
        for (String instruction : instructionList) {
            String replacedCommand;
            String newCommand;
            int instructionIndex = instructionList.indexOf(instruction);
            if(instruction.split("\\s")[0].equals("nop")) {
                instructionList.set(instructionIndex, instructionList.get(instructionIndex).replace("nop", "jmp"));
            } else if(instruction.split("\\s")[0].equals("jmp")) {
                instructionList.set(instructionIndex, instructionList.get(instructionIndex).replace("jmp", "nop"));
            } else {
                startUp.infiniteLoop = true;
            }
            while(!startUp.infiniteLoop) {
                if(startUp.indexValue < instructionList.size()) {
                    startUp.infiniteLoop = startUp.processCommand(instructionList.get(startUp.indexValue));
                } else if(startUp.indexValue < 0) {
                    startUp.infiniteLoop = true;
                } else {
                    System.out.println("Startup is Complete.");
                    break;
                }
            }
            if(startUp.infiniteLoop) {
                startUp.infiniteLoop = false;
                startUp.indexValue = 0;
                startUp.accumulatorValue = 0;
                startUp.visitedArrayIndexes.clear();
                instructionList.set(instructionIndex, instruction);
            } else {
                break;
            }
        }
        System.out.println("Accumulator Value: " + startUp.accumulatorValue);
    }
}