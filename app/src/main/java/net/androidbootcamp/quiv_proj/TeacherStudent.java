package net.androidbootcamp.quiv_proj;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;



public class TeacherStudent extends BaseAdapter {
    ArrayList<Course_Items> arrayList;
    Activity con;

    TeacherStudent(Activity con, int studentId) {
        this.con = con;
        arrayList = new ArrayList<>();
        ArrayList cursor = new DatabaseHelper(con).getAllDataCourse(studentId);

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
            row = inflater.inflate(R.layout.student_show_item, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) row.findViewById(R.id.name);
            viewHolder.img = (LinearLayout) row.findViewById(R.id.hori);

            row.setTag(viewHolder);

        } else {
            row = view;
            viewHolder = (ViewHolder) view.getTag();
        }

        final Course_Items item = arrayList.get(i);


        return row;
    }

    public class ViewHolder {
        TextView name;
        LinearLayout img;


    }}