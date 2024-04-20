package com.example.assignmentfour_spreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        // Get the username from intent extras
        String username = getIntent().getStringExtra("USERNAME");

        // Find the TextView
        TextView dashboardText = findViewById(R.id.dashboardText);

        // Set dynamic text
        if (username != null && !username.isEmpty()) {
            String welcomeMessage = "Welcome " + username + "! Now you are logged in.\nThank you for using my app.";
            dashboardText.setText(welcomeMessage);
        }
    }
}