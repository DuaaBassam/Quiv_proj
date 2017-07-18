package net.androidbootcamp.quiv_proj;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Hanan Dawod on 17/07/17.
 */

public class AddQuiz extends Fragment  {

    DatabaseHelper db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.add_quiz, container, false);
        db = new DatabaseHelper(getActivity());

        return view;
    }}
