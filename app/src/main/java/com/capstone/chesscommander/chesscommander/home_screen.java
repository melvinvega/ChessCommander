package com.capstone.chesscommander.chesscommander;

/**
 * Created by Melvin on 3/20/16.
 */
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class home_screen extends AppCompatActivity {

    private Button pve, pvp, freePlay;
    private boolean vsAi = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        pve = (Button) findViewById(R.id.pve);
        pvp = (Button) findViewById(R.id.pvp);
        freePlay = (Button) findViewById(R.id.freeplay);
    }

    public void onPvEButtonClick(View view) {
        //String message = "You selected to play against a Computer";
        //  Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
        Intent pveScreenIntent = new Intent(this,pve_options.class);
        pveScreenIntent.putExtra("GameType","pve");
        startActivity(pveScreenIntent);
    }

    public void onPVPButtonClick(View view) {
        //String message = "You selected to play against a Player";
        //Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        Intent pvpScreenIntent = new Intent(this,game_screen.class);
        pvpScreenIntent.putExtra("GameType","pvp");
        startActivity(pvpScreenIntent);
    }

    public void onFreePlayButtonClick(View view) {
        //String message = "You selected to Free Play";
        //Toast.makeText(this,message,Toast.LENGTH_SHORT).show();

        final Intent fpScreenIntent = new Intent(this,fp_options.class);
        fpScreenIntent.putExtra("GameType", "fp");
        CharSequence oponentType[] = new CharSequence[] {"Computer", "Player"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose opponent type");
        builder.setItems(oponentType, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                vsAi = true;
                fpScreenIntent.putExtra("GameType","fp");
                switch (which) {
                    case 0:
                        fpScreenIntent.putExtra("OpponentType", "Computer");
                        break;
                    case 1:
                        fpScreenIntent.putExtra("OpponentType", "Player");
                        break;
                }
                startActivity(fpScreenIntent);
            }

        });
        builder.show();

    }

}
