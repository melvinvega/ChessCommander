package com.capstone.chesscommander.chesscommander;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Melvin on 3/15/16.
 */
public class pve_options extends Activity {
    private Boolean difficultySelected, pieceColorSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pve_options);
        difficultySelected = false;
        pieceColorSelected = false;
    }

    public void onpveEasyDifficultyClick(View view) {
        difficultySelected = true;
    }

    public void onpveMediumDifficultyClick(View view) {
        difficultySelected = true;
    }

    public void onpveHardDifficultyClick(View view) {
        difficultySelected = true;
    }


    public void onpveWhiteColorClick(View view) {
        pieceColorSelected = true;
    }

    public void onpveBlackColorClick(View view) {
        pieceColorSelected = true;
    }

    public void onpveStartGameButtonClick(View view) {

        if (difficultySelected == false | pieceColorSelected == false) {
            if (difficultySelected == false) {
                String message = "Must Select a difficulty";
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }
            if (pieceColorSelected == false) {
                String message = "Must select the piece color";
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }
        } else {
            //Intent intent_start = new Intent();
            //String game_T = intent_start.getStringExtra("GameType");
            //String message = "Starting Game";
            Bundle extras = getIntent().getExtras();
            String game_T = extras.getString("GameType");
            Toast.makeText(this, game_T, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,game_screen.class);
            startActivity(intent);

        }
    }
}