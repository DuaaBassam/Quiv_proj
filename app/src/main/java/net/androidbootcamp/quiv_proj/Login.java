package net.androidbootcamp.quiv_proj;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class Login extends Fragment{
     DatabaseHelper databaseHelper;
//

    @Override
    //
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.login, container, false);
        final EditText idTeacher = (EditText) view.findViewById(R.id.number_person);
        final EditText password = (EditText) view.findViewById(R.id.password);
        Button btn_ok = (Button)view.findViewById(R.id.button);
        databaseHelper = new DatabaseHelper(getActivity());



        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
                        if (databaseHelper.loginTeacher(idTeacher.getText().toString(),password.getText().toString())){
                            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                            Fragment fragment = new Teacher();
                            Fragment listCourse = new ListCourse();
                            Bundle args = new Bundle();
                            args.putInt("id",Integer.parseInt(idTeacher.getText()+""));
                            fragment.setArguments(args);
                            Bundle arg = new Bundle();
                            arg.putInt("id",Integer.parseInt(idTeacher.getText()+""));
                            listCourse.setArguments(arg);

                            idTeacher.setText("");
                            password.setText("");

                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.frag, fragment);
                            fragmentTransaction.replace(R.id.teacherFrag, listCourse);
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.commit();
                            idTeacher.setText("");
                            password.setText("");

                        }

                           else if (databaseHelper.loginStudent(idTeacher.getText().toString(),password.getText().toString())){
                                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                Fragment fragment = new Student();
                                Fragment listCourse = new ListCourseStud();
                                Bundle args = new Bundle();
                                args.putInt("idStudent",Integer.parseInt(idTeacher.getText()+""));
                                fragment.setArguments(args);
                                listCourse.setArguments(args);

                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                fragmentTransaction.replace(R.id.frag, fragment);
                                fragmentTransaction.replace(R.id.studentFrag, listCourse);
                                fragmentTransaction.addToBackStack(null);
                                fragmentTransaction.commit();
                            idTeacher.setText("");
                            password.setText("");

                            }
        }});
getFragmentManager().popBackStack();

        return view;

    }


}
