package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //1:player1     2:player2    0:null
     int player = 1, count = 0;
     int[] board = {0, 0, 0, 0, 0, 0, 0, 0, 0};
     int[][] winningPosition = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
     boolean activeStatus = true;

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if(board[tappedCounter] == 0 && activeStatus) {
            board[tappedCounter] = player;
            counter.setTranslationY(-500);
            count++;
            if (player == 1) {
                counter.setImageResource(R.drawable.cross);
                player = 2;
            } else {
                counter.setImageResource(R.drawable.circle);
                player = 1;
            }
            counter.animate().translationYBy(500).setDuration(300);
            for (int[] win : winningPosition) {
                if (board[win[0]] == board[win[1]] && board[win[1]] == board[win[2]] && board[win[0]] != 0) {
                    String winner = "";
                    if (player == 2)
                        winner = "Cross";
                    else
                        winner = "Circle";
                    activeStatus = false;
                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                    TextView winnerTextView = (TextView) findViewById(R.id.textView);
                    winnerTextView.setText(winner + " Won!");
                    winnerTextView.setVisibility(View.VISIBLE);
                    playAgainButton.setVisibility(View.VISIBLE);
                }else if(count == 9){
                    activeStatus = false;
                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                    TextView winnerTextView = (TextView) findViewById(R.id.textView);
                    winnerTextView.setText("No-one Won!");
                    winnerTextView.setVisibility(View.VISIBLE);
                    playAgainButton.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void playAgain(View view) {
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
        TextView winnerTextView = (TextView) findViewById(R.id.textView);
        winnerTextView.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        Log.i("info","button pressed");
        for(int i=0; i<gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        for(int i=0; i<9 ;i++)
            board[i] = 0;
        player = 1;
        activeStatus = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}