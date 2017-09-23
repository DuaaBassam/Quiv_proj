package net.androidbootcamp.quiv_proj;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowStudent extends Fragment implements FragmentLifecycle {

   DatabaseHelper databaseHelper;
    private String nameCourse;
    private int idTeach;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseHelper= new DatabaseHelper(getActivity());
        idTeach= getArguments().getInt("idTeach");
        nameCourse=  getArguments().getString("namee");
        setRetainInstance(true);
    }
    
    ListView listView;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        
        View view = inflater.inflate(R.layout.fragment_show_student, container, false);
        ArrayList   cursor = databaseHelper.getAllData(idTeach, nameCourse);

        listView = (ListView) view.findViewById(R.id.listStudentShow);
        final TeacherStudentListview adapter = new TeacherStudentListview(getActivity(), cursor,nameCourse,idTeach);
        listView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onPauseFragment() {
    }

    @Override
    public void onResumeFragment() {
        ArrayList   cursor = databaseHelper.getAllData(idTeach, nameCourse);

        listView = (ListView) getView().findViewById(R.id.listStudentShow);
        final TeacherStudentListview adapter = new TeacherStudentListview(getActivity(), cursor,nameCourse,idTeach);
        listView.setAdapter(adapter);


    }
}
