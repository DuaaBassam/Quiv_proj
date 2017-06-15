package net.androidbootcamp.quiv_proj;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import net.androidbootcamp.quiv_proj.R;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;


public class Login_frag extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login_frag, container, false);
        EditText number = (EditText) view.findViewById(R.id.number_person);
        EditText password = (EditText) view.findViewById(R.id.password);
        TextView name_teacher = (TextView) view.findViewById(R.id.name_teacher);
        Button btn_ok =(Button)view.findViewById(R.id.button);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

           //     getFragmentManager().beginTransaction().replace(R.id.fragmentt,new teacher_frag()).commit();
              /*  Fragment fragment = new teacher_frag();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();*/

            }
        });
        return view;

    }



}
