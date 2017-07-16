package net.androidbootcamp.quiv_proj;


import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;



public class Teacher extends  Fragment {

    private int teacherId;
    @Override

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.teacher, container, false);

//        teacherId = getArguments().getInt("id");
//
//        DatabaseHelper databaseHelper=new DatabaseHelper(getActivity());
//
//        String s = databaseHelper.getNameTeacher(teacherId);
//        TextView nameTec=(TextView )view.findViewById(R.id.namePerson);
//       nameTec.setText("Name : "+s);
//        TextView idTec =(TextView)view.findViewById(R.id.idPerson);
//        idTec.setText("ID : "+teacherId);
//        ListView listView = (ListView)view.findViewById(R.id.ListCourseTec);
//        final ListViewCourse adapter = new ListViewCourse(getActivity(),teacherId);
//        listView.setAdapter(adapter);

        /*view .findViewById(R.id.Home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
Fragment fragment=new Login();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frag, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });*/
        return view;

    }

}
