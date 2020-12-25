/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kaz.aoc.day24;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author dhc10
 */
public class Day24 {
    public static void main(String args[]) {
        ArrayList<String> puzzleInput = new ArrayList<>();
        HashMap<String,FloorTile> floorTiles = new HashMap<>();
        ArrayList<FloorTile> tilesToFlip = new ArrayList<>();
        int numberOfRows = 130;
        try {
            //Read in Passport Data and separate passports based on blank lines
            //Note - Had to add in two New Lines at the end of the file to pick up last passport
            File input = new File("Day24Full.txt");
            try (Scanner myReader = new Scanner(input)) {
                while(myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    puzzleInput.add(data);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No File Found");
        }
        for(int i = -1 * numberOfRows;i < numberOfRows + 1;i++) {
            for(int j = -1 * numberOfRows;j < numberOfRows + 1;j = j + 2) {
                if(i % 2 != 0 && j == -1 * numberOfRows) {
                    j++;
                }
                String tileId = "North:" + i + " East:" + j;
                floorTiles.put(tileId, new FloorTile(i, j));
            }
        }
        for(FloorTile tile : floorTiles.values()) {
            for(FloorTile adjacentTile : floorTiles.values()) {
                if(Math.abs(tile.northTiles - adjacentTile.northTiles) == 1 && Math.abs(tile.eastTiles - adjacentTile.eastTiles) == 1) {
                    tile.adjacentTiles.add(adjacentTile);
                } else if (Math.abs(tile.eastTiles - adjacentTile.eastTiles) == 2 && Math.abs(tile.northTiles - adjacentTile.northTiles) == 0) {
                    tile.adjacentTiles.add(adjacentTile);
                }
            }
        }
        for(String directions : puzzleInput) {
            String tile = "";
            char[] tiles = directions.toCharArray();
            int north = 0;  
            int east = 0;
            Boolean halfStep = false;
            for(char direction : tiles) {
                switch(direction) {
                    case 'n':
                        north++;
                        halfStep = true;
                        break;
                    case 's':
                        north--;
                        halfStep = true;
                        break;
                    case 'e':
                        east++;
                        if(halfStep) {
                            halfStep = false;
                        } else {
                            east++;
                        }
                        break;
                    case 'w':
                        east--;
                        if(halfStep) {
                            halfStep = false;
                        } else {
                            east--;
                        }
                        break;
                }
            }
            tile += "North:" + north + " East:" + east;
            if(floorTiles.containsKey(tile)) {
                floorTiles.get(tile).flipTile();
            }
        }
        int count = 0;
//        for(FloorTile tile : floorTiles.values()) {
//            if(tile.tileColor.equals("Black")) {
//                count++;
//            }
//        }
        for(int i = 0;i < 100;i++) {
            count = 0;
            for(FloorTile tileToCheck : floorTiles.values()) {
                int numberOfBlackTiles = 0;
                int numberOfWhiteTiles = 0;
                for(FloorTile adjacentTile : tileToCheck.adjacentTiles) {
                    if(adjacentTile.tileColor.equals("Black")) {
                        numberOfBlackTiles++;
                    } else {
                        numberOfWhiteTiles++;
                    }
                }
                if(tileToCheck.tileColor.equals("Black") && (numberOfBlackTiles == 0 || numberOfBlackTiles > 2)) {
                    tilesToFlip.add(tileToCheck);
                } else if(tileToCheck.tileColor.equals("White") && numberOfBlackTiles == 2) {
                    tilesToFlip.add(tileToCheck);
                }
            }
            for(FloorTile tileToFlip : tilesToFlip) {
                tileToFlip.flipTile();
            }
            tilesToFlip.clear();
            for(FloorTile tileCount : floorTiles.values()) {
                if(tileCount.tileColor.equals("Black")) {
                    count++;
                }
            }
            System.out.println("Day " + (i+1) + ": " + count);
        }     
    }
}
