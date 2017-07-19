package net.androidbootcamp.quiv_proj;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;


 public  class ListViewCourse extends BaseAdapter {
    ArrayList<Course_Items> arrayList;
    Fragment con;
    int tea=0;
    Fragment fragment = new StudentInTeacher();


    ListViewCourse(Fragment con, int teacherId) {
        this.con = con;
        arrayList = new ArrayList<>();
        ArrayList cursor = new DatabaseHelper(con.getActivity()).getAllDataCourse(teacherId);
        arrayList = cursor;
        tea=teacherId;
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

    final ViewHolder viewHolder;

          if (view == null) {

            LayoutInflater inflater = con.getActivity().getLayoutInflater();
            row = inflater.inflate(R.layout.fra, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) row.findViewById(R.id.name);
            viewHolder.linearLayout = (LinearLayout) row.findViewById(R.id.hori);
            viewHolder.Studuent = (ImageButton) row.findViewById(R.id.Studuent);
            viewHolder.quiz = (ImageButton) row.findViewById(R.id.quiz);
            row.setTag(viewHolder);

        } else {
            row = view;
            viewHolder = (ViewHolder) view.getTag();
        }

        final Course_Items item = arrayList.get(i);

        viewHolder.name.setText(item.name);


        viewHolder.Studuent.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Bundle args = new Bundle();
                args.putString("nameCourse", viewHolder.name.getText().toString());
                args.putInt("idTeach",tea);
                fragment.setArguments(args);
                Log.d("name0",fragment.getArguments().getString("nameCourse")+"");
                FragmentTransaction fragmentTransaction =  con.getFragmentManager().beginTransaction();
                fragmentTransaction.remove(con);
                fragmentTransaction.replace(R.id.teacherFrag, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();


            }
        });
        viewHolder.quiz.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

            }
        });

        return row;
    }

    public class ViewHolder {
        TextView name;
        LinearLayout linearLayout;
        ImageButton quiz;
        ImageButton Studuent;
    }

}

