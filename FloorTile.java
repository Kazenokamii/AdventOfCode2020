/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kaz.aoc.day24;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author dhc10
 */
public class FloorTile {
    String tileId;
    String tileColor;
    ArrayList<FloorTile> adjacentTiles = new ArrayList<>();
    int northTiles;
    int eastTiles;
    
    public FloorTile() {
        
    }

    public FloorTile(int north, int east) {
        this.tileId = tileId;
        this.tileColor = "White";
        this.northTiles = north;
        this.eastTiles = east;
    }
    
    public void flipTile() {
        if(this.tileColor.equals("White")) {
            this.tileColor = "Black";
        } else {
            this.tileColor = "White";
        }
    }
}
