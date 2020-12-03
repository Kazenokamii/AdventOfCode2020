/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kaz.aoc.day3;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author dhc10
 */
public class Day3 {
	
    public static void main(String args[]) {
        String[] treeMap = new String[] {".....##.#.....#........#....##.","....#...#...#.#.......#........",".....##.#......#.......#.......","...##.........#...#............","........#...#.......#.........#","..........#......#..#....#....#","..................#..#..#....##",".....##...#..#..#..#..#.##.....","..##.###....#.#.........#......","#.......#......#......#....##..",".....#..#.#.......#......#.....","............#............#.....","...#.#........#........#.#.##.#",".#..#...#.....#....##..........","##..........#...#...#..........","...........#...###...#.......##",".#..#............#........#....","##.#..#.....#.......#.#.#......",".##.....#....#.#.......#.##....","..##...........#.......#..##.#.","##...#.#........#..#...#...#..#",".#..#........#.#.......#..#...#",".##.##.##...#.#............##..","..#.#..###......#..#......#....",".#..#..#.##.#.##.#.#...........","...#....#..#.#.#.........#..#..","......#.#....##.##......#......","#....#.##.##....#..#...........","...#.#.#.#..#.#..#.#..#.##.....","#.....#######.###.##.#.#.#.....","..#.##.....##......#...#.......","..#....#..#...##.#..#..#..#..#.",".............#.##....#.........",".#....#.##.....#...............",".#............#....#...#.##....",".#.....#.##.###.......#..#.....",".#...#.........#.......#..#....","..#.#..#.##.......##...........",".....##..#..#..#..#.##..#.....#","..##............##...#..#......","...#..#....#..##.....##..#.#...","#.....##....#.#.#...#...#..##.#","#.#..#.........#.##.#...#.#.#..",".....#.#....##....#............","#.......#..#.....##..#...#...#.",".....#.#...#...#..#......#.....","..##....#.#.#.#.#..#...........","##..#...#.........#......#...#.","..#...#.#.#.#..#.#.##..##......","#............###.....###.......","..........#...#........###.....",".......##...#...#...#........#.",".#..#.##.#.....................",".#..##........##.##...#.......#",".......##......#.....#......#..",".##.#.....#......#......#......","#...##.#.#...#.#...............","........#..#...#.##.......#....","...................#...#...##..","...#...#.........#.....#..#.#..",".###..#........#..##.##..#.##..","#...#.....#.....#.....#..#..#..","###..#.....#.#.#.#......#....#.","#........#....##.#...##........",".#.#..##........##....##.#.#...","#...#....#.###.#.#.........#...","...#...##..###.......#.........","......#....#..##..#.....#.#....","........#...##...###......##...","..........##.#.......##........","...#....#......#...##.....#....","###.#.....#.#..#..#....#...#..#",".#.....#.#....#...............#","..#....#....####....###....#.#.","....##........#..#.##.#....#...",".......##...#...#..#....####...","#...##.#......##...#..#........","..##..#.##....#.......##.#.#...","..#.#...............#...#.#....","....#.....#.#.....#.##.......#.","...#.#..##.#.#..............##.","..#.....#...#.............#.##.","##..#.#...#........#..#.....##.","...........##...#.#.###...#....","...#.#.#..#..................#.",".#...##.............#...#......","..#..#...#.#.......#...#.....#.","..##.......#.#.................",".##..#........###.....#....#.##","......#..###.......#....##....#","....#.....#.................#..","........#...#...#..............","...#..#.###.......#..#.#.#.##..","..#...#.....#....#.........#...","...#.............#........###..","......#..............#......#..","#..#...........#...#..........#","...##...#.###..#...#.....#.#...","....#..##......#.......##......","....#....##.#...#.#..#....#...#",".#...........#..#....##...#..##","..#.#.................###.#...#","..#.#.#...##...........#.......","..........#..##...#.#..##....##","........#........#.##..#.#...#.",".....#...##.......##......#...#","....#...#..#..#.....#..........",".#..#......#..#..#..###.......#",".##..........#...#...#.#.....##","..#..........#.#.#...###.......","....#................#...##....",".##..#....#..........#.#.#.....","..##...#.#........#.....#.##...","....####.....#..#.........##..#","......#.........#...#..........","....#...................#..##..",".##....#.#.........#....#...#..","....##...##.....#..####........","..##.#....#.#.......##...#.....","#...#.#.#...#..#..##.....#.....","#..................###.....#...","#.#.....#.......#.#...###.#....",".#..#....#............#........","#.#....#..#.#...............#..","..#..#..#.............#......#.","..#.......##...................",".#....#.........#....#.#.#..#..","....#....#..#...............#..","......#..#..##......#.........#","..#.##........##......#..#..#.#","#.....#.#....#.........##...#..","###..............#....###...##.","....#..##......#.......##......","......#...#.##......##....#..#.","..........#....#..##.......#..#",".#..#...##..#...........#..#..#",".....#....#...#..###...###....#",".#####..#...#.#.#..#.#.###...##","..##............##.#...#.##...#",".##..#...#...#....##.#..#..##..",".#....#...#............##..#...",".#.#......#....#....#..##..##..",".........#...#.......#.##..#...","#.........#.....##.....#..#..#.","...##.#...#...#..#..#....##..##",".#............#...#....##......","..#...#.##.........#.#......#.#","....#.##........#.........#..##","#.........#......#.#......#..#.","........#.#.......#.#........#.","..#..........##.#...#..#.#.....","..#...#....#...#...#..#.#..#.#.",".#.........#....#..#####..#....","#.#....#.#.###...#.............","..##...........##......##......","#.....#..#....#...............#","...#.#..#....##......#...##....","...#........#.....#...#..#.....",".#......##.........#......#....","..#..###.##...#.#.....#........",".............#......#..#.......","..#...............#.#...#..#..#",".......#..#...#.#####......#..#",".........#.....#...............","##........#............#.#.....",".#...#.....#..#..#...#....#...#","..#....#....##......##.....#.#.","#...##..##......#...#....#.....","....#.#.#.....###....##.##....#","..........##...##.......#......","..#.......#...##.#....##.##....","....#........................#.","...#...#.#.##...#.....#...#..#.",".#....##..#..#..........##..##.",".#.....#..#...#.##.....#.......",".#.##...#.#..#.....##....#...#.",".##...#........##....#..#......",".....#........#..........#.#..#","....#..##.......#..#.....#.....","...........#...#........#.##..#",".....#..#....#..#.#.....#....##",".....#....#.##.#..##...........","...##.......##.........#.......","...............##..#....#.#....",".......###..#........#..####.##",".......#.##...#.#....#.####....","....#...............#..........","##.#.......#.....#......#...#..","......##.....#....#.....#..#..#",".....#...##.............#......","#.#.##.#.....#..#........#.....","......##....#..#........#......","............#........#..#.#....","##.......#......#...####..#.##.","..##..#...#.............#.##...",".....#..##......#.##......###..","............#........#........#","#.#.#.#...#.#.....#.........#..",".........#...............#.....",".............###.#.......#....#","###.##..#..#..........#....#...","#......#...#..#..#.....#.##....","............#....#....#..#.....","..#.#....#...#......#.#..#..##.","...#........................#..","#.#...#..........#......#.#....",".........#................#...#","##.....#....#........##.......#","#...##........#...#...........#","...#...#..........##.......#.#.","..#.#.#....#......##...........","...#.#...#.##.#..#.#.##........","#....##.....###..#.......#.....","###.....#.#.#...#..#.........##","..#......#..###...#.#.#.....#.#",".#....#.....#............#..##.","....#....##..........#.....##..","#...........#....#...#..#...##.","..#.......#.....#..........#...",".#..#................#......#..","..#......#.#...#..#.#....#....#","...#..#...###..#..##....#.#....","..#..............#.....#.......","...#.#...#.........#.#.........","##......##...........##.#.##..#","..#..##..#....#.#......#.#...##","...#.###....###...#.....#......","#.#................#......#....","..#.....#.....#....##.......#..",".#.#...............##..#.......","...#....#.......#.#.....##..#..",".........#....#.......#.#...##.","#....#......##.#.........##...#","#.............#..##.#.#..##....","...#....#..#...#....#.#.#.#...#",".#....#....#..##.....#.#...###.","##............#.#...##.#..#.#..","##.#....##.....#..#..###....#..","##....#................##......","...##..#...#..###....#.....##..",".#...##......#..#.#.....#...#..","..##......##...#.##.......#....","......#.....#.....##........#.#","##....#...........#............","#.......#....#..#.##..##.#..#..",".#....##.#.....#..#..#.........",".#....#.#.#...#.....##.....#.#.",".......##.#.#........#......##.","##........#.##.......#...#..#..","...###..##....#.#....#.#.......","......#.......#...##.....#...#.","..#......##.#......#.....#.....",".....#.....###...#.............","#...#.#...#...#..#......#......","#.....#.......###.#....###.#...","...#.......#....####....##..#..","#.#.....#....#........#.......#",".........#.......#......#.#...#","..##....#.....##...............","..........#..#.#..#......#.....","..................##...##.#....","........#.......#...#..#.#.#...",".....#.#..##..#..#.#..#.......#",".....#........#..#..#....#....#","##............#..#..#...#....#.",".....#....................##..#","........##.#....###............","##.......#.##................#.",".....###.#..#..#...#....###.##.",".#......#.#....#.....##.#......","...##......##.........#...#....","....####..............#........","#...#.#..##..##.........##.....","......#......#....#..#.........","#.....#.....#.##...............","..#.##..#...##.#.####..#....###","#..#......#....#.##..##...#.#..","#....#.......#.....#.....#.#...","##.......#.....##...#.....#....","...#...##..........#..##..##..#",".###..#..##...#....#...#..#....","......##..###.......###...#....","....#...#.#.......#.##...##..##","#.#......#..##.#.#..#..#..#....","......#........#.......#.......","..........#.#.....##...........","......#..#........#..#.#..###..","##..#.............##..#........",".........#....#.....#.........#",".....#..##...#..#..##.##......#","###..#...........#.......#....#","...............#....#.#........",".##.#...#.#........##....#.....",".##.###...##..###....#...#...#.",".##..#....#.#.#...#.#.#.#...#..",".###.#...#.......#....#..#.....","..#..#.#.#.#........#.....##...",".#.......#.#...#.#...........##","...#.....##....#.....##...#....","................#.....####...#.",".#.#......#.......##...#.##....",".###.........#.#......#..#.#...","#......#...#....#..##.......#..",".##..#....#..#...........#...#.",".#...#.......##........#.##....","..#...........#...##...........",".....##....##......#....#..#...","#......#.#...#.##.#...##....#..","#....................#...##...#","..#............#........#......",".............#.........##.....#","...#...#......##.#...#...#.#...","..#...#.#.................#....","....##...#....#...###.##......#","...#....#...#..#...#....#.....#","...##.#........#..#.........#..","..##.....#..##...#.....##...#..","#.........#.#.#...#......#...#.","#.#...........#...#..#..#..##..","..#..#..##....#..........#.###.",".....#..#....#.#...#...#..#..#.","###.....#..#.................#.",".#..##.##.#......#....##..#...."};        
        //String[] treeMap = new String[] {"..##.......","#...#...#..",".#....#..#.","..#.#...#.#",".#...##..#.","..#.##.....",".#.#.#....#",".#........#","#.##...#...","#...##....#",".#..#...#.#"};
        ArrayList<Integer> treeCounts = new ArrayList<Integer>();
        Integer[] slopes = new Integer[] {1,1,3,1,5,1,7,1,1,2};
        for (int i = 0; i < slopes.length - 1; i = i+2) {
            int x = 0;
            int y = 0;
            int treeCount = 0;
            while(y < treeMap.length - 1) {
                x = x+slopes[i];
                y = y+slopes[i+1];
                if(x > treeMap[0].length() - 1) {
                    x = x - treeMap[0].length();
                }
                if(Character.compare(treeMap[y].charAt(x), '#') == 0) {
                    treeCount+=1;
                }   
            }
            System.out.println(treeCount);
            treeCounts.add(treeCount);
        }
        System.out.println(treeCounts);
        long product = 1;
        for (int tree : treeCounts) {
            product = product * tree;
            System.out.println(product);
        }
        System.out.println(product);
    }
}