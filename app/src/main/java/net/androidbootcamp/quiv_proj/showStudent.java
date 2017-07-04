package net.androidbootcamp.quiv_proj;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;


public class ShowStudent extends Fragment {

    private String title;
    public static ShowStudent newInstance(int page, String title) {
        ShowStudent fragmentFirst = new ShowStudent();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        // args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_student, container, false);
        ListView listView = (ListView)view.findViewById(R.id.list_course1);
        final TeacherStudent adapter = new TeacherStudent(getActivity(),2);
//        listView.setAdapter(adapter);
        return view;


    }


}
