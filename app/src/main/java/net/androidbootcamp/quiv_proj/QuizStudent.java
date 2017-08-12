package net.androidbootcamp.quiv_proj;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;


public class QuizStudent extends Fragment {
    int index = 0;
    private static final String FORMAT = "%02d:%02d:%02d";
    RadioButton radioButton;
    CheckBox checkBox;
    RadioGroup radioGroup;
    DatabaseHelper db;
    HashMap hashMap=new HashMap();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setRetainInstance(true);
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz_student, container, false);
        db = new DatabaseHelper(getActivity());
        final int count = db.getItemsQuestion(getArguments().getString("nameCourse"), getArguments().getString("nameQuiz"));
        TextView no = (TextView) view.findViewById(R.id.noques);
        TextView time = (TextView) view.findViewById(R.id.totaltime);
        final Button back =(Button)view.findViewById(R.id.back);

        final TextView timer = (TextView) view.findViewById(R.id.timer);
        final int totalTime = db.getTotalTime(getArguments().getString("nameCourse"), getArguments().getString("nameQuiz"));
        no.append(count + "");
        final boolean check = db.checkTime(getArguments().getString("nameQuiz"), getArguments().getString("nameCourse"));

        if (check) {
            time.setVisibility(View.VISIBLE);
            timer.setVisibility(View.VISIBLE);
            time.append(totalTime + "");
            new CountDownTimer(totalTime * 60000, 1000) { // adjust the milli seconds here

                public void onTick(long millisUntilFinished) {

                    timer.setText("" + String.format(FORMAT,
                            TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                    TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                }

                public void onFinish() {
                    timer.setText("done!");
                }
            }.start();
        }

        final Context c = getActivity();
        final LinearLayout ll = (LinearLayout) view.findViewById(R.id.answer);

        final ArrayList<String> question = db.getQuestion(getArguments().getString("nameQuiz"), getArguments().getString("nameCourse"));
        final TextView ques = (TextView) view.findViewById(R.id.question);
        final ArrayList<String> answer = db.getQuestionAnswer(getArguments().getString("nameQuiz"), getArguments().getString("nameCourse"));
        String correctans = db.getQuestionAnswerCorrect(1, getArguments().getString("nameQuiz"), getArguments().getString("nameCourse"));
        final String[] corr = correctans.split("~");
        final String[] a = (answer.get(0)).split("~");
        radioGroup = (RadioGroup) view.findViewById(R.id.group);
        radioGroup.setVisibility(View.GONE);
        back.setVisibility(View.INVISIBLE);
        ques.setText(index + 1 + ". " + question.get(index));

        for (int i = 0; i < a.length; i++) {
            if (corr.length == 1) {
                radioGroup.setVisibility(View.VISIBLE);
                radioButton = new RadioButton(c);
                radioGroup.addView(radioButton);
                radioButton.setText(a[i]);
                radioButton.setTextSize(20);
            } else {
                checkBox = new CheckBox(c);
                checkBox.setText(a[i]);
                checkBox.setTextSize(20);
                ll.addView(checkBox);
            }
        }


        Button next = (Button) view.findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (radioGroup.getVisibility()== View.VISIBLE){

                    int ii = radioGroup.getCheckedRadioButtonId();
                    View radio = radioGroup.findViewById(ii);
                    int m = radioGroup.indexOfChild(radio);


                    if (m>=0){
                        RadioButton r = (RadioButton)radioGroup.getChildAt(m);
                    hashMap.put(index,r.getText().toString());

                }
                }
                else {
                    for (int i= 0;i<ll.getChildCount();i++){
                        View radio = ll.findViewById(i);
                        int m = ll.indexOfChild(radio);
                        System.out.println(m+"");
                            CheckBox r = (CheckBox) ll.getChildAt(m);
//                            Toast.makeText(getActivity(),""+r.getText().toString(),Toast.LENGTH_SHORT).show();

                             //   hashMap.put(index, r.getText().toString());
                    }
                     }

                ll.removeAllViews();
                radioGroup.removeAllViews();
                index++;
                radioGroup.setVisibility(View.INVISIBLE);
                 if (check){
                     back.setVisibility(View.VISIBLE);
                 }
                                 if (count > index) {
                    String corectAnswer = db.getQuestionAnswerCorrect(index + 1, getArguments().getString("nameQuiz"), getArguments().getString("nameCourse"));
                    final String[] correct = corectAnswer.split("~");

                    ques.setText(index + 1 + ". " + question.get(index));

                    final String[] aa = (answer.get(index)).split("~");
                    for (int i = 0; i < aa.length; i++) {
                        if ((aa[i] + "").equals(null + "")) {
                        } else {
                            if (correct.length == 1) {

                                radioGroup.setVisibility(View.VISIBLE);
                                radioButton = new RadioButton(c);
                                radioGroup.addView(radioButton);
                                radioButton.setText(aa[i]);
                                radioButton.setTextSize(20);

                            } else {
                                checkBox = new CheckBox(c);
                                checkBox.setText(aa[i]);
                                checkBox.setTextSize(20);
                                ll.addView(checkBox);
                            }

                            //Toast.makeText(getActivity(),""+checkBox.isChecked(),Toast.LENGTH_SHORT).show();

                        }
                    }

                    ll.addView(radioGroup);

                } else {
                    Toast.makeText(getActivity(), "submit", Toast.LENGTH_SHORT).show();
                }
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ll.removeAllViews();
                radioGroup.removeAllViews();
                index--;

                    String corectAnswer = db.getQuestionAnswerCorrect(index + 1, getArguments().getString("nameQuiz"), getArguments().getString("nameCourse"));
                    final String[] correct = corectAnswer.split("~");

                    ques.setText(index+1  + ". " + question.get(index));

                    final String[] aa = (answer.get(index)).split("~");
                    for (int i = 0; i < aa.length; i++) {
                        if ((aa[i] + "").equals(null + "")) {
                        } else {
                            if (correct.length == 1) {

                                radioGroup.setVisibility(View.VISIBLE);
                                radioButton = new RadioButton(c);
                                radioGroup.addView(radioButton);
                                radioButton.setText(aa[i]);
                                radioButton.setTextSize(20);

                            } else {

                                checkBox = new CheckBox(c);
                                checkBox.setText(aa[i]);
                                checkBox.setTextSize(20);
                                ll.addView(checkBox);
                            }
                        }
                    }
                ll.addView(radioGroup);

if (index==0){
    back.setVisibility(View.INVISIBLE);
}


            }
        });

        return view;

    }
}
