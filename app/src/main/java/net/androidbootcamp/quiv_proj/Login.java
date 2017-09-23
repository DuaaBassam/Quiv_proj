package net.androidbootcamp.quiv_proj;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class Login extends Fragment{
     DatabaseHelper databaseHelper;
//

    @Override
    //
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.login, container, false);

        final EditText idUser = (EditText) view.findViewById(R.id.number_person);
        final EditText password = (EditText) view.findViewById(R.id.password);
        final TextView textView =(TextView) view.findViewById(R.id.hh);
        textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        textView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        if (idUser.getText().toString().isEmpty()){

            Toast.makeText(getActivity(),"idUser is empty",Toast.LENGTH_SHORT).show();

}

else {

    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
    Bundle args =new Bundle();

    if (databaseHelper.getCheckTeacher(Integer.parseInt(idUser.getText().toString()))){
        args.putInt("idTeacher",Integer.parseInt(idUser.getText().toString()));
        Fragment fragment = new ChangePassword();
        fragment.setArguments(args);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frag, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        closeKeyboard();
    }
    else if (databaseHelper.getCheckStudent(Integer.parseInt(idUser.getText().toString()))) {

        args.putInt("idStudent",Integer.parseInt(idUser.getText().toString()));
        Fragment fragment = new ChangePassword();
        fragment.setArguments(args);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frag, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        closeKeyboard();


    }
    else{
        Toast.makeText(getActivity(),"idUser is not found",Toast.LENGTH_SHORT).show();

    }



}
    }
});
        Button btn_ok = (Button)view.findViewById(R.id.button);
        databaseHelper = new DatabaseHelper(getActivity());

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                        if (databaseHelper.loginTeacher(idUser.getText().toString(),password.getText().toString())){

                            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                            Fragment fragment = new Teacher();
                            Fragment listCourse = new ListCourse();
                            Bundle args = new Bundle();
                            args.putInt("id",Integer.parseInt(idUser.getText()+""));
                            fragment.setArguments(args);
                            Bundle arg = new Bundle();
                            arg.putInt("id",Integer.parseInt(idUser.getText()+""));
                            listCourse.setArguments(arg);

                            idUser.setText("");
                            password.setText("");

                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.frag, fragment);
                            fragmentTransaction.replace(R.id.teacherFrag, listCourse);
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.commit();
                            closeKeyboard();
                            idUser.setText("");
                            password.setText("");

                        }

                           else if (databaseHelper.loginStudent(idUser.getText().toString(),password.getText().toString())){

                            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                Fragment fragment = new Student();
                                Fragment listCourse = new ListCourseStud();
                                Bundle args = new Bundle();
                                args.putInt("idStudent",Integer.parseInt(idUser.getText()+""));
                                fragment.setArguments(args);
                                listCourse.setArguments(args);

                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                fragmentTransaction.replace(R.id.frag, fragment);
                                fragmentTransaction.replace(R.id.studentFrag, listCourse);
                                fragmentTransaction.addToBackStack(null);
                                fragmentTransaction.commit();

                                closeKeyboard();
                                idUser.setText("");
                                password.setText("");

                            }else {
                            Toast.makeText(getActivity(),"ID or password is wrong",Toast.LENGTH_SHORT).show();
                        }
        }});

        return view;

    }
    public  void closeKeyboard(){
        View vv =getActivity().getCurrentFocus();
        if (vv != null){
            InputMethodManager manager = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(vv.getWindowToken(),0);
        }
    }



}
