/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kaz.aoc.day20;

import java.util.ArrayList;

/**
 *
 * @author dhc10
 */
public class DeckOfCards {
    
    String deckName;
    ArrayList<Integer> cardsInDeck;

    public DeckOfCards(String deckName, ArrayList<Integer> cardsInDeck) {
        this.deckName = deckName;
        this.cardsInDeck = cardsInDeck;
    }
    
    public Integer getTopCard() {
        Integer topCard = this.cardsInDeck.get(0);
        this.cardsInDeck.remove(0);
        return topCard;
    }
    
    public void placeCardOnBottom(Integer card) {
        this.cardsInDeck.add(card);
    }
    
}
