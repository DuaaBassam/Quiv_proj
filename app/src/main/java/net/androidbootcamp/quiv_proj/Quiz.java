package net.androidbootcamp.quiv_proj;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Hanan Dawod on 17/07/17.
 */

public class Quiz extends Fragment {
    DatabaseHelper databaseHelper =new DatabaseHelper(getActivity());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.quiz_teacher, container, false);
        final Button addquiz = (Button) view.findViewById(R.id.add_quizbutton);


        addquiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle arg;
                Fragment fragment1 = new AddQuiz();

                addquiz.setBackgroundColor(Color.RED);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.addquiz, fragment1);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }

        });
        return view;
    }
}