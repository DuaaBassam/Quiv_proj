package net.androidbootcamp.quiv_proj;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;


public class ExpandableListDataPump extends BaseExpandableListAdapter {

    private ArrayList<ArrayList<String>> mGroups;
    private Fragment mContext;
    private ArrayList<String>arrayList;
    private DatabaseHelper databaseHelper ;
    private String nameQuiz;
    private String nameCourse;
    private int idTeach;

    String answerQues = "";
    String correctAns = "";

    ExpandableListView listView;
    Spinner spinnerNameQuiz ;


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
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView,
                             ViewGroup parent) {

        View row ;
        ViewHolder viewHolder ;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) (mContext.getActivity()).getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.title_expandable, null);
            viewHolder = new ViewHolder();
            viewHolder.GroupName = (TextView) row.findViewById(R.id.nameQuizEdit);
            viewHolder.delete = (ImageView) row.findViewById(R.id.deleteQuestionn);
            viewHolder.edit = (ImageView) row.findViewById(R.id.editQuestionn);


            row.setTag(viewHolder);

        } else {
            row = convertView;
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Dialog dialog = new Dialog(mContext.getContext());
        viewHolder.GroupName.setText(arrayList.get(groupPosition));
        databaseHelper=new DatabaseHelper(mContext.getActivity());
        spinnerNameQuiz = (Spinner) mContext.getActivity().findViewById(R.id.quizName);
        listView =(ExpandableListView)mContext.getActivity().findViewById(R.id.exListView) ;


        viewHolder.edit.findViewById(R.id.editQuestionn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (databaseHelper.getNameQuiz(spinnerNameQuiz.getSelectedItem().toString(), nameCourse, idTeach)) {
                    if (databaseHelper.checkTime(spinnerNameQuiz.getSelectedItem().toString(), nameCourse, idTeach)) {
                        dialog.setContentView(R.layout.dialog_edit_question);
                        dialog.setTitle("Question");
                        final EditText statement = (EditText) dialog.findViewById(R.id.statement);
                        final EditText answer = (EditText) dialog.findViewById(R.id.answer);
                        Button addAnswer = (Button) dialog.findViewById(R.id.addanswer1);
                        final CheckBox correct = (CheckBox) dialog.findViewById(R.id.correct);
                        Button cancelQuiz = (Button) dialog.findViewById(R.id.cancelQuiz);
                        cancelQuiz.setOnClickListener(new View.OnClickListener() {
                                                          @Override
                                                          public void onClick(View view) {

                                                              dialog.cancel();

                                                          }
                                                      }
                        );

                        addAnswer.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View view) {
                                if (!((answer.getText().toString()).equals(""))) {
                                    answerQues = answerQues + (answer.getText().toString() + "~");
                                    if (correct.isChecked()) {
                                        if ((answerQues+"").equals("null"))
                                            answerQues="null ";
                                        correctAns += correctAns + (answer.getText().toString() + "~");
                                    }
                                    answer.setText("");
                                    correct.setChecked(false);
                                }
                            }
                        });
                        Button okQuiz = (Button) dialog.findViewById(R.id.okQuiz);
                        okQuiz.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (!((statement.getText().toString()).equals(""))) {
                                    Log.d("idQuestionnnnn", (groupPosition+ 1) + "");
                                    int a = databaseHelper.updateStatementQuestion(groupPosition + 1, spinnerNameQuiz.getSelectedItem().toString(),
                                            statement.getText().toString());
                                    Log.d("update question", "" + a);
                                }

                                if (!(answerQues.equals(""))) {
                                    String ans = databaseHelper.getAnswerforQues(spinnerNameQuiz.getSelectedItem().toString(), nameCourse, groupPosition + 1);
                                    ans = ans + answerQues;
                                    databaseHelper.updateAnswer(groupPosition + 1, spinnerNameQuiz.getSelectedItem().toString(), ans);
                                    if (!(correctAns.equals(""))) {
                                        String corr = databaseHelper.getQuestionAnswerCorrect(groupPosition + 1, spinnerNameQuiz.getSelectedItem().toString(), nameCourse);
                                        corr = corr + correctAns;
                                        databaseHelper.updateCorrectAnswer(groupPosition + 1, spinnerNameQuiz.getSelectedItem().toString(), ans);

                                    }
                                    answerQues = "";
                                    correctAns = "";
                                }
                                dialog.cancel();
                                ss();
                            }
                        });
                        dialog.show();
                    } else {
                        dialog.setContentView(R.layout.dialog_edit_question_time);
                        dialog.setTitle("Question");
                        final EditText statement = (EditText) dialog.findViewById(R.id.statement);
                        final EditText answer = (EditText) dialog.findViewById(R.id.answer);
                        final EditText time = (EditText) dialog.findViewById(R.id.time);
                        Button addAnswer = (Button) dialog.findViewById(R.id.addanswer);
                        final CheckBox correct = (CheckBox) dialog.findViewById(R.id.correct);

                        Button cancelQuiz = (Button) dialog.findViewById(R.id.cancelQuiz);
                        cancelQuiz.setOnClickListener(new View.OnClickListener() {
                                                          @Override
                                                          public void onClick(View view) {
                                                              dialog.cancel();
                                                          }
                                                      }
                        );
                        addAnswer.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View view) {
                                if (!((answer.getText().toString()).equals(""))) {
                                    answerQues = answerQues + (answer.getText().toString() + "~");
                                    if (correct.isChecked()) {
                                        correctAns += correctAns + (answer.getText().toString() + "~");
                                    }
                                    answer.setText("");
                                    correct.setChecked(false);
                                }
                            }
                        });
                        Button okQuiz = (Button) dialog.findViewById(R.id.okQuiz);
                        okQuiz.setOnClickListener(new View.OnClickListener() {
                                                      @Override
                                                      public void onClick(View view) {
                                                          if (!((statement.getText().toString()).equals(""))) {
                                                              Log.d("idQuestionnnnn", (groupPosition + 1) + "");
                                                              int a = databaseHelper.updateStatementQuestion(groupPosition + 1, spinnerNameQuiz.getSelectedItem().toString(),
                                                                      statement.getText().toString());
                                                              Log.d("update question", "" + a);
                                                          }

                                                          if (!(answerQues.equals(""))) {
                                                              String ans = databaseHelper.getAnswerforQues(spinnerNameQuiz.getSelectedItem().toString(), nameCourse,groupPosition + 1);
                                                              ans = ans + answerQues;
                                                              databaseHelper.updateAnswer(groupPosition + 1, spinnerNameQuiz.getSelectedItem().toString(), ans);
                                                              if (!(correctAns.equals(""))) {
                                                                  String corr = databaseHelper.getQuestionAnswerCorrect(groupPosition + 1, spinnerNameQuiz.getSelectedItem().toString(), nameCourse);
                                                                  corr = corr + correctAns;
                                                                  databaseHelper.updateCorrectAnswer(groupPosition + 1, spinnerNameQuiz.getSelectedItem().toString(), ans);

                                                              }
                                                              answerQues = "";
                                                              correctAns = "";
                                                          }
                                                          if (!(time.getText().toString().equals(""))) {
                                                              databaseHelper.updateTimeQuestion(groupPosition + 1, spinnerNameQuiz.getSelectedItem().toString(), Integer.parseInt(time.getText().toString()));
                                                          }

                                                          dialog.cancel();
                                                          ss();


                                                      }
                                                  }
                        );
                        dialog.show();

                    }
                } else
                    Toast.makeText(mContext.getActivity(), "Quiz doesn't existed ", Toast.LENGTH_SHORT).show();

            }});


        viewHolder.delete.findViewById(R.id.deleteQuestionn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(mContext.getActivity(), groupPosition+"", Toast.LENGTH_SHORT).show();

                if (databaseHelper.getNameQuiz(spinnerNameQuiz.getSelectedItem().toString(), nameCourse, idTeach)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext.getActivity());
                    builder.setMessage("Do you want to remove question?");
                    builder.setCancelable(false);
                    builder.setPositiveButton("Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    if (databaseHelper.getItemsQuestion(nameCourse, spinnerNameQuiz.getSelectedItem().toString()) == 1) {
                                        if (databaseHelper.getNameQuiz(spinnerNameQuiz.getSelectedItem().toString(), nameCourse, idTeach)) {
                                            AlertDialog.Builder builder = new AlertDialog.Builder(mContext.getActivity());
                                            builder.setMessage("Do you want to remove Quiz?");
                                            builder.setCancelable(false);
                                            builder.setPositiveButton("Yes",
                                                    new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog, int id) {

                                                            databaseHelper.deleteQuiz(spinnerNameQuiz.getSelectedItem().toString(), nameCourse, idTeach);
                                                            databaseHelper.deleteQues(nameCourse,spinnerNameQuiz.getSelectedItem().toString());

                                                            ss();
                                                            dd();

                                                        }
                                                    });

                                            builder.setNegativeButton("No",
                                                    new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog, int id) {
                                                            dialog.cancel();
                                                        }
                                                    }
                                            );

                                            AlertDialog alertDialog = builder.create();
                                            alertDialog.show();

                                        }

                                    }
                                    int idQues = groupPosition + 1;
                                    String nameQuiz = spinnerNameQuiz.getSelectedItem().toString();
                                    databaseHelper.deleteQues(idQues, nameCourse, nameQuiz);

                                    for (int i = idQues; i <= databaseHelper.getItemsQuestion(nameCourse, nameQuiz); i++) {
                                        int p=   databaseHelper.updateIdQuestion(i, i + 1, nameQuiz, nameCourse);
                                        Log.d("p",p+"");

                                    }


                                    ArrayList<String> listAnswer = databaseHelper.getQuestionAnswer(spinnerNameQuiz.getSelectedItem().toString(), nameCourse);
                                    ArrayList<ArrayList<String>> groups = new ArrayList<ArrayList<String>>();
                                    ArrayList<String> listQuestion = databaseHelper.getQuestion(spinnerNameQuiz.getSelectedItem().toString(), nameCourse);

                                    for (int j = 0; j < listAnswer.size(); j++) {

                                        String[] a = (listAnswer.get(j)).split("~");
                                        ArrayList<String> children = new ArrayList<String>();

                                        for (int h = 0; h < a.length; h++) {
                                            children.add(a[h]);

                                        }

                                        groups.add(children);
                                    }
                                    ExpandableListDataPump adapter = new ExpandableListDataPump(mContext, groups, listQuestion, spinnerNameQuiz.getSelectedItem().toString(), nameCourse, idTeach);
                                    listView.setAdapter(adapter);


                                }
                            }
                    );

                    builder.setNegativeButton("No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            }
                    );

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();


                } else
                    Toast.makeText(mContext.getActivity(), "Quiz doesn't existed", Toast.LENGTH_SHORT).show();


            }
        });





        return row;
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


        String s= databaseHelper.getQuestionAnswerCorrect(groupPosition+1,nameQuiz,nameCourse);
        String[] a =s.split("~");

        for (int i = 0 ; i< a.length ; i++){

            if (textChild.getText().toString().equals(a[i])){
                textChild.setTextColor(Color.GREEN);
                break;

            }else if(!textChild.getText().toString().equals(a[i])){
                textChild.setTextColor(Color.WHITE);
            }
        }


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

//                                String correctFromDatabase =databaseHelper.getQuestionAnswerCorrect(groupPosition+1,nameQuiz,nameCourse);
//                                String []correctArray = correctFromDatabase.split("~");
//                                String correctString="";
//
//                                Log.d("length",correctArray.length+"");
//                                Log.d("corrrr",correctFromDatabase+"");
//
//                                if (correctArray.length!=1){
//                                    Log.d("kkk",correctFromDatabase);
//                                    for (int i = 0 ; i<correctArray.length;i++) {
//                                        Log.d("hhh",child.get(childPosition));
//                                        if ((child.get(childPosition)).equals(correctArray[i])){
//                                        }else{
//                                            Log.d("ddd",correctString);
//                                            correctString+=(correctArray[i]+"~");
//                                        }
//                                    }
//                                }



                                String  answerStr = "";

                                    for (int i = 0; i <child.size(); i++) {
                                        if (i==childPosition){
                                        }
                                        else
                                            answerStr += (child.get(i) + "~");
                                    }


                                final Spinner spinnerNameQuiz = (Spinner) mContext.getActivity().findViewById(R.id.quizName);

                                if (answerStr.equals("")){

                                    databaseHelper.deleteQues(groupPosition+1,nameCourse,nameQuiz);

                                    for (int i = groupPosition+1 ; i<=databaseHelper.getItemsQuestion(nameCourse,nameQuiz) ;i++ ){
                                        databaseHelper.updateIdQuestion(i,i+1,nameQuiz,nameCourse);
                                    }

                                    mGroups.remove(groupPosition);
                                    notifyDataSetChanged();
                                    arrayList.remove(groupPosition);
                                    notifyDataSetChanged();


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

                                    }}else{

                                    databaseHelper.updateAnswer(groupPosition+1,nameQuiz,answerStr);
//                                    databaseHelper.updateCorrectAnswer(groupPosition+1,nameQuiz,correctString);
//                                    Log.d("correctString ",correctString);
//
//                                    if (correctArray.length==1&&correctArray[0].equals(child.get(childPosition))){
//
//                                    }else{
                                        child.remove(childPosition);
                                        notifyDataSetChanged();
                                    //}
                                }
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


    public void ss() {

        ArrayList<String> listAnswer = new DatabaseHelper(mContext.getActivity()).getQuestionAnswer(spinnerNameQuiz.getSelectedItem().toString(), nameCourse);
        ArrayList<ArrayList<String>> groups = new ArrayList<ArrayList<String>>();
        for (int j = 0; j < listAnswer.size(); j++) {

            String[] a = (listAnswer.get(j)).split("~");
            ArrayList<String> children = new ArrayList<String>();

            for (int h = 0; h < a.length; h++) {


                children.add(a[h]);

            }
            groups.add(children);
        }
        ArrayList<String> listQuestion = new DatabaseHelper(mContext.getActivity()).getQuestion(spinnerNameQuiz.getSelectedItem().toString(), nameCourse);

        ExpandableListDataPump adapter = new ExpandableListDataPump(mContext, groups, listQuestion, spinnerNameQuiz.getSelectedItem().toString(), nameCourse, idTeach);
        listView.setAdapter(adapter);

    }
    public void dd() {


        List<String> allNameQuiz = new DatabaseHelper(mContext.getActivity()).getAllNameQuiz(nameCourse, idTeach);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(mContext.getActivity(), android.R.layout.simple_spinner_item, allNameQuiz);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNameQuiz.setAdapter(dataAdapter);

    }


    public class ViewHolder{
        TextView  GroupName;
        ImageView edit;
        ImageView delete;
    }


}
