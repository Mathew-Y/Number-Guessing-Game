package com.example.myapplicationnew;

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

import com.example.myapplicationnew.authUtils.User;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MediumPage extends AppCompatActivity {
    private int numOfGuesses;
    private int randomNumber;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medium_page);

        Random random = new Random();
        numOfGuesses = 0;

        randomNumber = random.nextInt(100) + 1;
        TextView usernameText = (TextView)findViewById(R.id.usernameText);
        TextView numGuessesLeft = (TextView)findViewById(R.id.numberOfAttemptsText);
        TextView advice = (TextView)findViewById(R.id.adviceText);
        EditText guessBox = (EditText)findViewById(R.id.guessBox);
        Button enterButton = (Button)findViewById(R.id.enterButton);
        Button mainMenuButton = (Button)findViewById(R.id.mainMenuButton);

        usernameText.setText(SignInPage.currentUser.getUsername());

        numGuessesLeft.setText("# of attempts: " + numOfGuesses);

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guessBox.setHint("Guess");
                guessBox.setHintTextColor(Color.GRAY);
                guessBox.setBackgroundResource(R.drawable.black_border);

                if(Integer.parseInt(guessBox.getText().toString()) < 1 || Integer.parseInt(guessBox.getText().toString()) > 100) {
                    guessBox.setBackgroundResource(R.drawable.red_border);
                    guessBox.setHintTextColor(Color.RED);
                    guessBox.setHint("Number must be between 1-100");
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
                        updateMediumScores(SignInPage.currentUser);
                        for(int i = 0; i < CreateAccountPage.userList.size(); i++) {
                            if(CreateAccountPage.userList.get(i).getUsername().equalsIgnoreCase(SignInPage.currentUser.getUsername())) {
                                CreateAccountPage.userList.get(i).setMediumScores(SignInPage.currentUser.getMediumScores());
                            }
                        }
                        LevelSelectionPage.score = numOfGuesses;
                        numOfGuesses = 0;
                        randomNumber = random.nextInt(100) + 1;
                        numGuessesLeft.setText("# of attempts: " + numOfGuesses);
                        guessBox.setText("");
                        saveData();
                        startActivity(new Intent(MediumPage.this, WinPage.class));
                    }
                }
            }
        });

        mainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MediumPage.this, LevelSelectionPage.class));
            }
        });
    }

    public void updateMediumScores(User user) {
        ArrayList<Integer> mediumScores = user.getMediumScores();

        if(mediumScores.size() < 5) {
            mediumScores.add(numOfGuesses);
        }
        else {
            if(Collections.max(mediumScores) < numOfGuesses) {
                return;
            }
            else {
                mediumScores.remove(Collections.max(mediumScores));
                mediumScores.add(numOfGuesses);
            }
        }
        Collections.sort(mediumScores);
        user.setMediumScores(mediumScores);
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