package com.example.sqlite2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDB;
    EditText eName,esurName,eMarks;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new DatabaseHelper(this);

        eName = findViewById(R.id.namemm);
        esurName = findViewById(R.id.surnamess);
        eMarks = findViewById(R.id.markss);
        button = findViewById(R.id.button);

        addData();


    }

    private void addData() {
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
               boolean isInserted =  myDB.insertData(eName.getText().toString(),esurName.getText().toString(),eMarks.getText().toString());
               if(isInserted){
                    Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_SHORT).show();
               }else{
                   Toast.makeText(MainActivity.this,"Data Inserted Failed",Toast.LENGTH_SHORT).show();
               }
            }
        });
    }
}