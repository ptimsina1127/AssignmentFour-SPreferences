package com.example.assignmentfour_spreferences;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignmentfour_spreferences.data.User;

public class AboutActivity extends AppCompatActivity {

    private TextView title,about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        title = findViewById(R.id.titleView);
        about = findViewById(R.id.aboutView);

        //Getting data from start activity
        Intent intent = getIntent();
        String title = intent.getStringExtra("activityTitle");
        String about = intent.getStringExtra("activityAbout");
        int code = intent.getIntExtra("code",0);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            User user = intent.getSerializableExtra("user", User.class);
            Log.e("username",user.getName());
        } else {
            Bundle extras = intent.getExtras();
            User user = (User) extras.getSerializable("user");
            Log.e("username",user.getName());
        }
        Log.w("title",title);
        Log.w("about",about);

        this.title.setText(title);
        this.about.setText(about);

        Toast.makeText(this,"Code is "+ code,Toast.LENGTH_SHORT).show();
    }
}