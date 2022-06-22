package com.example.numberguessinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.numberguessinggame.R;
import com.example.numberguessinggame.authUtils.User;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class EasyPage extends AppCompatActivity {
    private int numOfGuesses;
    private int randomNumber;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_page);

        Random random = new Random();
        numOfGuesses = 0;

        randomNumber = random.nextInt(10) + 1;
        TextView usernameText = (TextView)findViewById(R.id.usernameText);
        TextView numGuessesLeft = (TextView)findViewById(R.id.numberOfAttemptsText);
        TextView advice = (TextView)findViewById(R.id.adviceText);
        TextView logoutText = (TextView)findViewById(R.id.logoutText);
        EditText guessBox = (EditText)findViewById(R.id.guessBox);
        Button enterButton = (Button)findViewById(R.id.enterButton);
        Button mainMenuButton = (Button)findViewById(R.id.mainMenuButton);

        usernameText.setText(SignInPage.currentUser.getUsername());

        numGuessesLeft.setText("# of attempts: " + numOfGuesses);

        logoutText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EasyPage.this, SignInPage.class));
            }
        });

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guessBox.setHint("Guess");
                guessBox.setHintTextColor(Color.GRAY);
                guessBox.setBackgroundResource(R.drawable.black_border);

                if(Integer.parseInt(guessBox.getText().toString()) < 1 || Integer.parseInt(guessBox.getText().toString()) > 10) {
                    guessBox.setBackgroundResource(R.drawable.red_border);
                    guessBox.setHintTextColor(Color.RED);
                    guessBox.setHint("Number must be between 1-10");
                    guessBox.setText("");
                }
                else {
                    if(Integer.parseInt(guessBox.getText().toString()) > randomNumber) {
                        advice.setText("Guess lower.");
                        numOfGuesses++;
                        numGuessesLeft.setText("# of attempts: " + numOfGuesses);

                    }
                    else if(Integer.parseInt(guessBox.getText().toString()) < randomNumber) {
                        advice.setText("Guess higher.");
                        numOfGuesses++;
                        numGuessesLeft.setText("# of attempts: " + numOfGuesses);
                    }
                    else {
                        numOfGuesses++;
                        advice.setText("");
                        updateEasyScores(SignInPage.currentUser);
                        for(int i = 0; i < CreateAccountPage.userList.size(); i++) {
                            if(CreateAccountPage.userList.get(i).getUsername().equalsIgnoreCase(SignInPage.currentUser.getUsername())) {
                                CreateAccountPage.userList.get(i).setEasyScores(SignInPage.currentUser.getEasyScores());
                            }
                        }
                        LevelSelectionPage.score = numOfGuesses;
                        numOfGuesses = 0;
                        randomNumber = random.nextInt(10) + 1;
                        numGuessesLeft.setText("# of attempts: " + numOfGuesses);
                        guessBox.setText("");
                        saveData();
                        startActivity(new Intent(EasyPage.this, WinPage.class));
                    }
                }
            }
        });

        mainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EasyPage.this, LevelSelectionPage.class));
            }
        });
    }

    public void updateEasyScores(User user) {
        ArrayList<Integer> easyScores = user.getEasyScores();

        if(easyScores.size() < 5) {
            easyScores.add(numOfGuesses);
        }
        else {
            if(Collections.max(easyScores) < numOfGuesses) {
                return;
            }
            else {
                easyScores.remove(Collections.max(easyScores));
                easyScores.add(numOfGuesses);
            }
        }
        Collections.sort(easyScores);
        user.setEasyScores(easyScores);
    }

    public void saveData() {
        SharedPreferences mPrefs = getSharedPreferences("shared_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(CreateAccountPage.userList);
        editor.putString("User list", json);
        editor.apply();
    }
}