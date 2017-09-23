package net.androidbootcamp.quiv_proj;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;
import android.widget.TextView;


public class Teacher extends Fragment {
    private int teacherId;

    @Override

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        teacherId = getArguments().getInt("id");

        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.teacher, container, false);


        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
        String s = databaseHelper.getNameTeacher(teacherId);
        TextView nameTec = (TextView) view.findViewById(R.id.namePerson);
        nameTec.setText("Name : " + s);
        view.findViewById(R.id.Home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int x = getFragmentManager().getBackStackEntryCount()-1;
                for (int i = 0; i < x; i++) {
                    getFragmentManager().popBackStack();
                }

            }//end of onClick method
        });

        return view;

    }





}
