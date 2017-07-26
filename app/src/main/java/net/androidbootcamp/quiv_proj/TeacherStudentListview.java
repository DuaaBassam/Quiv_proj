package net.androidbootcamp.quiv_proj;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class TeacherStudentListview extends BaseAdapter {
   ArrayList<StudentItems> arrayList;
    Activity con;
    DatabaseHelper db;
    
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
        } else {
            row = view;
            viewHolder = (ViewHolder) view.getTag();
        }

        final StudentItems item = arrayList.get(i);

        viewHolder.name.setText(item.name);
        viewHolder.id.setText(item.id+"");
        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                db.deleteData(viewHolder.id.getText().toString());
//                Log.d("hhhhh  ",i + "   "+viewHolder.name.getText().toString()+"     "+viewHolder.id.getText().toString());
//             deleteItem(i);
                AlertDialog.Builder builder = new AlertDialog.Builder(con);
                builder.setMessage("Do you want to remove?");
                builder.setCancelable(false);
               // db.deleteData(viewHolder.id.getText().toString());

                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                db.deleteData(viewHolder.id.getText().toString());
                              //  notifyDataSetChanged();
                                //new ShowStudent().onPauseFragment();
                                dialog.cancel();
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


    public void deleteItem (int position) {
        arrayList.remove(position);
          notifyDataSetChanged();
    }

public class ViewHolder {
        TextView name;
        TextView id;
        Button delete;

    }
}