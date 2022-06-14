package com.example.employees;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddnewemployeeActivity extends AppCompatActivity {
    EditText employee_name,employee_designation;
    Button submitBtn,cancelBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnewemployee);

        employee_name = findViewById(R.id.etName);
        employee_designation = findViewById(R.id.etDesignation);
        cancelBtn = findViewById(R.id.BtnCancel);
        submitBtn = findViewById(R.id.BtnSubmit);

        submitBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddnewemployeeActivity.this);
                myDB.addEmployee(employee_name.getText().toString().trim(),
                        employee_designation.getText().toString().trim());
                employee_name.setText("");
                employee_designation.setText("");

            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                employee_name.setText("");
                employee_designation.setText("");
                Intent intent = new Intent(AddnewemployeeActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}