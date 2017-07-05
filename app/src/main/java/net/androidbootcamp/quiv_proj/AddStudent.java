package net.androidbootcamp.quiv_proj;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by مركز الخبراء on 07/04/2017.
 */

public class AddStudent extends Fragment{
        // Store instance variables
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
            Button add = (Button) view.findViewById(R.id.add);
            Button delete = (Button) view.findViewById(R.id.delete);

            final TextView id = (TextView)view.findViewById(R.id.id);
    add.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if(db.checkIdStudent(id.getText().toString())){


                       Log.d("namebbbb","      " + getArguments().getString("name"));
            //Log.d("id","      " + getArguments().getInt("idTeacher"));

              //db.insertStudentInCourse(getArguments().getString("nameCourse"),""+getArguments().getInt("idTeacher"));
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

