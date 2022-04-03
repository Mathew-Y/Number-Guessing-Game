package com.example.myapplicationnew;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
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
        TextView showOrHidePassword = (TextView)findViewById(R.id.showOrHidePassword);
        TextView createAccount = (TextView)findViewById(R.id.createAccountText);
        TextView forgotPassword = (TextView)findViewById(R.id.forgotPasswordText);
        EditText usernameBox = (EditText)findViewById(R.id.userNameBox);
        EditText passwordBox = (EditText)findViewById(R.id.passwordBox);

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInPage.this, LevelSelectionPage.class));
                username = usernameBox.getText().toString();
                usernameBox.setText("");
                passwordBox.setText("");
            }
        });

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInPage.this, CreateAccountPage.class));
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInPage.this, ForgotPasswordPage.class));
            }
        });

        showOrHidePassword.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(showOrHidePassword.getText().toString().equals("SHOW")) {
                    passwordBox.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    showOrHidePassword.setText("HIDE");
                }
                else {
                    passwordBox.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    showOrHidePassword.setText("SHOW");
                }
            }
        });
    }
}