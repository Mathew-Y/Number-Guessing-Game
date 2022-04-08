package com.example.myapplicationnew;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class WinPage extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win_page);

        TextView scoreOne = (TextView)findViewById(R.id.leaderboardNumberOne);
        TextView scoreTwo = (TextView)findViewById(R.id.leaderboardNumberTwo);
        TextView scoreThree = (TextView)findViewById(R.id.leaderboardNumberThree);
        TextView scoreFour = (TextView)findViewById(R.id.leaderboardNumberFour);
        TextView scoreFive = (TextView)findViewById(R.id.leaderboardNumberFive);

        ArrayList<TextView> texts = new ArrayList<>();
        texts.add(scoreOne);
        texts.add(scoreTwo);
        texts.add(scoreThree);
        texts.add(scoreFour);
        texts.add(scoreFive);

        TextView usernameText = (TextView)findViewById(R.id.usernameText);
        TextView logoutText = (TextView)findViewById(R.id.logoutText);
        TextView difficultyText = (TextView)findViewById(R.id.difficultyText);
        TextView numOfGuesses = (TextView)findViewById(R.id.numOfGuesses);
        TextView leaderboardText = (TextView)findViewById(R.id.leaderboardText);
        Button playAgainButton = (Button)findViewById(R.id.playAgainButton);

        usernameText.setText(SignInPage.currentUser.getUsername());

        if(LevelSelectionPage.score != 1) {
            numOfGuesses.setText("You won in " + LevelSelectionPage.score + " guesses.");
        }
        else {
            numOfGuesses.setText("You won in " + LevelSelectionPage.score + " guess.");
        }

        difficultyText.setText("Difficulty: " + LevelSelectionPage.difficulty);

        if (LevelSelectionPage.difficulty.equals("Easy")) {
            leaderboardText.setText("Your top 5 best easy scores");
            for(int i = 0; i < SignInPage.currentUser.getEasyScores().size(); i++) {
                texts.get(i).setText((i + 1) + ". " + SignInPage.currentUser.getEasyScores().get(i));
            }
        }
        else if(LevelSelectionPage.difficulty.equals("Medium")) {
            leaderboardText.setText("Your top 5 medium scores");
            for(int i = 0; i < SignInPage.currentUser.getMediumScores().size(); i++) {
                texts.get(i).setText((i + 1) + ". " + SignInPage.currentUser.getMediumScores().get(i));
            }
        }
        else if(LevelSelectionPage.difficulty.equals("Hard")) {
            leaderboardText.setText("Your top 5 hard scores");
            for(int i = 0; i < SignInPage.currentUser.getHardScores().size(); i++) {
                texts.get(i).setText((i + 1) + ". " + SignInPage.currentUser.getHardScores().get(i));
            }
        }

        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WinPage.this, LevelSelectionPage.class));
            }
        });

        logoutText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WinPage.this, SignInPage.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, LevelSelectionPage.class));
        finish();
    }
}