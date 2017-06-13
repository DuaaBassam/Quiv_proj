package net.androidbootcamp.quiv_proj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.*;


public class MainActivity extends AppCompatActivity {
    DatabaseHelper database ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = new DatabaseHelper(this);
        boolean a1=   database.insertData_teacher("1","duaa","123");
        boolean a2=   database.insertData_teacher("2","Hanan","123");
        boolean a3=   database.insertData_course("1","php","1");
        if (a1==true && a3==true && a2 ==true){
            Toast.makeText(MainActivity.this,"data ins.",Toast.LENGTH_SHORT).show();

        }
        else
            Toast.makeText(MainActivity.this,"data not ins.",Toast.LENGTH_SHORT).show();


    }
}