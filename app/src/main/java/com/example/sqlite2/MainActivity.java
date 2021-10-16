package com.example.sqlite2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDB;
    EditText eName,esurName,eMarks;
    Button button,button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new DatabaseHelper(this);

        eName = findViewById(R.id.namemm);
        esurName = findViewById(R.id.surnamess);
        eMarks = findViewById(R.id.markss);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);

        addData();
        showAll();


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

    private void showAll() {
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Cursor res = myDB.getData();
                if(res.getCount() == 0){
                    showMessage("Error","nothing found");
                    return;
                }else{
                    StringBuffer buffer = new StringBuffer();
                    while(res.moveToNext()){
                        buffer.append("Id :"+res.getString(0)+"\n");
                        buffer.append("Name :"+res.getString(1)+"\n");
                        buffer.append("Surname :"+res.getString(2)+"\n");
                        buffer.append("Marks :"+res.getString(3)+"\n\n");

                    }

                    showMessage("Data",buffer.toString());
                }
            }
        });

    }

    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();

    }
}