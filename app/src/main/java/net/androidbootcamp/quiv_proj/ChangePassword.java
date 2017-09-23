package net.androidbootcamp.quiv_proj;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.Context.NOTIFICATION_SERVICE;

public class ChangePassword extends Fragment {

    DatabaseHelper databaseHelper;

    TextView namePerson ;
    EditText oldPassword;
    EditText newPassword;
    EditText confirmPassword;
    Button changePassword;

    String name ;
    String passwordOld;
    String passwordNew;
    String passwordConfirm;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view  =inflater.inflate(R.layout.fragment_change_password, container, false);


        databaseHelper = new DatabaseHelper(getActivity());
        namePerson = (TextView)view.findViewById(R.id.namePersonChange);
        oldPassword =(EditText)view.findViewById(R.id.passwordPerson);
        newPassword = (EditText)view.findViewById(R.id.passwordChange);
        confirmPassword =(EditText)view.findViewById(R.id.confirmPassword);
        changePassword =(Button) view.findViewById(R.id.buttonChangePassword);


        final int idTeacher =getArguments().getInt("idTeacher");
        final int idStudent =getArguments().getInt("idStudent");

        if (idTeacher!=0){
             name = databaseHelper.getNameTeacher(idTeacher);

        }else if (idStudent!=0){
            name = databaseHelper.getNameStudent(idStudent);

        }
        namePerson.setText(name);


        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             passwordOld=oldPassword.getText().toString();
             passwordNew=newPassword.getText().toString();
             passwordConfirm=confirmPassword.getText().toString();


                if(passwordOld.isEmpty()||passwordNew.isEmpty()||passwordConfirm.isEmpty()){
                    Toast.makeText(getActivity(),"Fill all fields",Toast.LENGTH_SHORT).show();
                }else{

                    if (idTeacher!=0){
                     if (databaseHelper.getCheckPasswordInTeacher(passwordOld,idTeacher)){
                         if (passwordNew.equals(passwordConfirm)){

                             databaseHelper.updatePasswordInTeacher(idTeacher,passwordNew);
                             newPassword.setText("");
                             oldPassword.setText("");
                             confirmPassword.setText("");
                             Toast.makeText(getActivity(),"Successful change password ",Toast.LENGTH_SHORT).show();

                         }else{
                             Toast.makeText(getActivity(),"password not the same",Toast.LENGTH_SHORT).show();

                         }

                     }else{
                         Toast.makeText(getActivity(),"Sorry password is error",Toast.LENGTH_SHORT).show();

                     }


                    }else if (idStudent!=0){
                        if (databaseHelper.getCheckPasswordInStudent(passwordOld,idStudent)){
                            if (passwordNew.equals(passwordConfirm)){
                                databaseHelper.updatePasswordInStudent(idStudent,passwordNew);
                                newPassword.setText("");
                                oldPassword.setText("");
                                confirmPassword.setText("");
                                Toast.makeText(getActivity(),"Successful change password ",Toast.LENGTH_SHORT).show();


                            }else{
                                Toast.makeText(getActivity(),"password not the same",Toast.LENGTH_SHORT).show();

                            }

                        }else{

                            Toast.makeText(getActivity(),"Sorry password is error",Toast.LENGTH_SHORT).show();

                        }}}
            }
        });

        return view;
    }
}