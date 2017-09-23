package net.androidbootcamp.quiv_proj;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper database;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = new DatabaseHelper(this);

        boolean a1 = database.insertDataTeacher("1", "Duaa");
        boolean a2 = database.insertDataTeacher("2", "Hanan");

        boolean b1 = database.insertDataCourse( "php", "1");
        boolean b2 = database.insertDataCourse("android", "1");
        boolean b3 = database.insertDataCourse( "Java", "1");
        boolean b4 = database.insertDataCourse("python", "1");
        boolean b5 = database.insertDataCourse("HTML", "1");
        boolean b7 = database.insertDataCourse("Math", "1");
        boolean b8 = database.insertDataCourse("HH", "1");
        boolean b9 = database.insertDataCourse("ggg", "1");
        boolean b10 = database.insertDataCourse("dd", "1");
        boolean b11 = database.insertDataCourse("kk", "1");



        boolean c1 = database.insertDataCourse("C++", "2");
        boolean c2 = database.insertDataCourse("CSS", "2");
        boolean c3 = database.insertDataCourse("JavaScript", "2");
        boolean c4 = database.insertDataCourse("Software", "2");
        boolean c5 = database.insertDataCourse("ASP.NET", "2");
        boolean c6 = database.insertDataCourse("DD", "2");
        boolean c7 = database.insertDataCourse("Go", "2");
        boolean c8 = database.insertDataCourse("HH", "2");

//

        boolean s1 = database.insertDataStudent("26", "Asmaa");
        boolean s2 = database.insertDataStudent("27", "Amal");
        boolean s3 = database.insertDataStudent("3", "Islam");
        boolean s4 = database.insertDataStudent("4", "Noor");
        boolean s5 = database.insertDataStudent("5", "Reem");
        boolean s6 = database.insertDataStudent("6", "Israa");
        database.insertDataStudent("7", "Tala");
        database.insertDataStudent("8", "Lana");
        database.insertDataStudent("9", "Shahed");
        database.insertDataStudent("10", "Anas");
        database.insertDataStudent("11", "Sohaib");
        database.insertDataStudent("12", "Ibraheem");
        database.insertDataStudent("13", "Dawoud");
        database.insertDataStudent("14", "Ahmad");
        database.insertDataStudent("15", "Amr");
        database.insertDataStudent("16", "Taleen");
        database.insertDataStudent("17", "Dawoud");
        database.insertDataStudent("18", "Ahmad");
        database.insertDataStudent("19", "Amr");
        database.insertDataStudent("20", "Taleen");
        database.insertDataStudent("21", "Taleen");
        database.insertDataStudent("22", "Dawoud");
        database.insertDataStudent("23", "Ahmad");
        database.insertDataStudent("24", "Amr");
        database.insertDataStudent("25", "Taleen");


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frag, new Login()).commit();
        }



    }


}
