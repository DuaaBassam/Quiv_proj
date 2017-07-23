package net.androidbootcamp.quiv_proj;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class AddQuiz extends Fragment implements FragmentLifecycle{

    DatabaseHelper db;
    private String nameCourse;
    private int idTeach;
    private String teacher;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        nameCourse = getArguments().getString("namee");
  //      idTeach = getArguments().getInt("idTeach");

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.add_quiz, container, false);


    //    Log.d("nameCC", "      " + nameCourse+"   "+idTeach);


        db = new DatabaseHelper(getActivity());

        return view;
    }

    @Override
    public void onPauseFragment() {

    }

    @Override
    public void onResumeFragment() {

    }
}