package com.example.assignmentfour_spreferences;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignmentfour_spreferences.adapters.Country;
import com.example.assignmentfour_spreferences.adapters.CountryAdapter;

import java.util.Arrays;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private Button button, clearbutton;
    private EditText nameEditText, emailEditText, passwordEditText, aboutEditText, datePickerButton;
    private RadioGroup genderGroup;
    private RadioButton femaleRadioButton, maleRadioButton, otherRadioButton;

    private CheckBox javaCheck, pythonCheck, androidCheck;
    private Spinner spinner;

    private Country listItem[]={
            new Country(1,"Nepal", "This is the First Country in the list",R.drawable.nepal),
            new Country(2,"India", "This is the Second Country in the list",R.drawable.india),
            new Country(3,"United States", "This is the Third Country in the list",R.drawable.usa),
            new Country(4,"Mexico", "This is the Fourth Country in the list",R.drawable.mexico),
            new Country(5,"Canada", "This is the Fifth Country in the list",R.drawable.canada),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //button is referenced to UI's signup button
        this.initializeviews();
        // Attaching listener to date picker button
        datePickerButton.setOnClickListener(view -> {
            // This code is executed once the button is clicked
            // Toast.makeText(this,"Selecting Date",Toast.LENGTH_SHORT).show();
            // Show the Date picker

            // We Picked Year month and day and saved in this variables
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    this,
                    (view1, selectedYear, selectedMonth, selectedDay) -> {
                        //This will be called once we pick the date
                        Toast.makeText(this,"Year: "+ selectedYear + " month: "+ (selectedMonth+1) + " day: "+ selectedDay,Toast.LENGTH_SHORT).show();
                        datePickerButton.setText(selectedDay+"/"+(selectedMonth+1)+"/"+selectedYear);
                    },year,month,day
            );
            datePickerDialog.show();
        });
        //Create an adapter to populate the spinner
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, countryNames);
        CountryAdapter countryAdapter = new CountryAdapter(Arrays.asList(listItem), this);
        spinner.setAdapter(countryAdapter);
        spinner.setSelection(0);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,listItem[position].getName()+" Selected",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//        //Creating listener
//        View.OnClickListener viewListener = (view) -> {
//            //As soon as the button is clicked this code is executed
//
//            Toast.makeText(this,"button clicked",Toast.LENGTH_SHORT).show();
//        };

        // Attach listener to Button
        button.setOnClickListener(view -> {
//            As soon as the button is clicked this code is executed

//            Toast.makeText(this,"Button is clicked",Toast.LENGTH_SHORT).show();
            String name = nameEditText.getText().toString();
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            String about = aboutEditText.getText().toString();
            String dob = datePickerButton.getText().toString();
            Country country = (Country)spinner.getSelectedItem();

            if (name.trim().equals("")) {
                Toast.makeText(this, "Name is Required !!", Toast.LENGTH_SHORT).show();
                nameEditText.setError("Name is required !!");
                return;
            }
            if (password.trim().equals("")) {
                Toast.makeText(this, "Password is Required !!", Toast.LENGTH_SHORT).show();
                passwordEditText.setError("Password is required !!");
                return;
            }
            String gender = "";
            if (maleRadioButton.isChecked()) {
                gender = "Male";
            }
            if (femaleRadioButton.isChecked()) {
                gender = "Female";
            }
            if (otherRadioButton.isChecked()) {
                gender = "Other";
            }
            if (gender.equals("")) {
                Toast.makeText(this, "Select Gender", Toast.LENGTH_SHORT).show();
                maleRadioButton.setError("Select Gender !!");
                femaleRadioButton.setError("Select Gender !!");
                return;
            }
            String courses = "";
            if (javaCheck.isChecked()) {
                courses += "Java";
            }
            if (pythonCheck.isChecked()) {
                courses += ", Python";
            }
            if (androidCheck.isChecked()) {
                courses += ", Android";
            }
            Log.w("Name", name);
            Log.w("Gender", gender);
            Log.w("Courses", courses);
            Log.w("Country", country.getName());

            Toast.makeText(this, "Got the Data", Toast.LENGTH_SHORT).show();

            //Validate the data

            // Call api to send data...
            // Save data to the database
            //
        });


    }

    public void initializeviews() {
        //button is referenced to UI's signup button
        button = findViewById(R.id.submit_button);
        nameEditText = findViewById(R.id.name_edit_text);
        emailEditText = findViewById(R.id.email_edit_text);
        passwordEditText = findViewById(R.id.password_edit_text);
        aboutEditText = findViewById(R.id.about_edit_text);
        genderGroup = findViewById(R.id.gender_radio_group);
        femaleRadioButton = findViewById(R.id.gender_female);
        maleRadioButton = findViewById(R.id.gender_male);
        otherRadioButton = findViewById(R.id.gender_other);
        javaCheck = findViewById(R.id.java_course);
        pythonCheck = findViewById(R.id.python_course);
        androidCheck = findViewById(R.id.android_course);
        clearbutton = findViewById(R.id.clear_button);
        datePickerButton = findViewById(R.id.pick_date_button);
        spinner = findViewById(R.id.country_spinner);
    }
}