package com.example.connect3recreate;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    int tapped_counter=0;
    int[] gamestate={2,2,2,2,2,2,2,2,2};
    int[][] winning_positions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6},{1,4,7}, {2,5,8},{0,4,8},{2,4,6}};
    boolean gameactive=true;
    TextView textView;
    Button play_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        play_button = (Button) findViewById(R.id.playButton);
    }

    public void dropIn(View view) {

        ImageView imageViewCounter = (ImageView) view;
        int counter_tag = Integer.parseInt(imageViewCounter.getTag().toString());
        if (gamestate[counter_tag] == 2 && gameactive) {
            gamestate[counter_tag] = tapped_counter;
            imageViewCounter.setTranslationY(-1500);
            if (tapped_counter == 0) {
                imageViewCounter.setImageResource(R.drawable.red);
                tapped_counter = 1;
            } else {
                imageViewCounter.setImageResource(R.drawable.yellow);
                tapped_counter = 0;
            }

            imageViewCounter.animate().translationYBy(1500).rotation(3000).setDuration(300);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                if(Arrays.stream(gamestate).anyMatch(i -> i== 2)){
                    System.out.print("found");
                }
                else{
                    textView.setText("Game Tie");
                    gameactive=false;
                    textView.setVisibility(View.VISIBLE);
                    play_button.setVisibility(View.VISIBLE);
                }
            }

            for (int winning_position[] : winning_positions) {
                if (gamestate[winning_position[0]] == gamestate[winning_position[1]] && gamestate[winning_position[1]] == gamestate[winning_position[2]] && gamestate[winning_position[0]] != 2) {
                    if(tapped_counter==0){
                        textView.setText("Yellow has won");

                    }
                    else{
                        textView.setText("Red has won");
                    }
                    gameactive=false;
                    textView.setVisibility(View.VISIBLE);
                    play_button.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void playAgain(View view){
        System.out.println("piya kiya jiay dola");
        textView.setVisibility(View.INVISIBLE);
        play_button.setVisibility(View.INVISIBLE);
        gameactive=true;
        tapped_counter=0;
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for(int i=0; i<gridLayout.getChildCount();i++){
            gamestate[i]=2;
           ImageView imageViewCounter= (ImageView) gridLayout.getChildAt(i);
            imageViewCounter.setImageDrawable(null);
        }


    }
}
