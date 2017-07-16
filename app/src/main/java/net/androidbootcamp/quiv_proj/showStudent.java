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


   private String teacherId;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        teacherId = getArguments().getString("nameecourse");

        View view = inflater.inflate(R.layout.fragment_show_student, container, false);
        ListView listView = (ListView)view.findViewById(R.id.listStudentShow);

        Log.d("namejbkj", "      " + teacherId);
        final TeacherStudentListview adapter = new TeacherStudentListview(getActivity(),teacherId);
        listView.setAdapter(adapter);
        return view;


    }


}
