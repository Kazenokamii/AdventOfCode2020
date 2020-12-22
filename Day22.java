/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kaz.aoc.day20;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author dhc10
 */
public class Day22 {
    public static void main(String args[]) {
        ArrayList<String> puzzleInput = new ArrayList<>();
        ArrayList<DeckOfCards> decks = new ArrayList<>();
        Boolean gameContinuing = true;
        String deckName = "";
        ArrayList<Integer> cardsInDeck = new ArrayList<>();
        DeckOfCards winningDeck = new DeckOfCards("", new ArrayList<>());
        Integer winningScore = 0;
        HashMap<Integer, ArrayList<Integer>> deck1Configuration = new HashMap<>();
        HashMap<Integer, ArrayList<Integer>> deck2Configuration = new HashMap<>();
        int round = 0;
        try {
            //Read in Passport Data and separate passports based on blank lines
            //Note - Had to add in two New Lines at the end of the file to pick up last passport
            File input = new File("Day22Full.txt");
            try (Scanner myReader = new Scanner(input)) {
                while(myReader.hasNextLine()) {
                    try {
                        String data = myReader.nextLine();
                        puzzleInput.add(data);
                    } catch (StringIndexOutOfBoundsException e) {}
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No File Found");
        }
        for(int i = 0;i < puzzleInput.size();i++) {
            if(puzzleInput.get(i).isEmpty()) {
                String tempDeckName = "";
                tempDeckName += deckName;
                ArrayList<Integer> tempCards = new ArrayList<>();
                tempCards = (ArrayList<Integer>) cardsInDeck.clone();
                decks.add(new DeckOfCards(tempDeckName, tempCards));
                cardsInDeck.clear();
            } else if(Character.compare(puzzleInput.get(i).charAt(0), 'P') == 0) {
                deckName = puzzleInput.get(i);
            } else if (Character.isDigit(puzzleInput.get(i).charAt(0))) {
                cardsInDeck.add(Integer.parseInt(puzzleInput.get(i)));
            }
        }
        while(gameContinuing) {
            round++;
            deck1Configuration.put(round, (ArrayList<Integer>) decks.get(0).cardsInDeck.clone());
            deck2Configuration.put(round, (ArrayList<Integer>) decks.get(1).cardsInDeck.clone());
            int player1Card = decks.get(0).getTopCard();
            int player2Card = decks.get(1).getTopCard();
            if(player1Card <= decks.get(0).cardsInDeck.size() && player2Card <= decks.get(1).cardsInDeck.size()) {
                DeckOfCards player1SubDeck = new DeckOfCards("Player 1:", new ArrayList<>(decks.get(0).cardsInDeck.subList(0, player1Card)));
                DeckOfCards player2SubDeck = new DeckOfCards("Player 2:", new ArrayList<>(decks.get(1).cardsInDeck.subList(0, player2Card)));
                DeckOfCards subGameWinningDeck = recursiveCombat(player1SubDeck, player2SubDeck);
                if(subGameWinningDeck.deckName.equals("Player 1:")) {
                    decks.get(0).placeCardOnBottom(player1Card);
                    decks.get(0).placeCardOnBottom(player2Card);
                } else {
                    decks.get(1).placeCardOnBottom(player2Card);
                    decks.get(1).placeCardOnBottom(player1Card);
                }
            } else if(player1Card > player2Card) {
                decks.get(0).placeCardOnBottom(player1Card);
                decks.get(0).placeCardOnBottom(player2Card);
            } else if(player2Card > player1Card) {
                decks.get(1).placeCardOnBottom(player2Card);
                decks.get(1).placeCardOnBottom(player1Card);
            }
            if(!(decks.get(0).cardsInDeck.size() > 0 && decks.get(1).cardsInDeck.size() > 0)) {
                gameContinuing = false;
                if(decks.get(0).cardsInDeck.size() > 0) {
                    winningDeck = decks.get(0);
                } else {
                    winningDeck = decks.get(1);
                }
            } else if (deck1Configuration.containsValue(decks.get(0).cardsInDeck) && deck2Configuration.containsValue(decks.get(1).cardsInDeck)) {
                winningDeck = decks.get(0);
                gameContinuing = false;
            }
        }
        for(int i = 1;i < winningDeck.cardsInDeck.size()+1;i++) {
            winningScore += winningDeck.cardsInDeck.get(winningDeck.cardsInDeck.size()-i)*i;
        }
        System.out.println("Winning Score: " + winningScore);
    }
    
    public static DeckOfCards recursiveCombat(DeckOfCards player1Deck, DeckOfCards player2Deck) {
        DeckOfCards winningDeck = new DeckOfCards("", new ArrayList<>());
        int round = 0;
        HashMap<Integer, ArrayList<Integer>> deck1Configuration = new HashMap<>();
        HashMap<Integer, ArrayList<Integer>> deck2Configuration = new HashMap<>();
        Boolean gameContinuing = true;
        while(gameContinuing) {
            round++;
            deck1Configuration.put(round, (ArrayList<Integer>) player1Deck.cardsInDeck.clone());
            deck2Configuration.put(round, (ArrayList<Integer>) player2Deck.cardsInDeck.clone());
            int player1Card = player1Deck.getTopCard();
            int player2Card = player2Deck.getTopCard();
            if(player1Card <= player1Deck.cardsInDeck.size() && player2Card <= player2Deck.cardsInDeck.size()) {
                DeckOfCards player1SubDeck = new DeckOfCards("Player 1:", new ArrayList<>(player1Deck.cardsInDeck.subList(0, player1Card)));
                DeckOfCards player2SubDeck = new DeckOfCards("Player 2:", new ArrayList<>(player2Deck.cardsInDeck.subList(0, player2Card)));
                DeckOfCards subGameWinningDeck = recursiveCombat(player1SubDeck, player2SubDeck);
                if(subGameWinningDeck.deckName.equals("Player 1:")) {
                    player1Deck.placeCardOnBottom(player1Card);
                    player1Deck.placeCardOnBottom(player2Card);
                } else {
                    player2Deck.placeCardOnBottom(player2Card);
                    player2Deck.placeCardOnBottom(player1Card);
                }
            } else if(player1Card > player2Card) {
                player1Deck.placeCardOnBottom(player1Card);
                player1Deck.placeCardOnBottom(player2Card);
            } else if(player2Card > player1Card) {
                player2Deck.placeCardOnBottom(player2Card);
                player2Deck.placeCardOnBottom(player1Card);
            }
            if(!(player1Deck.cardsInDeck.size() > 0 && player2Deck.cardsInDeck.size() > 0)) {
                gameContinuing = false;
                if(player1Deck.cardsInDeck.size() > 0) {
                    winningDeck = player1Deck;
                } else {
                    winningDeck = player2Deck;
                }
            } else if (deck1Configuration.containsValue(player1Deck.cardsInDeck) && deck2Configuration.containsValue(player2Deck.cardsInDeck)) {
                winningDeck = player1Deck;
                gameContinuing = false;
            }
        }
        return winningDeck;
    }
}
