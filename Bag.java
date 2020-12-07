/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kaz.aoc.day7;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dhc10
 */
public class Bag {
    String bagName;
    int numberOfUniqueBagsContained;
    List<Bag> bagsContained;
    Boolean containingShinyGoldBag;
    List<Integer> bagsByType = new ArrayList<Integer>();
    int totalNumberOfBags;

    public Bag(String bagName, int numberOfUniqueBagsContained, List<Bag> bagsContained, Boolean containingShinyGoldBag) {
        this.bagName = bagName;
        this.numberOfUniqueBagsContained = numberOfUniqueBagsContained;
        this.bagsContained = bagsContained;
        this.containingShinyGoldBag = containingShinyGoldBag;
    }

    public Bag(String bagName) {
        this.bagName = bagName;
        this.bagsContained = new ArrayList<>();
        this.containingShinyGoldBag = false;
    }

    @Override
    public String toString() {
        return "Bag{" + "bagName=" + bagName + ", numberOfUniqueBagsContained=" + numberOfUniqueBagsContained + ", bagsContained=" + bagsContained + ", containingShinyGoldBag=" + containingShinyGoldBag + ", bagsByType=" + bagsByType + ", totalNumberOfBags=" + totalNumberOfBags + '}';
    }
}
