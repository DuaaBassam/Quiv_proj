package net.androidbootcamp.quiv_proj;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowStudent extends Fragment implements FragmentLifecycle {


    private String nameCourse;
    private int idTeach;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        idTeach= getArguments().getInt("idTeach");
        nameCourse=  getArguments().getString("namee");
    }

    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_show_student, container, false);
        ArrayList<StudentItems> arrayList;

  ArrayList   cursor = new DatabaseHelper(getActivity()).getAllData(idTeach, nameCourse);
        arrayList = cursor;

        listView = (ListView) view.findViewById(R.id.listStudentShow);
        Log.d("namecoursee", "      " + idTeach + "  " + nameCourse);
       final TeacherStudentListview adapter = new TeacherStudentListview(getActivity(), arrayList);
       listView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onPauseFragment() {
    }

    @Override
    public void onResumeFragment() {
        ArrayList<StudentItems> arrayList;
        ArrayList cursor = new DatabaseHelper(getActivity()).getAllData(idTeach, nameCourse);
        arrayList = cursor;

        Log.d("namecoursee", "      " + idTeach + "  " + nameCourse);
        final TeacherStudentListview adapter = new TeacherStudentListview(getActivity(), arrayList);
        listView.setAdapter(adapter);

    }
}