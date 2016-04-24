package com.capstone.chesscommander.chesscommander;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Melvin on 3/20/16.
 */
//
public class game_screen extends Activity {


    /*
    * prevId = hold the ID of the previous button pressed, this is used to know from where to take
    *          the background from and then set it to 0. -1 means no previous ID
    *
    * bgdraw = holds the background image while it is moved to the new button.
    *
    * empty  = determines if the button's background is empty or not, used to differentiated empty
    *          tiles from ones with a piece
    *
    * board  = a 2D array that represents the board, it holds all of the buttons.
    *          Used board[row][col]
    *             row[0-7] = 1-8
    *             col[0-7] = A-H
    * */
    private int prevId;
    private Drawable bgdraw;
    private boolean empty;

    private String gameType,difficulty,playerColor;
    private int[] tempBoard = new int[64];
    private String[] numberss = new String[8];

    private String[] rank = new String[6];
    private String[] column = new String[8];
    private String[] row = new String[8];

    private String[] queenOmophones = new String[10];
    private String[] kingOmophones = new String[10];
    private String[] rookOmophones = new String[15];
    private String[] knightOmophones = new String[10];
    private String[] bishopOmophones = new String[10];
    private String[] pawnOmophones = new String[10];

    private String[] aOmophones = new String[10];
    private String[] bOmophones = new String[10];
    private String[] cOmophones = new String[10];
    private String[] dOmophones = new String[10];
    private String[] eOmophones = new String[10];
    private String[] fOmophones = new String[10];
    private String[] gOmophones = new String[10];
    private String[] hOmophones = new String[10];

    private String[] Omophones1 = new String[10];
    private String[] Omophones2 = new String[10];
    private String[] Omophones3 = new String[10];
    private String[] Omophones4 = new String[10];
    private String[] Omophones5 = new String[10];
    private String[] Omophones6 = new String[10];
    private String[] Omophones7 = new String[10];
    private String[] Omophones8 = new String[10];

    private String[] castlingOmophones = new String[10];

    private String[] finalCommand = new String[2];

    private ImageButton[][] board = new ImageButton[8][8];
    private ImageButton[][] initialBoard = new ImageButton[8][8];
    private ImageButton A8_button,B8_button,C8_button,D8_button,E8_button,F8_button,G8_button,H8_button;
    private ImageButton A7_button,B7_button,C7_button,D7_button,E7_button,F7_button,G7_button,H7_button;
    private ImageButton A6_button,B6_button,C6_button,D6_button,E6_button,F6_button,G6_button,H6_button;
    private ImageButton A5_button,B5_button,C5_button,D5_button,E5_button,F5_button,G5_button,H5_button;
    private ImageButton A4_button,B4_button,C4_button,D4_button,E4_button,F4_button,G4_button,H4_button;
    private ImageButton A3_button,B3_button,C3_button,D3_button,E3_button,F3_button,G3_button,H3_button;
    private ImageButton A2_button,B2_button,C2_button,D2_button,E2_button,F2_button,G2_button,H2_button;
    private ImageButton A1_button,B1_button,C1_button,D1_button,E1_button,F1_button,G1_button,H1_button;
    private Bundle extras;
    private static final int SPEECH_REQUEST_CODE_PIECE = 0;
    private static final int SPEECH_REQUEST_CODE_POSITION = 1;
    private List ranklist,columnlist, rowlist;
    private List rookOmoList,pawnOmoList,knightOmoList,kingOmoList,queenOmoList,bishopOmoList;
    private List aOmoList,bOmoList,cOmoList,dOmoList,eOmoList,fOmoList,gOmoList,hOmoList;
    private List OmoList1,OmoList2,OmoList3,OmoList4,OmoList5,OmoList6,OmoList7,OmoList8;
    private List numbers,castling;


    private String[][] pawnMoves = new String[8][3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_screen);
        extras = getIntent().getExtras();
        gameType = extras.getString("GameType");
        difficulty = extras.getString("Difficulty");
        playerColor = extras.getString("PlayerColor");
        tempBoard = extras.getIntArray("Board");
        boardSetup(gameType);
        setPieceTags();
        setDescriptions();
        prevId = -1;
        initialBoard = board.clone();
        voiceKeyWordsArray();
        resetBoard();
        setupOmophones();
        setupLists();


    }

    public void onButtonClick(View view){
        empty = (view.getBackground()==null);

        if(empty && prevId==-1){
            //Do nothing
        }
        else if(!empty && prevId==-1){
            prevId = view.getId();

        }
        else if(empty && prevId>0){
            //Verify if legal move
            bgdraw = findViewById(prevId).getBackground();

            view.setBackground(bgdraw);
            view.setTag(R.id.tagpiece, findViewById(prevId).getTag(R.id.tagpiece));
            view.setTag(R.id.tagcolor, findViewById(prevId).getTag(R.id.tagcolor));
            CharSequence description = view.getContentDescription().subSequence(0,2);
            description = description + " " + view.getTag(R.id.tagcolor) + " " + view.getTag(R.id.tagpiece);
            view.setContentDescription(description);

            findViewById(prevId).setBackgroundResource(0);
            findViewById(prevId).setTag(R.id.tagpiece, "");
            findViewById(prevId).setTag(R.id.tagcolor, "");
            description = findViewById(prevId).getContentDescription().subSequence(0,2);
            findViewById(prevId).setContentDescription(description);
            prevId = -1;
        }
        else if(!empty && prevId>0){
            //Verify if legal move
            //Verify if capturable
        }
    }

    public void onOptionsButtonClick(View view){
        final Intent homeScreenIntent = new Intent(this,home_screen.class);
        homeScreenIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        CharSequence oponentType[] = new CharSequence[] {"Change Color", "Move List", "New Game","Home Screen"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Game Options");
        builder.setItems(oponentType, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0://Change Color
                        //To be implemented
                        break;
                    case 1://Move List
                        //Get move list from game
                        break;
                    case 2://New Game
                        board = initialBoard.clone();
                        resetBoard();
                        boardSetup(gameType);
                        setPieceTags();
                        setDescriptions();
                        prevId = -1;
                        break;
                    case 3://Home Screen
                        startActivity(homeScreenIntent);
                        finish();
                        break;
                }
            }
        });
        builder.show();
    }

    public void onVoiceButtonClick(View view){
        displaySpeechRecognizer(SPEECH_REQUEST_CODE_PIECE);
    }

    public void onUndoButtonClick(View view){
        //Ask engine for undo
    }

    public void onRedoButtonClick(View view){
        //ask game for redo
    }

    /*
    * Initial board setup,includes assigning the buttons,setting up the board array, and setting the
    * background of empty buttons to 0.
    * */
    private void boardSetup(String gameType){
        //ImageButton setup
        A8_button = (ImageButton)findViewById(R.id.A8_button);
        B8_button = (ImageButton)findViewById(R.id.B8_button);
        C8_button = (ImageButton)findViewById(R.id.C8_button);
        D8_button = (ImageButton)findViewById(R.id.D8_button);
        E8_button = (ImageButton)findViewById(R.id.E8_button);
        F8_button = (ImageButton)findViewById(R.id.F8_button);
        G8_button = (ImageButton)findViewById(R.id.G8_button);
        H8_button = (ImageButton)findViewById(R.id.H8_button);

        A7_button = (ImageButton)findViewById(R.id.A7_button);
        B7_button = (ImageButton)findViewById(R.id.B7_button);
        C7_button = (ImageButton)findViewById(R.id.C7_button);
        D7_button = (ImageButton)findViewById(R.id.D7_button);
        E7_button = (ImageButton)findViewById(R.id.E7_button);
        F7_button = (ImageButton)findViewById(R.id.F7_button);
        G7_button = (ImageButton)findViewById(R.id.G7_button);
        H7_button = (ImageButton)findViewById(R.id.H7_button);

        A6_button = (ImageButton)findViewById(R.id.A6_button);
        B6_button = (ImageButton)findViewById(R.id.B6_button);
        C6_button = (ImageButton)findViewById(R.id.C6_button);
        D6_button = (ImageButton)findViewById(R.id.D6_button);
        E6_button = (ImageButton)findViewById(R.id.E6_button);
        F6_button = (ImageButton)findViewById(R.id.F6_button);
        G6_button = (ImageButton)findViewById(R.id.G6_button);
        H6_button = (ImageButton)findViewById(R.id.H6_button);

        A5_button = (ImageButton)findViewById(R.id.A5_button);
        B5_button = (ImageButton)findViewById(R.id.B5_button);
        C5_button = (ImageButton)findViewById(R.id.C5_button);
        D5_button = (ImageButton)findViewById(R.id.D5_button);
        E5_button = (ImageButton)findViewById(R.id.E5_button);
        F5_button = (ImageButton)findViewById(R.id.F5_button);
        G5_button = (ImageButton)findViewById(R.id.G5_button);
        H5_button = (ImageButton)findViewById(R.id.H5_button);

        A4_button = (ImageButton)findViewById(R.id.A4_button);
        B4_button = (ImageButton)findViewById(R.id.B4_button);
        C4_button = (ImageButton)findViewById(R.id.C4_button);
        D4_button = (ImageButton)findViewById(R.id.D4_button);
        E4_button = (ImageButton)findViewById(R.id.E4_button);
        F4_button = (ImageButton)findViewById(R.id.F4_button);
        G4_button = (ImageButton)findViewById(R.id.G4_button);
        H4_button = (ImageButton)findViewById(R.id.H4_button);

        A3_button = (ImageButton)findViewById(R.id.A3_button);
        B3_button = (ImageButton)findViewById(R.id.B3_button);
        C3_button = (ImageButton)findViewById(R.id.C3_button);
        D3_button = (ImageButton)findViewById(R.id.D3_button);
        E3_button = (ImageButton)findViewById(R.id.E3_button);
        F3_button = (ImageButton)findViewById(R.id.F3_button);
        G3_button = (ImageButton)findViewById(R.id.G3_button);
        H3_button = (ImageButton)findViewById(R.id.H3_button);

        A2_button = (ImageButton)findViewById(R.id.A2_button);
        B2_button = (ImageButton)findViewById(R.id.B2_button);
        C2_button = (ImageButton)findViewById(R.id.C2_button);
        D2_button = (ImageButton)findViewById(R.id.D2_button);
        E2_button = (ImageButton)findViewById(R.id.E2_button);
        F2_button = (ImageButton)findViewById(R.id.F2_button);
        G2_button = (ImageButton)findViewById(R.id.G2_button);
        H2_button = (ImageButton)findViewById(R.id.H2_button);

        A1_button = (ImageButton)findViewById(R.id.A1_button);
        B1_button = (ImageButton)findViewById(R.id.B1_button);
        C1_button = (ImageButton)findViewById(R.id.C1_button);
        D1_button = (ImageButton)findViewById(R.id.D1_button);
        E1_button = (ImageButton)findViewById(R.id.E1_button);
        F1_button = (ImageButton)findViewById(R.id.F1_button);
        G1_button = (ImageButton)findViewById(R.id.G1_button);
        H1_button = (ImageButton)findViewById(R.id.H1_button);
        //Board array setup
        board[7][0] = A8_button;
        board[7][1] = B8_button;
        board[7][2] = C8_button;
        board[7][3] = D8_button;
        board[7][4] = E8_button;
        board[7][5] = F8_button;
        board[7][6] = G8_button;
        board[7][7] = H8_button;

        board[6][0] = A7_button;
        board[6][1] = B7_button;
        board[6][2] = C7_button;
        board[6][3] = D7_button;
        board[6][4] = E7_button;
        board[6][5] = F7_button;
        board[6][6] = G7_button;
        board[6][7] = H7_button;

        board[5][0] = A6_button;
        board[5][1] = B6_button;
        board[5][2] = C6_button;
        board[5][3] = D6_button;
        board[5][4] = E6_button;
        board[5][5] = F6_button;
        board[5][6] = G6_button;
        board[5][7] = H6_button;

        board[4][0] = A5_button;
        board[4][1] = B5_button;
        board[4][2] = C5_button;
        board[4][3] = D5_button;
        board[4][4] = E5_button;
        board[4][5] = F5_button;
        board[4][6] = G5_button;
        board[4][7] = H5_button;

        board[3][0] = A4_button;
        board[3][1] = B4_button;
        board[3][2] = C4_button;
        board[3][3] = D4_button;
        board[3][4] = E4_button;
        board[3][5] = F4_button;
        board[3][6] = G4_button;
        board[3][7] = H4_button;

        board[2][0] = A3_button;
        board[2][1] = B3_button;
        board[2][2] = C3_button;
        board[2][3] = D3_button;
        board[2][4] = E3_button;
        board[2][5] = F3_button;
        board[2][6] = G3_button;
        board[2][7] = H3_button;

        board[1][0] = A2_button;
        board[1][1] = B2_button;
        board[1][2] = C2_button;
        board[1][3] = D2_button;
        board[1][4] = E2_button;
        board[1][5] = F2_button;
        board[1][6] = G2_button;
        board[1][7] = H2_button;

        board[0][0] = A1_button;
        board[0][1] = B1_button;
        board[0][2] = C1_button;
        board[0][3] = D1_button;
        board[0][4] = E1_button;
        board[0][5] = F1_button;
        board[0][6] = G1_button;
        board[0][7] = H1_button;

        if(gameType.equals("pve") || gameType.equals("pvp")) {
            /*
                This loop goes through all empty tiles and sets the background to 0, if this is not done a grey
                square will appear on top of empty tiles since it is a imageButton and the default background is grey
                board[r][c] represents the button on the board.
             */
            String content = "";
            for (int r = 0; r < 8; r++) {
                for (int c = 0; c < 8; c++) {
                    switch (c) {
                        case 0:
                            content = "A";
                            break;
                        case 1:
                            content = "B";
                            break;
                        case 2:
                            content = "C";
                            break;
                        case 3:
                            content = "D";
                            break;
                        case 4:
                            content = "E";
                            break;
                        case 5:
                            content = "F";
                            break;
                        case 6:
                            content = "G";
                            break;
                        case 7:
                            content = "H";
                            break;
                        default:
                            break;
                    }
                    board[r][c].setContentDescription(content + (r+1));
                    if (r >= 2 && r <= 5) {
                        board[r][c].setBackgroundResource(0);
                        board[r][c].setTag(R.id.tagpiece,"");
                        board[r][c].setTag(R.id.tagcolor,"");
                        board[r][c].setContentDescription(content + (r+1));
                    }
                }//for c
            }//for r
        }//if pve or pvp

        else if(gameType.equals("fp")){
            int t = 0;
            String content = "";
            for (int r = 0; r < 8; r++) {
                for (int c = 0; c < 8; c++) {
                    switch (c) {
                        case 0:
                            content = "A";
                            break;
                        case 1:
                            content = "B";
                            break;
                        case 2:
                            content = "C";
                            break;
                        case 3:
                            content = "D";
                            break;
                        case 4:
                            content = "E";
                            break;
                        case 5:
                            content = "F";
                            break;
                        case 6:
                            content = "G";
                            break;
                        case 7:
                            content = "H";
                            break;
                        default:
                            break;
                    }
                    board[r][c].setContentDescription(content + (r+1));
                    switch (tempBoard[t]){
                        case 0:
                            board[r][c].setBackgroundResource(0);
                            board[r][c].setTag(R.id.tagpiece,"");
                            board[r][c].setTag(R.id.tagcolor,"");
                            break;
                        case 1:
                            board[r][c].setBackgroundResource(R.drawable.king_w);
                            board[r][c].setTag(R.id.tagpiece,"king");
                            board[r][c].setTag(R.id.tagcolor,"white");
                            break;
                        case 2:
                            board[r][c].setBackgroundResource(R.drawable.queen_w);
                            board[r][c].setTag(R.id.tagpiece,"queen");
                            board[r][c].setTag(R.id.tagcolor,"white");
                            break;
                        case 3:
                            board[r][c].setBackgroundResource(R.drawable.rook_w);
                            board[r][c].setTag(R.id.tagpiece,"rook");
                            board[r][c].setTag(R.id.tagcolor,"white");
                            break;
                        case 4:
                            board[r][c].setBackgroundResource(R.drawable.bishop_w);
                            board[r][c].setTag(R.id.tagpiece,"bishop");
                            board[r][c].setTag(R.id.tagcolor,"white");
                            break;
                        case 5:
                            board[r][c].setBackgroundResource(R.drawable.knight_w);
                            board[r][c].setTag(R.id.tagpiece,"knight");
                            board[r][c].setTag(R.id.tagcolor,"white");
                            break;
                        case 6:
                            board[r][c].setBackgroundResource(R.drawable.pawn_w);
                            board[r][c].setTag(R.id.tagpiece,"pawn");
                            board[r][c].setTag(R.id.tagcolor,"white");
                            break;
                        case 7:
                            board[r][c].setBackgroundResource(R.drawable.king_b);
                            board[r][c].setTag(R.id.tagpiece,"king");
                            board[r][c].setTag(R.id.tagcolor,"black");
                            break;
                        case 8:
                            board[r][c].setBackgroundResource(R.drawable.queen_b);
                            board[r][c].setTag(R.id.tagpiece,"queen");
                            board[r][c].setTag(R.id.tagcolor,"black");
                            break;
                        case 9:
                            board[r][c].setBackgroundResource(R.drawable.rook_b);
                            board[r][c].setTag(R.id.tagpiece,"rook");
                            board[r][c].setTag(R.id.tagcolor,"black");
                            break;
                        case 10:
                            board[r][c].setBackgroundResource(R.drawable.bishop_b);
                            board[r][c].setTag(R.id.tagpiece,"bishop");
                            board[r][c].setTag(R.id.tagcolor,"black");
                            break;
                        case 11:
                            board[r][c].setBackgroundResource(R.drawable.knight_b);
                            board[r][c].setTag(R.id.tagpiece,"knight");
                            board[r][c].setTag(R.id.tagcolor,"black");
                            break;
                        case 12:
                            board[r][c].setBackgroundResource(R.drawable.pawn_b);
                            board[r][c].setTag(R.id.tagpiece,"pawn");
                            board[r][c].setTag(R.id.tagcolor,"black");
                            break;
                    }
                    t++;
                }
            }
        }
    }//End of method

    private void setPieceTags(){

        if(gameType.equals("pve") | gameType.equals("pvp")){
            for(int c=0;c<8;c++){
                switch (c){
                    case 0:
                        A1_button.setTag(R.id.tagpiece,"rook");
                        A1_button.setTag(R.id.tagcolor,"white");
                        A2_button.setTag(R.id.tagpiece,"pawn");
                        A2_button.setTag(R.id.tagcolor,"white");
                        A7_button.setTag(R.id.tagpiece,"pawn");
                        A7_button.setTag(R.id.tagcolor,"black");
                        A8_button.setTag(R.id.tagpiece,"rook");
                        A8_button.setTag(R.id.tagcolor,"black");
                        break;
                    case 1:
                        B1_button.setTag(R.id.tagpiece,"knight");
                        B1_button.setTag(R.id.tagcolor,"white");
                        B2_button.setTag(R.id.tagpiece,"pawn");
                        B2_button.setTag(R.id.tagcolor,"white");
                        B7_button.setTag(R.id.tagpiece,"pawn");
                        B7_button.setTag(R.id.tagcolor,"black");
                        B8_button.setTag(R.id.tagpiece,"knight");
                        B8_button.setTag(R.id.tagcolor,"black");
                        break;
                    case 2:
                        C1_button.setTag(R.id.tagpiece,"bishop");
                        C1_button.setTag(R.id.tagcolor,"white");
                        C2_button.setTag(R.id.tagpiece,"pawn");
                        C2_button.setTag(R.id.tagcolor,"white");
                        C7_button.setTag(R.id.tagpiece,"pawn");
                        C7_button.setTag(R.id.tagcolor,"black");
                        C8_button.setTag(R.id.tagpiece,"bishop");
                        C8_button.setTag(R.id.tagcolor,"black");
                        break;
                    case 3:
                        D1_button.setTag(R.id.tagpiece,"queen");
                        D1_button.setTag(R.id.tagcolor,"white");
                        D2_button.setTag(R.id.tagpiece,"pawn");
                        D2_button.setTag(R.id.tagcolor,"white");
                        D7_button.setTag(R.id.tagpiece,"pawn");
                        D7_button.setTag(R.id.tagcolor,"black");
                        D8_button.setTag(R.id.tagpiece,"queen");
                        D8_button.setTag(R.id.tagcolor,"black");
                        break;
                    case 4:
                        E1_button.setTag(R.id.tagpiece,"king");
                        E1_button.setTag(R.id.tagcolor,"white");
                        E2_button.setTag(R.id.tagpiece,"pawn");
                        E2_button.setTag(R.id.tagcolor,"white");
                        E7_button.setTag(R.id.tagpiece,"pawn");
                        E7_button.setTag(R.id.tagcolor,"black");
                        E8_button.setTag(R.id.tagpiece,"king");
                        E8_button.setTag(R.id.tagcolor,"black");
                        break;
                    case 5:
                        F1_button.setTag(R.id.tagpiece,"bishop");
                        F1_button.setTag(R.id.tagcolor,"white");
                        F2_button.setTag(R.id.tagpiece,"pawn");
                        F2_button.setTag(R.id.tagcolor,"white");
                        F7_button.setTag(R.id.tagpiece,"pawn");
                        F7_button.setTag(R.id.tagcolor,"black");
                        F8_button.setTag(R.id.tagpiece,"bishop");
                        F8_button.setTag(R.id.tagcolor,"black");
                        break;
                    case 6:
                        G1_button.setTag(R.id.tagpiece,"knight");
                        G1_button.setTag(R.id.tagcolor,"white");
                        G2_button.setTag(R.id.tagpiece,"pawn");
                        G2_button.setTag(R.id.tagcolor,"white");
                        G7_button.setTag(R.id.tagpiece,"pawn");
                        G7_button.setTag(R.id.tagcolor,"black");
                        G8_button.setTag(R.id.tagpiece,"knight");
                        G8_button.setTag(R.id.tagcolor,"black");
                        break;
                    case 7:
                        H1_button.setTag(R.id.tagpiece,"rook");
                        H1_button.setTag(R.id.tagcolor,"white");
                        H2_button.setTag(R.id.tagpiece,"pawn");
                        H2_button.setTag(R.id.tagcolor,"white");
                        H7_button.setTag(R.id.tagpiece,"pawn");
                        H7_button.setTag(R.id.tagcolor,"black");
                        H8_button.setTag(R.id.tagpiece,"rook");
                        H8_button.setTag(R.id.tagcolor,"black");
                        break;
                }//Switch
            }//for loop
        }// if pve or pvp

        if(gameType.equals("fp")){

        }

    }//End of setPiece method

    private void setDescriptions(){
        for(int r=0;r<8;r++){
            for(int c=0;c<8;c++){
                CharSequence description = board[r][c].getContentDescription().subSequence(0,2);
                if(!(board[r][c].getTag(R.id.tagcolor).equals(""))){
                    description = description + " " + board[r][c].getTag(R.id.tagcolor) + " " + board[r][c].getTag(R.id.tagpiece);
                }
                board[r][c].setContentDescription(description);
            }
        }
    }

    private void resetBoard(){
        if(gameType.equals("pve") | gameType.equals("pvp")){

            A1_button.setTag(R.id.tagpiece,"rook");
            A1_button.setTag(R.id.tagcolor, "white");
            A1_button.setBackgroundResource(R.drawable.rook_w);
            A2_button.setTag(R.id.tagpiece, "pawn");
            A2_button.setTag(R.id.tagcolor, "white");
            A2_button.setBackgroundResource(R.drawable.pawn_w);
            A7_button.setTag(R.id.tagpiece, "pawn");
            A7_button.setTag(R.id.tagcolor, "black");
            A7_button.setBackgroundResource(R.drawable.pawn_b);
            A8_button.setTag(R.id.tagpiece, "rook");
            A8_button.setTag(R.id.tagcolor, "black");
            A8_button.setBackgroundResource(R.drawable.rook_b);

            B1_button.setTag(R.id.tagpiece, "knight");
            B1_button.setTag(R.id.tagcolor, "white");
            B1_button.setBackgroundResource(R.drawable.knight_w);
            B2_button.setTag(R.id.tagpiece, "pawn");
            B2_button.setTag(R.id.tagcolor, "white");
            B2_button.setBackgroundResource(R.drawable.pawn_w);
            B7_button.setTag(R.id.tagpiece, "pawn");
            B7_button.setTag(R.id.tagcolor, "black");
            B7_button.setBackgroundResource(R.drawable.pawn_b);
            B8_button.setTag(R.id.tagpiece, "knight");
            B8_button.setTag(R.id.tagcolor, "black");
            B8_button.setBackgroundResource(R.drawable.knight_b);

            C1_button.setTag(R.id.tagpiece, "bishop");
            C1_button.setTag(R.id.tagcolor, "white");
            C1_button.setBackgroundResource(R.drawable.bishop_w);
            C2_button.setTag(R.id.tagpiece, "pawn");
            C2_button.setTag(R.id.tagcolor, "white");
            C2_button.setBackgroundResource(R.drawable.pawn_w);
            C7_button.setTag(R.id.tagpiece, "pawn");
            C7_button.setTag(R.id.tagcolor,"black");
            C7_button.setBackgroundResource(R.drawable.pawn_b);
            C8_button.setTag(R.id.tagpiece, "bishop");
            C8_button.setTag(R.id.tagcolor,"black");
            C8_button.setBackgroundResource(R.drawable.bishop_b);

            D1_button.setTag(R.id.tagpiece, "queen");
            D1_button.setTag(R.id.tagcolor,"white");
            D1_button.setBackgroundResource(R.drawable.queen_w);
            D2_button.setTag(R.id.tagpiece, "pawn");
            D2_button.setTag(R.id.tagcolor,"white");
            D2_button.setBackgroundResource(R.drawable.pawn_w);
            D7_button.setTag(R.id.tagpiece, "pawn");
            D7_button.setTag(R.id.tagcolor,"black");
            D7_button.setBackgroundResource(R.drawable.pawn_b);
            D8_button.setTag(R.id.tagpiece, "queen");
            D8_button.setTag(R.id.tagcolor,"black");
            D8_button.setBackgroundResource(R.drawable.queen_b);

            E1_button.setTag(R.id.tagpiece, "king");
            E1_button.setTag(R.id.tagcolor,"white");
            E1_button.setBackgroundResource(R.drawable.king_w);
            E2_button.setTag(R.id.tagpiece, "pawn");
            E2_button.setTag(R.id.tagcolor,"white");
            E2_button.setBackgroundResource(R.drawable.pawn_w);
            E7_button.setTag(R.id.tagpiece, "pawn");
            E7_button.setTag(R.id.tagcolor,"black");
            E7_button.setBackgroundResource(R.drawable.pawn_b);
            E8_button.setTag(R.id.tagpiece, "king");
            E8_button.setTag(R.id.tagcolor,"black");
            E8_button.setBackgroundResource(R.drawable.king_b);

            F1_button.setTag(R.id.tagpiece, "bishop");
            F1_button.setTag(R.id.tagcolor,"white");
            F1_button.setBackgroundResource(R.drawable.bishop_w);
            F2_button.setTag(R.id.tagpiece, "pawn");
            F2_button.setTag(R.id.tagcolor,"white");
            F2_button.setBackgroundResource(R.drawable.pawn_w);
            F7_button.setTag(R.id.tagpiece, "pawn");
            F7_button.setTag(R.id.tagcolor,"black");
            F7_button.setBackgroundResource(R.drawable.pawn_b);
            F8_button.setTag(R.id.tagpiece, "bishop");
            F8_button.setTag(R.id.tagcolor,"black");
            F8_button.setBackgroundResource(R.drawable.bishop_b);

            G1_button.setTag(R.id.tagpiece, "knight");
            G1_button.setTag(R.id.tagcolor,"white");
            G1_button.setBackgroundResource(R.drawable.knight_w);
            G2_button.setTag(R.id.tagpiece, "pawn");
            G2_button.setTag(R.id.tagcolor,"white");
            G2_button.setBackgroundResource(R.drawable.pawn_w);
            G7_button.setTag(R.id.tagpiece, "pawn");
            G7_button.setTag(R.id.tagcolor,"black");
            G7_button.setBackgroundResource(R.drawable.pawn_b);
            G8_button.setTag(R.id.tagpiece, "knight");
            G8_button.setTag(R.id.tagcolor,"black");
            G8_button.setBackgroundResource(R.drawable.knight_b);

            H1_button.setTag(R.id.tagpiece, "rook");
            H1_button.setTag(R.id.tagcolor,"white");
            H1_button.setBackgroundResource(R.drawable.rook_w);
            H2_button.setTag(R.id.tagpiece, "pawn");
            H2_button.setTag(R.id.tagcolor,"white");
            H2_button.setBackgroundResource(R.drawable.pawn_w);
            H7_button.setTag(R.id.tagpiece, "pawn");
            H7_button.setTag(R.id.tagcolor,"black");
            H7_button.setBackgroundResource(R.drawable.pawn_b);
            H8_button.setTag(R.id.tagpiece, "rook");
            H8_button.setTag(R.id.tagcolor,"black");
            H8_button.setBackgroundResource(R.drawable.rook_b);
        }
        if(gameType.equals("fp")){
            int t = 0;
            String content = "";
            for (int r = 0; r < 8; r++) {
                for (int c = 0; c < 8; c++) {
                    switch (c) {
                        case 0:
                            content = "A";
                            break;
                        case 1:
                            content = "B";
                            break;
                        case 2:
                            content = "C";
                            break;
                        case 3:
                            content = "D";
                            break;
                        case 4:
                            content = "E";
                            break;
                        case 5:
                            content = "F";
                            break;
                        case 6:
                            content = "G";
                            break;
                        case 7:
                            content = "H";
                            break;
                        default:
                            break;
                    }
                    board[r][c].setContentDescription(content + (r+1));
                    switch (tempBoard[t]){
                        case 0:
                            board[r][c].setBackgroundResource(0);
                            board[r][c].setTag(R.id.tagpiece,"");
                            board[r][c].setTag(R.id.tagcolor,"");
                            break;
                        case 1:
                            board[r][c].setBackgroundResource(R.drawable.king_w);
                            board[r][c].setTag(R.id.tagpiece,"king");
                            board[r][c].setTag(R.id.tagcolor,"white");
                            break;
                        case 2:
                            board[r][c].setBackgroundResource(R.drawable.queen_w);
                            board[r][c].setTag(R.id.tagpiece,"queen");
                            board[r][c].setTag(R.id.tagcolor,"white");
                            break;
                        case 3:
                            board[r][c].setBackgroundResource(R.drawable.rook_w);
                            board[r][c].setTag(R.id.tagpiece,"rook");
                            board[r][c].setTag(R.id.tagcolor,"white");
                            break;
                        case 4:
                            board[r][c].setBackgroundResource(R.drawable.bishop_w);
                            board[r][c].setTag(R.id.tagpiece,"bishop");
                            board[r][c].setTag(R.id.tagcolor,"white");
                            break;
                        case 5:
                            board[r][c].setBackgroundResource(R.drawable.knight_w);
                            board[r][c].setTag(R.id.tagpiece,"knight");
                            board[r][c].setTag(R.id.tagcolor,"white");
                            break;
                        case 6:
                            board[r][c].setBackgroundResource(R.drawable.pawn_w);
                            board[r][c].setTag(R.id.tagpiece,"pawn");
                            board[r][c].setTag(R.id.tagcolor,"white");
                            break;
                        case 7:
                            board[r][c].setBackgroundResource(R.drawable.king_b);
                            board[r][c].setTag(R.id.tagpiece,"king");
                            board[r][c].setTag(R.id.tagcolor,"black");
                            break;
                        case 8:
                            board[r][c].setBackgroundResource(R.drawable.queen_b);
                            board[r][c].setTag(R.id.tagpiece,"queen");
                            board[r][c].setTag(R.id.tagcolor,"black");
                            break;
                        case 9:
                            board[r][c].setBackgroundResource(R.drawable.rook_b);
                            board[r][c].setTag(R.id.tagpiece,"rook");
                            board[r][c].setTag(R.id.tagcolor,"black");
                            break;
                        case 10:
                            board[r][c].setBackgroundResource(R.drawable.bishop_b);
                            board[r][c].setTag(R.id.tagpiece,"bishop");
                            board[r][c].setTag(R.id.tagcolor,"black");
                            break;
                        case 11:
                            board[r][c].setBackgroundResource(R.drawable.knight_b);
                            board[r][c].setTag(R.id.tagpiece,"knight");
                            board[r][c].setTag(R.id.tagcolor,"black");
                            break;
                        case 12:
                            board[r][c].setBackgroundResource(R.drawable.pawn_b);
                            board[r][c].setTag(R.id.tagpiece,"pawn");
                            board[r][c].setTag(R.id.tagcolor,"black");
                            break;
                    }//Switch
                    t++;
                }//for c
            }//for r
        }
    }

    // Create an intent that can start the Speech Recognizer activity
    private void displaySpeechRecognizer(int request_code) {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Hello say movement");
        // Start the activity, the intent will be populated with the speech text
        startActivityForResult(intent, request_code);
    }

    // This callback is invoked when the Speech Recognizer returns.
    // This is where you process the intent and extract the speech text from the intent.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String pieceResult;
        String positionResult;
        ArrayList<String> pieceResultsList;
        ArrayList<String> positionResultsList;
        if (requestCode == SPEECH_REQUEST_CODE_PIECE && resultCode == RESULT_OK) {
            pieceResultsList = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            System.out.println("InputPiece: ");
            System.out.println(pieceResultsList);
            if(castling.contains(pieceResultsList.get(0))){
                String temp="Castling";
                Toast.makeText(this, temp, Toast.LENGTH_SHORT).show();
            }
            else{
            pieceResult = checkPieceOmophones(pieceResultsList.get(0).toLowerCase());
            finalCommand[0]=pieceResult;
            displaySpeechRecognizer(SPEECH_REQUEST_CODE_POSITION);
            }
        }
        if(requestCode == SPEECH_REQUEST_CODE_POSITION && resultCode == RESULT_OK) {
            positionResultsList = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            System.out.println("InputPosition: ");
            System.out.println(positionResultsList);
            positionResult = positionResultsList.get(0).toLowerCase();
            if(positionResult.charAt(0)=='0'){
                positionResult= "A" + positionResult.charAt(1);
            }
            if(positionResult.charAt(0)=='V'){
                positionResult= "B" + positionResult.charAt(1);
            }
            if(positionResult.charAt(1)=='H'){
                positionResult= positionResult.charAt(0) + "8";
            }
            finalCommand[1] = positionResult;

            String spokenText = finalCommand[0] + " " + finalCommand[1];
            System.out.println("Result: ");
            System.out.println(spokenText);
            Toast.makeText(this, spokenText, Toast.LENGTH_SHORT).show();
        }
    }

    public void voiceKeyWordsArray(){
        rank[0] = "rook";
        rank[1] = "knight";
        rank[2] = "bishop";
        rank[3] = "queen";
        rank[4] = "king";
        rank[5] = "pawn";

        column[0] = "a";
        column[1] = "b";
        column[2] = "c";
        column[3] = "d";
        column[4] = "e";
        column[5] = "f";
        column[6] = "g";
        column[7] = "h";

        row[0] = "1";
        row[1] = "2";
        row[2] = "3";
        row[3] = "4";
        row[4] = "5";
        row[5] = "6";
        row[6] = "7";
        row[7] = "8";
    }

    public View stringToView(String[] s){
        String buttonId = s[1].toUpperCase() + s[2]+"_button";
        int resID = getResources().getIdentifier(buttonId , "id", getPackageName());
        return findViewById(resID);
    }

    public int possibleMovesPawn(String[] s){
        int targetId;
        if(A2_button.getTag(R.id.tagpiece).equals("pawn")){
            pawnMoves[0][0] = "A2";
            pawnMoves[0][1] = "A3";
            pawnMoves[0][2] = "A4";
        }
        if(B2_button.getTag(R.id.tagpiece).equals("pawn")){
            pawnMoves[1][0] = "B2";
            pawnMoves[1][1] = "B3";
            pawnMoves[1][2] = "B4";
        }
        if(C2_button.getTag(R.id.tagpiece).equals("pawn")){
            pawnMoves[2][0] = "C2";
            pawnMoves[2][1] = "C3";
            pawnMoves[2][2] = "C4";
        }
        if(D2_button.getTag(R.id.tagpiece).equals("pawn")){
            pawnMoves[3][0] = "D2";
            pawnMoves[3][1] = "D3";
            pawnMoves[3][2] = "D4";
        }
        if(E2_button.getTag(R.id.tagpiece).equals("pawn")){
            pawnMoves[4][0] = "E2";
            pawnMoves[4][1] = "E3";
            pawnMoves[4][2] = "E4";
        }
        if(F2_button.getTag(R.id.tagpiece).equals("pawn")){
            pawnMoves[5][0] = "F2";
            pawnMoves[5][1] = "F3";
            pawnMoves[5][2] = "F4";
        }
        if(G2_button.getTag(R.id.tagpiece).equals("pawn")){
            pawnMoves[6][0] = "G2";
            pawnMoves[6][1] = "G3";
            pawnMoves[6][2] = "G4";
        }
        if(H2_button.getTag(R.id.tagpiece).equals("pawn")){
            pawnMoves[7][0] = "H2";
            pawnMoves[7][1] = "H3";
            pawnMoves[7][2] = "H4";
        }
        String destination = s[1].toUpperCase()+s[2];
        int champion = 0;
        for(int i=0;i<8;i++){
            List tempList = Arrays.asList(pawnMoves[i]);
            if(tempList.contains(destination)){
                champion = i;
            }
        }
        targetId = getResources().getIdentifier(pawnMoves[champion][0]+"_button" , "id", getPackageName());
        pawnMoves[champion][0] =   pawnMoves[champion][1].charAt(0) + (Integer.parseInt(s[2])) + "";
        pawnMoves[champion][1] =   pawnMoves[champion][1].charAt(0) + (Integer.parseInt(s[2])+1) + "";
        pawnMoves[champion][2] = "plas";
        return targetId;
    }

    private void setupOmophones(){
        rookOmophones[0] = "work";
        rookOmophones[1] = "hook";
        rookOmophones[2] = "route";
        rookOmophones[3] = "rock";
        rookOmophones[4] = "brooke";
        rookOmophones[5] = "group";
        rookOmophones[6] = "luke";
        rookOmophones[7] = "roxy";
        rookOmophones[8] = "brook";
        rookOmophones[9] = "look";
        rookOmophones[10]= "root";
        rookOmophones[11]= "brooks";

        pawnOmophones[0] = "pon";
        pawnOmophones[1] = "pond";
        pawnOmophones[2] = "palm";
        pawnOmophones[3] = "pong";
        pawnOmophones[4] = "pawned";
        pawnOmophones[5] = "home";
        pawnOmophones[6] = "paul";
        pawnOmophones[7] = "pawnee";

        knightOmophones[0] = "night";
        knightOmophones[1] = "nite";
        knightOmophones[2] = "knife";
        knightOmophones[3] = "nice";
        knightOmophones[4] = "light";
        knightOmophones[5] = "9th";
        knightOmophones[5] = "ninth";

        queenOmophones[0] = "quean";
        queenOmophones[1] = "queens";
        queenOmophones[2] = "kween";
        queenOmophones[3] = "quinn";
        queenOmophones[4] = "clean";
        queenOmophones[4] = "quin";

        kingOmophones[0]="kane";
        kingOmophones[1]="quing";
        kingOmophones[2]="kim";
        kingOmophones[3]="can";
        kingOmophones[4]="kinh";
        kingOmophones[5]="kang";
        kingOmophones[6]="tang";
        kingOmophones[7]="ting";
        kingOmophones[8]="kin";

        bishopOmophones[0] = "bisharp";
        bishopOmophones[1] = "bischoff";
        bishopOmophones[2] = "fish shop";
        bishopOmophones[3] = "fish app";
        bishopOmophones[3] = "the shop";
        bishopOmophones[3] = "vishal";

        aOmophones[0] = "8" ;
        aOmophones[1] = "eight";

        bOmophones[0] = "bee";
        bOmophones[1] = "be";
        bOmophones[2] = "me";
        bOmophones[3] = "v";
        bOmophones[4] = "before";
        bOmophones[5] = "de";
        bOmophones[6] = "beat";

        cOmophones[0] = "z";
        cOmophones[1] = "see";
        cOmophones[2] = "sea";
        cOmophones[3] = "x";
        cOmophones[3] = "seat";
        cOmophones[3] = "seats";

        dOmophones[0] = "depot" ;
        dOmophones[1] = "deep";

        eOmophones[0] = "essex" ;
        eOmophones[1] = "east";
        eOmophones[2] = "eat";

        fOmophones[0] = "ff" ;
        fOmophones[1] = "of";
        fOmophones[0] = "after" ;

        hOmophones[0] = "8";

        Omophones1[0] = "one";

        Omophones2[0] = "two";
        Omophones2[1] = "to";
        Omophones2[2] = "too";
        Omophones2[3] = "t";
        Omophones2[4] = "ii";

        Omophones3[0] = "z";
        Omophones3[1] = "sea";
        Omophones3[2] = "see";
        Omophones3[3] = "iii";

        Omophones4[0] = "four";
        Omophones4[1] = "store";
        Omophones4[2] = "before";
        Omophones4[3] = "seaford";
        Omophones4[4] = "for";

        Omophones5[0] = "five";
        Omophones5[1] = "v";

        Omophones6[0] = "six";
        Omophones6[1] = "asics";

        Omophones7[0] = "seven";

        Omophones8[0] = "eight";
        Omophones8[1] = "date";
        Omophones8[2] = "h";

        numberss[0] = "1";
        numberss[1] = "2";
        numberss[2] = "3";
        numberss[3] = "4";
        numberss[4] = "5";
        numberss[5] = "6";
        numberss[6] = "7";
        numberss[7] = "8";

        castlingOmophones[0] = "kathleen";
        castlingOmophones[1] = "gasoline";
        castlingOmophones[2] = "cathleen";
        castlingOmophones[3] = "cancelling";
        castlingOmophones[4] = "castle";
        castlingOmophones[5] = "cassie";
        castlingOmophones[6] = "cassel";
        castlingOmophones[7] = "catholic";
        castlingOmophones[8] = "castling";


    }

    private void setupLists(){
        rookOmoList   = Arrays.asList(rookOmophones);
        pawnOmoList   = Arrays.asList(pawnOmophones);
        knightOmoList = Arrays.asList(knightOmophones);
        kingOmoList   = Arrays.asList(kingOmophones);
        queenOmoList  = Arrays.asList(queenOmophones);
        bishopOmoList = Arrays.asList(bishopOmophones);

        aOmoList = Arrays.asList(aOmophones);
        bOmoList = Arrays.asList(bOmophones);
        cOmoList = Arrays.asList(cOmophones);
        dOmoList = Arrays.asList(dOmophones);
        eOmoList = Arrays.asList(eOmophones);
        fOmoList = Arrays.asList(fOmophones);
        gOmoList = Arrays.asList(gOmophones);
        hOmoList = Arrays.asList(hOmophones);

        OmoList1 = Arrays.asList(Omophones1);
        OmoList2 = Arrays.asList(Omophones2);
        OmoList3 = Arrays.asList(Omophones3);
        OmoList4 = Arrays.asList(Omophones4);
        OmoList5 = Arrays.asList(Omophones5);
        OmoList6 = Arrays.asList(Omophones6);
        OmoList7 = Arrays.asList(Omophones7);
        OmoList8 = Arrays.asList(Omophones7);

        ranklist   = Arrays.asList(rank);
        columnlist = Arrays.asList(column);
        rowlist    = Arrays.asList(row);

        numbers = Arrays.asList(numberss);

        castling = Arrays.asList(castlingOmophones);

    }

    private String checkPieceOmophones(String s) {
        for(int i=0;i<6;i++){
            switch (i) {
                case 0: {
                    if(rookOmoList.contains(s)){
                        s="rook";
                    }
                    break;
                }
                case 1: {
                    if(pawnOmoList.contains(s)){
                        s="pawn";
                    }
                    break;
                }
                case 2: {
                    if(knightOmoList.contains(s)){
                        s="knight";
                    }
                    break;
                }
                case 3: {
                    if(bishopOmoList.contains(s)){
                        s="bishop";
                    }
                    break;
                }
                case 4: {
                    if(queenOmoList.contains(s)){
                        s="queen";
                    }
                    break;
                }
                case 5: {
                    if(kingOmoList.contains(s)){
                        s="king";
                    }
                    break;
                }
                default: {
                    break;
                }
            }
        }
        return s;
    }

    private String[] checkcolumOmophones(String[] s){
        for(int i=0;i<6;i++){
            switch (i) {
                case 0: {
                    if(aOmoList.contains(s[1])){
                        s[1]="a";
                    }
                    if(s[1].length()==2 && !aOmoList.contains(s[1])){
                        if(numbers.contains(s[1].charAt(1)+"")){
                            s[2]= s[1].charAt(1)+"";
                        }
                        if(aOmoList.contains(s[1].charAt(0)+"")){
                            s[1] = "a";
                        }
                    }
                    break;
                }
                case 1: {
                    if(bOmoList.contains(s[1])){
                        if(s[1].equals("before")){
                            s[2]="4";
                        }
                        s[1]="b";
                    }
                    if(s[1].length()==2 && !bOmoList.contains(s[1])){
                        if(numbers.contains(s[1].charAt(1)+"")){
                            s[2]= s[1].charAt(1)+"";
                        }
                        if(bOmoList.contains(s[1].charAt(0)+"")){
                            s[1] = "b";
                        }
                    }
                    break;
                }
                case 2: {
                    if(cOmoList.contains(s[1])){
                        s[1]="c";
                    }
                    if(s[1].length()==2 && !cOmoList.contains(s[1])){
                        if(numbers.contains(s[1].charAt(1)+"")){
                            s[2]= s[1].charAt(1)+"";
                        }
                        if(aOmoList.contains(s[1].charAt(0)+"")){
                            s[1] = "c";
                        }
                    }
                    break;
                }
                case 3: {
                    if(dOmoList.contains(s[1])){
                        s[1]="d";
                    }
                    if(s[1].length()==2 && !dOmoList.contains(s[1])){
                        if(numbers.contains(s[1].charAt(1)+"")){
                            s[2]= s[1].charAt(1)+"";
                        }
                        if(dOmoList.contains(s[1].charAt(0)+"")){
                            s[1] = "d";
                        }
                    }
                    break;
                }
                case 4: {
                    if(eOmoList.contains(s[1])){
                        s[1]="e";
                    }
                    if(s[1].length()==2 && !eOmoList.contains(s[1])){
                        if(numbers.contains(s[1].charAt(1)+"")){
                            s[2]= s[1].charAt(1)+"";
                        }
                        if(eOmoList.contains(s[1].charAt(0)+"")){
                            s[1] = "e";
                        }
                    }
                    break;
                }
                case 5: {
                    if(fOmoList.contains(s[1])){
                        s[1]="f";
                    }
                    if(s[1].length()==2 && !fOmoList.contains(s[1])){
                        if(numbers.contains(s[1].charAt(1)+"")){
                            s[2]= s[1].charAt(1)+"";
                        }
                        if(fOmoList.contains(s[1].charAt(0)+"")){
                            s[1] = "f";
                        }
                    }
                    break;
                }
                case 6: {
                    if(gOmoList.contains(s[1])){
                        s[1]="g";
                    }
                    if(s[1].length()==2 && !gOmoList.contains(s[1])){
                        if(numbers.contains(s[1].charAt(1)+"")){
                            s[2]= s[1].charAt(1)+"";
                        }
                        if(gOmoList.contains(s[1].charAt(0)+"")){
                            s[1] = "g";
                        }
                    }
                    break;
                }
                case 7: {
                    if(hOmoList.contains(s[1])){
                        s[1]="h";
                    }
                    if(s[1].length()==2 && !hOmoList.contains(s[1])){
                        if(numbers.contains(s[1].charAt(1)+"")){
                            s[2]= s[1].charAt(1)+"";
                        }
                        if(hOmoList.contains(s[1].charAt(0)+"")){
                            s[1] = "h";
                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }
        return s;
    }

    private String[] checkrowOmophones(String[] s){
        for(int i=0;i<8;i++){
            switch (i) {
                case 0: {
                    if(OmoList1.contains(s[2])){
                        s[2]="1";
                    }
                    break;
                }
                case 1: {
                    if(OmoList2.contains(s[2])){
                        s[2]="2";
                    }
                    break;
                }
                case 2: {
                    if(OmoList3.contains(s[2])){
                        s[2]="3";
                    }
                    break;
                }
                case 3: {
                    if(OmoList4.contains(s[2])){
                        s[2]="4";
                    }
                    break;
                }
                case 4: {
                    if(OmoList5.contains(s[2])){
                        s[2]="5";
                    }
                    break;
                }
                case 5: {
                    if(OmoList6.contains(s[2])){
                        s[2]="6";
                    }
                    break;
                }
                case 6: {
                    if(OmoList7.contains(s[2])){
                        s[2]="7";
                    }
                    break;
                }
                case 7: {
                    if(OmoList8.contains(s[2])){
                        s[2]="8";
                    }
                    break;
                }
                default: {
                    break;
                }
            }
        }
        return s;
    }
}
