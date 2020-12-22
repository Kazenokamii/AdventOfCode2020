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
        
public class Day21 {
    @SuppressWarnings("removal")
    public static void main(String args[]) {
        ArrayList<String> puzzleInput = new ArrayList<>();
        HashMap<String, ArrayList<String>> allergens = new HashMap<>();
        Boolean multipleIngredientsPerAllergen = true;
        HashMap<String, Integer> timesIngredientsAppear = new HashMap<>();
        ArrayList<String> alphabetical = new ArrayList<>();
        String canonicalDangerousIngredientList = "";
        int totalNumber = 0;
        try {
            //Read in Passport Data and separate passports based on blank lines
            //Note - Had to add in two New Lines at the end of the file to pick up last passport
            File input = new File("Day21Full.txt");
            try (Scanner myReader = new Scanner(input)) {
                while(myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    puzzleInput.add(data);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No File Found");
        }
        for(String ingredientList : puzzleInput) {
            for(String allergenList : ingredientList.substring(ingredientList.indexOf("contains")+9, ingredientList.indexOf(")")).split(", ")) {
                ArrayList<String> ingredients = new ArrayList<>();
                for(String ingredient : ingredientList.substring(0, ingredientList.indexOf("(")).split(" ")) {
                    ingredients.add(ingredient);
                }
                if(!allergens.keySet().contains(allergenList)) {
                    allergens.put(allergenList, ingredients);
                } else {
                    ArrayList<String> duplicateIngredients = new ArrayList<>();
                    for(String ingredient : ingredients) {
                        if(allergens.get(allergenList).contains(ingredient)) {
                            duplicateIngredients.add(ingredient);
                        }
                    }
                    allergens.replace(allergenList, duplicateIngredients);
                }
            }
        }
        for(String temp : puzzleInput) {
            for(String ingredient : temp.substring(0, temp.indexOf("(")).split(" ")) {
                if(!timesIngredientsAppear.keySet().contains(ingredient)) {
                    timesIngredientsAppear.put(ingredient, 1);
                } else {
                    timesIngredientsAppear.replace(ingredient, timesIngredientsAppear.get(ingredient) + 1);
                }
            }
        }
        while(multipleIngredientsPerAllergen) {
            multipleIngredientsPerAllergen = false;
            for(String ingredient : allergens.keySet()) {
                if(allergens.get(ingredient).size() == 1) {
                    for(String ingredientToRemove : allergens.keySet()) {
                        if(allergens.get(ingredientToRemove).contains(allergens.get(ingredient).get(0)) && allergens.get(ingredientToRemove) != allergens.get(ingredient)) {
                            allergens.get(ingredientToRemove).remove(allergens.get(ingredient).get(0));
                        }
                    }
                }
            }
            for(String ingredient : allergens.keySet()) {
                if(allergens.get(ingredient).size() > 1) {
                    multipleIngredientsPerAllergen = true;
                }
            }
        }
        for(String ingredient : timesIngredientsAppear.keySet()) {
            ArrayList<String> temp = new ArrayList<>();
            temp.add(ingredient);
            if(!allergens.containsValue(temp)) {
                totalNumber += timesIngredientsAppear.get(ingredient);
            }
        }
        alphabetical.addAll(allergens.keySet());
        Collections.sort(alphabetical, String.CASE_INSENSITIVE_ORDER);
        for(String ingredient : alphabetical) {
            canonicalDangerousIngredientList += allergens.get(ingredient) + ",";
        }
        String newCanon = canonicalDangerousIngredientList.replace("[", "");
        String newNewCanon = newCanon.replace("]", "");
        System.out.println("Puzzle Answer: " + totalNumber);
        System.out.println(newNewCanon);
    }
}
