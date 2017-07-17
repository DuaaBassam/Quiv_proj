package net.androidbootcamp.quiv_proj;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by مركز الخبراء on 07/04/2017.
 */

public class StudentInTeacher extends Fragment {

    DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        //  Log.d("name", "      " + getArguments().getString("nameecourse"));


        View view = inflater.inflate(R.layout.student_in_teacher, container, false);
        final Button addd = (Button) view.findViewById(R.id.addd);
        final Button showw = (Button) view.findViewById(R.id.showw);
        if (savedInstanceState == null) {
           getActivity(). getSupportFragmentManager().beginTransaction().replace(R.id.add_delete_show, new AddStudent()).commit();
        }

        addd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle arg;
                Fragment fragment1 = new AddStudent();

                int dd =  getArguments().getInt("idTeach");
                arg = new Bundle();
                   arg.putInt("idTeach",dd);
                   arg.putString("nameCourse",getArguments().getString("nameCourse"));
                   fragment1.setArguments(arg);

                showw.setBackgroundColor(Color.WHITE);
                addd.setBackgroundColor(Color.RED);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.add_delete_show, fragment1);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }

        });

        showw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment teach = new ShowStudent();
                Bundle arg;
                arg = new Bundle();
                int dd =  getArguments().getInt("idTeach");
                arg = new Bundle();
                arg.putInt("idTeach",dd);
                arg.putString("nameCourse",getArguments().getString("nameCourse"));
                teach.setArguments(arg);

                showw.setBackgroundColor(Color.RED);
                addd.setBackgroundColor(Color.WHITE);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.add_delete_show, teach);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }

        });



        return view;
    }

}
