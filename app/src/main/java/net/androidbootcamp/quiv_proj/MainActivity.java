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
        boolean a=   database.insertData("1","duaa","123");
        if (a==true){
            Toast.makeText(MainActivity.this,"data ins.",Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(MainActivity.this,"data not ins.",Toast.LENGTH_SHORT).show();
//

    }}