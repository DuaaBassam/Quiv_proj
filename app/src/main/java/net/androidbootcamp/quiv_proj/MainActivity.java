package net.androidbootcamp.quiv_proj;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper database;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = new DatabaseHelper(this);

        boolean a1 = database.insertDataTeacher("1", "Duaa");
        boolean a2 = database.insertDataTeacher("2", "Hanan");

        boolean b1 = database.insertDataCourse("1", "php", "1");
        boolean b2 = database.insertDataCourse("2", "android", "1");
        boolean b3 = database.insertDataCourse("3", "Java", "1");
        boolean b4 = database.insertDataCourse("4", "python", "1");
        boolean b5 = database.insertDataCourse("5", "HTML", "1");


        boolean c1 = database.insertDataCourse("6", "ios", "2");
        boolean c2 = database.insertDataCourse("7", "CSS", "2");
        boolean c3 = database.insertDataCourse("8", "JavaScript", "2");
        boolean c4 = database.insertDataCourse("9", "Software", "2");
        boolean c5 = database.insertDataCourse("10", "ASP.NET", "2");

//

        boolean s1 = database.insertDataStudent("1", "Asmaa");
        boolean s2 = database.insertDataStudent("2", "Amal");
        boolean s3 = database.insertDataStudent("3", "Islam");
        boolean s4 = database.insertDataStudent("4", "Noor");
        boolean s5 = database.insertDataStudent("5", "Reem");
        boolean s6 = database.insertDataStudent("6", "Israa");
        boolean s7 = database.insertStudentInCourse("1", "1");


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frag, new Login()).commit();
        }

        if (a1 == true ) {
            Toast.makeText(MainActivity.this, "data ins.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "data not ins.", Toast.LENGTH_SHORT).show();
        }
    }
}