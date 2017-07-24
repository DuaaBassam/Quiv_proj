package net.androidbootcamp.quiv_proj;

import android.app.Activity;
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



public class TeacherStudentListview extends BaseAdapter {
   ArrayList<StudentItems> arrayList;
    Activity con;
    DatabaseHelper db;

    ///
    TeacherStudentListview(Activity con,ArrayList<StudentItems>arrayList1) {
        this.con = con;
        arrayList=arrayList1;
        db = new DatabaseHelper(con);
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
    public View getView( int i ,View view, ViewGroup viewGroup) {
        View row;
        final ViewHolder viewHolder;
        if (view == null) {
            LayoutInflater inflater = con.getLayoutInflater();
            row = inflater.inflate(R.layout.student_show_item, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) row.findViewById(R.id.name_student);
            viewHolder.id = (TextView) row.findViewById(R.id.id_student);
            viewHolder.delete = (Button)row.findViewById(R.id.delStud);
            viewHolder.delete.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //Log.d("idddddd",viewHolder.id.getText().toString());
                    db.deleteData(viewHolder.id.getText().toString());
                    Toast.makeText(con, "delete Student ", Toast.LENGTH_SHORT).show();

                                       }
            });

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
        Button delete;
    }}