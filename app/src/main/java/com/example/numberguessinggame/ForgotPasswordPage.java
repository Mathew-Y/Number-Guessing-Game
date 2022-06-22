package com.example.numberguessinggame;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.numberguessinggame.R;
import com.example.numberguessinggame.authUtils.User;

public class ForgotPasswordPage extends AppCompatActivity {

    public static User foundUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_page);

        ImageButton backButton = (ImageButton)findViewById(R.id.backButton);
        Button continueButton = (Button)findViewById(R.id.continueButton);
        EditText usernameBox = (EditText)findViewById(R.id.enterUsernameBox);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        continueButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                boolean userFound = false;

                if(usernameBox.getText().toString().equals("")) {
                    usernameBox.setBackgroundResource(R.drawable.red_border);
                    usernameBox.setText("");
                    usernameBox.setHintTextColor(Color.RED);
                    usernameBox.setHint("Required");
                }
                else {
                    for(int i = 0; i < CreateAccountPage.userList.size(); i++) {
                        if(CreateAccountPage.userList.get(i).getUsername().equalsIgnoreCase(usernameBox.getText().toString())) {
                            foundUser = CreateAccountPage.userList.get(i);
                            userFound = true;
                            usernameBox.setText("");
                            usernameBox.setHintTextColor(Color.GRAY);
                            usernameBox.setBackgroundResource(R.drawable.black_border);
                            usernameBox.setHint("Username");
                            startActivity(new Intent(ForgotPasswordPage.this, SecurityQuestionPage.class));
                        }
                    }

                    if(!userFound) {
                        usernameBox.setBackgroundResource(R.drawable.red_border);
                        usernameBox.setText("");
                        usernameBox.setHintTextColor(Color.RED);
                        usernameBox.setHint("Username does not exist");
                    }
                }


            }
        });
    }
}