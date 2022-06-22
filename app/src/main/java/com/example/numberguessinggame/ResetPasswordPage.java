package com.example.numberguessinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.numberguessinggame.R;
import com.example.numberguessinggame.authUtils.PasswordEncryption;
import com.example.numberguessinggame.authUtils.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.math.BigInteger;
import java.util.ArrayList;

public class ResetPasswordPage extends AppCompatActivity {
    public static User newPasswordUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password_page);

        newPasswordUser = SecurityQuestionPage.forgottenUser;

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
                if(resetPasswordBox.getText().toString().length() < 7 || resetPasswordBox.getText().toString().length() > 15) {
                    resetPasswordBox.setBackgroundResource(R.drawable.red_border);
                    confirmPasswordBox.setBackgroundResource(R.drawable.red_border);
                    resetPasswordBox.setHintTextColor(Color.RED);
                    confirmPasswordBox.setHintTextColor(Color.RED);
                    resetPasswordBox.setHint("Password must be 3-10 characters");
                    confirmPasswordBox.setHint("Password must be 3-10 characters");
                    resetPasswordBox.setText("");
                    confirmPasswordBox.setText("");
                }
                else if(!resetPasswordBox.getText().toString().equals(confirmPasswordBox.getText().toString())) {
                    resetPasswordBox.setBackgroundResource(R.drawable.red_border);
                    confirmPasswordBox.setBackgroundResource(R.drawable.red_border);
                    resetPasswordBox.setHintTextColor(Color.RED);
                    confirmPasswordBox.setHintTextColor(Color.RED);
                    resetPasswordBox.setHint("Passwords do not match");
                    confirmPasswordBox.setHint("Passwords do not match");
                    resetPasswordBox.setText("");
                    confirmPasswordBox.setText("");
                }
                else {

                    for(int i = 0; i < CreateAccountPage.userList.size(); i++) {
                        if(CreateAccountPage.userList.get(i).getUsername().equalsIgnoreCase(newPasswordUser.getUsername())) {
                            byte[] md5Input = resetPasswordBox.getText().toString().getBytes();

                            BigInteger md5Data = null;
                            String md5Str;

                            try {
                                md5Data = new BigInteger(1, PasswordEncryption.encryptMD5(md5Input));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            md5Str = md5Data.toString(16);

                            CreateAccountPage.userList.get(i).setPasswordHash(md5Str);
                        }
                    }

                    resetPasswordBox.setBackgroundResource(R.drawable.black_border);
                    confirmPasswordBox.setBackgroundResource(R.drawable.black_border);
                    resetPasswordBox.setHintTextColor(Color.GRAY);
                    confirmPasswordBox.setHintTextColor(Color.GRAY);
                    resetPasswordBox.setHint("Password");
                    confirmPasswordBox.setHint("Confirm password");
                    resetPasswordBox.setText("");
                    confirmPasswordBox.setText("");

                    saveData();
                    startActivity(new Intent(ResetPasswordPage.this, PasswordStatusPage.class));
                }
            }
        });

    }

    public void saveData() {
        SharedPreferences mPrefs = getSharedPreferences("shared_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(CreateAccountPage.userList);
        editor.putString("User list", json);
        editor.apply();
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
}