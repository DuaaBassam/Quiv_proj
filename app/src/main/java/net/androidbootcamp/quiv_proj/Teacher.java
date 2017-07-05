package net.androidbootcamp.quiv_proj;


import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;



public class Teacher extends  Fragment {
    private int teacherId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    TextView textView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        teacherId = getArguments().getInt("id");
        System.out.println(teacherId);


        View view = inflater.inflate(R.layout.teacher, container, false);
        //view.findViewById(R.id.list_course1).setVisibility(View.VISIBLE);
        textView = (TextView) view.findViewById(R.id.name_teacher);
        ListView listView = (ListView)view.findViewById(R.id.list_course1);
        DatabaseHelper databaseHelper =new DatabaseHelper(getActivity());
        textView.setText(databaseHelper.getNameTeacher(teacherId));
        final ListViewCourse adapter = new ListViewCourse(getActivity(),teacherId);
        Fragment teach=new AddStudent();
        Bundle args =new Bundle();
        args.putInt("idTeacher",teacherId);
        teach.setArguments(args);
        listView.setAdapter(adapter);
        return view;
        ///
    }


}
