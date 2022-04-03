package com.example.myapplicationnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignInPage extends AppCompatActivity {
    public String username;
    public String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginButton = (Button) findViewById(R.id.loginButton);
        TextView createAccount = (TextView)findViewById(R.id.createAccountText);
        EditText usernameBox = (EditText)findViewById(R.id.userNameBox);
        EditText passwordBox = (EditText)findViewById(R.id.passwordBox);

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInPage.this, LevelSelectionPage.class));
                username = usernameBox.getText().toString();
                password = passwordBox.getText().toString();
            }
        });

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInPage.this, CreateAccountPage.class));
            }
        });
    }
}