/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kaz.aoc.day8;

import java.util.ArrayList;

/**
 *
 * @author dhc10
 */
public class Accumulator {
    public long accumulatorValue;
    public int indexValue;
    public Boolean infiniteLoop;
    public ArrayList<Integer> visitedArrayIndexes = new ArrayList<Integer>();

    public Accumulator(long accumulatorValue, int indexValue, Boolean infiniteLoop) {
        this.accumulatorValue = accumulatorValue;
        this.indexValue = indexValue;
        this.infiniteLoop = infiniteLoop;
    }
    
    public Boolean processCommand(String instruction) {
        String[] instructionArray = instruction.split("\\s");
        if(instructionArray[0].equals("nop")) {
            this.indexValue += 1;
        } else if(instructionArray[0].equals("acc")) {
            this.accumulatorValue += Integer.parseInt(instructionArray[1]);
            this.indexValue += 1;
        } else {
            this.indexValue += Integer.parseInt(instructionArray[1]);
        }
        if(this.visitedArrayIndexes.contains(this.indexValue)) {
            return true;
        } else {
            this.visitedArrayIndexes.add(this.indexValue);
            return false;
        }
    }
    
}
