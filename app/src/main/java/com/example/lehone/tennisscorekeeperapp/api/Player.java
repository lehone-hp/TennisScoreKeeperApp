package com.example.lehone.tennisscorekeeperapp.api;

public class Player {

    private String name;
    private int[] setScores;
    private int gameScore;
    private int currentSet;

    private static int currentGame = 0;

    public Player() {
        setScores = new int[3];
        gameScore = 0;
        currentSet = 0;
        currentGame++;
    }

    public int getCurrentSetScore() {
        return setScores[currentSet];
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

    public static int getCurrentGame() {
        return currentGame;
    }

    public static void setCurrentGame(int currentGame) {
        Player.currentGame = currentGame;
    }
}
