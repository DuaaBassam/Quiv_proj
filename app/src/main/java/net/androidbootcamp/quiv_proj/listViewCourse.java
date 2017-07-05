package net.androidbootcamp.quiv_proj;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by مركز الخبراء on 07/03/2017.
 */

class ListViewCourse extends BaseAdapter {
    ArrayList<Course_Items> arrayList;
    Activity con;

    ListViewCourse(Activity con, int teacherId) {
        this.con = con;
        arrayList = new ArrayList<>();
        ArrayList cursor = new DatabaseHelper(con).getAllDataCourse(teacherId);

        arrayList = cursor;
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
            LayoutInflater inflater = con.getLayoutInflater();
            row = inflater.inflate(R.layout.fra, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) row.findViewById(R.id.name);
            viewHolder.img = (LinearLayout) row.findViewById(R.id.hori);
            viewHolder.comment = (ImageButton) row.findViewById(R.id.Studuent);
            viewHolder.fav = (ImageButton) row.findViewById(R.id.quiz);
            row.setTag(viewHolder);

        } else {
            row = view;
            viewHolder = (ViewHolder) view.getTag();
        }

        final Course_Items item = arrayList.get(i);

        viewHolder.name.setText(item.name);


        viewHolder.fav.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Fragment fragment= new StudentInTeacher();

                Fragment std = new AddStudent();
                Bundle args = new Bundle();
                Bundle s = new Bundle();

                args.putString("nameCourse",viewHolder.name.getText().toString());
                fragment.setArguments(args);

                ((FragmentActivity)con).getSupportFragmentManager().beginTransaction().remove(fragment).addToBackStack(null).commit();
                ((FragmentActivity)con).getSupportFragmentManager().beginTransaction().replace(R.id.frag,fragment).addToBackStack(null).commit();




            }});
        viewHolder.comment.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

            }

        });


        return row;
    }

    public class ViewHolder {
        TextView name;
        LinearLayout img;
        ImageButton fav;
        ImageButton comment;

    }
}