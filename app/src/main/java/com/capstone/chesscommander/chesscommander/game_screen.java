package com.capstone.chesscommander.chesscommander;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;



/**
 * Created by Melvin on 3/20/16.
 */
public class game_screen extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board_test);

        ImageView A8 = (ImageView) findViewById(R.id.A8);
        String resName = A8.getDrawable().toString();
        String contentDescription = "A8 " + resName;
        A8.setContentDescription(contentDescription);

    }

}
