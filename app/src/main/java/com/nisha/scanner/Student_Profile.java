package com.nisha.scanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Student_Profile extends AppCompatActivity {

    EditText tv_student_name,tv_enroll, tv_mobile, tv_college, tv_branch, tv_phase, tv_year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);
        tv_student_name = findViewById(R.id.tv_student_name);
        tv_enroll = findViewById(R.id.tv_enroll);
        tv_mobile = findViewById(R.id.tv_mobile);
        tv_college = findViewById(R.id.tv_college);
        tv_branch = findViewById(R.id.tv_branch);
        tv_phase = findViewById(R.id.tv_phase);
        tv_year = findViewById(R.id.tv_year);

        tv_student_name.setText(getIntent().getStringExtra("guest_name"));
        tv_enroll.setText(getIntent().getStringExtra("guest_id"));
        tv_mobile.setText(getIntent().getStringExtra("mobile"));
        tv_college.setText(getIntent().getStringExtra("studentcollege"));
        tv_branch.setText(getIntent().getStringExtra("branch"));
        tv_phase.setText(getIntent().getStringExtra("studentphase"));
        tv_year.setText(getIntent().getStringExtra("studentyear"));


    }

}