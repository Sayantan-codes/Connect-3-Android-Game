package com.example.sayan.connect3game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int active_pl = 0;
    boolean gameActive = true;
    int click_count=0;
    boolean visible = false;
    //0 : yellow, 1 : red
    int gameState[]={2,2,2,2,2,2,2,2,2};
    int red=0, yellow = 0;
    int win[][] = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    void dropIn(View view) {
        ImageView counter = (ImageView) view;
        Log.i("Info", counter.getTag().toString());

        click_count++;
        int x = Integer.parseInt(counter.getTag().toString());
        if (gameState[x] == 2 && gameActive)
        {
            gameState[x] = active_pl;
            counter.setTranslationY(-1500);
            if (active_pl == 0) {
                counter.setImageResource(R.drawable.yellow);
                active_pl = 1;
            }
            else {
                counter.setImageResource(R.drawable.red);
                active_pl = 0;

            }
            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);
            int i, j, p1 = 0, p2 = 0;
            for (i = 0; i < 8; i++) {
                p1 = 0;
                p2 = 0;
                for (j = 0; j < 3; j++) {
                    if (gameState[win[i][j]] == 0) {
                        p1++;
                    }
                    if (gameState[win[i][j]] == 1) {
                        p2++;
                    }
                }
                if (p1 == 3) {
                    Toast.makeText(this, "Yellow Won!", Toast.LENGTH_SHORT).show();
                    gameActive = false;
                    visible = true;
                    yellow++;

                } else if (p2 == 3) {
                    Toast.makeText(this, "Red Won!", Toast.LENGTH_SHORT).show();
                    gameActive = false;
                    visible = true;
                    red++;
                }
            }

        }
        if (click_count==9 && gameActive)
        {
            Toast.makeText(this, "It is a tie!", Toast.LENGTH_SHORT).show();
            visible = true;
        }
        if (visible)
        {
            Button rb = (Button) findViewById(R.id.retryButton);
            rb.setVisibility(View.VISIBLE);
            TextView t = (TextView) findViewById(R.id.blank);
            t.setText("Red: "+red+" | "+"Yellow: "+yellow);
            t.setVisibility(View.VISIBLE);
        }
    }

    void retry(View view)
    {
        Button rb = (Button) findViewById(R.id.retryButton);
        rb.setVisibility(View.INVISIBLE);

        int i;
        GridLayout grd = (GridLayout) findViewById(R.id.gridLayout);
        for(i=0;i<grd.getChildCount();i++)
        {
            ImageView counter = (ImageView) grd.getChildAt(i);
            counter.setImageDrawable(null);
            gameState[i]=2;
        }
        active_pl = 0;
        gameActive = true;
        visible = false;
        click_count = 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

