package net.androidbootcamp.quiv_proj;

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


public class ShowStudent extends Fragment {

    private String courseNamee;
    private int idCourse;
    // newInstance constructor for creating fragment with arguments
    public static ShowStudent newInstance(String page,int id,ShowStudent fragmentFirst) {
        Bundle args = new Bundle();
        args.putString(page, page);
        args.putInt(id+"",id);
        // args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       courseNamee= getArguments().getString("namee");
       idCourse  =  getArguments().getInt("idTeach");

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_show_student, container, false);

		/*
		 * When this container fragment is created, we fill it with our first
		 * "real" fragment
		 */

        ListView listView = (ListView)view.findViewById(R.id.listStudentShow);

        Log.d("namecoursee", "      " + courseNamee);
        final TeacherStudentListview adapter = new TeacherStudentListview(getActivity(),courseNamee,idCourse);
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


}
*/