package net.androidbootcamp.quiv_proj;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Hanan Dawod on 10/08/17.
 */

public class ListQuizInStud extends BaseAdapter {
    ArrayList<Course_Items> arrayList;
    Fragment con;
    String courseName = "";
    ListCourse fragment = new ListCourse();
    int idStudent = 0;
    QuizStudent quizStudent = new QuizStudent();
    DatabaseHelper db;

    ListQuizInStud(Fragment con, String name, int idstud) {

        this.con = con;
        arrayList = new ArrayList<>();
        ArrayList cursor = new DatabaseHelper(con.getActivity()).getListQuiz(name);
        arrayList = cursor;
        idStudent = idstud;
        courseName = name;

    }


    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row;

        final ListQuizInStud.ViewHolder viewHolder;

        if (view == null) {

            LayoutInflater inflater = (con.getActivity()).getLayoutInflater();
            row = inflater.inflate(R.layout.item, viewGroup, false);
            viewHolder = new ListQuizInStud.ViewHolder();
            viewHolder.name = (TextView) row.findViewById(R.id.name);
            row.setTag(viewHolder);

        } else {
            row = view;
            viewHolder = (ListQuizInStud.ViewHolder) view.getTag();
        }

        final Course_Items item = arrayList.get(i);

        viewHolder.name.setText(item.name);
        db = new DatabaseHelper(con.getActivity());
        viewHolder.name.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String nameQuiz =viewHolder.name.getText().toString();

                if (db.checkStudentSolveQuiz(idStudent,courseName,nameQuiz)) {

                    Fragment gradeStudent = new GradeStudent();
                    int grade = db.getGradeStudent(idStudent,courseName,nameQuiz);
                    int count = db.getItemsQuestion(courseName,nameQuiz);
                    Bundle args = new Bundle();
                    args.putInt("grade",grade);
                    args.putInt("count",count);
                    args.putString("nameQuiz", viewHolder.name.getText().toString());
                    args.putString("nameCourse", courseName);
                    args.putInt("idStudent", idStudent);
                    gradeStudent.setArguments(args);
                    FragmentTransaction fragmentTransaction = con.getFragmentManager().beginTransaction();
                    fragmentTransaction.remove(con);
                    fragmentTransaction.replace(R.id.studentFrag, gradeStudent);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                }  else {
                    final Dialog dialog = new Dialog(con.getActivity());
                    dialog.setContentView(R.layout.checkdialog);
                    dialog.setTitle("Question");
                    Button btn_dialog = (Button) dialog.findViewById(R.id.cancel);
                    final EditText pass = (EditText) dialog.findViewById(R.id.password);
                    btn_dialog.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.cancel();
                        }
                    });

                    Button btn_ok = (Button) dialog.findViewById(R.id.ok);

                    btn_ok.setOnClickListener(new View.OnClickListener() {
                        @Override

                        public void onClick(View view) {


                            boolean b = db.checkPasswordQuiz(viewHolder.name.getText().toString(), pass.getText().toString());
                            if (b) {

                                Bundle args = new Bundle();
                                args.putString("nameQuiz", viewHolder.name.getText().toString());
                                args.putString("nameCourse", courseName);
                                args.putInt("idStudent", idStudent);
                                quizStudent.setArguments(args);
                                FragmentTransaction fragmentTransaction = con.getFragmentManager().beginTransaction();
                                fragmentTransaction.remove(con);
                                fragmentTransaction.replace(R.id.studentFrag, quizStudent);
                                fragmentTransaction.addToBackStack(null);
                                fragmentTransaction.commit();
                                dialog.cancel();


                            } else
                                Toast.makeText(con.getActivity(), "Password is wrong ", Toast.LENGTH_SHORT).show();
                        }
                    });

                    dialog.show();

                } }});

                 return row;}

               public class ViewHolder {
               TextView name;
    }

}

