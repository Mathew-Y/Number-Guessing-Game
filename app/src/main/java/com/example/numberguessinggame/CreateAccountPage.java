package com.example.numberguessinggame;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
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

public class CreateAccountPage extends AppCompatActivity {
    public static ArrayList<User> userList;

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
        ImageButton backButton = (ImageButton)findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                createUsernameBox.setBackgroundResource(R.drawable.black_border);
                createUsernameBox.setHintTextColor(Color.GRAY);
                createUsernameBox.setHint("Username");
                createPasswordBox.setBackgroundResource(R.drawable.black_border);
                confirmPasswordBox.setBackgroundResource(R.drawable.black_border);
                createPasswordBox.setHint("Password");
                confirmPasswordBox.setHint("Confirm password");
                createPasswordBox.setHintTextColor(Color.GRAY);
                confirmPasswordBox.setHintTextColor(Color.GRAY);
                secQuestionOneBox.setBackgroundResource(R.drawable.black_border);
                secQuestionOneBox.setHintTextColor(Color.GRAY);
                secQuestionOneBox.setHint("Answer");
                secQuestionTwoBox.setBackgroundResource(R.drawable.black_border);
                secQuestionTwoBox.setHintTextColor(Color.GRAY);
                secQuestionTwoBox.setHint("Answer");
                secQuestionThreeBox.setBackgroundResource(R.drawable.black_border);
                secQuestionThreeBox.setHintTextColor(Color.GRAY);
                secQuestionThreeBox.setHint("Answer");

                if(createUsernameBox.getText().toString().length() < 3 || createUsernameBox.getText().toString().length() > 10) {
                    createUsernameBox.setText("");
                    createUsernameBox.setBackgroundResource(R.drawable.red_border);
                    createUsernameBox.setHintTextColor(Color.RED);
                    createUsernameBox.setHint("Username must be 3-10 characters long");
                }
                else if(createPasswordBox.getText().toString().length() < 7 || createPasswordBox.getText().toString().length() > 15) {
                    createPasswordBox.setText("");
                    confirmPasswordBox.setText("");
                    createPasswordBox.setHintTextColor(Color.RED);
                    confirmPasswordBox.setHintTextColor(Color.RED);
                    createPasswordBox.setHint("Password must be 7-15 characters long");
                    confirmPasswordBox.setHint("Password must be 7-15 characters long");
                    createPasswordBox.setBackgroundResource(R.drawable.red_border);
                    confirmPasswordBox.setBackgroundResource(R.drawable.red_border);
                }
                else if(!createPasswordBox.getText().toString().equals(confirmPasswordBox.getText().toString())) {
                    createPasswordBox.setText("");
                    confirmPasswordBox.setText("");
                    createPasswordBox.setHintTextColor(Color.RED);
                    confirmPasswordBox.setHintTextColor(Color.RED);
                    createPasswordBox.setHint("Passwords do not match");
                    confirmPasswordBox.setHint("Passwords do not match");
                    createPasswordBox.setBackgroundResource(R.drawable.red_border);
                    confirmPasswordBox.setBackgroundResource(R.drawable.red_border);
                }
                else if(secQuestionOneBox.getText().toString().isEmpty()) {
                    secQuestionOneBox.setBackgroundResource(R.drawable.red_border);
                    secQuestionOneBox.setHintTextColor(Color.RED);
                    secQuestionOneBox.setHint("Required");
                }
                else if(secQuestionTwoBox.getText().toString().isEmpty()) {
                    secQuestionTwoBox.setBackgroundResource(R.drawable.red_border);
                    secQuestionTwoBox.setHintTextColor(Color.RED);
                    secQuestionTwoBox.setHint("Required");
                }
                else if(secQuestionThreeBox.getText().toString().isEmpty()) {
                    secQuestionThreeBox.setBackgroundResource(R.drawable.red_border);
                    secQuestionThreeBox.setHintTextColor(Color.RED);
                    secQuestionThreeBox.setHint("Required");
                }
                else if(userList.stream().anyMatch(o -> createUsernameBox.getText().toString().equalsIgnoreCase(o.getUsername()))) {
                    createUsernameBox.setBackgroundResource(R.drawable.red_border);
                    createUsernameBox.setText("");
                    createUsernameBox.setHintTextColor(Color.RED);
                    createUsernameBox.setHint("Username already exists");
                }
                else {

                    String md5Pass = encryptTextBox(createPasswordBox.getText().toString().getBytes());
                    String md5Sec1 = encryptTextBox(secQuestionOneBox.getText().toString().getBytes());
                    String md5Sec2 = encryptTextBox(secQuestionTwoBox.getText().toString().getBytes());
                    String md5Sec3 = encryptTextBox(secQuestionThreeBox.getText().toString().getBytes());

                    User newUser = new User(createUsernameBox.getText().toString(), md5Pass, md5Sec1, md5Sec2, md5Sec3);

                    saveData(newUser);

                    startActivity(new Intent(CreateAccountPage.this, AccountCreatedPage.class));
                }
            }
        });
    }

    public void saveData(User user) {
        loadData();
        SharedPreferences mPrefs = getSharedPreferences("shared_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = mPrefs.edit();
        Gson gson = new Gson();
        userList.add(user);
        String json = gson.toJson(userList);
        editor.putString("User list", json);
        editor.apply();
    }

    public void loadData() {
        SharedPreferences mPrefs = getSharedPreferences("shared_prefs", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("User list", null);
        Type type = new TypeToken<ArrayList<User>>(){}.getType();
        userList = gson.fromJson(json, type);

        if(userList == null) {
            userList = new ArrayList<>();
        }
    }

    public static User findUser(String username) {
        for(int i = 0; i < userList.size(); i++) {
            if(userList.get(i).getUsername().equalsIgnoreCase(username)) {
                return userList.get(i);
            }
        }
        return null;
    }

    public static String encryptTextBox(byte[] bytes) {
        BigInteger md5Data = null;
        String encryptedText;

        try {
            md5Data = new BigInteger(1, PasswordEncryption.encryptMD5(bytes));
        } catch (Exception e) {
            e.printStackTrace();
        }

        encryptedText = md5Data.toString(16);

        return encryptedText;
    }
}