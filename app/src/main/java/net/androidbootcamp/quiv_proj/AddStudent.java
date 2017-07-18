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
    public static AddStudent newInstance(String  page,int id) {
        AddStudent fragmentFirst = new AddStudent();
        Bundle args = new Bundle();

            args.putString(page, page);
          args.putInt(id+"",id);

        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //page = getArguments().getInt("someInt", 0);
        nameCourse = getArguments().getString("nameCourse");
        idTeach = getArguments().getInt("idTeach");

    }
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
                final String nameCourse = getArguments().getString("nameCourse");
                final int idTeach = getArguments().getInt("idTeach");

                if (db.checkIdStudent(id.getText().toString())) {
                    if (!(db.getcheckadd(Integer.parseInt(id.getText() + ""), db.getIdCourse(idTeach, nameCourse)))) {
                        Toast.makeText(getActivity(), "Student isn't added ", Toast.LENGTH_SHORT).show();
                    } else {
                        db.deleteData(id.getText().toString());
                        id.setText("");
                        Toast.makeText(getActivity(), "delete Student ", Toast.LENGTH_SHORT).show();
                    }
//
                }
            }

        });

        return view;
    }
}




/* // Store instance variables
    private String title;
    //private int page;
    DatabaseHelper db;
    // newInstance constructor for creating fragment with arguments
    public static AddStudent newInstance(int page, String title) {
        AddStudent fragmentFirst = new AddStudent();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        // args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View view = inflater.inflate(R.layout.add_student, container, false);
        db = new DatabaseHelper(getActivity());
        Button add = (Button) view.findViewById(R.id.addStud);
        Button delete = (Button) view.findViewById(R.id.deleteStudent);

        final TextView id = (TextView)view.findViewById(R.id.idStud);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(db.checkIdStudent(id.getText().toString())){
                    Log.d("namebbbb","      " + getArguments().getString("nameCourse"));
                    Log.d("id","      " + getArguments().getInt("idTeacher"));

                    db.insertStudentInCourse(getArguments().getString("nameCourse"),""+getArguments().getInt("idTeacher"));
                    ;
                }
            }});

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(db.checkIdStudent(id.getText().toString())){
                    db.deleteData(id.getText().toString());
                }}

        });

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