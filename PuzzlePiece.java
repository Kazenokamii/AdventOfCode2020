/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kaz.aoc.day20;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author dhc10
 */
public class PuzzlePiece {
    ArrayList<String> puzzleRows;
    int tileNumber;
    String topSide;
    String leftSide;
    String rightSide;
    String bottomSide;
    HashMap<String, PuzzlePiece> connectedTiles = new HashMap<>();
    ArrayList<String> puzzleSides = new ArrayList<>();
    
    public PuzzlePiece() {
        
    }

    public PuzzlePiece(Integer tileNumber, ArrayList<String> puzzlePiece) {
        this.tileNumber = tileNumber;
        this.puzzleRows = puzzlePiece;
        setTopSide();
        setLeftSide();
        setRightSide();
        setBottomSide();
        setPuzzleSides();
        this.connectedTiles.put("Left", new PuzzlePiece());
        this.connectedTiles.put("Right", new PuzzlePiece());
        this.connectedTiles.put("Top", new PuzzlePiece());
        this.connectedTiles.put("Bottom", new PuzzlePiece());
    }
    
    public void setTopSide() {
        this.topSide = this.puzzleRows.get(0);
    }
    
    public String getTopSide() {
        return this.topSide;
    }
    
    public void setLeftSide() {
        this.leftSide = "";
        for(int i = 0;i < 10;i++) {
            this.leftSide += this.puzzleRows.get(i).charAt(0);
        }
    }
    
    public String getLeftSide() {
        return this.leftSide;
    }
    
    public void setRightSide() {
        this.rightSide = "";
        for(int i = 0;i < 10;i++) {
            this.rightSide += this.puzzleRows.get(i).charAt(9);
        }
    }
    
    public String getRightSide() {
        return this.rightSide;
    }
    
    public void setBottomSide() {
        this.bottomSide = this.puzzleRows.get(9);
    }
    
    public String getBottomSide() {
        return this.bottomSide;
    }
    
    public void rotate () {
        ArrayList<String> newPuzzlePiece = new ArrayList<>();
        for(int i = 9;i > - 1;i--) {
            String puzzleRow = "";
            for(int j = 0;j < 10;j++) {
                puzzleRow += this.puzzleRows.get(j).charAt(i);
            }
            newPuzzlePiece.add(puzzleRow);
        }
        this.puzzleRows = newPuzzlePiece;
        setTopSide();
        setLeftSide();
        setRightSide();
        setBottomSide();
        setPuzzleSides();
        PuzzlePiece topPiece = this.connectedTiles.get("Top");
        PuzzlePiece leftPiece = this.connectedTiles.get("Left");
        PuzzlePiece bottomPiece = this.connectedTiles.get("Bottom");
        PuzzlePiece rightPiece = this.connectedTiles.get("Right");
        this.connectedTiles.put("Top", rightPiece);
        this.connectedTiles.put("Left", topPiece);
        this.connectedTiles.put("Bottom", leftPiece);
        this.connectedTiles.put("Right", bottomPiece);
    }
    
    public void flipHorizontal () {
        ArrayList<String> newPuzzlePiece = new ArrayList<>();
        for(int i = 0;i < 10;i++) {
            String puzzleRow = "";
            for(int j = 9;j > -1;j--) {
                puzzleRow += this.puzzleRows.get(i).charAt(j);
            }
            newPuzzlePiece.add(puzzleRow);
        }
        this.puzzleRows = newPuzzlePiece;
        setTopSide();
        setLeftSide();
        setRightSide();
        setBottomSide();
        setPuzzleSides();
        PuzzlePiece leftPiece = this.connectedTiles.get("Left");
        PuzzlePiece rightPiece = this.connectedTiles.get("Right");
        this.connectedTiles.put("Left", rightPiece);
        this.connectedTiles.put("Right", leftPiece);
    }
    
    public void flipVertical () {
        ArrayList<String> newPuzzlePiece = new ArrayList();
        for(int i = 9;i > -1;i--) {
            newPuzzlePiece.add(this.puzzleRows.get(i));
        }
        this.puzzleRows = newPuzzlePiece;
        setTopSide();
        setLeftSide();
        setRightSide();
        setBottomSide();
        setPuzzleSides();
        PuzzlePiece topPiece = this.connectedTiles.get("Top");
        PuzzlePiece bottomPiece = this.connectedTiles.get("Bottom");
        this.connectedTiles.put("Top", bottomPiece);
        this.connectedTiles.put("Bottom", topPiece);
    }
    
    public ArrayList<String> getPuzzleSides() {
        return this.puzzleSides;
    }
    
    public void setPuzzleSides() {
        this.puzzleSides.clear();
        this.puzzleSides.add(this.topSide);
        this.puzzleSides.add(this.leftSide);
        this.puzzleSides.add(this.rightSide);
        this.puzzleSides.add(this.bottomSide);
    }
}
