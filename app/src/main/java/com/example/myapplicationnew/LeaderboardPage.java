package com.example.myapplicationnew;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class LeaderboardPage extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard_page);

        ImageButton backButton = (ImageButton)findViewById(R.id.backButton);

        TextView easyLeaderboardOne = (TextView)findViewById(R.id.easyLeaderboardNumberOne);
        TextView easyLeaderboardTwo = (TextView)findViewById(R.id.easyLeaderboardNumberTwo);
        TextView easyLeaderboardThree = (TextView)findViewById(R.id.easyLeaderboardNumberThree);
        TextView easyLeaderboardFour = (TextView)findViewById(R.id.easyLeaderboardNumberFour);
        TextView easyLeaderboardFive = (TextView)findViewById(R.id.easyLeaderboardNumberFive);
        TextView mediumLeaderboardOne = (TextView)findViewById(R.id.mediumLeaderboardNumberOne);
        TextView mediumLeaderboardTwo = (TextView)findViewById(R.id.mediumLeaderboardNumberTwo);
        TextView mediumLeaderboardThree = (TextView)findViewById(R.id.mediumLeaderboardNumberThree);
        TextView mediumLeaderboardFour = (TextView)findViewById(R.id.mediumLeaderboardNumberFour);
        TextView mediumLeaderboardFive = (TextView)findViewById(R.id.mediumLeaderboardNumberFive);
        TextView hardLeaderboardOne = (TextView)findViewById(R.id.hardLeaderboardNumberOne);
        TextView hardLeaderboardTwo = (TextView)findViewById(R.id.hardLeaderboardNumberTwo);
        TextView hardLeaderboardThree = (TextView)findViewById(R.id.hardLeaderboardNumberThree);
        TextView hardLeaderboardFour = (TextView)findViewById(R.id.hardLeaderboardNumberFour);
        TextView hardLeaderboardFive = (TextView)findViewById(R.id.hardLeaderboardNumberFive);

        ArrayList<TextView> easyTexts = new ArrayList<>();
        ArrayList<TextView> mediumTexts = new ArrayList<>();
        ArrayList<TextView> hardTexts = new ArrayList<>();

        easyTexts.add(easyLeaderboardOne);
        easyTexts.add(easyLeaderboardTwo);
        easyTexts.add(easyLeaderboardThree);
        easyTexts.add(easyLeaderboardFour);
        easyTexts.add(easyLeaderboardFive);

        mediumTexts.add(mediumLeaderboardOne);
        mediumTexts.add(mediumLeaderboardTwo);
        mediumTexts.add(mediumLeaderboardThree);
        mediumTexts.add(mediumLeaderboardFour);
        mediumTexts.add(mediumLeaderboardFive);

        hardTexts.add(hardLeaderboardOne);
        hardTexts.add(hardLeaderboardTwo);
        hardTexts.add(hardLeaderboardThree);
        hardTexts.add(hardLeaderboardFour);
        hardTexts.add(hardLeaderboardFive);

        for(int i = 0; i < SignInPage.currentUser.getEasyScores().size(); i++) {
            easyTexts.get(i).setText((i + 1) + ". " + SignInPage.currentUser.getEasyScores().get(i));
        }

        for(int i = 0; i < SignInPage.currentUser.getMediumScores().size(); i++) {
            mediumTexts.get(i).setText((i + 1) + ". " + SignInPage.currentUser.getMediumScores().get(i));
        }

        for(int i = 0; i < SignInPage.currentUser.getHardScores().size(); i++) {
            hardTexts.get(i).setText((i + 1) + ". " + SignInPage.currentUser.getHardScores().get(i));
        }

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}