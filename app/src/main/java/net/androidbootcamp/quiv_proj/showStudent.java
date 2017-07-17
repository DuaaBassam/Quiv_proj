package net.androidbootcamp.quiv_proj;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;


public class ShowStudent extends Fragment {


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
