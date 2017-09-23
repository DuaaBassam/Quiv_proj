package net.androidbootcamp.quiv_proj;

import android.animation.Animator;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by مركز الخبراء on 08/21/2017.
 */

public class AdapterAnswer extends BaseAdapter {
    Fragment con;
 ArrayList <ItemQuestionStudent>arrayList;



    AdapterAnswer(Fragment con,ArrayList<ItemQuestionStudent>arrayList) {
        this.con = con;
        this.arrayList=arrayList;

    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row;

        final AdapterAnswer.ViewHolder viewHolder;
        final HashMap map = new HashMap();
        if (view == null) {

            LayoutInflater inflater = con.getActivity().getLayoutInflater();
            row = inflater.inflate(R.layout.item_question_student, viewGroup, false);

            viewHolder = new AdapterAnswer.ViewHolder();
            viewHolder.question = (TextView) row.findViewById(R.id.questionStatement);
            viewHolder.quesImage = (ImageView) row.findViewById(R.id.trueQuestion);
            viewHolder.studentAnswer = (TextView) row.findViewById(R.id.studentAnswer);
            viewHolder.correctAns = (TextView) row.findViewById(R.id.correctAnswer);

            row.setTag(viewHolder);

        } else {
            row = view;
            viewHolder = (AdapterAnswer.ViewHolder) view.getTag();
        }

        final ItemQuestionStudent item = arrayList.get(i);
        viewHolder.question.setText(item.question);

        String answerStudentString = "";
        String [] answerArray =item.answerStudent.split("~");

        if(answerArray.length==1){
            answerStudentString=item.answerStudent;

        }else{
            for ( int j = 0 ; j < answerArray.length;j++){
                if (j==answerArray.length-1){
                    answerStudentString += answerArray[j];
                }else
                answerStudentString += (answerArray[j]+",");
            }
        }

        viewHolder.studentAnswer.setText(answerStudentString);

        String correctString = "";
        String [] correctArray =item.correct.split("~");

        if(correctArray.length==1){
            correctString=item.correct;

        }else{
            for ( int j = 0 ; j < correctArray.length;j++){
                if (j==correctArray.length-1){
                    correctString += correctArray[j];

                }else
                    correctString += (correctArray[j]+",");
            }
        }


        if (item.correct.equals(item.answerStudent)){
            map.put(item.correct,item.correct);
        }else{
            map.remove(item.correct);
        }

        if (map.containsKey(item.correct)) {

            Resources r =con.getResources();
            Drawable d = r.getDrawable(R.drawable.ic_done_black_24dp);
            viewHolder.quesImage.setBackground(d);
            viewHolder.studentAnswer.setTextColor(Color.GREEN);
            viewHolder.correctAns.setText(" ");

        } else {

            Resources r =con.getResources();
            Drawable d = r.getDrawable(R.drawable.ic_clear_black_24dp);
            viewHolder.quesImage.setBackground(d);
            viewHolder.correctAns.setText("Correct answer:   "+correctString);
            viewHolder.studentAnswer.setTextColor(Color.RED);

        }

        return row;
    }

    public class ViewHolder {
        TextView  question;
        TextView  studentAnswer;
        TextView  correctAns;
        ImageView quesImage;

    }
}

