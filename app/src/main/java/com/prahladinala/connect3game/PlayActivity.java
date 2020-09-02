package com.prahladinala.connect3game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PlayActivity extends AppCompatActivity {

    // 0:yellow 1:red 2:empty
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int activePlayer = 0;
    boolean gameActive = true;
    public void dropIn(View view){
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter] == 2 && gameActive) {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1500);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);
            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    String winner = "";
                    gameActive = false;
                    String playerOne = getIntent().getStringExtra("playerOne");
                    String playerTwo = getIntent().getStringExtra("playerTwo");
                    if (activePlayer == 1) {
                        winner = playerOne;
                    } else {
                        winner = playerTwo;
                    }
                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
                    Button playerNameChange = (Button) findViewById(R.id.playerNameChange);
                    winnerTextView.setText(winner + " Has Won..!");
                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);
                    playerNameChange.setVisibility(View.VISIBLE);
                }
            }
        }
    }
    public void playAgain(View view){
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
        Button playerNameChange = (Button) findViewById(R.id.playerNameChange);
        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
        playAgainButton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);
        playerNameChange.setVisibility(View.INVISIBLE);
        androidx.gridlayout.widget.GridLayout gridLayout = (androidx.gridlayout.widget.GridLayout) findViewById(R.id.board);
        for (int i=0; i<gridLayout.getChildCount(); i++){
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        for (int i=0; i<gameState.length; i++) {
            gameState[i] = 2;
        }
        activePlayer = 0;
        gameActive = true;
    }

    public void resetGame(View view){
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
        playAgainButton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);
        androidx.gridlayout.widget.GridLayout gridLayout = (androidx.gridlayout.widget.GridLayout) findViewById(R.id.board);
        for (int i=0; i<gridLayout.getChildCount(); i++){
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        for (int i=0; i<gameState.length; i++) {
            gameState[i] = 2;
        }
        activePlayer = 0;
        gameActive = true;
        Toast toast = Toast.makeText(PlayActivity.this,"Board refreshed ...!", Toast.LENGTH_SHORT);
        toast.getView().setBackgroundColor(Color.parseColor("#B589D6"));
        toast.show();
    }

    public void playerNameChange(View view){
        Intent intent = new Intent(PlayActivity.this, MainActivity.class);
        Toast toast = Toast.makeText(PlayActivity.this,"Now change the player name ...!", Toast.LENGTH_SHORT);
        toast.getView().setBackgroundColor(Color.parseColor("#B589D6"));
        toast.show();
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }
}