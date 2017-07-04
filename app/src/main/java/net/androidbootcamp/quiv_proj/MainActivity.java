package net.androidbootcamp.quiv_proj;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

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
        boolean b6 = database.insertDataCourse("6", "Software", "1");
        boolean b7 = database.insertDataCourse("7", "ASP.NET", "1");

        boolean c1 = database.insertDataCourse("8", "php", "2");
        boolean c2 = database.insertDataCourse("9", "android", "2");
        boolean c3 = database.insertDataCourse("10", "Java", "2");
        boolean c4 = database.insertDataCourse("11", "python", "2");
        boolean c5 = database.insertDataCourse("12", "HTML", "2");
        boolean c6 = database.insertDataCourse("13", "Software", "2");
        boolean c7 = database.insertDataCourse("14", "ASP.NET", "2");
        boolean c8 = database.insertDataCourse("15", "CSS", "2");
//

        boolean s1 = database.insertDataStudent("1", "Asmaa");
        boolean s2 = database.insertDataStudent("2", "Amal");
        boolean s3 = database.insertDataStudent("3", "Islam");
        boolean s4 = database.insertDataStudent("4", "Noor");
        boolean s5 = database.insertDataStudent("5", "Reem");
        boolean s6 = database.insertDataStudent("6", "Israa");
        boolean s7 = database.insertDataStudent("7", "Abeer");

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frag, new Login()).commit();
        }

        if (a1 == true && b1 == true && a2 == true && b2 == true && b3 == true
                && b4 == true && b5 == true && b6 == true && b7 == true && c2 == true
                && c3 == true && c4 == true && c5 == true && c6 == true && c7 == true
                && c1 == true && s1 == true && s2 == true && s3 == true && s4 == true
                && s5 == true && s6 == true && s7 == true
                && c8 == true) {
            Toast.makeText(MainActivity.this, "data ins.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "data not ins.", Toast.LENGTH_SHORT).show();
        }
    }
}