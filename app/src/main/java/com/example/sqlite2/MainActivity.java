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
    EditText eName,esurName,eMarks,editId;
    Button button,button2,button3,button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new DatabaseHelper(this);

        eName = findViewById(R.id.namemm);
        esurName = findViewById(R.id.surnamess);
        eMarks = findViewById(R.id.markss);
        editId = findViewById(R.id.ids);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);

        addData();
        showAll();
        updateData();
        deletedata();


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

    private void updateData() {
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                boolean isUpdated = myDB.updateData(editId.getText().toString(),eName.getText().toString(),esurName.getText().toString(),eMarks.getText().toString());

                if(isUpdated){
                    Toast.makeText(MainActivity.this,"Data updated",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"Data not updated",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void deletedata() {
        button4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Integer deleteddata = myDB.deleteData(editId.getText().toString());

                if(deleteddata > 0){
                    Toast.makeText(MainActivity.this,"Data deleted",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"Data not deleted",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}