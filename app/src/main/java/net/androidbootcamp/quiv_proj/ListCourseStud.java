package net.androidbootcamp.quiv_proj;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by Hanan Dawod on 05/08/17.
 */

public class ListCourseStud extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.listcourse, container, false);

        ListView listView = (ListView) view.findViewById(R.id.ListCourseTec);
        Log.d("idStudent",getArguments().getInt("idStudent")+"");
        final ListViewCourseInStudent adapter = new ListViewCourseInStudent(this, getArguments().getInt("idStudent"));
        listView.setAdapter(adapter);
        return view;
    }}
