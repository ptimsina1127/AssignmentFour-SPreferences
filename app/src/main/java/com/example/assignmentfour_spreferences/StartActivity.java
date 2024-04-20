package com.example.assignmentfour_spreferences;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignmentfour_spreferences.data.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class StartActivity extends AppCompatActivity {
    private Button signUpButton,loginButton,aboutButton,dashboardButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Log.e("LifeofAndroidAPP","onCreate");
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();

        //Testing of data in internal storage
        checkDataInInternalStorage();

        initializeViews();

        signUpButton.setOnClickListener(view ->{
            //Transition from StartActivity --> To  SignupActivity
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            //finish(); // This closes the first activity. It is occasionally used.
        });
        loginButton.setOnClickListener(view ->{
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);

        });
        aboutButton.setOnClickListener(view ->{

            User userPravat = new User("Pravat Kiran Timsina","Pravatktimsina@gmail.com");

            String activityTitle = "Welcome to About Activity";
            String activityAbout = "This is about Activity";
            Intent intent = new Intent(this, AboutActivity.class);
            intent.putExtra("activityTitle",activityTitle);
            intent.putExtra("activityAbout",activityAbout);
            intent.putExtra("code",200);
            intent.putExtra("user",userPravat);
            startActivity(intent);
        });
        dashboardButton.setOnClickListener(view ->{
            Intent intent = new Intent(this, DashboardActivity.class);
            startActivity(intent);
        });
    }

    private void initializeViews() {
        signUpButton = findViewById(R.id.signup_button);
        loginButton = findViewById(R.id.login_button);
        dashboardButton = findViewById(R.id.dashboard_button);
        aboutButton = findViewById(R.id.about_button);
    }

    @Override
    protected void onStart() {
        Log.e("Life","onStart");
        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.e("LifeofAndroidAPP","onResume");
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.e("LifeofAndroidAPP","onPause");
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
        super.onPause();
    }
    @Override
    protected void onStop() {
        Log.e("LifeofAndroidAPP", "onStop");
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
        super.onStop();
    }
    @Override
    protected void onDestroy() {
        Log.e("LifeofAndroidAPP","onDestroy");
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    private void checkDataInInternalStorage() {
        try {
            FileInputStream fis = openFileInput("users_data.json");
            InputStreamReader inputStreamReader = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            fis.close();
            String usersDataString = stringBuilder.toString();
            Log.d("UsersData", usersDataString);
            // Now you have the users data in the usersDataString variable
            // You can parse it into JSON and extract the values if needed
            JSONArray usersArray = new JSONArray(usersDataString);
            for (int i = 0; i < usersArray.length(); i++) {
                JSONObject userData = usersArray.getJSONObject(i);
                Log.d("UserData", userData.toString());
                // Extract user data here and do whatever you need
            }
        } catch (FileNotFoundException e) {
            Log.e("FileNotFound", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("IOException", "Error reading file: " + e.toString());
        } catch (JSONException e) {
            Log.e("JSONException", "Error parsing JSON: " + e.toString());
        }
    }
}