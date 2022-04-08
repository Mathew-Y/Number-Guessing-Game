package com.example.myapplicationnew;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplicationnew.authUtils.PasswordEncryption;
import com.example.myapplicationnew.authUtils.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.logging.Level;

public class SignInPage extends AppCompatActivity {
    public static User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentUser = null;

        loadData();


        Button loginButton = (Button) findViewById(R.id.loginButton);
        TextView showOrHidePassword = (TextView)findViewById(R.id.showOrHidePassword);
        TextView createAccount = (TextView)findViewById(R.id.createAccountText);
        TextView forgotPassword = (TextView)findViewById(R.id.forgotPasswordText);
        EditText usernameBox = (EditText)findViewById(R.id.userNameBox);
        EditText passwordBox = (EditText)findViewById(R.id.passwordBox);

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                User user = CreateAccountPage.findUser(usernameBox.getText().toString());

                if(user == null) {
                    usernameBox.setBackgroundResource(R.drawable.red_border);
                    passwordBox.setBackgroundResource(R.drawable.red_border);
                    usernameBox.setHintTextColor(Color.RED);
                    passwordBox.setHintTextColor(Color.RED);
                    usernameBox.setHint("Invalid login credentials");
                    passwordBox.setHint("Invalid login credentials");
                    usernameBox.setText("");
                    passwordBox.setText("");
                }


                else if(!isCorrectPassword(user, passwordBox)) {
                    usernameBox.setBackgroundResource(R.drawable.red_border);
                    passwordBox.setBackgroundResource(R.drawable.red_border);
                    usernameBox.setHintTextColor(Color.RED);
                    passwordBox.setHintTextColor(Color.RED);
                    usernameBox.setHint("Invalid login credentials");
                    passwordBox.setHint("Invalid login credentials");
                    usernameBox.setText("");
                    passwordBox.setText("");
                }
                else if(isCorrectPassword(user, passwordBox)) {
                    currentUser = user;
                    usernameBox.setHintTextColor(Color.GRAY);
                    passwordBox.setHintTextColor(Color.GRAY);
                    usernameBox.setHint("Username");
                    passwordBox.setHint("Password");
                    usernameBox.setBackgroundResource(R.drawable.black_border);
                    passwordBox.setBackgroundResource(R.drawable.black_border);
                    usernameBox.setText("");
                    passwordBox.setText("");
                    startActivity(new Intent(SignInPage.this, LevelSelectionPage.class));
                }
            }
        });

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usernameBox.setText("");
                passwordBox.setText("");
                startActivity(new Intent(SignInPage.this, CreateAccountPage.class));
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usernameBox.setText("");
                passwordBox.setText("");
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

    public boolean isCorrectPassword(User user, EditText passwordBox) {
        byte[] md5Input = passwordBox.getText().toString().getBytes();

        BigInteger md5Data = null;
        String md5Str;

        try {
            md5Data = new BigInteger(1, PasswordEncryption.encryptMD5(md5Input));
        } catch (Exception e) {
            e.printStackTrace();
        }

        md5Str = md5Data.toString(16);

        return md5Str.equals(user.getPasswordHash());
    }

    public void onBackPressed() {

    }
}