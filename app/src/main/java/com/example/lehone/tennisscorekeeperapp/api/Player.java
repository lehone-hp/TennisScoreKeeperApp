package com.example.lehone.tennisscorekeeperapp.api;

public class Player {

    private String name;
    private int[] setScores;
    private int gameScore;
    private static int currentSet;
    private final int maxSets;
    private int setsWon;

    public Player(int maxSets) {
        this.maxSets = maxSets;
        setScores = new int[maxSets];
        gameScore = 0;
        currentSet = 0;
        setsWon = 0;
    }

    public void setGameScore(int gameScore) {
        this.gameScore = gameScore;
    }

    public int getCurrentSet() {
        return currentSet;
    }

    public void setCurrentSet(int currentSet) {
        this.currentSet = currentSet;
    }

    public int getCurrentSetScore() {
        return setScores[currentSet];
    }

    public void incrementCurrentSetScore() {
        int prevScore = getCurrentSetScore();
        setCurrentSetScore(prevScore+1);
    }

    public void setCurrentSetScore(int setScore) {
        setScores[currentSet] = setScore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getSetScores() {
        return setScores;
    }

    public void setSetScores(int[] setScores) {
        this.setScores = setScores;
    }

    public int getGameScore() {
        return gameScore;
    }

    public void incrementGame() {
        switch (gameScore){
            case 0:
                gameScore = 15;
                break;

            case 15:
                gameScore = 30;
                break;

            case 30:
                gameScore = 40;
                break;

            case 40:
                gameScore = 50;
                break;

            case 50:
                gameScore = 0;
                break;
        }
    }

    public int getMaxSets() {
        return maxSets;
    }

    public int getSetsWon() {
        return setsWon;
    }

    public void incrementSetsWon() {
        setsWon++;
    }
}
