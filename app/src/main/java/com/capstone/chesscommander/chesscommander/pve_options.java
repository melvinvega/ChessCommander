package com.capstone.chesscommander.chesscommander;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Melvin on 3/15/16.
 */
public class pve_options extends Activity {
    private Boolean difficultySelected, pieceColorSelected;
    private String difficultyValue,colorValue,gameType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pve_options);
        difficultySelected = false;
        pieceColorSelected = false;
    }

    public void onpveEasyDifficultyClick(View view) {
        difficultySelected = true;
        difficultyValue = "easy";
    }

    public void onpveMediumDifficultyClick(View view) {
        difficultySelected = true;
        difficultyValue = "medium";
    }

    public void onpveHardDifficultyClick(View view) {
        difficultySelected = true;
        difficultyValue = "hard";
    }


    public void onpveWhiteColorClick(View view) {
        pieceColorSelected = true;
        colorValue = "white";
    }

    public void onpveBlackColorClick(View view) {
        pieceColorSelected = true;
        colorValue = "black";
    }

    public void onpveStartGameButtonClick(View view) {

        if (!difficultySelected | !pieceColorSelected ) {
            if (!difficultySelected) {
                String message = "Must select a difficulty";
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }
            if (!pieceColorSelected) {
                String message = "Must select the piece color";
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }
        } else {
            //Intent intent_start = new Intent();
            //String game_T = intent_start.getStringExtra("GameType");
            //String message = "Starting Game";
            Bundle extras = getIntent().getExtras();
            gameType = extras.getString("GameType");
            //Toast.makeText(this, game_T, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,game_screen.class);
            intent.putExtra("GameType",gameType);
            intent.putExtra("Difficulty",difficultyValue);
            intent.putExtra("PlayerColor",colorValue);
            intent.putExtra("OpponentType","computer");

            startActivity(intent);

        }
    }
}