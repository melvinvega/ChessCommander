package com.capstone.chesscommander.chesscommander;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

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
    private int prevId;
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

       }

    public void onButtonClick(View view){
        empty = (view.getBackground()==null);

        if(empty && pieceSelected){
            bgdraw = currentPiece.getBackground();
            view.setBackground(bgdraw);
        }
        else if(!empty && pieceSelected){
            if(view.equals(currentPiece)){
                view.setBackgroundResource(0);
            }
            else{
                bgdraw = currentPiece.getBackground();
                view.setBackground(bgdraw);
            }
        }
    }

    public void onBenchButtonClick(View view){
        pieceSelected = view.getTag(R.id.tag2).equals("bench");
        currentPiece = (ImageButton) findViewById(view.getId());
    }

    private void boardSetup(){

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

        /*
        This loop goes through all empty tiles and sets the background to 0, if this is not done a grey
        square will appear on top of empty tiles since it is a imageButton and the default background is grey
        board[r][c] represents the button on the board.
         */
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                board[r][c].setBackgroundResource(0);
            }
        }
    }//End of method

    private void setPieceTags(){

        rook_w.setTag(R.id.tag0,"rook");
        rook_w.setTag(R.id.tag1,"white");
        rook_w.setTag(R.id.tag2,"bench");

        knight_w.setTag(R.id.tag0,"knight");
        knight_w.setTag(R.id.tag1,"white");
        knight_w.setTag(R.id.tag2,"bench");

        bishop_w.setTag(R.id.tag0,"bishop");
        bishop_w.setTag(R.id.tag1,"white");
        bishop_w.setTag(R.id.tag2,"bench");

        queen_w.setTag(R.id.tag0,"queen");
        queen_w.setTag(R.id.tag1,"white");
        queen_w.setTag(R.id.tag2,"bench");

        king_w.setTag(R.id.tag0,"king");
        king_w.setTag(R.id.tag1,"white");
        king_w.setTag(R.id.tag2,"bench");

        pawn_w.setTag(R.id.tag0,"pawn");
        pawn_w.setTag(R.id.tag1,"white");
        pawn_w.setTag(R.id.tag2,"bench");

        rook_b.setTag(R.id.tag0,"rook");
        rook_b.setTag(R.id.tag1,"black");
        rook_b.setTag(R.id.tag2,"bench");

        knight_b.setTag(R.id.tag0,"knight");
        knight_b.setTag(R.id.tag1,"black");
        knight_b.setTag(R.id.tag2,"bench");

        bishop_b.setTag(R.id.tag0,"bishop");
        bishop_b.setTag(R.id.tag1,"black");
        bishop_b.setTag(R.id.tag2,"bench");

        queen_b.setTag(R.id.tag0,"queen");
        queen_b.setTag(R.id.tag1,"black");
        queen_b.setTag(R.id.tag2,"bench");

        king_b.setTag(R.id.tag0,"king");
        king_b.setTag(R.id.tag1,"black");
        king_b.setTag(R.id.tag2,"bench");

        pawn_b.setTag(R.id.tag0,"pawn");
        pawn_b.setTag(R.id.tag1,"black");
        pawn_b.setTag(R.id.tag2,"bench");

    }
    }

