package com.prahladinala.connect3game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView yellowImage = (ImageView) findViewById(R.id.firstPlayerImage);
        ImageView redImage = (ImageView) findViewById(R.id.secondPlayerImage);
        yellowImage.animate().rotation(18000).setDuration(60000);
        redImage.animate().rotation(18000).setDuration(60000);


    }
    public void moveToGame(View view) {
        EditText playerOneName = (EditText) findViewById(R.id.playerOneName);
        EditText playerTwoName = (EditText) findViewById(R.id.playerTwoName);
        String playerOne = playerOneName.getText().toString();
        String playerTwo = playerTwoName.getText().toString();
        if(playerOne.length() == 0 && playerTwo.length() == 0){
            playerOneName.setText("Odie");
            playerTwoName.setText("Garfield");
        } else {
            if (playerTwo.length() == 0) {
                playerTwoName.setError("Enter the Player two Name");
            } else {
                Intent intent = new Intent(MainActivity.this, PlayActivity.class);
                intent.putExtra("playerOne", playerOne);
                intent.putExtra("playerTwo", playerTwo);
                startActivity(intent);
            }
        }
    }
}