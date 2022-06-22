package com.example.numberguessinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.numberguessinggame.R;
import com.example.numberguessinggame.authUtils.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

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
        Button resetScoresButton = (Button)findViewById(R.id.resetScoresButton);

        TextView logoutText = (TextView)findViewById(R.id.logoutText);
        Button howToPlayButton = (Button)findViewById(R.id.howToPlayButton);

        resetScoresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignInPage.currentUser.setEasyScores(new ArrayList<>());
                SignInPage.currentUser.setMediumScores(new ArrayList<>());
                SignInPage.currentUser.setHardScores(new ArrayList<>());

                for(int i = 0; i < CreateAccountPage.userList.size(); i++) {
                    if(CreateAccountPage.userList.get(i).getUsername().equalsIgnoreCase(SignInPage.currentUser.getUsername())) {
                        CreateAccountPage.userList.get(i).setEasyScores(new ArrayList<>());
                        CreateAccountPage.userList.get(i).setMediumScores(new ArrayList<>());
                        CreateAccountPage.userList.get(i).setHardScores(new ArrayList<>());
                    }
                }

                saveData();
                startActivity(new Intent(LevelSelectionPage.this, ScoresResetPage.class));
            }
        });

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

    public void saveData() {
        SharedPreferences mPrefs = getSharedPreferences("shared_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(CreateAccountPage.userList);
        editor.putString("User list", json);
        editor.apply();
    }

    public void loadData() {
        SharedPreferences mPrefs = getSharedPreferences("shared_prefs", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("User list", null);
        Type type = new TypeToken<ArrayList<User>>(){}.getType();
        CreateAccountPage.userList = gson.fromJson(json, type);

        if(CreateAccountPage.userList == null) {
            CreateAccountPage.userList = new ArrayList<>();
        }
    }

    @Override
    public void onBackPressed() {

    }
}