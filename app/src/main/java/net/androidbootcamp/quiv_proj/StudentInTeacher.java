package net.androidbootcamp.quiv_proj;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by مركز الخبراء on 07/04/2017.
 */

public class StudentInTeacher extends Fragment {
    FragmentPagerAdapter adapterViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        Log.d("name", "      " + getArguments().getString("nameCourse"));


        View view = inflater.inflate(R.layout.student_in_teacher, container, false);
        Button addd = (Button)view.findViewById(R.id.addd);
        Button showw = (Button)view.findViewById(R.id.showw);

        addd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.add_delete_show, new AddStudent());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

                }

        });

        showw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.add_delete_show, new ShowStudent());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }

        });

        return view ;
    }}
