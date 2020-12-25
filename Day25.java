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
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author dhc10
 */
public class Day25 {
    public static void main(String args[]) {
        ArrayList<Long> puzzleInput = new ArrayList<>();
        long value = 1;
        long subjectKey = 7;
        //long cardPublicKey = 5764801;
        long cardPublicKey = 11239946;
        //long doorPublicKey = 17807724;
        long doorPublicKey = 10464955;
        int cardLoop = 0;
        int doorLoop = 0;
        while(value != cardPublicKey) {
            value = value * subjectKey;
            value = value % 20201227;
            cardLoop++;
        }
        System.out.println("Card Loop Number: " + cardLoop);
        value = 1;
        while(value != doorPublicKey) {
            value = value * subjectKey;
            value = value % 20201227;
            doorLoop++;
        }
        System.out.println("Door Loop Number: " + doorLoop);
        value = 1;
        for(int i = 0;i < cardLoop;i++) {
            value = value * doorPublicKey;
            value = value % 20201227;
        }
        System.out.println("Card Encryption Key: " + value);
        value = 1;
        for(int i = 0;i < doorLoop;i++) {
            value = value * cardPublicKey;
            value = value % 20201227;
        }
        System.out.println("Door Encryption Key: " + value);
    }
}
