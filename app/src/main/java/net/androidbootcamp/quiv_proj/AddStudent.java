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
import android.widget.Toast;

/**
 * Created by مركز الخبراء on 07/04/2017.
 */

public class AddStudent extends Fragment{

DatabaseHelper db;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View view = inflater.inflate(R.layout.add_student, container, false);
            db = new DatabaseHelper(getActivity());
            Button add = (Button) view.findViewById(R.id.add);
            Button delete = (Button) view.findViewById(R.id.delete);

            final TextView id = (TextView)view.findViewById(R.id.idPerson);
            add.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if(db.checkIdStudent(id.getText().toString())){

               Log.d("namebbbb","      " + getArguments().getString("name"));
;
        }
    }});

    delete.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if(db.checkIdStudent(id.getText().toString())){
                db.deleteData(id.getText().toString());
                Toast.makeText(getActivity(), "delete Student ", Toast.LENGTH_SHORT).show();

            }}

            });

            return view;
        }
}

