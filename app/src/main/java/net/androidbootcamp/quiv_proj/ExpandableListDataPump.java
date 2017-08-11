package net.androidbootcamp.quiv_proj;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class ExpandableListDataPump extends BaseExpandableListAdapter {

    private ArrayList<ArrayList<String>> mGroups;
    private Fragment mContext;
    private ArrayList<String>arrayList;
    private DatabaseHelper databaseHelper;
    private String nameQuiz;
    private String nameCourse;
    private int idTeach;


    public  ExpandableListDataPump (Fragment context, ArrayList<ArrayList<String>> groups,ArrayList<String> arrayList1,
                                   String nameQu,String nameCo,int id1){

        mContext = context;
        mGroups = groups;
        arrayList=arrayList1;
        nameQuiz=nameQu;
        nameCourse=nameCo;
        idTeach = id1;

    }


    @Override
    public int getGroupCount() {
        return mGroups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mGroups.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGroups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mGroups.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                             ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) (mContext.getActivity()).getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.title_expandable, null);
        }

        if (isExpanded){
        }
        else{
        }
        TextView textGroup = (TextView) convertView.findViewById(R.id.nameQuizEdit);
        textGroup.setText(arrayList.get(groupPosition));
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent) {
        databaseHelper=new DatabaseHelper(mContext.getActivity());

        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) (mContext.getActivity()).getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custem_edit_quiz, null);

        }

        final TextView textChild = (TextView) convertView.findViewById(R.id.nameQuizE);
        textChild.setText(mGroups.get(groupPosition).get(childPosition));



        ImageButton button = (ImageButton) convertView.findViewById(R.id.deleteQuizEdit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(mContext.getActivity());
                builder.setMessage("Do you want to remove answer?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                List<String> child = mGroups.get(groupPosition);
                                String s= databaseHelper.getQuestionAnswerCorrect(groupPosition+1,nameQuiz,nameCourse);
                                String [] a = s.split("~");

                                String  answerStr = "";
                                for (int i = 0; i <child.size(); i++) {
                                     if (i==childPosition){
                                     }
                                    else
                                    answerStr += (child.get(i) + "~");
                                }
                                String  answerCorr = "";
                                final Spinner spinner = (Spinner) mContext.getActivity().findViewById(R.id.quesName);
                                final Spinner spinnerNameQuiz = (Spinner) mContext.getActivity().findViewById(R.id.quizName);
                                if (answerStr.equals("")){

                                    databaseHelper.deleteQues(arrayList.get(groupPosition),nameCourse,nameQuiz);
                                    mGroups.remove(groupPosition);
                                    notifyDataSetChanged();
                                    arrayList.remove(groupPosition);
                                    notifyDataSetChanged();

                                    List<String> categories = new DatabaseHelper(mContext.getActivity()).getAllQuestion(nameCourse, nameQuiz);
                                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(mContext.getActivity(), android.R.layout.simple_spinner_item, categories);
                                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinner.setAdapter(dataAdapter);

                                    if(mGroups.size()== 0){
                                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext.getActivity());
                                        builder.setMessage("Do you want to remove Quiz?");
                                        builder.setCancelable(false);
                                        builder.setPositiveButton("Yes",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {


                                                        databaseHelper.deleteQuiz(nameQuiz,nameCourse,idTeach);

                                                        List<String> allNameQuiz = new DatabaseHelper(mContext.getActivity()).getAllNameQuiz(nameCourse,idTeach);
                                                        ArrayAdapter<String> ddd = new ArrayAdapter<String>(mContext.getActivity(), android.R.layout.simple_spinner_item, allNameQuiz);
                                                        ddd.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                                        spinnerNameQuiz.setAdapter(ddd);

                                                        List<String> categories = new DatabaseHelper(mContext.getActivity()).getAllQuestion(nameCourse, nameQuiz);
                                                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(mContext.getActivity(), android.R.layout.simple_spinner_item, categories);
                                                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                                        spinner.setAdapter(dataAdapter);

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

                                    }}

                                databaseHelper.updateAnswer(groupPosition+1,nameQuiz,answerStr);
                                answerStr="";
                                child.remove(childPosition);
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
        return convertView;
    }

    public boolean checkCorrect(List child,String correct){
        for(int h = 0;h<child.size(); h++) {
            if (child.get(h)== correct){
            return  true;
            }
        }
          return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;

    }
}
