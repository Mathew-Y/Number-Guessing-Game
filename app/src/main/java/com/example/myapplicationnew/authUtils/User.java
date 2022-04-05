package com.example.myapplicationnew.authUtils;

import java.util.ArrayList;

public class User {
    private String username;
    private String passwordHash;
    private ArrayList<Integer> easyScores;
    private ArrayList<Integer> mediumScores;
    private ArrayList<Integer> hardScores;
    private String securityResponseOne;
    private String securityResponseTwo;
    private String securityResponseThree;

    public User(String username, String passwordHash, String securityResponseOne, String securityResponseTwo, String securityResponseThree) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.securityResponseOne = securityResponseOne;
        this.securityResponseTwo = securityResponseTwo;
        this.securityResponseThree = securityResponseThree;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public ArrayList<Integer> getEasyScores() {
        return easyScores;
    }

    public void setEasyScores(ArrayList<Integer> easyScores) {
        this.easyScores = easyScores;
    }

    public ArrayList<Integer> getMediumScores() {
        return mediumScores;
    }

    public void setMediumScores(ArrayList<Integer> mediumScores) {
        this.mediumScores = mediumScores;
    }

    public ArrayList<Integer> getHardScores() {
        return hardScores;
    }

    public void setHardScores(ArrayList<Integer> hardScores) {
        this.hardScores = hardScores;
    }

    public String getSecurityResponseOne() {
        return securityResponseOne;
    }

    public void setSecurityResponseOne(String securityResponseOne) {
        this.securityResponseOne = securityResponseOne;
    }

    public String getSecurityResponseTwo() {
        return securityResponseTwo;
    }

    public void setSecurityResponseTwo(String securityResponseTwo) {
        this.securityResponseTwo = securityResponseTwo;
    }

    public String getSecurityResponseThree() {
        return securityResponseThree;
    }

    public void setSecurityResponseThree(String securityResponseThree) {
        this.securityResponseThree = securityResponseThree;
    }

}
