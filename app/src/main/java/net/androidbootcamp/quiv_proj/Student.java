package net.androidbootcamp.quiv_proj;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Hanan Dawod on 05/08/17.
 */

public class Student extends Fragment {
    private int idStudent;

    @Override

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        idStudent = getArguments().getInt("idStudent");
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.student, container, false);
        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
        String s = databaseHelper.getNameStudent(idStudent);
        TextView nameTec = (TextView) view.findViewById(R.id.namePerson);
        nameTec.setText("Name : " + s);


        view.findViewById(R.id.Home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int x = getFragmentManager().getBackStackEntryCount()-1;
                for(int i = 0;i<x;i++) {
                    getFragmentManager().popBackStack();
                }
            }
        });

        return view;
    }

    }
