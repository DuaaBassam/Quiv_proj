package net.androidbootcamp.quiv_proj;

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


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frag, new Login()).commit();
        }


//
//        FragmentManager fragmentManager=getSupportFragmentManager();
//        Fragment current =fragmentManager.findFragmentById(R.id.frag);
//        if(current.getTag().equals("Login")){
//

            }
//    @Override
//    public void onBackPressed() {
//        getSupportFragmentManager().popBackStack();
//
//    }//getSupportFragmentManager
//        Log.d("class",((ViewGroup)findViewById(R.id.frag)).getChildAt(0).getClass().toString());
//        if (((ViewGroup)findViewById(R.id.frag)).getChildAt(0).getClass().equals(Login.class))
//            finish();
//        else
//            super.onBackPressed();
//    }
}