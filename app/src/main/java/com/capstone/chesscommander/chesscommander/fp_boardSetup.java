package com.capstone.chesscommander.chesscommander;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by Melvin on 3/23/16.
 */
public class fp_boardSetup extends Activity {
/**
 * Tag Map
 * [Key,Tag]
 * [0,piece type(rook,king,pawn)]
 * [1,piece color]
 * [2,bench]
* */
    private String message;
    private int wKing,wQueen,wPawn,wKnight,wBishop,wRook;
    private int bKing,bQueen,bPawn,bKnight,bBishop,bRook;
    private int wPieces,bPieces;
    private Drawable bgdraw;
    private boolean empty,pieceSelected;
    private String gameType,opponentType,currentMove,playAs,difficulty;
    private ImageButton[][] board = new ImageButton[8][8];
    private ImageButton currentPiece;
    private ImageButton rook_w,knight_w,bishop_w,queen_w,king_w,pawn_w;
    private ImageButton rook_b,knight_b,bishop_b,queen_b,king_b,pawn_b;
    private ImageButton A8_button,B8_button,C8_button,D8_button,E8_button,F8_button,G8_button,H8_button;
    private ImageButton A7_button,B7_button,C7_button,D7_button,E7_button,F7_button,G7_button,H7_button;
    private ImageButton A6_button,B6_button,C6_button,D6_button,E6_button,F6_button,G6_button,H6_button;
    private ImageButton A5_button,B5_button,C5_button,D5_button,E5_button,F5_button,G5_button,H5_button;
    private ImageButton A4_button,B4_button,C4_button,D4_button,E4_button,F4_button,G4_button,H4_button;
    private ImageButton A3_button,B3_button,C3_button,D3_button,E3_button,F3_button,G3_button,H3_button;
    private ImageButton A2_button,B2_button,C2_button,D2_button,E2_button,F2_button,G2_button,H2_button;
    private ImageButton A1_button,B1_button,C1_button,D1_button,E1_button,F1_button,G1_button,H1_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        gameType = extras.getString("GameType");
        opponentType = extras.getString("OpponentType");
        if(opponentType.equals("Computer")){
            currentMove = extras.getString("CurrentMove");
            playAs = extras.getString("PlayAs");
            difficulty = extras.getString("Difficulty");
        }
        setContentView(R.layout.fp_boardsetup);

        boardSetup();

        rook_w   =(ImageButton)findViewById(R.id.rook_w_button);
        knight_w =(ImageButton)findViewById(R.id.knight_w_button);
        bishop_w =(ImageButton)findViewById(R.id.bishop_w_button);
        queen_w  =(ImageButton)findViewById(R.id.queen_w_button);
        king_w   =(ImageButton)findViewById(R.id.king_w_button);
        pawn_w   =(ImageButton)findViewById(R.id.pawn_w_button);

        rook_b   =(ImageButton)findViewById(R.id.rook_b_button);
        knight_b =(ImageButton)findViewById(R.id.knight_b_button);
        bishop_b =(ImageButton)findViewById(R.id.bishop_b_button);
        queen_b  =(ImageButton)findViewById(R.id.queen_b_button);
        king_b   =(ImageButton)findViewById(R.id.king_b_button);
        pawn_b   =(ImageButton)findViewById(R.id.pawn_b_button);

        setPieceTags();
        pieceSelected = false;

        wPieces = 0;
        wKing = 0;
        wQueen = 0;
        wPawn = 0;
        wKnight = 0;
        wBishop = 0;
        wRook = 0;

        bPieces = 0;
        bKing = 0;
        bQueen = 0;
        bPawn = 0;
        bKnight = 0;
        bBishop = 0;
        bRook = 0;

       }

    public void onButtonClick(View view){
        empty = (view.getBackground()==null);

        if(empty && pieceSelected){//Place piece
            bgdraw = currentPiece.getBackground();
            view.setBackground(bgdraw);
            view.setTag(R.id.tagpiece, currentPiece.getTag(R.id.tagpiece));
            view.setTag(R.id.tagcolor, currentPiece.getTag(R.id.tagcolor));
            CharSequence description = view.getContentDescription().subSequence(0,2);
            description = description + " " + view.getTag(R.id.tagcolor) + " " + view.getTag(R.id.tagpiece);
            view.setContentDescription(description);
            String taginfo =currentPiece.getTag(R.id.tagpiece).toString();
            switch (taginfo){
                case "king":
                    if(currentPiece.getTag(R.id.tagcolor).toString().equals("white")){
                        wPieces++;
                        wKing++;
                    }
                    if(currentPiece.getTag(R.id.tagcolor).toString().equals("black")){
                        bPieces++;
                        bKing++;
                    }
                    break;
                case "queen":
                    if(currentPiece.getTag(R.id.tagcolor).toString().equals("white")){
                        wPieces++;
                        wQueen++;
                    }
                    if(currentPiece.getTag(R.id.tagcolor).toString().equals("black")){
                        bPieces++;
                        bQueen++;
                    }
                    break;
                case "rook":
                    if(currentPiece.getTag(R.id.tagcolor).toString().equals("white")){
                        wPieces++;
                        wRook++;
                    }
                    if(currentPiece.getTag(R.id.tagcolor).toString().equals("black")){
                        bPieces++;
                        bRook++;
                    }
                    break;
                case "bishop":
                    if(currentPiece.getTag(R.id.tagcolor).toString().equals("white")){
                        wPieces++;
                        wBishop++;
                    }
                    if(currentPiece.getTag(R.id.tagcolor).toString().equals("black")){
                        bPieces++;
                        bBishop++;
                    }
                    break;
                case "knight":
                    if(currentPiece.getTag(R.id.tagcolor).toString().equals("white")){
                        wPieces++;
                        wKnight++;
                    }
                    if(currentPiece.getTag(R.id.tagcolor).toString().equals("black")){
                        bPieces++;
                        bKnight++;
                    }
                    break;
                case "pawn":
                    if(currentPiece.getTag(R.id.tagcolor).toString().equals("white")){
                        wPieces++;
                        wPawn++;
                    }
                    if(currentPiece.getTag(R.id.tagcolor).toString().equals("black")){
                        bPieces++;
                        bPawn++;
                    }
                    break;
            }
        }
        else if(!empty && pieceSelected){//Remove com.capstone.chesscommander.chesscommander.GameLogic.Piece
            if(view.getBackground().equals(currentPiece.getBackground())){
                view.setBackgroundResource(0);
                view.setTag(R.id.tagpiece, "");
                view.setTag(R.id.tagcolor, "");
                CharSequence description = view.getContentDescription().subSequence(0,2);
                view.setContentDescription(description);
                String taginfo =currentPiece.getTag(R.id.tagpiece).toString();
                switch (taginfo){
                    case "king":
                        if(currentPiece.getTag(R.id.tagcolor).toString().equals("white")){
                            wPieces--;
                            wKing--;
                        }
                        if(currentPiece.getTag(R.id.tagcolor).toString().equals("black")){
                            bPieces--;
                            bKing--;
                        }
                        break;
                    case "queen":
                        if(currentPiece.getTag(R.id.tagcolor).toString().equals("white")){
                            wPieces--;
                            wQueen--;
                        }
                        if(currentPiece.getTag(R.id.tagcolor).toString().equals("black")){
                            bPieces--;
                            bQueen--;
                        }
                        break;
                    case "rook":
                        if(currentPiece.getTag(R.id.tagcolor).toString().equals("white")){
                            wPieces--;
                            wRook--;
                        }
                        if(currentPiece.getTag(R.id.tagcolor).toString().equals("black")){
                            bPieces--;
                            bRook--;
                        }
                        break;
                    case "bishop":
                        if(currentPiece.getTag(R.id.tagcolor).toString().equals("white")){
                            wPieces--;
                            wBishop--;
                        }
                        if(currentPiece.getTag(R.id.tagcolor).toString().equals("black")){
                            bPieces--;
                            bBishop--;
                        }
                        break;
                    case "knight":
                        if(currentPiece.getTag(R.id.tagcolor).toString().equals("white")){
                            wPieces--;
                            wKnight--;
                        }
                        if(currentPiece.getTag(R.id.tagcolor).toString().equals("black")){
                            bPieces--;
                            bKnight--;
                        }
                        break;
                    case "pawn":
                        if(currentPiece.getTag(R.id.tagcolor).toString().equals("white")){
                            wPieces--;
                            wPawn--;
                        }
                        if(currentPiece.getTag(R.id.tagcolor).toString().equals("black")){
                            bPieces--;
                            bPawn--;
                        }
                        break;
                }
            }
            else{//Replace com.capstone.chesscommander.chesscommander.GameLogic.Piece
                String taginfo =view.getTag(R.id.tagpiece).toString();
                switch (taginfo){
                    case "king":
                        if(view.getTag(R.id.tagcolor).toString().equals("white")){
                            wPieces--;
                            wKing--;
                        }
                        if(view.getTag(R.id.tagcolor).toString().equals("black")){
                            bPieces--;
                            bKing--;
                        }
                        break;
                    case "queen":
                        if(view.getTag(R.id.tagcolor).toString().equals("white")){
                            wPieces--;
                            wQueen--;
                        }
                        if(view.getTag(R.id.tagcolor).toString().equals("black")){
                            bPieces--;
                            bQueen--;
                        }
                        break;
                    case "rook":
                        if(view.getTag(R.id.tagcolor).toString().equals("white")){
                            wPieces--;
                            wRook--;
                        }
                        if(view.getTag(R.id.tagcolor).toString().equals("black")){
                            bPieces--;
                            bRook--;
                        }
                        break;
                    case "bishop":
                        if(view.getTag(R.id.tagcolor).toString().equals("white")){
                            wPieces--;
                            wBishop--;
                        }
                        if(view.getTag(R.id.tagcolor).toString().equals("black")){
                            bPieces--;
                            bBishop--;
                        }
                        break;
                    case "knight":
                        if(view.getTag(R.id.tagcolor).toString().equals("white")){
                            wPieces--;
                            wKnight--;
                        }
                        if(view.getTag(R.id.tagcolor).toString().equals("black")){
                            bPieces--;
                            bKnight--;
                        }
                        break;
                    case "pawn":
                        if(view.getTag(R.id.tagcolor).toString().equals("white")){
                            wPieces--;
                            wPawn--;
                        }
                        if(view.getTag(R.id.tagcolor).toString().equals("black")){
                            bPieces--;
                            bPawn--;
                        }
                        break;
                }
                bgdraw = currentPiece.getBackground();
                view.setBackground(bgdraw);
                view.setTag(R.id.tagpiece, currentPiece.getTag(R.id.tagpiece));
                view.setTag(R.id.tagcolor, currentPiece.getTag(R.id.tagcolor));
                CharSequence description = view.getContentDescription().subSequence(0,2);
                description = description + " " + view.getTag(R.id.tagcolor) + " " + view.getTag(R.id.tagpiece);
                view.setContentDescription(description);
                taginfo =view.getTag(R.id.tagpiece).toString();
                switch (taginfo){
                    case "king":
                        if(view.getTag(R.id.tagcolor).toString().equals("white")){
                            wPieces++;
                            wKing++;
                        }
                        if(view.getTag(R.id.tagcolor).toString().equals("black")){
                            bPieces++;
                            bKing++;
                        }
                        break;
                    case "queen":
                        if(view.getTag(R.id.tagcolor).toString().equals("white")){
                            wPieces++;
                            wQueen++;
                        }
                        if(view.getTag(R.id.tagcolor).toString().equals("black")){
                            bPieces++;
                            bQueen++;
                        }
                        break;
                    case "rook":
                        if(view.getTag(R.id.tagcolor).toString().equals("white")){
                            wPieces++;
                            wRook++;
                        }
                        if(view.getTag(R.id.tagcolor).toString().equals("black")){
                            bPieces++;
                            bRook++;
                        }
                        break;
                    case "bishop":
                        if(view.getTag(R.id.tagcolor).toString().equals("white")){
                            wPieces++;
                            wBishop++;
                        }
                        if(view.getTag(R.id.tagcolor).toString().equals("black")){
                            bPieces++;
                            bBishop++;
                        }
                        break;
                    case "knight":
                        if(view.getTag(R.id.tagcolor).toString().equals("white")){
                            wPieces++;
                            wKnight++;
                        }
                        if(view.getTag(R.id.tagcolor).toString().equals("black")){
                            bPieces++;
                            bKnight++;
                        }
                        break;
                    case "pawn":
                        if(view.getTag(R.id.tagcolor).toString().equals("white")){
                            wPieces++;
                            wPawn++;
                        }
                        if(view.getTag(R.id.tagcolor).toString().equals("black")){
                            bPieces++;
                            bPawn++;
                        }
                        break;
                }
            }
        }
    }

    public void onBenchButtonClick(View view){
        pieceSelected = view.getTag(R.id.tagbench).equals("bench");
        currentPiece = (ImageButton) findViewById(view.getId());
    }

    private void boardSetup(){
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
        //Background Loop
                /**
         * This loop goes through all empty tiles and sets the background to 0, if this is not done a grey
         * square will appear on top of empty tiles since it is a imageButton and the default background is grey
         * board[r][c] represents the button on the board.
         */
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                board[r][c].setBackgroundResource(0);
            }
        }
        String content = "";
        for(int r=0;r<8;r++){
            for(int c=0;c<8;c++){
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
                board[r][c].setBackgroundResource(0);
                board[r][c].setTag(R.id.tagpiece,"");
                board[r][c].setTag(R.id.tagcolor,"");
            }
        }
    }//End of method

    private void setPieceTags(){
        rook_w.setTag(R.id.tagpiece,"rook");
        rook_w.setTag(R.id.tagcolor,"white");
        rook_w.setTag(R.id.tagbench,"bench");

        knight_w.setTag(R.id.tagpiece,"knight");
        knight_w.setTag(R.id.tagcolor,"white");
        knight_w.setTag(R.id.tagbench,"bench");

        bishop_w.setTag(R.id.tagpiece,"bishop");
        bishop_w.setTag(R.id.tagcolor,"white");
        bishop_w.setTag(R.id.tagbench,"bench");

        queen_w.setTag(R.id.tagpiece,"queen");
        queen_w.setTag(R.id.tagcolor,"white");
        queen_w.setTag(R.id.tagbench,"bench");

        king_w.setTag(R.id.tagpiece,"king");
        king_w.setTag(R.id.tagcolor,"white");
        king_w.setTag(R.id.tagbench,"bench");

        pawn_w.setTag(R.id.tagpiece,"pawn");
        pawn_w.setTag(R.id.tagcolor,"white");
        pawn_w.setTag(R.id.tagbench,"bench");

        rook_b.setTag(R.id.tagpiece,"rook");
        rook_b.setTag(R.id.tagcolor,"black");
        rook_b.setTag(R.id.tagbench,"bench");

        knight_b.setTag(R.id.tagpiece,"knight");
        knight_b.setTag(R.id.tagcolor,"black");
        knight_b.setTag(R.id.tagbench,"bench");

        bishop_b.setTag(R.id.tagpiece,"bishop");
        bishop_b.setTag(R.id.tagcolor,"black");
        bishop_b.setTag(R.id.tagbench,"bench");

        queen_b.setTag(R.id.tagpiece,"queen");
        queen_b.setTag(R.id.tagcolor,"black");
        queen_b.setTag(R.id.tagbench,"bench");

        king_b.setTag(R.id.tagpiece,"king");
        king_b.setTag(R.id.tagcolor,"black");
        king_b.setTag(R.id.tagbench,"bench");

        pawn_b.setTag(R.id.tagpiece,"pawn");
        pawn_b.setTag(R.id.tagcolor,"black");
        pawn_b.setTag(R.id.tagbench,"bench");
    }

    public void onStartButtonClick(View view) {
        boolean startable = false;
        if(wKing==1 && bKing==1){
            startable = true;
        }
        if(wQueen>9 | bQueen>9){
            startable = false;
            if(wQueen>9){
                message = "You must have less than 9 White Queens";
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }
            if(bQueen>9){
                message = "You must have less than 9 Black Queens";
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }
        }
        if(wPawn>8 | bPawn>8){
            startable = false;
            if(wPawn>8){
                message = "You must have less than 8 White Pawns";
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }
            if(bPawn>8){
                message = "You must have less than 8 Black Pawns";
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }
        }
        if(wKnight>10 | bKnight>10){
            startable = false;
            if(wKnight>8){
                message = "You must have less than 10 White Knights";
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }
            if(bKnight>8){
                message = "You must have less than 10 Black Knights";
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }
        }
        if(wBishop>10 | bBishop>10){
            startable = false;
            if(wBishop>8){
                message = "You must have less than 10 White Bishops";
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }
            if(bBishop>8){
                message = "You must have less than 10 Black Bishops";
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }
        }
        if(wRook>10 | bRook>10){
            startable = false;
            if(wRook>8){
                message = "You must have less than 8 White Rooks";
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }
            if(bRook>8){
                message = "You must have less than 8 Black Rooks";
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }
        }
        if(wPieces>17 && bPieces>17){
            startable = false;
            if(wPieces>17){
                message = "You must have less than 17 White Pieces";
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }
            if(bPieces>17){
                message = "You must have less than 17 Black Pieces";
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }
        }
        if(!startable && (!(wKing==1) | !(bKing==1)) ){
            if(!(wKing==1)){
                message = "You must have 1 white king";
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }
            if(!(bKing==1)){
                message = "You must have 1 black king";
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }
        }
        if(startable){
            Intent startGameIntent = new Intent(this,game_screen.class);
            startGameIntent.putExtra("GameType",gameType);
            startGameIntent.putExtra("OpponentType",opponentType);
            if(opponentType.equals("Computer")){
                startGameIntent.putExtra("CurrentMove",currentMove);
                startGameIntent.putExtra("PlayAs",playAs);
                startGameIntent.putExtra("Difficulty",difficulty);
            }
            int transfer[] = new int[64];
            int pos=0;
                for(int r=0;r<8;r++){
                    for(int c=0;c<8;c++){
                        String taginfo =board[r][c].getTag(R.id.tagpiece).toString();
                        switch (taginfo){
                            case "king":
                                if(board[r][c].getTag(R.id.tagcolor).toString().equals("white")){
                                    transfer[pos] = 1;
                                }
                                if(board[r][c].getTag(R.id.tagcolor).toString().equals("black")){
                                    transfer[pos] = 7;
                                }
                                break;
                            case "queen":
                                if(board[r][c].getTag(R.id.tagcolor).toString().equals("white")){
                                    transfer[pos] = 2;
                                }
                                if(board[r][c].getTag(R.id.tagcolor).toString().equals("black")){
                                    transfer[pos] = 8;
                                }
                                break;
                            case "rook":
                                if(board[r][c].getTag(R.id.tagcolor).toString().equals("white")){
                                    transfer[pos] = 3;
                                }
                                if(board[r][c].getTag(R.id.tagcolor).toString().equals("black")){
                                    transfer[pos] = 9;
                                }
                                break;
                            case "bishop":
                                if(board[r][c].getTag(R.id.tagcolor).toString().equals("white")){
                                    transfer[pos] = 4;
                                }
                                if(board[r][c].getTag(R.id.tagcolor).toString().equals("black")){
                                    transfer[pos] = 10;
                                }
                                break;
                            case "knight":
                                if(board[r][c].getTag(R.id.tagcolor).toString().equals("white")){
                                    transfer[pos] = 5;
                                }
                                if(board[r][c].getTag(R.id.tagcolor).toString().equals("black")){
                                    transfer[pos] = 11;
                                }
                                break;
                            case "pawn":
                                if(board[r][c].getTag(R.id.tagcolor).toString().equals("white")){
                                    transfer[pos] = 6;
                                }
                                if(board[r][c].getTag(R.id.tagcolor).toString().equals("black")){
                                    transfer[pos] = 12;
                                }
                                break;
                            default:
                                transfer[pos] = 0;
                                break;
                        }
                      pos++;
                    }
                }
            startGameIntent.putExtra("Board",transfer);
            startActivity(startGameIntent);
            }

        }

}


