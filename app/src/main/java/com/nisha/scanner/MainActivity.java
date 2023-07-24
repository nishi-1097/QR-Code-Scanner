package com.nisha.scanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    /*private TextView studentIdTextView;
    private TextView studentNameTextView;
    private TextView studentAddressTextView;*/
    String guest_id, guest_name, mobile,studentyear,studentcollege,studentphase,branch;
    Button bt_scan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       /* studentIdTextView = findViewById(R.id.studentIdTextView);
        studentNameTextView = findViewById(R.id.studentNameTextView);
        studentAddressTextView = findViewById(R.id.studentAddressTextView);*/
        bt_scan = findViewById(R.id.bt_scan);

        bt_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
                integrator.setOrientationLocked(false);
                integrator.initiateScan();
            }
        });

        // Start the QR code scanner

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //if qrcode has nothing in it
            if (result.getContents() == null) {
                Toast.makeText(MainActivity.this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {

                try {

                    JSONObject obj = new JSONObject(result.getContents());
                    guest_id = (obj.getString("Student_id"));
                    guest_name = (obj.getString("Student_name"));
                    mobile = (obj.getString("student_Mobile"));
                    studentcollege = (obj.getString("student_College"));
                    branch = (obj.getString("student_Branch"));
                    studentphase = (obj.getString("student_Phase"));
                    studentyear = (obj.getString("student_Year"));

                    Intent intent = new Intent(getApplicationContext(), Student_Profile.class);
                    intent.putExtra("guest_id", guest_id);
                    intent.putExtra("guest_name", guest_name);
                    intent.putExtra("mobile", mobile);
                    intent.putExtra("studentcollege", studentcollege);
                    intent.putExtra("branch", branch);
                    intent.putExtra("studentphase", studentphase);
                    intent.putExtra("studentyear", studentyear);

                    startActivity(intent);


                    /*studentIdTextView.setText("Student ID: " + guest_id);
                    studentNameTextView.setText("Student Name: " + guest_name);
                    studentAddressTextView.setText("Address: " + studentAddress);*/

                    System.out.println("guest_id---" + guest_id);
                    System.out.println("Scann ---" + result.getContents());

                } catch (JSONException e) {
                    e.printStackTrace();


                    System.out.println("Scann ---" + result.getContents());
                    Toast.makeText(MainActivity.this, result.getContents(), Toast.LENGTH_LONG).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void updateUI(String qrData) {
        // Assuming the QR data is in a comma-separated format like: "student_id,student_name,address"
        String[] details = qrData.split(",");

        if (details.length == 3) {
            String studentId = details[0];
            String studentName = details[1];
            String studentAddress = details[2];

            /*studentIdTextView.setText("Student ID: " + studentId);
            studentNameTextView.setText("Student Name: " + studentName);
            studentAddressTextView.setText("Address: " + studentAddress);*/
        } else {
            Toast.makeText(this, "Invalid QR code data format!", Toast.LENGTH_SHORT).show();
            finish(); // Close the activity if the QR code data is invalid
        }
    }
}