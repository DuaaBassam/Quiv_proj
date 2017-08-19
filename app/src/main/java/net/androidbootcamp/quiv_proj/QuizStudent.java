package net.androidbootcamp.quiv_proj;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;


public class QuizStudent extends Fragment {
    int index = 0;
    private static final String FORMAT = "%02d:%02d:%02d";
    DatabaseHelper db;
    HashMap<Integer,String> hashMap = new HashMap();
    HashMap<Integer,String> hashMap1 = new HashMap();

    CountDownTimer timer1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setRetainInstance(true);
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_quiz_student, container, false);
        db = new DatabaseHelper(getActivity());
        final int count = db.getItemsQuestion(getArguments().getString("nameCourse"), getArguments().getString("nameQuiz"));
        TextView no = (TextView) view.findViewById(R.id.noques);
        TextView time = (TextView) view.findViewById(R.id.totaltime);
        final Button back = (Button) view.findViewById(R.id.back);
        final TextView questimee = (TextView) view.findViewById(R.id.questtime);
        final ListView listAnswer = (ListView) view.findViewById(R.id.listAnswer);

        final TextView timer = (TextView) view.findViewById(R.id.timer);
        final int totalTime = db.getTotalTime(getArguments().getString("nameCourse"), getArguments().getString("nameQuiz"));
        no.append(count + "");
        final boolean check = db.checkTime(getArguments().getString("nameQuiz"), getArguments().getString("nameCourse"));
        final Context c = getActivity();

        final ArrayList<String> question = db.getQuestion(getArguments().getString("nameQuiz"), getArguments().getString("nameCourse"));
        final TextView ques = (TextView) view.findViewById(R.id.question);
        final ArrayList<String> answer = db.getQuestionAnswer(getArguments().getString("nameQuiz"), getArguments().getString("nameCourse"));
        String correctans = db.getQuestionAnswerCorrect(1, getArguments().getString("nameQuiz"), getArguments().getString("nameCourse"));

        final String[] corr = correctans.split("~");
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
        } else {
            questimee.setVisibility(View.VISIBLE);
            int quesTime = db.getQuesTime(1, getArguments().getString("nameCourse"), getArguments().getString("nameQuiz"));

            ques.append("                      Time Question : " + quesTime);
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

        final String[] a = (answer.get(0)).split("~");

        if (corr.length == 1) {
            listAnswer.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_singlechoice, a);
            listAnswer.setAdapter(adapter);

        } else {
            listAnswer.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_multichoice, a);
            listAnswer.setAdapter(adapter);
        }

        final Button next = (Button) view.findViewById(R.id.next);
       // listAnswer.setSelection(2);
        next.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {


                if (index < count) {
                    Resources r = getResources();
                    Drawable d = r.getDrawable(R.drawable.ic_redo_black_24dp);
                    next.setBackground(d);
                    if (listAnswer.getChoiceMode() == ListView.CHOICE_MODE_SINGLE) {
                        String choice = (String) listAnswer.getItemAtPosition(listAnswer.getCheckedItemPosition());
                        hashMap.put(index, choice);
                        hashMap1.put(index, listAnswer.getCheckedItemPosition() + "");


                    } else {
                        String answerrr = "";
                        String postion = "";

                        SparseBooleanArray array = listAnswer.getCheckedItemPositions();
                        for (int i = 0; i < listAnswer.getCount(); i++) {
                            boolean choice = array.get(i);
                            if (choice == true) {
                                answerrr += listAnswer.getItemAtPosition(i) + "~";
                                postion += i + "~";
                            }
                        }
                        hashMap.put(index, answerrr);
                        hashMap1.put(index, postion);
                        answerrr = "";
                    }
                    if (index==count-2){
                        next.setBackgroundColor(Color.GREEN);
                    }

                        index++;
                        String correctans = db.getQuestionAnswerCorrect(index + 1, getArguments().getString("nameQuiz"), getArguments().getString("nameCourse"));

                        final String[] corr = correctans.split("~");

                        ques.setText(index + 1 + ". " + question.get(index));

                        if (!check) {
                            questimee.setVisibility(View.VISIBLE);
                            int quesT = db.getQuesTime(index + 1, getArguments().getString("nameCourse"), getArguments().getString("nameQuiz"));
                            ques.append("                      Time Question : " + quesT);
                            timer1.cancel();
                            timer1 = new CountDownTimer(quesT * 60000, 1000) {
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

                        } else {
                            back.setVisibility(View.VISIBLE);
                        }

                        final String[] a = (answer.get(index)).split("~");

                        if (corr.length == 1) {
                            listAnswer.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_singlechoice, a);
                            listAnswer.setAdapter(adapter);
                            if(!((hashMap1.get(index)+"").equals("null"))) {
                                String check1 = hashMap1.get(index);
                                listAnswer.setItemChecked(Integer.parseInt(check1), true);
                            }
                        } else {
                            listAnswer.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_multichoice, a);
                            listAnswer.setAdapter(adapter);
                            if(!((hashMap1.get(index)+"").equals("null"))) {
                                String check1 = hashMap1.get(index);
                                String[] annn = (check1.split("~"));
                                if (annn[0]!=""){
                                    for (int i = 0;i<annn.length;i++) {
                                        listAnswer.setItemChecked(Integer.parseInt(annn[i]), true);
                                    }
                                }
                            }
                            }}else{


                              }


            }
                             });

        back.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {


                Resources r = getResources();
                Drawable d = r.getDrawable(R.drawable.ic_redo_black_24dp);
                next.setBackground(d);
                if (listAnswer.getChoiceMode() == ListView.CHOICE_MODE_SINGLE) {
                    String choice = (String) listAnswer.getItemAtPosition(listAnswer.getCheckedItemPosition());
                    hashMap.put(index, choice);
                    hashMap1.put(index, listAnswer.getCheckedItemPosition()+"");


                }
                else {
                    String answerrr="";
                    String postion="";

                    SparseBooleanArray array =listAnswer.getCheckedItemPositions();
                    for (int i = 0; i<listAnswer.getCount() ; i++){
                        boolean choice = array.get(i);
                        if (choice==true){
                            answerrr+=listAnswer.getItemAtPosition(i)+"~";
                            postion+=i+"~";
                            Log.d("postion",i+"~");
                        }
                    }

                    hashMap.put(index,answerrr);
                    hashMap1.put(index,postion);

                }

                index--;

                String correctans = db.getQuestionAnswerCorrect(index+1, getArguments().getString("nameQuiz"), getArguments().getString("nameCourse"));

                final String[] corr = correctans.split("~");
                  String checkAns = hashMap.get(index);
                  String check = hashMap1.get(index);
                Log.d("check",check+" "+checkAns);

                ques.setText(index+1 + ". " + question.get(index));

                final String[] a = (answer.get(index)).split("~");

                if (corr.length == 1) {
                    listAnswer.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_singlechoice, a);
                    listAnswer.setAdapter(adapter);
                    listAnswer.setItemChecked(Integer.parseInt(check),true);

                } else {

                    listAnswer.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_multichoice, a);
                    listAnswer.setAdapter(adapter);
                     String[] annn = (check.split("~"));
                if (annn[0]!=""){
                    for (int i = 0;i<annn.length;i++) {
                        listAnswer.setItemChecked(Integer.parseInt(annn[i]), true);
                    }
                    }
                }

                if (index==0){
                    back.setVisibility(View.INVISIBLE);
                }
            }
        });

        return view;


    }

}




