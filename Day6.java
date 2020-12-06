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
import java.util.stream.IntStream;

/**
 *
 * @author dhc10
 */

public class Day6 {

    public static void main(String args[]) {
        ArrayList<String> groupAnswers = new ArrayList<String>();
        ArrayList<Long> yesAnswers = new ArrayList<Long>();
        ArrayList<Integer> allYesAnswers = new ArrayList<Integer>();
        long numberOfYesAnswers = 0;
        int numberOfGroupMembers;
        long numberOfAllYesAnswers = 0;
        try {
            //Read in Passport Data and separate passports based on blank lines
            //Note - Had to add in two New Lines at the end of the file to pick up last passport
            File passports = new File("Day6FullAnswers.txt");
            Scanner myReader = new Scanner(passports);
            String singleGroupAnswers = "";
            while(myReader.hasNextLine()) {
                String data = myReader.nextLine();
                singleGroupAnswers = singleGroupAnswers + " " + data;
                if(data.equals("")) {
                    groupAnswers.add(singleGroupAnswers);
                    singleGroupAnswers = "";
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("No File Found");
            e.printStackTrace();
        }
        System.out.println("Number of Groups: " + groupAnswers.size());
        //Check each passport for all required fields
        for (String groupAnswer : groupAnswers) {
            yesAnswers.add(getYesAnswers(groupAnswer));
            numberOfGroupMembers = getNumberOfMembers(groupAnswer);
            System.out.println("Number of Group Members: " + numberOfGroupMembers);
            allYesAnswers.add(getAllYesAnswers(groupAnswer, numberOfGroupMembers));
        }
        for (long number : yesAnswers) {
            numberOfYesAnswers+=number;
            System.out.println("Yes answer for group: " + number);
        }
        for(long number: allYesAnswers) {
            numberOfAllYesAnswers+=number;
        }
        System.out.println("Sum of Yes Answers: " + numberOfYesAnswers);
        System.out.println("Sum of All Yes Answer: " + numberOfAllYesAnswers);
    }
    
    public static long getYesAnswers(String answers) {
        //Returning distinct characters minus 1 to account for white spaces
        return answers.chars().distinct().count()-1;
    }
    
    public static int getNumberOfMembers(String answer) {
        //Returning number of whites spaces in array
        int whiteSpaces = 0;
        for (char whiteSpace : answer.toCharArray()) {
            if(Character.compare(whiteSpace, ' ') == 0){
                whiteSpaces++;
            }
        }
        return whiteSpaces-1;
    }
    
    public static int getAllYesAnswers(String answer, int numberOfGroupMembers) {
        ArrayList<Character> questions = new ArrayList<Character>();
        int numberOfYesesToQuestion;
        int totalYeses = 0;
        for (char question : answer.toCharArray()) {
            if(!questions.contains(question)) {
                questions.add(question);
            }
        }
        for (int i = 0; i < questions.size(); i++) {
            numberOfYesesToQuestion = 0;
            for (int j = 0; j < answer.length(); j++) {
                if(answer.charAt(j) == questions.get(i)) {
                    numberOfYesesToQuestion++;
                }
            }
            if(numberOfYesesToQuestion == numberOfGroupMembers) {
                totalYeses++;
            }
        }
        return totalYeses;
    }
}