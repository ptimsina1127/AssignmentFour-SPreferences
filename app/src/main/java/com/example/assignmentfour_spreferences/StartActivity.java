package com.example.assignmentfour_spreferences;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignmentfour_spreferences.data.User;

public class StartActivity extends AppCompatActivity {
    private Button signUpButton,loginButton,aboutButton,dashboardButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Log.e("LifeofAndroidAPP","onCreate");
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();

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
        Log.e("LifeofAndroidAPP","onStop");
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
        super.onStop();
    }
    @Override
    protected void onDestroy() {
        Log.e("LifeofAndroidAPP","onDestroy");
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

}