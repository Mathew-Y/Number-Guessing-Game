package com.example.numberguessinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.numberguessinggame.R;
import com.example.numberguessinggame.authUtils.User;

public class SecurityQuestionPage extends AppCompatActivity {
    public static User forgottenUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_question_page);

        forgottenUser = ForgotPasswordPage.foundUser;

        ImageButton backButton = (ImageButton)findViewById(R.id.backButton);
        Button continueButton = (Button)findViewById(R.id.continueButton);
        EditText questionOne = (EditText)findViewById(R.id.securityQuestion1Box);
        EditText questionTwo = (EditText)findViewById(R.id.securityQuestion2Box);
        EditText questionThree = (EditText)findViewById(R.id.securityQuestion3Box);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String questionOneAttempt = CreateAccountPage.encryptTextBox(questionOne.getText().toString().getBytes());
                String questionTwoAttempt = CreateAccountPage.encryptTextBox(questionTwo.getText().toString().getBytes());
                String questionThreeAttempt = CreateAccountPage.encryptTextBox(questionThree.getText().toString().getBytes());
                questionOne.setBackgroundResource(R.drawable.black_border);
                questionTwo.setBackgroundResource(R.drawable.black_border);
                questionThree.setBackgroundResource(R.drawable.black_border);

                if(!questionOneAttempt.equals(forgottenUser.getSecurityResponseOne())) {
                    questionOne.setBackgroundResource(R.drawable.red_border);
                    questionOne.setHintTextColor(Color.RED);
                    questionOne.setHint("Incorrect response");
                    questionOne.setText("");
                }
                else if(!questionTwoAttempt.equals(forgottenUser.getSecurityResponseTwo())) {
                    questionTwo.setBackgroundResource(R.drawable.red_border);
                    questionTwo.setHintTextColor(Color.RED);
                    questionTwo.setHint("Incorrect response");
                    questionTwo.setText("");
                }
                else if(!questionThreeAttempt.equals(forgottenUser.getSecurityResponseThree())) {
                    questionThree.setBackgroundResource(R.drawable.red_border);
                    questionThree.setHintTextColor(Color.RED);
                    questionThree.setHint("Incorrect response");
                    questionThree.setText("");
                }
                else {
                    questionOne.setBackgroundResource(R.drawable.black_border);
                    questionTwo.setBackgroundResource(R.drawable.black_border);
                    questionThree.setBackgroundResource(R.drawable.black_border);
                    questionOne.setHintTextColor(Color.GRAY);
                    questionTwo.setHintTextColor(Color.GRAY);
                    questionThree.setHintTextColor(Color.GRAY);
                    questionOne.setHint("Answer");
                    questionTwo.setHint("Answer");
                    questionThree.setHint("Answer");
                    questionOne.setText("");
                    questionTwo.setText("");
                    questionThree.setText("");
                    startActivity(new Intent(SecurityQuestionPage.this, ResetPasswordPage.class));
                }
            }
        });
    }
}