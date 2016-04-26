package com.capstone.chesscommander.chesscommander;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.capstone.chesscommander.chesscommander.GameLogic.Board;

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

    private int prevId,currentTurn,numberOfMoves;
    private Drawable bgdraw;
    private boolean empty;

    private String gameType,difficulty,playerColor;
    private String currentMove,playAs,opponentType;
    private int[] tempBoard = new int[64];
    private String[] numberss = new String[8];
    private String[] boardNotation = new String[64];

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

    private ImageView A8_view,B8_view,C8_view,D8_view,E8_view,F8_view,G8_view,H8_view;
    private ImageView A7_view,B7_view,C7_view,D7_view,E7_view,F7_view,G7_view,H7_view;
    private ImageView A6_view,B6_view,C6_view,D6_view,E6_view,F6_view,G6_view,H6_view;
    private ImageView A5_view,B5_view,C5_view,D5_view,E5_view,F5_view,G5_view,H5_view;
    private ImageView A4_view,B4_view,C4_view,D4_view,E4_view,F4_view,G4_view,H4_view;
    private ImageView A3_view,B3_view,C3_view,D3_view,E3_view,F3_view,G3_view,H3_view;
    private ImageView A2_view,B2_view,C2_view,D2_view,E2_view,F2_view,G2_view,H2_view;
    private ImageView A1_view,B1_view,C1_view,D1_view,E1_view,F1_view,G1_view,H1_view;
    
    private Bundle extras;
    private static final int SPEECH_REQUEST_CODE_PIECE = 0;
    private static final int SPEECH_REQUEST_CODE_POSITION = 1;
    private List ranklist,columnlist, rowlist;
    private List rookOmoList,pawnOmoList,knightOmoList,kingOmoList,queenOmoList,bishopOmoList;
    private List aOmoList,bOmoList,cOmoList,dOmoList,eOmoList,fOmoList,gOmoList,hOmoList;
    private List OmoList1,OmoList2,OmoList3,OmoList4,OmoList5,OmoList6,OmoList7,OmoList8;
    private List numbers,castling;

    private String[][] pawnMoves = new String[8][3];

    private Board pastEven = new Board();
    private Board initialtester = new Board();
    private Board past = new Board();
    private Board currentBoard = new Board();
    private Board holder = new Board();

    private Board[] pastArray = new Board[2];

    private String currentAllowedColor;
    final Context context = this;

    AccessibilityManager am;
    boolean isAccessibilityEnabled;



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
        refreshBoard();
        prevId = -1;
        initialBoard = board.clone();
        voiceKeyWordsArray();

        setupOmophones();
        setupLists();
        am = (AccessibilityManager) getSystemService(ACCESSIBILITY_SERVICE);
        isAccessibilityEnabled = am.isEnabled();

    }

    public void onButtonClick(View view){
        empty = (view.getBackground()==null);

        if(empty && prevId==-1){
            //Do nothing
        }
        else if(!empty && prevId==-1){
           if(currentAllowedColor.equals(view.getTag(R.id.tagcolor)) && currentAllowedColor.equals(playerColor)){
               prevId = view.getId();
           }
        }
        else if(prevId>0) {
            if (!findViewById(prevId).equals(view)) {

                int SSQ = (Integer) findViewById(prevId).getTag(R.id.tagboardpos);
                int ESQ = (Integer) view.getTag(R.id.tagboardpos);
                char color = currentBoard.getTile((Integer) findViewById(prevId).getTag(R.id.tagboardpos)).getPiece().getColor();
                if (currentBoard.move(SSQ, ESQ, color, true)) {
                    refreshBoard();
                    numberOfMoves++;
                }
                switch(opponentType){
                    case "player":
                        changeAllowedColor();
                        changePlayerColor();
                        break;
                    case "computer":
                        changeAllowedColor();
                        break;
                }
            }
            past.setInitialPosition();
            prevId = -1;

            //currentBoard.printVisualBoard();
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
                        reverseRefreshBoard();
                        break;
                    case 1://Move List
                       //System.out.println(currentBoard.getGameMoveList().toString());
                       String moves = "";
                        for(int i = 0; i< currentBoard.getGameMoveList().size(); i++){
                            String SSQ = intToNotation(currentBoard.getGameMoveList().get(i).getStartSquareID());
                            String ESQ = intToNotation(currentBoard.getGameMoveList().get(i).getEndSquareID());
                            moves = moves + "\n" + (i+1) + "."+ SSQ + " " + ESQ + " " ;
                        }
                        CharSequence msg = moves;
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                       if(isAccessibilityEnabled){
                           builder.setTitle(msg);
                       }
                       else{
                           builder.setTitle("Move List");
                           builder.setMessage(msg);
                       }
                        builder.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            // if this button is clicked, close dialog
                            dialog.cancel();
                            }
                        });
                        //Create alert dialog
                        AlertDialog alertDialog = builder.create();
                        //Show alert dialog
                        alertDialog.show();
                        break;
                    case 2://New Game
                        board = initialBoard.clone();
                        boardSetup(gameType);
                        if(gameType.equals("fp")){
                            currentBoard.setCustomBoard(tempBoard);
                            refreshBoard();
                        }
                        else{
                            currentBoard.setInitialPosition();
                            refreshBoard();
                        }
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
        if(numberOfMoves==0){
            Toast.makeText(this, "Cant Undo", Toast.LENGTH_SHORT).show();
        }
        else{
            switch(opponentType){
                case "player":
                    simulateMoves();
                    copyCurrentToHolder();
                    copyPastToCurrent();
                    changeAllowedColor();
                    changePlayerColor();
                    numberOfMoves--;
                    break;
                case "computer":
                    simulateMoves();
                    copyCurrentToHolder();
                    copyPastToCurrent();
                    changeAllowedColor();
                    numberOfMoves--;
                    numberOfMoves--;
                    break;
            }
            refreshBoard();
        }
    }

    public void onRedoButtonClick(View view){
      if(holder.equals(initialtester.setInitialPosition())){
          Toast.makeText(this, "Cant Redo", Toast.LENGTH_SHORT).show();
      }
      else{
          copyHolderToCurrent();
          numberOfMoves = currentBoard.getGameMoveList().size();
          refreshBoard();
          switch(opponentType){
              case "player":
                  changeAllowedColor();
                  changePlayerColor();

                  break;
              case "computer":
                  changeAllowedColor();
                  break;
            }
      }
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

        A8_button.setTag(R.id.tagboardpos,0);
        B8_button.setTag(R.id.tagboardpos,1);
        C8_button.setTag(R.id.tagboardpos,2);
        D8_button.setTag(R.id.tagboardpos,3);
        E8_button.setTag(R.id.tagboardpos,4);
        F8_button.setTag(R.id.tagboardpos,5);
        G8_button.setTag(R.id.tagboardpos,6);
        H8_button.setTag(R.id.tagboardpos,7);

        A7_button.setTag(R.id.tagboardpos,8);
        B7_button.setTag(R.id.tagboardpos,9);
        C7_button.setTag(R.id.tagboardpos,10);
        D7_button.setTag(R.id.tagboardpos,11);
        E7_button.setTag(R.id.tagboardpos,12);
        F7_button.setTag(R.id.tagboardpos,13);
        G7_button.setTag(R.id.tagboardpos,14);
        H7_button.setTag(R.id.tagboardpos,15);

        A6_button.setTag(R.id.tagboardpos,16);
        B6_button.setTag(R.id.tagboardpos,17);
        C6_button.setTag(R.id.tagboardpos,18);
        D6_button.setTag(R.id.tagboardpos,19);
        E6_button.setTag(R.id.tagboardpos,20);
        F6_button.setTag(R.id.tagboardpos,21);
        G6_button.setTag(R.id.tagboardpos,22);
        H6_button.setTag(R.id.tagboardpos,23);

        A5_button.setTag(R.id.tagboardpos,24);
        B5_button.setTag(R.id.tagboardpos,25);
        C5_button.setTag(R.id.tagboardpos,26);
        D5_button.setTag(R.id.tagboardpos,27);
        E5_button.setTag(R.id.tagboardpos,28);
        F5_button.setTag(R.id.tagboardpos,29);
        G5_button.setTag(R.id.tagboardpos,30);
        H5_button.setTag(R.id.tagboardpos,31);

        A4_button.setTag(R.id.tagboardpos,32);
        B4_button.setTag(R.id.tagboardpos,33);
        C4_button.setTag(R.id.tagboardpos,34);
        D4_button.setTag(R.id.tagboardpos,35);
        E4_button.setTag(R.id.tagboardpos,36);
        F4_button.setTag(R.id.tagboardpos,37);
        G4_button.setTag(R.id.tagboardpos,38);
        H4_button.setTag(R.id.tagboardpos,39);

        A3_button.setTag(R.id.tagboardpos,40);
        B3_button.setTag(R.id.tagboardpos,41);
        C3_button.setTag(R.id.tagboardpos,42);
        D3_button.setTag(R.id.tagboardpos,43);
        E3_button.setTag(R.id.tagboardpos,44);
        F3_button.setTag(R.id.tagboardpos,45);
        G3_button.setTag(R.id.tagboardpos,46);
        H3_button.setTag(R.id.tagboardpos,47);

        A2_button.setTag(R.id.tagboardpos,48);
        B2_button.setTag(R.id.tagboardpos,49);
        C2_button.setTag(R.id.tagboardpos,50);
        D2_button.setTag(R.id.tagboardpos,51);
        E2_button.setTag(R.id.tagboardpos,52);
        F2_button.setTag(R.id.tagboardpos,53);
        G2_button.setTag(R.id.tagboardpos,54);
        H2_button.setTag(R.id.tagboardpos,55);

        A1_button.setTag(R.id.tagboardpos,56);
        B1_button.setTag(R.id.tagboardpos,57);
        C1_button.setTag(R.id.tagboardpos,58);
        D1_button.setTag(R.id.tagboardpos,59);
        E1_button.setTag(R.id.tagboardpos,60);
        F1_button.setTag(R.id.tagboardpos,61);
        G1_button.setTag(R.id.tagboardpos,62);
        H1_button.setTag(R.id.tagboardpos,63);

        boardNotation[0]  = "a8";
        boardNotation[1]  = "b8";
        boardNotation[2]  = "c8";
        boardNotation[3]  = "d8";
        boardNotation[4]  = "e8";
        boardNotation[5]  = "f8";
        boardNotation[6]  = "g8";
        boardNotation[7]  = "h8";
        boardNotation[8]  = "a7";
        boardNotation[9]  = "b7";
        boardNotation[10] = "c7";
        boardNotation[11] = "d7";
        boardNotation[12] = "e7";
        boardNotation[13] = "f7";
        boardNotation[14] = "g7";
        boardNotation[15] = "h7";
        boardNotation[16] = "a6";
        boardNotation[17] = "b6";
        boardNotation[18] = "c6";
        boardNotation[19] = "d6";
        boardNotation[20] = "e6";
        boardNotation[21] = "f6";
        boardNotation[22] = "g6";
        boardNotation[23] = "h6";
        boardNotation[24] = "a5";
        boardNotation[25] = "b5";
        boardNotation[26] = "c5";
        boardNotation[27] = "d5";
        boardNotation[28] = "e5";
        boardNotation[29] = "f5";
        boardNotation[30] = "g5";
        boardNotation[31] = "h5";
        boardNotation[32] = "a4";
        boardNotation[33] = "b4";
        boardNotation[34] = "c4";
        boardNotation[35] = "d4";
        boardNotation[36] = "e4";
        boardNotation[37] = "f4";
        boardNotation[38] = "g4";
        boardNotation[39] = "h4";
        boardNotation[40] = "a3";
        boardNotation[41] = "b3";
        boardNotation[42] = "c3";
        boardNotation[43] = "d3";
        boardNotation[44] = "e3";
        boardNotation[45] = "f3";
        boardNotation[46] = "g3";
        boardNotation[47] = "h3";
        boardNotation[48] = "a2";
        boardNotation[49] = "b2";
        boardNotation[50] = "c2";
        boardNotation[51] = "d2";
        boardNotation[52] = "e2";
        boardNotation[53] = "f2";
        boardNotation[54] = "g2";
        boardNotation[55] = "h2";
        boardNotation[56] = "a1";
        boardNotation[57] = "b1";
        boardNotation[58] = "c1";
        boardNotation[59] = "d1";
        boardNotation[60] = "e1";
        boardNotation[61] = "f1";
        boardNotation[62] = "g1";
        boardNotation[63] = "h1";

        if(gameType.equals("pve") || gameType.equals("pvp")) {
            currentBoard.setInitialPosition();
            past.setInitialPosition();

            holder.setInitialPosition();
        }
        else if(gameType.equals("fp")){
            currentBoard.setCustomBoard(tempBoard);
            past.setCustomBoard(tempBoard);
            holder.setCustomBoard(tempBoard);
            }
        if(gameType.equals("fp")){
            currentMove = extras.getString("CurrentMove");
            playerColor = extras.getString("PlayAs");
            opponentType = extras.getString("OpponentType");
            currentAllowedColor = currentMove;
        }
        if(gameType.equals("pvp") | gameType.equals("pve") ){
            playerColor="white";
            currentAllowedColor = "white";
            if(gameType.equals("pve")){
                opponentType = "computer";
            }
            else{
                opponentType = "player";
            }
        }
        currentTurn=0;
        numberOfMoves=0;
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
            //System.out.println("InputPiece: ");
            //System.out.println(pieceResultsList);
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
            //System.out.println("InputPosition: ");
            //System.out.println(positionResultsList);
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

            //System.out.println("Result: ");
            int tempnum = notationToInt(finalCommand[1]);
            //System.out.println("ESQ voice = "+ tempnum);
            int[] resultNum = currentBoard.list.getMoveVoice(finalCommand[0],notationToInt(finalCommand[1]),'W');
            int SSQ = resultNum[0];
            int ESQ = resultNum[1];
            System.out.println("SSQ = "+ SSQ);
            System.out.println("ESQ = "+ ESQ);
            if(playerColor.equals(currentAllowedColor)){
                char c = playerColor.toUpperCase().charAt(0);
                if(currentBoard.move(SSQ,ESQ,c,true)){
                    refreshBoard();
                    String spokenText = finalCommand[0] + " " + finalCommand[1];
                    Toast.makeText(this, spokenText, Toast.LENGTH_SHORT).show();
                    switch(opponentType){
                        case "player":
                            changeAllowedColor();
                            changePlayerColor();
                            break;
                        case "computer":
                            changeAllowedColor();
                            break;
                    }

                }
            }
            else{
                Toast.makeText(this, "Illegal Move", Toast.LENGTH_SHORT).show();
            }

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

    private void refreshBoard(){
        int i=0;
       for(int r=7;r>=0;r--){
           for(int c=0;c<8;c++){
               if(currentBoard.getTile(i).getIfOccupied()){
                   switch (currentBoard.getTile(i).getPieceChar()){
                       case 'P':
                           board[r][c].setTag(R.id.tagpiece,"pawn");
                           board[r][c].setTag(R.id.tagcolor,"white");
                           board[r][c].setBackgroundResource(R.drawable.pawn_w);
                           break;
                       case 'K':
                           board[r][c].setTag(R.id.tagpiece,"king");
                           board[r][c].setTag(R.id.tagcolor,"white");
                           board[r][c].setBackgroundResource(R.drawable.king_w);
                           break;
                       case 'Q':
                           board[r][c].setTag(R.id.tagpiece,"queen");
                           board[r][c].setTag(R.id.tagcolor,"white");
                           board[r][c].setBackgroundResource(R.drawable.queen_w);
                           break;
                       case 'B':
                           board[r][c].setTag(R.id.tagpiece,"bishop");
                           board[r][c].setTag(R.id.tagcolor,"white");
                           board[r][c].setBackgroundResource(R.drawable.bishop_w);
                           break;
                       case 'N':
                           board[r][c].setTag(R.id.tagpiece,"knight");
                           board[r][c].setTag(R.id.tagcolor,"white");
                           board[r][c].setBackgroundResource(R.drawable.knight_w);
                           break;
                       case 'R':
                           board[r][c].setTag(R.id.tagpiece,"rook");
                           board[r][c].setTag(R.id.tagcolor,"white");
                           board[r][c].setBackgroundResource(R.drawable.rook_w);
                           break;
                       case 'p':
                           board[r][c].setTag(R.id.tagpiece,"pawn");
                           board[r][c].setTag(R.id.tagcolor,"black");
                           board[r][c].setBackgroundResource(R.drawable.pawn_b);
                           break;
                       case 'k':
                           board[r][c].setTag(R.id.tagpiece,"king");
                           board[r][c].setTag(R.id.tagcolor,"black");
                           board[r][c].setBackgroundResource(R.drawable.king_b);
                           break;
                       case 'q':
                           board[r][c].setTag(R.id.tagpiece,"queen");
                           board[r][c].setTag(R.id.tagcolor,"black");
                           board[r][c].setBackgroundResource(R.drawable.queen_b);
                           break;
                       case 'b':
                           board[r][c].setTag(R.id.tagpiece,"bishop");
                           board[r][c].setTag(R.id.tagcolor,"black");
                           board[r][c].setBackgroundResource(R.drawable.bishop_b);
                           break;
                       case 'n':
                           board[r][c].setTag(R.id.tagpiece,"knight");
                           board[r][c].setTag(R.id.tagcolor,"black");
                           board[r][c].setBackgroundResource(R.drawable.knight_b);
                           break;
                       case 'r':
                           board[r][c].setTag(R.id.tagpiece,"rook");
                           board[r][c].setTag(R.id.tagcolor,"black");
                           board[r][c].setBackgroundResource(R.drawable.rook_b);
                           break;
                   }
                   CharSequence description = currentBoard.getTile(i).getNotation().toUpperCase() + " "
                                            + board[r][c].getTag(R.id.tagcolor) + " "
                                            + board[r][c].getTag(R.id.tagpiece);

                   board[r][c].setContentDescription(description);
               }
               else{
                   CharSequence description = currentBoard.getTile(i).getNotation();
                   board[r][c].setBackgroundResource(0);
                   board[r][c].setContentDescription(description);
               }
               i++;
           }
       }
    }

    private void reverseRefreshBoard(){
        int i=63;
        //System.out.println("i = "+i);
        for(int r=7;r>=0;r--){
            for(int c=0;c<8;c++){
                if(currentBoard.getTile(i).getIfOccupied()){
                    switch (currentBoard.getTile(i).getPieceChar()){
                        case 'P':
                            board[r][c].setTag(R.id.tagpiece,"pawn");
                            board[r][c].setTag(R.id.tagcolor,"white");
                            board[r][c].setBackgroundResource(R.drawable.pawn_w);
                            break;
                        case 'K':
                            board[r][c].setTag(R.id.tagpiece,"king");
                            board[r][c].setTag(R.id.tagcolor,"white");
                            board[r][c].setBackgroundResource(R.drawable.king_w);
                            break;
                        case 'Q':
                            board[r][c].setTag(R.id.tagpiece,"queen");
                            board[r][c].setTag(R.id.tagcolor,"white");
                            board[r][c].setBackgroundResource(R.drawable.queen_w);
                            break;
                        case 'B':
                            board[r][c].setTag(R.id.tagpiece,"bishop");
                            board[r][c].setTag(R.id.tagcolor,"white");
                            board[r][c].setBackgroundResource(R.drawable.bishop_w);
                            break;
                        case 'N':
                            board[r][c].setTag(R.id.tagpiece,"knight");
                            board[r][c].setTag(R.id.tagcolor,"white");
                            board[r][c].setBackgroundResource(R.drawable.knight_w);
                            break;
                        case 'R':
                            board[r][c].setTag(R.id.tagpiece,"rook");
                            board[r][c].setTag(R.id.tagcolor,"white");
                            board[r][c].setBackgroundResource(R.drawable.rook_w);
                            break;
                        case 'p':
                            board[r][c].setTag(R.id.tagpiece,"pawn");
                            board[r][c].setTag(R.id.tagcolor,"black");
                            board[r][c].setBackgroundResource(R.drawable.pawn_b);
                            break;
                        case 'k':
                            board[r][c].setTag(R.id.tagpiece,"king");
                            board[r][c].setTag(R.id.tagcolor,"black");
                            board[r][c].setBackgroundResource(R.drawable.king_b);
                            break;
                        case 'q':
                            board[r][c].setTag(R.id.tagpiece,"queen");
                            board[r][c].setTag(R.id.tagcolor,"black");
                            board[r][c].setBackgroundResource(R.drawable.queen_b);
                            break;
                        case 'b':
                            board[r][c].setTag(R.id.tagpiece,"bishop");
                            board[r][c].setTag(R.id.tagcolor,"black");
                            board[r][c].setBackgroundResource(R.drawable.bishop_b);
                            break;
                        case 'n':
                            board[r][c].setTag(R.id.tagpiece,"knight");
                            board[r][c].setTag(R.id.tagcolor,"black");
                            board[r][c].setBackgroundResource(R.drawable.knight_b);
                            break;
                        case 'r':
                            board[r][c].setTag(R.id.tagpiece,"rook");
                            board[r][c].setTag(R.id.tagcolor,"black");
                            board[r][c].setBackgroundResource(R.drawable.rook_b);
                            break;
                    }
                    CharSequence description = currentBoard.getTile(i).getNotation().toUpperCase() + " "
                            + board[r][c].getTag(R.id.tagcolor) + " "
                            + board[r][c].getTag(R.id.tagpiece);

                    board[r][c].setContentDescription(description);
                }
                else{
                    CharSequence description = currentBoard.getTile(i).getNotation();
                    board[r][c].setBackgroundResource(0);
                    board[r][c].setContentDescription(description);
                }
                i--;
            }
        }
    }

    private String intToNotation(int pos){
        return boardNotation[pos];
    }

    private int notationToInt(String notation){
        switch (notation.toLowerCase()) {
            case "a8":
                return 0;
            case "b8":
                return 1;
            case "c8":
                return 2;
            case "d8":
                return 3;
            case "e8":
                return 4;
            case "f8":
                return 5;
            case "g8":
                return 6;
            case "h8":
                return 7;
            case "a7":
                return 8;
            case "b7":
                return 9;
            case "c7":
                return 10;
            case "d7":
                return 11;
            case "e7":
                return 12;
            case "f7":
                return 13;
            case "g7":
                return 14;
            case "h7":
                return 15;
            case "a6":
                return 16;
            case "b6":
                return 17;
            case "c6":
                return 18;
            case "d6":
                return 19;
            case "e6":
                return 20;
            case "f6":
                return 21;
            case "g6":
                return 22;
            case "h6":
                return 23;
            case "a5":
                return 24;
            case "b5":
                return 25;
            case "c5":
                return 26;
            case "d5":
                return 27;
            case "e5":
                return 28;
            case "f5":
                return 29;
            case "g5":
                return 30;
            case "h5":
                return 31;
            case "a4":
                return 32;
            case "b4":
                return 33;
            case "c4":
                return 34;
            case "d4":
                return 35;
            case "e4":
                return 36;
            case "f4":
                return 37;
            case "g4":
                return 38;
            case "h4":
                return 39;
            case "a3":
                return 40;
            case "b3":
                return 41;
            case "c3":
                return 42;
            case "d3":
                return 43;
            case "e3":
                return 44;
            case "f3":
                return 45;
            case "g3":
                return 46;
            case "h3":
                return 47;
            case "a2":
                return 48;
            case "b2":
                return 49;
            case "c2":
                return 50;
            case "d2":
                return 51;
            case "e2":
                return 52;
            case "f2":
                return 53;
            case "g2":
                return 54;
            case "h2":
                return 55;
            case "a1":
                return 56;
            case "b1":
                return 57;
            case "c1":
                return 58;
            case "d1":
                return 59;
            case "e1":
                return 60;
            case "f1":
                return 61;
            case "g1":
                return 62;
            case "h1":
                return 63;
            default:
                return -1;
        }
    }

    private void changeAllowedColor(){
        if(currentAllowedColor.equals("white")){
            currentAllowedColor = "black";
        }
        else{
            currentAllowedColor = "white";
        }
    }

    private void changePlayerColor(){
        if(playerColor.equals("white")){
            playerColor = "black";
        }
        else{
            playerColor = "white";
        }
    }

    private void simulateMoves(){
        past.setInitialPosition();
        switch (opponentType){
            case "player":
                for(int i =0;i<numberOfMoves-1;i++){
                    int SSQ = currentBoard.getGameMoveList().get(i).getStartSquareID();
                    int ESQ = currentBoard.getGameMoveList().get(i).getEndSquareID();
                    char color = currentBoard.getGameMoveList().get(i).getMovedPiece().getColor();
                    past.move(SSQ,ESQ,color,true);
                }
                break;
            case "computer":
                for(int i =0;i<numberOfMoves-2;i++){
                    int SSQ = currentBoard.getGameMoveList().get(i).getStartSquareID();
                    int ESQ = currentBoard.getGameMoveList().get(i).getEndSquareID();
                    char color = currentBoard.getGameMoveList().get(i).getMovedPiece().getColor();
                    past.move(SSQ,ESQ,color,true);
                }
                break;
        }
    }

    private void copyPastToCurrent(){
        currentBoard.setInitialPosition();
        for(int i =0;i<past.getGameMoveList().size();i++){
            int SSQ = currentBoard.getGameMoveList().get(i).getStartSquareID();
            int ESQ = currentBoard.getGameMoveList().get(i).getEndSquareID();
            char color = currentBoard.getGameMoveList().get(i).getMovedPiece().getColor();
            currentBoard.move(SSQ,ESQ,color,true);
        }
    }

    private void copyCurrentToHolder(){
        holder.setInitialPosition();
        for(int i =0;i<currentBoard.getGameMoveList().size();i++){
            int SSQ = currentBoard.getGameMoveList().get(i).getStartSquareID();
            int ESQ = currentBoard.getGameMoveList().get(i).getEndSquareID();
            char color = currentBoard.getGameMoveList().get(i).getMovedPiece().getColor();
            holder.move(SSQ,ESQ,color,true);
        }
    }

    private void copyHolderToCurrent(){
        currentBoard.setInitialPosition();
        for(int i =0;i<holder.getGameMoveList().size();i++){
            int SSQ = holder.getGameMoveList().get(i).getStartSquareID();
            int ESQ = holder.getGameMoveList().get(i).getEndSquareID();
            char color = holder.getGameMoveList().get(i).getMovedPiece().getColor();
            currentBoard.move(SSQ,ESQ,color,true);
        }
        holder.setInitialPosition();
    }
}
