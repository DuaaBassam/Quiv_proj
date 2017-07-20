package net.androidbootcamp.quiv_proj;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;



public class TeacherStudentListview extends BaseAdapter {
   ArrayList<StudentItems> arrayList;
    Activity con;


    ///
    TeacherStudentListview(Activity con,ArrayList<StudentItems>arrayList1) {
        this.con = con;
        arrayList=arrayList1;

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
            viewHolder.name = (TextView) row.findViewById(R.id.name_student);
            viewHolder.id = (TextView) row.findViewById(R.id.id_student);

            row.setTag(viewHolder);

        } else {
            row = view;
            viewHolder = (ViewHolder) view.getTag();
        }

        final StudentItems item = arrayList.get(i);

        viewHolder.name.setText(item.name);
        viewHolder.id.setText(item.id+"");
        return row;
    }

    public class ViewHolder {
        TextView name;
        TextView id;
    }}