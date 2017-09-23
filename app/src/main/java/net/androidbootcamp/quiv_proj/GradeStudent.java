package net.androidbootcamp.quiv_proj;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;


public class GradeStudent extends Fragment {

    DatabaseHelper databaseHelper;

    String nameQuiz ;
    String nameCourse;
    int grade;
    int count;
    int idStudent;

    ListView listView;
    TextView rate;
    TextView gradeStudent;
    ProgressBar seekBar;

    ArrayList <ItemQuestionStudent> arrayList ;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        databaseHelper=new DatabaseHelper(getActivity());
        nameCourse =getArguments().getString("nameCourse");
        nameQuiz=getArguments().getString("nameQuiz");
        idStudent =getArguments().getInt("idStudent");
        grade = getArguments().getInt("grade");
        count = getArguments().getInt("count");


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.grade_student, container, false);


        listView =(ListView)view.findViewById(R.id.list);
        gradeStudent=(TextView)view.findViewById(R.id.grade);
        rate =(TextView)view.findViewById(R.id.rate);
        seekBar = (ProgressBar) view.findViewById(R.id.seek);

        final int g =grade*100/count;
        gradeStudent.setText(g+"%");

        if(g>=90){
            rate.setText("Excellent");
            seekBar.getProgressDrawable().setColorFilter(Color.parseColor("#0a7105"),android.graphics.PorterDuff.Mode.SRC_IN);

        }
        else if(g>=80) {
            rate.setText("Very good");
            seekBar.getProgressDrawable().setColorFilter(Color.parseColor("#76f211"),android.graphics.PorterDuff.Mode.SRC_IN);

        }
        else if(g>=70) {
            rate.setText("good");
            seekBar.getProgressDrawable().setColorFilter(Color.parseColor("#c9f211"),android.graphics.PorterDuff.Mode.SRC_IN);
        }
        else if(g>=60){
        rate.setText("Accepted");
        seekBar.getProgressDrawable().setColorFilter(Color.parseColor("#f26f11"),android.graphics.PorterDuff.Mode.SRC_IN);

        }

        else {
            rate.setText("Failed");
            seekBar.getProgressDrawable().setColorFilter(Color.RED,android.graphics.PorterDuff.Mode.SRC_IN);
        }
            seekBar.setProgress(g);

        ArrayList<String>question = databaseHelper.getQuestion(nameQuiz,nameCourse);
        String answerStudent = databaseHelper.getStudentAnswer(nameQuiz,nameCourse,idStudent);
        arrayList= new ArrayList<>();


        String []anStudent = answerStudent.split("ّ");
        Log.d("size",anStudent.length+"");

        for (int i = 0 ; i< question.size() ; i++){

            String correctAnswer = databaseHelper.getQuestionAnswerCorrect(i + 1, nameQuiz, nameCourse);
            arrayList.add(i, new ItemQuestionStudent(question.get(i), anStudent[i], correctAnswer));

        }
        final AdapterAnswer adapterAnswer =new AdapterAnswer(this,arrayList);
        listView.setAdapter(adapterAnswer);

        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(keyEvent.getAction()==KeyEvent.ACTION_UP && i==KeyEvent.KEYCODE_BACK){


                    int x = getFragmentManager().getBackStackEntryCount()-2;
                    for (int j = 0; j < x; j++) {
                        getFragmentManager().popBackStack();
                    }
                    return true;
                }
                return false;
            }});
    }
}