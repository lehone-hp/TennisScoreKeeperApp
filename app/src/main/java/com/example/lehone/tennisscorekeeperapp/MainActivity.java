package com.example.lehone.tennisscorekeeperapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.lehone.tennisscorekeeperapp.api.Player;

public class MainActivity extends AppCompatActivity {

    private Player player1;
    private Player player2;

    private final int MAX_SETS = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player1 = new Player(MAX_SETS);
        player2 = new Player(MAX_SETS);

        displayPlayer1SetScore();
        displayPlayer2SetScore();
    }

    public void addPlayer1Game(View view) {
        // TODO check if player 1 has won then display a dialog
        if (!gameIsFinished()) {
            player1.incrementGame();

            // check if the game score is 50. if so,
            if (player1.getGameScore() == 50) {

                // check if the current set is less than 6 and increment the current set's score
                if (player1.getCurrentSetScore() < 6 && player2.getCurrentSetScore() < 6) {
                    player1.incrementCurrentSetScore();

                    // if player 1 won the set
                    if (player1.getCurrentSetScore() == 6) {
                        player1.incrementSetsWon();
                    }
                } else {
                    // else go to the next set and increment it
                    if (player1.getCurrentSet() < player1.getMaxSets()-1) {
                        player1.setCurrentSet(player1.getCurrentSet()+1);
                        player1.incrementCurrentSetScore();
                    }
                }

                // reset the game scores
                player1.setGameScore(0);
                player2.setGameScore(0);
            }
        }

        // display current game score for both player 1 and 2
        displayPlayer1GameScore();
        displayPlayer2GameScore();

        // display the current set score for both player 1 and 2
        displayPlayer1SetScore();
        displayPlayer2SetScore();


    }

    public void addPlayer2Game(View view) {
        if (!gameIsFinished()) {
            player2.incrementGame();

            if (player2.getGameScore() == 50) {

                if (player2.getCurrentSetScore() < 6 && player1.getCurrentSetScore() < 6) {
                    player2.incrementCurrentSetScore();

                    // if player 2 won the set
                    if (player2.getCurrentSetScore() == 6) {
                        player2.incrementSetsWon();
                    }
                } else {
                    if (player1.getCurrentSet() < player1.getMaxSets()-1) {
                        player2.setCurrentSet(player2.getCurrentSet() + 1);
                        player2.incrementCurrentSetScore();
                    }
                }

                player1.setGameScore(0);
                player2.setGameScore(0);
            }
        }

        displayPlayer1GameScore();
        displayPlayer2GameScore();
        displayPlayer1SetScore();
        displayPlayer2SetScore();

    }

    public void displayPlayer1GameScore() {
        TextView gameView = (TextView) findViewById(R.id.player1_game);

        try {
            gameView.setText(String.valueOf(player1.getGameScore()));
        } catch (Exception e) {}
    }

    public void displayPlayer2GameScore() {
        TextView gameView = (TextView) findViewById(R.id.player2_game);

        try {
            gameView.setText(String.valueOf(player2.getGameScore()));
        } catch (Exception e) {}
    }

    public void displayPlayer1SetScore() {
        TextView scoreView = null;

        if (player1.getCurrentSet() == 0) {
            scoreView = (TextView) findViewById(R.id.player1_set1);
        } else {
            if (player1.getCurrentSet() == 1) {
                scoreView = (TextView) findViewById(R.id.player1_set2);
            } else {
                if (player1.getCurrentSet() == 2) {
                    scoreView = (TextView) findViewById(R.id.player1_set3);
                }
            }
        }

        try {
            scoreView.setText(String.valueOf(player1.getCurrentSetScore()));
        } catch (Exception e) {}
    }

    public void displayPlayer2SetScore() {
        TextView scoreView = null;

        if (player2.getCurrentSet() == 0) {
            scoreView = (TextView) findViewById(R.id.player2_set1);
        } else {
            if (player2.getCurrentSet() == 1) {
                scoreView = (TextView) findViewById(R.id.player2_set2);
            } else {
                if (player2.getCurrentSet() == 2) {
                    scoreView = (TextView) findViewById(R.id.player2_set3);
                }
            }
        }

        try {
            scoreView.setText(String.valueOf(player2.getCurrentSetScore()));
        } catch (Exception e) {}
    }

    public boolean gameIsFinished() {
        boolean retVal = false;
        String message = "";

        if (player1.getSetsWon() == (player1.getMaxSets() / 2) + 1){
            message = "Winner Player 1";
            retVal = true;
        } else {
            if (player2.getSetsWon() == (player2.getMaxSets() / 2) + 1){
                message = "Winner Player 2";
                retVal = true;
            }
        }

        if (retVal) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage(message);
            builder1.setCancelable(true);
            builder1.setNegativeButton(
                    "Cancel",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert1 = builder1.create();
            alert1.show();

        }

        return retVal;
    }

    public void resetGame(View view) {
        for (int i=MAX_SETS-1; i>=0; i--) {
            player1.setCurrentSet(i);

            player1.setCurrentSetScore(0);
            player2.setCurrentSetScore(0);

            displayPlayer1SetScore();
            displayPlayer2SetScore();
        }

        player1 = new Player(MAX_SETS);
        player2 = new Player(MAX_SETS);
        displayPlayer1GameScore();
        displayPlayer2GameScore();

    }
}
