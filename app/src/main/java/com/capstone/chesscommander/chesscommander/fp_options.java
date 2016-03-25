package com.capstone.chesscommander.chesscommander;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Melvin on 3/23/16.
 */
public class fp_options extends Activity {

    private Button nextButton;
    private String currentMove,playAs,difficutly,opponentType;
    private boolean currentMoveSelected,playAsSelected,difficultySelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        currentMoveSelected = false;
        playAsSelected = false;
        difficultySelected = false;
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        opponentType = extras.getString("OpponentType");
        /*
           If the user selected to go against a player show the board setupscreen instead of the
           additional options.Since the options in this screen are basically irrelevant for the user.
           Else show the computer options.
         */
        if(extras.getString("OpponentType").equals("Player")){
            Intent boardScreenIntent = new Intent(this,fp_boardSetup.class);
            boardScreenIntent.putExtra("GameType", "fp");
            boardScreenIntent.putExtra("OponentType", "Player");
            startActivity(boardScreenIntent);
            /*
            * This finish is here so that when the user presses back on the device it shows the home
            * screen instead of this one, this is to prevent the user from seeing a blank screen.
            */
            finish();
        }
        else{
            setContentView(R.layout.fp_options);
        }
        nextButton = (Button) findViewById(R.id.freeplay_next_button);

    }

    public void onRadioButtonClick(View view){
        String tagName = (String)view.getTag();
        switch (tagName){
            case "whiteMove":
                currentMove = "white";
                currentMoveSelected = true;
                break;
            case "blackMove":
                currentMove = "black";
                currentMoveSelected = true;
                break;
            case "whitePlay":
                playAs = "white";
                playAsSelected = true;
                break;
            case "blackPlay":
                playAs = "black";
                playAsSelected = true;
                break;
            case "easy":
                difficutly = "easy";
                difficultySelected = true;
                break;
            case "medium":
                difficutly = "medium";
                difficultySelected = true;
                break;
            case "hard":
                difficutly = "hard";
                difficultySelected = true;
                break;
        }
    }

    public void onNextButtonClick(View view){
        if(currentMoveSelected && playAsSelected && difficultySelected){
            Intent fpOptionsScreenIntent = new Intent(this,fp_boardSetup.class);
            fpOptionsScreenIntent.putExtra("CurrentMove",currentMove);
            fpOptionsScreenIntent.putExtra("PlayAs",playAs);
            fpOptionsScreenIntent.putExtra("Difficulty",difficutly);
            fpOptionsScreenIntent.putExtra("OpponentType",opponentType);
            startActivity(fpOptionsScreenIntent);
        }
    }
}
