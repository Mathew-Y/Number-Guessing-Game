package com.example.myapplicationnew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateAccountPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_page);

        Button createAccountButton = (Button)findViewById(R.id.createAccountButton);
        EditText createUsernameBox = (EditText)findViewById(R.id.createUsernameBox);
        EditText createPasswordBox = (EditText)findViewById(R.id.createPasswordBox);
        EditText confirmPasswordBox = (EditText)findViewById(R.id.confirmPasswordBox);
        EditText secQuestionOneBox = (EditText)findViewById(R.id.securityQuestion1Box);
        EditText secQuestionTwoBox = (EditText)findViewById(R.id.securityQuestion2Box);
        EditText secQuestionThreeBox = (EditText)findViewById(R.id.securityQuestion3Box);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                createUsernameBox.setBackgroundResource(R.drawable.black_border);
                createPasswordBox.setBackgroundResource(R.drawable.black_border);
                confirmPasswordBox.setBackgroundResource(R.drawable.black_border);

                if(createUsernameBox.getText().toString().length() < 3 || createUsernameBox.getText().toString().length() > 10) {
                    createUsernameBox.setText("");
                    createUsernameBox.setBackgroundResource(R.drawable.red_border);
                }
                else if(createPasswordBox.getText().toString().length() < 7 || createPasswordBox.getText().toString().length() > 15 || !createPasswordBox.getText().toString().equals(confirmPasswordBox.getText().toString())) {
                    createPasswordBox.setText("");
                    confirmPasswordBox.setText("");
                    createPasswordBox.setBackgroundResource(R.drawable.red_border);
                    confirmPasswordBox.setBackgroundResource(R.drawable.red_border);
                }
                else if(secQuestionOneBox.getText().toString().isEmpty()) {
                    secQuestionOneBox.setBackgroundResource(R.drawable.red_border);
                }
                else if(secQuestionTwoBox.getText().toString().isEmpty()) {
                    secQuestionTwoBox.setBackgroundResource(R.drawable.red_border);
                }
                else if(secQuestionThreeBox.getText().toString().isEmpty()) {
                    secQuestionThreeBox.setBackgroundResource(R.drawable.red_border);
                }
                else{
                    startActivity(new Intent(CreateAccountPage.this, LevelSelectionPage.class));

                }
            }
        });
    }
}