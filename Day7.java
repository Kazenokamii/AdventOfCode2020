/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kaz.aoc.day7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 *
 * @author dhc10
 */

public class Day7 {

    public static void main(String args[]) {
        ArrayList<String> luggageRuleList = new ArrayList<String>();
        ArrayList<Bag> bagsByColor = new ArrayList<Bag>();
        ArrayList<Bag> bagsContaining = new ArrayList<Bag>();
        try {
            //Read in Passport Data and separate passports based on blank lines
            //Note - Had to add in two New Lines at the end of the file to pick up last passport
            File luggageRules = new File("Day7Full.txt");
            Scanner myReader = new Scanner(luggageRules);
            String singleGroupAnswers = "";
            while(myReader.hasNextLine()) {
                String data = myReader.nextLine();
                luggageRuleList.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("No File Found");
            e.printStackTrace();
        }
        System.out.println("Number of Luggage Rules: " + luggageRuleList.size());
        for (String luggageRule : luggageRuleList) {
            Bag bag = new Bag(luggageRule.split("s contain")[0]);
            bagsByColor.add(bag);
        }
        for (String luggageRule : luggageRuleList) {
            int differentColorBagsContained = (int) Math.floor((luggageRule.split(" ").length/4.0)-1);
            for (Bag bag : bagsByColor) {
                if(bag.bagName.contains(luggageRule.split("s contain")[0])) {
                    bag.numberOfUniqueBagsContained = differentColorBagsContained;
                    int totalBagsContained = 0;
                    if(differentColorBagsContained > 0) {
                        for(int i = 0; i < differentColorBagsContained; i++) {
                            String bagContained = luggageRule.split("\\d")[i+1];
                            int numberOfBags = Integer.parseInt(String.valueOf(luggageRule.replaceAll("[A-Z|a-z|\\s+|.|,]", "").charAt(i)));
                            bag.bagsByType.add(numberOfBags);
                            for (Bag innerBag : bagsByColor) {
                                if (bagContained.contains(innerBag.bagName)) {
                                    bag.bagsContained.add(innerBag);
                                    break;
                                }
                            }
                            if(bagContained.contains("shiny gold")) {
                                bag.containingShinyGoldBag = true;
                                bagsContaining.add(bag);
                            }
                        }
                    }
                    break;
                }
            }
        }
        int numberOfBagsContainingBag = 0;
        int numberOfChangedBags = 100;
        while(numberOfChangedBags > 0) {
            numberOfChangedBags = 0;
            ArrayList<Bag> bagsToAdd = new ArrayList<Bag>();
            for (Bag trueBag : bagsContaining) {
                for (Bag changing : bagsByColor) {
                    if(changing.bagsContained.contains(trueBag) && !changing.containingShinyGoldBag) {
                        numberOfChangedBags = 1;
                        changing.containingShinyGoldBag = true;
                        bagsToAdd.add(changing);
                    }
                }
            }
            bagsContaining.addAll(bagsToAdd);
        }
        for (Bag bag : bagsByColor) {
            if(bag.bagsContained.size() > 0) {
                bag.totalNumberOfBags = getTotalNumberOfBagsInside(bag);
            }
            if(bag.containingShinyGoldBag) {
                numberOfBagsContainingBag++;
            }
            System.out.println("Bag name: " + bag.bagName + ".  This bag contains " + bag.totalNumberOfBags + " other bags inside of it.");
        }
        System.out.println("Number of Bags Containing shiny gold bag: " + numberOfBagsContainingBag); 
    }
    
    public static int getTotalNumberOfBagsInside(Bag bag) {
        int totalNumberOfBags = 0;
        int i = 0;
        if (bag.bagsContained.size() > 0) {
            for (Bag innerBag : bag.bagsContained) {
                int innerBags = getTotalNumberOfBagsInside(innerBag) * bag.bagsByType.get(i) + bag.bagsByType.get(i);
                if(innerBags == 0) {
                    totalNumberOfBags += bag.bagsByType.get(i);
                } else {
                    totalNumberOfBags += innerBags;
                }
                i++;
            }
        } else {
            return 0;
        }
        return totalNumberOfBags;
    }
}