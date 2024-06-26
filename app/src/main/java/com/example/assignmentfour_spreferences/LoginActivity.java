package com.example.assignmentfour_spreferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoginActivity extends AppCompatActivity {

    private Button login, clear;
    private EditText userName, userPassword;
    private CheckBox keepLoggedInCheckbox;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initializeViews();

        sharedPreferences = getSharedPreferences("login_prefs", Context.MODE_PRIVATE);
        boolean keepLoggedIn = sharedPreferences.getBoolean("keep_logged_in", false);
        keepLoggedInCheckbox.setChecked(keepLoggedIn);

        if (keepLoggedIn) {
            // If "Keep You Logged In" is checked, prepopulate email and password fields
            String savedEmail = sharedPreferences.getString("saved_email", "");
            String savedPassword = sharedPreferences.getString("saved_password", "");
            userName.setText(savedEmail);
            userPassword.setText(savedPassword);
        }

        login.setOnClickListener(view -> {
            //Get the entered username and password
            String username = userName.getText().toString();
            String password = userPassword.getText().toString();

            // Save the "Keep You Logged In" state to shared preferences
            boolean keepLoggedInState = keepLoggedInCheckbox.isChecked();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("keep_logged_in", keepLoggedInState);
            // If "Keep You Logged In" is checked, save the email and password
            if (keepLoggedInState) {
                editor.putString("saved_email", username);
                editor.putString("saved_password", password);
            } else {
                // Clear saved email and password if "Keep You Logged In" is unchecked
                editor.remove("saved_email");
                editor.remove("saved_password");
            }
            editor.apply();

            // Perform login authentication here
            if (isValid(username, password)) {
                // If login is successful, show a toast message
                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, DashboardActivity.class);
                intent.putExtra("USERNAME", username); // Username is the variable storing the username
                startActivity(intent);
                finish();

            } else {
                // If login fails, show an error message
                Toast.makeText(LoginActivity.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void initializeViews() {
        login = findViewById(R.id.submit);
        clear = findViewById(R.id.clear);
        userName = findViewById(R.id.email_input);
        userPassword = findViewById(R.id.password_input);
        keepLoggedInCheckbox = findViewById(R.id.checkBox);
    }

    // Method to validate username and password
    private boolean isValid(String username, String password) {
        // Perform your authentication logic here
        try {
            // Read the JSON data from internal storage
            FileInputStream fis = openFileInput("users_data.json");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            fis.close();

            // Parse the JSON data
            JSONArray jsonArray = new JSONArray(stringBuilder.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                // Compare the entered username and password with each user entry
                if (username.equals(jsonObject.getString("email")) && password.equals(jsonObject.getString("password"))) {
                    return true; // Authentication successful
                }
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return false; // Authentication failed
    }
}
