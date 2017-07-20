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

import java.util.ArrayList;


public class ShowStudent extends Fragment {

DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
    private int idCourse;
    private String courseNamee;
private String nameee;
    private int idTeach;
    // newInstance constructor for creating fragment with arguments
    public  ShowStudent newInstance(String page,int id,ShowStudent fragmentFirst) {

        Bundle args = new Bundle();
        args.putString(page, page);
        args.putInt(id+"",id);
        fragmentFirst.setArguments(args);
        idCourse=fragmentFirst.getArguments().getInt(id+"");
        courseNamee=fragmentFirst.getArguments().getString(page);
        Log.d("nameSHOW",fragmentFirst.getArguments().getInt(id+"")+"");
        return fragmentFirst;

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       idTeach= getArguments().getInt("idTeach");
      nameee=  getArguments().getString("namee");
        Log.d("nmaee",nameee+"  "+idTeach);


    }


    @Override
    public void onResume () {


        super.onResume();
        ArrayList<StudentItems> arrayList;
        ArrayList cursor = new DatabaseHelper(getActivity()).getAllData(getArguments().getInt("idTeach"), getArguments().getString("namee"));
        arrayList = cursor;

        //Log.d("namecoursee", "      " + getArguments().getString("namee")+"  "+getArguments().getInt("idTeach"));
        final TeacherStudentListview adapter = new TeacherStudentListview(getActivity(),arrayList);
        listView.setAdapter(adapter);
     //  databaseHelper.getAllData(idTeach,nameee);
        Log.d("nmaJGLJBeerrrr",nameee+"  "+idTeach);

    }

    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_show_student, container, false);

        ArrayList<StudentItems> arrayList;
        ArrayList cursor = new DatabaseHelper(getActivity()).getAllData(getArguments().getInt("idTeach"), getArguments().getString("namee"));
        arrayList = cursor;


        listView = (ListView)view.findViewById(R.id.listStudentShow);

        Log.d("namecoursee", "      " + getArguments().getString("namee")+"  "+getArguments().getInt("idTeach"));
        final TeacherStudentListview adapter = new TeacherStudentListview(getActivity(),arrayList);
        listView.setAdapter(adapter);
        return view;


    }


}

/*   private String title;
    private String nameCourse;
    private int idTeach;

/*
    // newInstance constructor for creating fragment with arguments
    public static ShowStudent newInstance(int page, String title) {
        ShowStudent fragmentFirst = new ShowStudent();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        // args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
        nameCourse = getArguments().getString("nameCourse");
        idTeach = getArguments().getInt("idTeach");
    }


    private String courseNamee;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_show_student, container, false);
        ListView listView = (ListView) view.findViewById(R.id.listStudentShow);
        final TeacherStudentListview adapter = new TeacherStudentListview(getActivity(), nameCourse, idTeach);
        listView.setAdapter(adapter);
        return view;


    }
}
/*
    private String title;
    //private int page;

    // newInstance constructor for creating fragment with arguments
    public static ShowStudent newInstance(int page, String title) {
        ShowStudent fragmentFirst = new ShowStudent();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        // args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }


   private String courseNamee;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     //   courseNamee = getArguments().getString("nameecourse");
//        Log.d("",courseNamee);

        View view = inflater.inflate(R.layout.fragment_show_student, container, false);
        ListView listView = (ListView)view.findViewById(R.id.listStudentShow);

       Log.d("namecoursee", "      " + getArguments().getString("nameCourse"));
        final TeacherStudentListview adapter = new TeacherStudentListview(getActivity(),getArguments().getString("nameCourse"),getArguments().getInt("idTeach"));
        listView.setAdapter(adapter);
        return view;


    }



*/