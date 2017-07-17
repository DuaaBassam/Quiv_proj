package net.androidbootcamp.quiv_proj;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;
import android.widget.TextView;


public class Teacher extends Fragment {
    private int teacherId;

    @Override

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.teacher, container, false);

        teacherId = getArguments().getInt("id");

        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
        String s = databaseHelper.getNameTeacher(teacherId);
        TextView nameTec = (TextView) view.findViewById(R.id.namePerson);
        nameTec.setText("Name : " + s);
        TextView idTec = (TextView) view.findViewById(R.id.idStud);
        idTec.setText("ID : " + teacherId);


        view.findViewById(R.id.Home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int x = getFragmentManager().getBackStackEntryCount();
                for(int i = 0;i<x;i++) {

                    getFragmentManager().popBackStack();
                }



                Fragment fragment = new Login();


                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frag, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();


            }
        });
        return view;

    }
}
