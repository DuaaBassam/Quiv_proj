package net.androidbootcamp.quiv_proj;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TeacherStudentListview extends BaseAdapter {

     ArrayList<StudentItems> arrayList;
     Activity con;
     DatabaseHelper db;
     String nameCourse ;
     int idTeacher;

    TeacherStudentListview(Activity con,ArrayList<StudentItems>arrayList1,String nameCoursee,int idTeach) {

        this.con = con;
        arrayList=arrayList1;
        db = new DatabaseHelper(con);
        nameCourse=nameCoursee;
        idTeacher=idTeach;

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
    public View getView(final int i , View view, ViewGroup viewGroup) {
        View row;
        final ViewHolder viewHolder;
        if (view == null) {

            LayoutInflater inflater = con.getLayoutInflater();
            row = inflater.inflate(R.layout.student_show_item, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) row.findViewById(R.id.name_student);
            viewHolder.id = (TextView) row.findViewById(R.id.id_student);
            viewHolder.delete = (Button)row.findViewById(R.id.delStud);

            row.setTag(viewHolder);

        } else {
            row = view;
            viewHolder = (ViewHolder) view.getTag();
        }

        final StudentItems item = arrayList.get(i);
        viewHolder.name.setText(item.name);
        viewHolder.id.setText(item.id+"");

        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(con);
                builder.setMessage("Do you want to remove?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                db.deleteData(viewHolder.id.getText().toString(),nameCourse,idTeacher+"");
                                db.deleteStudentFromAnswerStudent(viewHolder.id.getText().toString(),nameCourse);
                                db.deleteStudentFromGradeStudent(viewHolder.id.getText().toString(),nameCourse);
                                arrayList.remove(i);
                                notifyDataSetChanged();

                            }
                        });
                builder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        return row;
    }

    public class ViewHolder {

        TextView name;
        TextView id;
        Button delete;

    }}