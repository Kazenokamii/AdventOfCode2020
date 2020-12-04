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
import java.util.Scanner;

/**
 *
 * @author dhc10
 */
public class Day4 {
	
    public static void main(String args[]) {
        ArrayList<String> uncheckedPassports = new ArrayList<String>();
        ArrayList<String> requiredFieldsPresentPassports = new ArrayList<String>();
        String[] requiredData = new String[] {"ecl", "pid", "eyr", "hcl", "byr", "iyr", "hgt"};
        ArrayList<String> allowedEcls = new ArrayList<String>();
        allowedEcls.add("amb");
        allowedEcls.add("blu");
        allowedEcls.add("brn");
        allowedEcls.add("gry");
        allowedEcls.add("grn");
        allowedEcls.add("hzl");
        allowedEcls.add("oth");
        Boolean valid = true;
        int byr;
        int iyr;
        int eyr;
        String hgt;
        String hcl;
        String ecl;
        String pid;
        int fullyValidatedPassports = 0;
        try {
            //Read in Passport Data and separate passports based on blank lines
            //Note - Had to add in two New Lines at the end of the file to pick up last passport
            File passports = new File("Day4PassportsFull.txt");
            Scanner myReader = new Scanner(passports);
            String fullPassport = "";
            while(myReader.hasNextLine()) {
                String data = myReader.nextLine();
                fullPassport = fullPassport + " " + data;
                if(data.equals("")) {
                    uncheckedPassports.add(fullPassport);
                    fullPassport = "";
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("No File Found");
            e.printStackTrace();
        }
        int validPassports = 0;
        System.out.println("Number of Passports: " + uncheckedPassports.size());
        //Check each passport for all required fields
        for (String validPassport : uncheckedPassports) {
            valid = true;
            for (String requiredField : requiredData) {
                if(!validPassport.contains(requiredField)) {
                    valid = false;
                    break;
                }
            }
            if(valid) {
                validPassports+=1;
                requiredFieldsPresentPassports.add(validPassport);
            }
        }
        System.out.println("Number of Valid Passports: " + validPassports);
        for (String presentField : requiredFieldsPresentPassports) {
            //System.out.println(presentField);
            valid = true;
            String[] fields = presentField.split("\\s");
            for (String data : fields) {
                String[] dataSplit = data.split(":");
                if(dataSplit[0].equals("byr")) {
                    //Byr must be between 1920 and 2002
                    byr = Integer.parseInt(dataSplit[1]);
                    //System.out.println("Birth Year is: " + byr);
                    if(byr < 1920 || byr > 2002) {
                        //System.out.println("Invalid");
                        valid = false;
                        break;
                    }
                } else if(dataSplit[0].equals("iyr")) {
                    //Iyr must be between 2010 and 2020
                    iyr = Integer.parseInt(dataSplit[1]);
                    //System.out.println("Issue Year is: " + iyr);
                    if(iyr < 2010 || iyr > 2020) {
                        //System.out.println("Invalid");
                        valid = false;
                        break;
                    }
                } else if(dataSplit[0].equals("eyr")) {
                    //Eyr must be between 2020 and 2030
                    eyr = Integer.parseInt(dataSplit[1]);
                    //System.out.println("Expiration Year is: " + eyr);
                    if(eyr < 2020 || eyr > 2030) {
                        //System.out.println("Invalid");
                        valid = false;
                        break;
                    }
                } else if(dataSplit[0].equals("hgt")) {
                    String height[];
                    //Hgt must be between 150 and 193 cm or 59 and 76 in
                    if(dataSplit[1].length() == 5 && dataSplit[1].charAt(3) == 'c') {
                        height = dataSplit[1].split("c");
                        int heightInCm = Integer.parseInt(height[0]);
                        //System.out.println("Height in Cm is: " + heightInCm);
                        if(heightInCm < 150 || heightInCm > 193) {
                            //System.out.println("Invalid");
                            valid = false;
                            break;
                        }
                    } else if(dataSplit[1].length() == 4 && dataSplit[1].charAt(2) == 'i') {
                        height = dataSplit[1].split("i");
                        int heightInIn = Integer.parseInt(height[0]);
                        //System.out.println("Height in In is: " + heightInIn);
                        if(heightInIn < 59 || heightInIn > 76) {
                            //System.out.println("Invalid");
                            valid = false;
                            break;
                        }
                    } else {
                        //System.out.println("Height is Invalid");
                        valid = false;
                        break;
                    }
                } else if(dataSplit[0].equals("hcl")) {
                    //Hcl must be a # followed by six characters 0-9 or a-f
                    if(dataSplit[1].charAt(0) == '#') {
                        //System.out.println("Hair Color is: " + dataSplit[1]);
                        if(!(dataSplit[1].length() == 7 && dataSplit[1].substring(1).matches("-?[0-9a-f]+"))) {
                            //System.out.println("Invalid");
                            valid = false;
                            break;
                        }
                    } else {
                        //System.out.println("Hair Color is Invalid");
                        valid = false;
                        break;
                    }
                } else if(dataSplit[0].equals("ecl")) {
                    //Ecl must be amb, blu, brn, gry, grn, hzl, oth
                    //System.out.println("Eye Color is: " + dataSplit[1]);
                    if(!allowedEcls.contains(dataSplit[1])) {
                        //System.out.println("Invalid");
                        valid = false;
                        break;
                    }
                } else if(dataSplit[0].equals("pid")) {
                    //Pid must be a 9-digit number including leading 0s
                    //System.out.println("PID is: " + dataSplit[1]);
                    if(!((dataSplit[1].length() == 9) && dataSplit[1].matches("[0-9]+"))) {
                        //System.out.println("Invalid");
                        valid = false;
                        break;
                    }
                }
            }
            if(valid) {
                fullyValidatedPassports+=1;
                System.out.println(presentField);
            }
        }
        System.out.println("Number of Fully Validated Passports: " + fullyValidatedPassports);
    }
}