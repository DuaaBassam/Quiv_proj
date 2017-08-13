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
    CheckBox[] checkBox;
    RadioGroup radioGroup;
    DatabaseHelper db;
    HashMap hashMap=new HashMap();
    CountDownTimer timer1;
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
        final TextView questimee = (TextView)view.findViewById(R.id.questtime);
        final TextView timer = (TextView) view.findViewById(R.id.timer);
        final int totalTime = db.getTotalTime(getArguments().getString("nameCourse"), getArguments().getString("nameQuiz"));
        no.append(count + "");
        final boolean check = db.checkTime(getArguments().getString("nameQuiz"), getArguments().getString("nameCourse"));
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
        }else{
            questimee.setVisibility(View.VISIBLE);
            int quesTime = db.getQuesTime(1,getArguments().getString("nameCourse"),getArguments().getString("nameQuiz"));

            ques.append("                      Time Question : "+quesTime);
            questimee.setText("");

            timer1 = new CountDownTimer(quesTime * 60000, 1000) { // adjust the milli seconds here

                public void onTick(long millisUntilFinished) {

                    questimee.setText("" + String.format(FORMAT,
                            TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                    TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                }

                public void onFinish() {
                    questimee.setText("done!");
                }
            }.start();

        }


        checkBox= new CheckBox[a.length];

        for (int i = 0; i < a.length; i++) {

            if (corr.length == 1) {
                radioGroup.setVisibility(View.VISIBLE);
                radioButton = new RadioButton(c);
                radioGroup.addView(radioButton);
                radioButton.setText(a[i]);
                radioButton.setTextSize(20);
            } else {

                checkBox[i] = new CheckBox(c);
                checkBox[i].setText(a[i]);
                checkBox[i].setTextSize(20);
                ll.addView(checkBox[i]);

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
                else{
//                    if(check) {
//                        String[] cc = (answer.get(index)).split("~");
//                        String answerCheckBox = "";
//                        for (int i = 0; i < cc.length; i++) {
//                            if (checkBox[i].isChecked()) {
//                                answerCheckBox += checkBox[i].getText().toString() + "~";
//                            }
//                        }
//                        hashMap.put(index, answerCheckBox);
//                        Toast.makeText(getActivity(), answerCheckBox, Toast.LENGTH_SHORT).show();
                    }







                ll.removeAllViews();
                radioGroup.removeAllViews();
                index++;
                radioGroup.setVisibility(View.INVISIBLE);

                if (count > index) {

                    String correctAnswer = db.getQuestionAnswerCorrect(index + 1, getArguments().getString("nameQuiz"), getArguments().getString("nameCourse"));

                    final String[] correct = (correctAnswer.split("~"));
                    String mmm = "";
                    for(int x = 0;x<correct.length;x++){
                        mmm +=correct[x];
                    }
                    System.out.println(mmm);
                    final String[] corr1 = mmm.split(" ");
                   // System.out.println(Arrays.toString(correct));
                    for (int i = 0; i < corr1.length ; i++){
                        System.out.println(corr1[i]+" "+corr1.length);

                    }
                    ques.setText(index + 1 + ". " + question.get(index));
                    if(!check){
                        questimee.setVisibility(View.VISIBLE);
                        int quesT = db.getQuesTime(index+1,getArguments().getString("nameCourse"),getArguments().getString("nameQuiz"));
                        ques.append("                      Time Question : "+quesT);
                        timer1.cancel();

                        timer1=    new CountDownTimer(quesT * 60000, 1000) {

                            // adjust the milli second
                            public void onTick(long millisUntilFinished) {
                                questimee.setText("");


                                questimee.setText("" + String.format(FORMAT,
                                        TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                                TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                            }

                            public void onFinish() {
                                questimee.setText("done!");

                            }
                        }.start();

                    }

                    final String[] aa = (answer.get(index)).split("~");
                    checkBox= new CheckBox[aa.length];

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
                                checkBox[i]= new CheckBox(c);
                                checkBox[i].setText(aa[i]);
                                checkBox[i].setTextSize(20);
                                ll.addView(checkBox[i]);
                            }
                        }}

                    ll.addView(radioGroup);

                }

                else {

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

                            checkBox[i] = new CheckBox(c);
                            checkBox[i].setText(aa[i]);
                            checkBox[i].setTextSize(20);
                            ll.addView(checkBox[i]);
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





