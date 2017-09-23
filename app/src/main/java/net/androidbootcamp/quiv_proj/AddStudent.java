package net.androidbootcamp.quiv_proj;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class AddStudent extends Fragment implements FragmentLifecycle{

    DatabaseHelper db;
    private String nameCourse;
    private int idTeach;
    private String teacher;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        nameCourse = getArguments().getString("namee");
        idTeach = getArguments().getInt("idTeach");

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.add_student, container, false);

        db = new DatabaseHelper(getActivity());
        ImageView add = (ImageView) view.findViewById(R.id.addStud);
        ImageView delete = (ImageView) view.findViewById(R.id.deleteStudent);
        final TextView id = (TextView) view.findViewById(R.id.idStud);

        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (id.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "write idStudent ", Toast.LENGTH_SHORT).show();

                }else{
                if (db.checkIdStudent(Integer.parseInt(id.getText().toString()))) {

                    if((db.getcheckadd(Integer.parseInt(id.getText().toString()),nameCourse,idTeach))){
                        Toast.makeText(getActivity(), "Student was added ", Toast.LENGTH_SHORT).show();
                    }

                    else{
                        db.insertStudentInCourse(nameCourse ,Integer.parseInt(id.getText().toString()),idTeach);
                        Toast.makeText(getActivity(), "Add Student ", Toast.LENGTH_SHORT).show();
                        id.setText("");
                    }
                }
                }
        }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (id.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "write idStudent ", Toast.LENGTH_SHORT).show();

                }else{
                if (db.checkIdStudent(Integer.parseInt(id.getText().toString()))) {
                    if (!(db.getcheckadd(Integer.parseInt(id.getText().toString()),nameCourse,idTeach))) {
                        Toast.makeText(getActivity(), "Student isn't added ", Toast.LENGTH_SHORT).show();
                    } else {

                        db.deleteData(id.getText().toString(),nameCourse,idTeach+"");
                        db.deleteStudentFromAnswerStudent(id.getText().toString(),nameCourse);
                        db.deleteStudentFromGradeStudent(id.getText().toString(),nameCourse);

                        id.setText("");
                        Toast.makeText(getActivity(), "delete Student ", Toast.LENGTH_SHORT).show();

                    }
                }}

            }});

        return view;
    }

    @Override
    public void onPauseFragment() {

    }

    @Override
    public void onResumeFragment() {

    }
}