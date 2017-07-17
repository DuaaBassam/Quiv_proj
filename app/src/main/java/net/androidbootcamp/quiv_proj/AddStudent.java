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


public class AddStudent extends Fragment {
 
    DatabaseHelper db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.add_student, container, false);
        db = new DatabaseHelper(getActivity());
        Button add = (Button) view.findViewById(R.id.addStud);
        Button delete = (Button) view.findViewById(R.id.deleteStudent);

        final TextView id = (TextView) view.findViewById(R.id.idStud);

        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Log.d("namebbbb",  getArguments().getString("nameCourse"));
                Log.d("id", "      " + getArguments().getInt("idTeach"));

                if (db.checkIdStudent(id.getText().toString())) {
                    db.insertStudentInCourse((db.getIdCourse(getArguments().getInt("idTeach"), getArguments().getString("nameCourse") + "")) + "", id.getText().toString());
                    Toast.makeText(getActivity(), "Add Student ", Toast.LENGTH_SHORT).show();
                    id.setText("");
                }

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
                if (db.checkIdStudent(id.getText().toString())) {
                    db.deleteData(id.getText().toString());
                    id.setText("");
                    Toast.makeText(getActivity(), "delete Student ", Toast.LENGTH_SHORT).show();

                }
            }

        });

        return view;
    }
}

