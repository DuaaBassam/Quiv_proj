package net.androidbootcamp.quiv_proj;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

/**
 * Created by Hanan Dawod on 05/07/17.
 */

public class ListCourse extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.listcourse, container, false);
        ListView listView = (ListView)view.findViewById(R.id.list_course1);

        final ListViewCourse adapter = new ListViewCourse(getActivity(),getArguments().getInt("idTeacher"));
        listView.setAdapter(adapter);
        return view;

}}
