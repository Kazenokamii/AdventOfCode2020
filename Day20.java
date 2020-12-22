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
public class Day20 {
    public static void main(String args[]) {
        ArrayList<String> puzzleInput = new ArrayList<>();
        long puzzleAnswer = 1;
        ArrayList<PuzzlePiece> puzzlePieces = new ArrayList<>();
        PuzzlePiece firstCorner = new PuzzlePiece();
        HashMap<Integer, ArrayList<PuzzlePiece>> connectedPuzzle = new HashMap<>();
        int seaMonsters = 0;
        try {
            //Read in Passport Data and separate passports based on blank lines
            //Note - Had to add in two New Lines at the end of the file to pick up last passport
            File input = new File("Day20Full.txt");
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
            Integer tileNumber = 0;
            ArrayList<String> tileRows = new ArrayList<>();
            if(puzzleInput.get(i).substring(0,4).equals("Tile")) {
                tileNumber = Integer.parseInt(puzzleInput.get(i).substring(5,9));
            }
            i++;
            for(int j = 0;j < 10;j++) {
                tileRows.add(puzzleInput.get(i));
                i++;
            }
            PuzzlePiece piece = new PuzzlePiece(tileNumber, tileRows);
            puzzlePieces.add(piece);
        }
        for(PuzzlePiece originalTile : puzzlePieces) {
            String connectedSide;
            int sidesMatching = 0;
            HashMap<String, PuzzlePiece> connectedPieces = new HashMap<>();
            for(PuzzlePiece tileToCheck : puzzlePieces) {
                if(!(originalTile == tileToCheck)) {
                    for(String originalTileSide : originalTile.puzzleSides) {
                        for(String tileToCheckSide : tileToCheck.puzzleSides) {
                            if(originalTileSide.equals(tileToCheckSide)) {
                                if(originalTileSide.equals(originalTile.getLeftSide())) {
                                    connectedSide = "Left";
                                } else if(originalTileSide.equals(originalTile.getRightSide())) {
                                    connectedSide = "Right";
                                } else if(originalTileSide.equals(originalTile.getTopSide())) {
                                    connectedSide = "Top";
                                } else {
                                    connectedSide = "Bottom";
                                }
                                sidesMatching++;
                                originalTile.connectedTiles.put(connectedSide, tileToCheck);
                            }
                        }
                    }
                    tileToCheck.flipHorizontal();
                    for(String originalTileSide : originalTile.puzzleSides) {
                        for(String tileToCheckSide : tileToCheck.puzzleSides) {
                            if(originalTileSide.equals(tileToCheckSide) && !originalTile.connectedTiles.values().contains(tileToCheck)) {
                                if(originalTileSide.equals(originalTile.getLeftSide())) {
                                    connectedSide = "Left";
                                } else if(originalTileSide.equals(originalTile.getRightSide())) {
                                    connectedSide = "Right";
                                } else if(originalTileSide.equals(originalTile.getTopSide())) {
                                    connectedSide = "Top";
                                } else {
                                    connectedSide = "Bottom";
                                }
                                sidesMatching++;
                                originalTile.connectedTiles.put(connectedSide, tileToCheck);
                            }
                        }
                    }
                    tileToCheck.flipVertical();
                    for(String originalTileSide : originalTile.puzzleSides) {
                        for(String tileToCheckSide : tileToCheck.puzzleSides) {
                            if(originalTileSide.equals(tileToCheckSide) && !originalTile.connectedTiles.values().contains(tileToCheck)) {
                                if(originalTileSide.equals(originalTile.getLeftSide())) {
                                    connectedSide = "Left";
                                } else if(originalTileSide.equals(originalTile.getRightSide())) {
                                    connectedSide = "Right";
                                } else if(originalTileSide.equals(originalTile.getTopSide())) {
                                    connectedSide = "Top";
                                } else {
                                    connectedSide = "Bottom";
                                }
                                sidesMatching++;
                                originalTile.connectedTiles.put(connectedSide, tileToCheck);
                            }
                        }
                    }
                }
            }
            if(sidesMatching == 2) {
                System.out.println("Tile Number: " + originalTile.tileNumber + " has " + sidesMatching + " matching sides.");
                puzzleAnswer = puzzleAnswer * originalTile.tileNumber;
                firstCorner = originalTile;
            }
        }
        if(firstCorner.connectedTiles.get("Right").leftSide == null) {
            firstCorner.flipHorizontal();
        }
        if(firstCorner.connectedTiles.get("Bottom").leftSide == null) {
            firstCorner.flipVertical();
        }
        int piecesPerRow = (int) Math.sqrt(puzzlePieces.size());
        for(int i = 0;i < piecesPerRow;i++) {
            ArrayList<PuzzlePiece> rowPieces = new ArrayList<>();
            if(i == 0) {
                rowPieces.add(firstCorner);
            } else {
                rowPieces.add(connectedPuzzle.get(i-1).get(0).connectedTiles.get("Bottom"));
                int k = 0;
                while(!connectedPuzzle.get(i-1).get(0).bottomSide.equals(rowPieces.get(0).topSide)) {
                    if(k < 4) {
                        rowPieces.get(0).rotate();
                        k++;
                    } else {
                        rowPieces.get(0).flipHorizontal();
                        k = 0;
                    }
                }
            }
            for(int j = 0;j < piecesPerRow-1;j++) {
                rowPieces.add(rowPieces.get(j).connectedTiles.get("Right"));
                int k = 0;
                while(!rowPieces.get(j+1).leftSide.equals(rowPieces.get(j).rightSide)) {
                    if(k < 4) {
                        rowPieces.get(j+1).rotate();
                        k++;
                    } else {
                        rowPieces.get(j+1).flipHorizontal();
                        k = 0;
                    }
                }
            }
            connectedPuzzle.put(i, rowPieces);
        }
        int rowLength = connectedPuzzle.get(0).size()*8;
        Character[][] puzzleWithoutBorders = new Character[rowLength][rowLength];
        for(int i = 0;i < connectedPuzzle.size();i++) {
            for(int j = 0;j < connectedPuzzle.get(i).size();j++) {
                for(int k = 0;k < 8;k++) {
                    for(int l = 0;l < 8;l++) {
                        puzzleWithoutBorders[k+i*rowLength/connectedPuzzle.get(i).size()][l+j*rowLength/connectedPuzzle.get(i).size()] = connectedPuzzle.get(i).get(j).puzzleRows.get(k+1).charAt(l+1);
                    }
                }
            }
        }
        Character[][] seaMonster = new Character[3][20];
        for(int i = 0;i < 3;i++) {
            for(int j = 0;j < 20;j++) {
                seaMonster[i][j] = ' ';
            }
        }
        seaMonster[1][0] = '#';
        seaMonster[2][1] = '#';
        seaMonster[2][4] = '#';
        seaMonster[1][5] = '#';
        seaMonster[1][6] = '#';
        seaMonster[2][7] = '#';
        seaMonster[2][10] = '#';
        seaMonster[1][11] = '#';
        seaMonster[1][12] = '#';
        seaMonster[2][13] = '#';
        seaMonster[2][16] = '#';
        seaMonster[1][17] = '#';
        seaMonster[0][18] = '#';
        seaMonster[1][18] = '#';
        seaMonster[1][19] = '#';
        int timeToFlip = 0;
        while(seaMonsters == 0) {
            if(timeToFlip < 4) {
                Character[][] tempArray = new Character[rowLength][rowLength];
                for(int i = rowLength-1;i > -1;i--) {
                    for(int j = 0;j < rowLength;j++) {
                        tempArray[j][rowLength-i-1] = puzzleWithoutBorders[i][j];
                    }
                }
                puzzleWithoutBorders = tempArray.clone();
            } else {
                Character[][] tempArray = new Character[rowLength][rowLength];
                for(int i = 0;i < rowLength;i++) {
                    for(int j = rowLength;j > 0;j--) {
                        tempArray[i][rowLength-j] = puzzleWithoutBorders[i][j];
                    }
                }
                puzzleWithoutBorders = tempArray.clone();
                timeToFlip = 0;
            }
            for(int i = 0;i < puzzleWithoutBorders.length - 2;i++) {
                for(int j = 0;j < puzzleWithoutBorders.length - 20;j++) {
                    int tempSeaMonster = 0;
                    for(int k = 0;k < 3;k++) {
                        for(int l = 0;l < 20;l++) {
                            if(Character.compare(puzzleWithoutBorders[i+k][j+l], seaMonster[k][l]) == 0 && Character.compare(seaMonster[k][l], '#') == 0) {
                                tempSeaMonster++;
                            }
                        }
                    }
                    if(tempSeaMonster == 15) {
                        seaMonsters++;
                    }
                }
            }
            timeToFlip++;
        }
        int poundCount = 0;
        for(int i = 0;i < rowLength;i++) {
            for(int j = 0;j < rowLength;j++) {
                if(Character.compare(puzzleWithoutBorders[i][j], '#') == 0) {
                    poundCount++;
                }
            }
        }
        System.out.println("Total Number of Pound Signs in Puzzle: " + poundCount);
        System.out.println("Total Number of Sea Monsters: " + seaMonsters);
        System.out.println("Puzzle Answer: " + (poundCount - 15*seaMonsters));
    }
}
