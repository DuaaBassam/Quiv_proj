package net.androidbootcamp.quiv_proj;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Hanan Dawod on 05/08/17.
 */

public class ListViewCourseInStudent extends BaseAdapter {
    ArrayList<Course_Items> arrayList;
    Fragment con;
    int stud;
    ListQuiz fragment = new ListQuiz();


    ListViewCourseInStudent(Fragment con, int StudentId) {
        this.con = con;
        arrayList = new ArrayList<>();
        ArrayList cursor = new DatabaseHelper(con.getActivity()).getListCourse(StudentId);
        arrayList = cursor;
        stud=StudentId;
        Log.d("size",stud+"jh");

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

        final ListViewCourseInStudent.ViewHolder viewHolder;

        if (view == null) {

            LayoutInflater inflater = con.getActivity().getLayoutInflater();
            row = inflater.inflate(R.layout.item, viewGroup, false);
            viewHolder = new ListViewCourseInStudent.ViewHolder();
            viewHolder.name = (TextView) row.findViewById(R.id.name);
                 row.setTag(viewHolder);

        } else {
            row = view;
            viewHolder = (ListViewCourseInStudent.ViewHolder) view.getTag();
        }

        final Course_Items item = arrayList.get(i);

        viewHolder.name.setText(item.name);
        viewHolder.name.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Bundle args = new Bundle();
                args.putString("nameCourse", viewHolder.name.getText().toString());
                args.putInt("idStudent",stud);
                fragment.setArguments(args);
                Log.d("gggg",fragment.getArguments().getInt("idStudent")+"");

                FragmentTransaction fragmentTransaction =  con.getFragmentManager().beginTransaction();
                fragmentTransaction.remove(con);
                fragmentTransaction.replace(R.id.studentFrag, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });


        return row;
    }

    public class ViewHolder {
        TextView name;
    }

}
