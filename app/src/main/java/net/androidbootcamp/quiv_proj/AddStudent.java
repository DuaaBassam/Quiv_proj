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
    private String nameCourse;
    private int idTeach;
    private int teacher;

    public  AddStudent newInstance(String  page,int id,AddStudent fragmentFirst) {

        Bundle args = new Bundle();
        args.putString(page, page);
        Log.d("namecoursee",page);
        args.putInt(id+"",id);
        fragmentFirst.setArguments(args);
        teacher=fragmentFirst.getArguments().getInt(id+"");
        nameCourse = fragmentFirst.getArguments().getString(page);
        Log.d("name",fragmentFirst.getArguments().getInt(id+"")+"");
        return fragmentFirst;

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // nameCourse = getArguments().getString("namee");
        idTeach = teacher;
        Log.d("idddd",idTeach+"");

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.add_student, container, false);

      //  nameCourse = getArguments().getString("namee");

        Log.d("nameCC", "      " + nameCourse);
        Log.d("nameId",idTeach+"");


        db = new DatabaseHelper(getActivity());
        Button add = (Button) view.findViewById(R.id.addStud);
        Button delete = (Button) view.findViewById(R.id.deleteStudent);
        final TextView id = (TextView) view.findViewById(R.id.idStud);

        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (db.checkIdStudent(id.getText().toString())) {

                    if((db.getcheckadd(Integer.parseInt(id.getText()+""),db.getIdCourse(idTeach,nameCourse)))){
                        Toast.makeText(getActivity(), "Student was added ", Toast.LENGTH_SHORT).show();

                    }
                    else{
                        db.insertStudentInCourse((db.getIdCourse(idTeach, nameCourse )) + "", id.getText().toString());
                        Toast.makeText(getActivity(), "Add Student ", Toast.LENGTH_SHORT).show();
                        id.setText("");
                    }
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (db.checkIdStudent(id.getText().toString())) {
                    if (!(db.getcheckadd(Integer.parseInt(id.getText() + ""), db.getIdCourse(idTeach, nameCourse)))) {
                        Toast.makeText(getActivity(), "Student isn't added ", Toast.LENGTH_SHORT).show();
                    } else {
                        db.deleteData(id.getText().toString());
                        id.setText("");
                        Toast.makeText(getActivity(), "delete Student ", Toast.LENGTH_SHORT).show();


                    }
                }}});

        return view;
    }
}


/*    DatabaseHelper db;

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
                final String nameCourse = getArguments().getString("nameCourse");
             final int idTeach = getArguments().getInt("idTeach");


                if (db.checkIdStudent(id.getText().toString())) {
                  if((db.getcheckadd(Integer.parseInt(id.getText()+""),db.getIdCourse(idTeach,nameCourse)))){
                      Toast.makeText(getActivity(), "Student was added ", Toast.LENGTH_SHORT).show();
                  }
                    else{
                       db.insertStudentInCourse((db.getIdCourse(idTeach, nameCourse )) + "", id.getText().toString());
                        Toast.makeText(getActivity(), "Add Student ", Toast.LENGTH_SHORT).show();
                       id.setText("");
                    }

//
//                }
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

*/