package com.example.myapplicationnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class LevelSelectionPage extends AppCompatActivity {
    public static String difficulty = "";
    public static int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_selection_page);
        score = 0;

        TextView usernameText = (TextView)findViewById(R.id.usernameText);
        usernameText.setText(SignInPage.currentUser.getUsername());
        Button easyButton = (Button)findViewById(R.id.easyButton);
        Button mediumButton = (Button)findViewById(R.id.mediumButton);
        Button hardButton = (Button)findViewById(R.id.hardButton);
        Button leaderboardButton = (Button)findViewById(R.id.viewLeaderboardButton);

        TextView logoutText = (TextView)findViewById(R.id.logoutText);
        Button howToPlayButton = (Button)findViewById(R.id.howToPlayButton);

        logoutText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LevelSelectionPage.this, SignInPage.class));
            }
        });

        howToPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LevelSelectionPage.this, InstructionsPage.class));
            }
        });

        easyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                difficulty = "Easy";
                startActivity(new Intent(LevelSelectionPage.this, EasyPage.class));
            }
        });

        mediumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                difficulty = "Medium";
                startActivity(new Intent(LevelSelectionPage.this, MediumPage.class));
            }
        });

        hardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                difficulty = "Hard";
                startActivity(new Intent(LevelSelectionPage.this, HardPage.class));
            }
        });

        leaderboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LevelSelectionPage.this, LeaderboardPage.class));
            }
        });

    }

    @Override
    public void onBackPressed() {

    }
}