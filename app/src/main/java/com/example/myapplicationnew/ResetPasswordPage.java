package com.example.myapplicationnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class ResetPasswordPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password_page);

        ImageButton backButton = (ImageButton)findViewById(R.id.backButton);
        EditText resetPasswordBox = (EditText)findViewById(R.id.resetPasswordBox);
        EditText confirmPasswordBox = (EditText)findViewById(R.id.confirmPasswordBox);
        Button resetButton = (Button)findViewById(R.id.resetPasswordButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(resetPasswordBox.getText().toString().length() < 7 || resetPasswordBox.getText().toString().length() > 15 || !resetPasswordBox.getText().toString().equals(confirmPasswordBox.getText().toString())) {
                    resetPasswordBox.setBackgroundResource(R.drawable.red_border);
                    confirmPasswordBox.setBackgroundResource(R.drawable.red_border);
                }
                else {
                    startActivity(new Intent(ResetPasswordPage.this, PasswordStatusPage.class));
                }
            }
        });

    }
}