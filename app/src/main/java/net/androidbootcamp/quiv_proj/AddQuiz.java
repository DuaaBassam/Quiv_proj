package net.androidbootcamp.quiv_proj;


import android.app.Dialog;
import android.preference.CheckBoxPreference;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;


public class AddQuiz extends Fragment implements FragmentLifecycle {

    DatabaseHelper db;
    private String nameCourse;
    private int idTeach;
    private String answer;
    int indexAns = 0;
    int indexCorrect = 0;
    private String answerStr;
    private String answerCorr;
    HashMap answerQuestion =new HashMap();
    HashMap correctAnswer =new HashMap();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        nameCourse = getArguments().getString("namee");
        idTeach = getArguments().getInt("idTeach");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_quiz, container, false);
        Button Add = (Button) view.findViewById(R.id.addquiz);
        Button Submit = (Button) view.findViewById(R.id.submit);
        final Button addAnswer = (Button) view.findViewById(R.id.addAnswer);
        final EditText nameQuiz = (EditText) view.findViewById(R.id.nameQuiz);
        final EditText password = (EditText) view.findViewById(R.id.password);
        final TextView noQues = (TextView) view.findViewById(R.id.quesNo);
        final EditText question = (EditText) view.findViewById(R.id.question);
        final EditText answer = (EditText) view.findViewById(R.id.answer);
        final RadioButton radioButton1 = (RadioButton) view.findViewById(R.id.radioButton1);
        final RadioButton radioButton2 = (RadioButton) view.findViewById(R.id.radioButton2);
        final CheckBox correct = (CheckBox) view.findViewById(R.id.checkBox2);
        db = new DatabaseHelper(getActivity());

        addAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String answerQues = answer.getText().toString();
                if (!(question.getText().toString()).isEmpty()) {
                    if (!answerQues.isEmpty()) {
                        answerQuestion.put(indexAns,answerQues);
                        indexAns++;
                        answer.setText("");
                        if (correct.isChecked()) {
                            correctAnswer.put(indexCorrect,answerQues);
                            indexCorrect++;
                            correct.setChecked(false);

                        }
                    } else {
                        Toast.makeText(getActivity(), "Write answer.", Toast.LENGTH_SHORT).show();

                    }

                } else {
                    Toast.makeText(getActivity(), "Write question.", Toast.LENGTH_SHORT).show();

                }
            }});
        final Dialog dialog= new Dialog(getActivity());



        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                answerStr=answerQuestion.get(0)+"@#%@#$%";
                for (int i =1;i<indexAns;i++){
                    answerStr=answerStr+(answerQuestion.get(i))+"@#%@#$%";
                }
                System.out.println(answerStr);
                answerCorr=correctAnswer.get(0)+"@#%@#$%";
                for (int i =1;i<indexCorrect;i++){
                    answerCorr+=(correctAnswer.get(i)+"@#%@#$%");
                }


                if (!(nameQuiz.getText().toString()).isEmpty()&&!(password.getText().toString()).isEmpty()
                        &&!correctAnswer.isEmpty()&&!answerQuestion.isEmpty()){

                    int aa= (Integer.parseInt(noQues.getText().toString()));
                    String name= nameQuiz.getText().toString();
                    String ques=question.getText().toString();
                    question.setText("");
                    answer.setText("");

                    noQues.setText((aa+1)+"");
                    db.insertDataQuestion(aa,name,ques,answerStr  ,answerCorr,1);
                }


            }
        });


    Submit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if((!(nameQuiz.getText().toString()).isEmpty())&& (!(password.getText().toString()).isEmpty())){
              if(radioButton1.isChecked()){
                  dialog.setContentView(R.layout.submit);
                  dialog.setTitle("Question");
                  Button btn_dialog = (Button)dialog.findViewById(R.id.cancel_submit);
                  btn_dialog.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View view) {
                          dialog.cancel();
                      }
                  });
                  Button btn_ok = (Button)dialog.findViewById(R.id.ok_submit);

                  btn_ok.setOnClickListener(new View.OnClickListener() {
                      @Override

                      public void onClick(View view) {
                        db.insertDataQuiz(nameQuiz.getText().toString(),Integer.parseInt(password.getText().toString()),
                                idTeach,0);
                          Toast.makeText(getActivity(), "Add Quiz ", Toast.LENGTH_SHORT).show();
                           dialog.cancel();
                          nameQuiz.setText("");
                          password.setText("");
                      }
                  });
                  dialog.show();
              }
                else if(radioButton2.isChecked()){
                  dialog.setContentView(R.layout.dialog);
                  dialog.setTitle("Question");
                  final EditText txt = (EditText)dialog.findViewById(R.id.timeTotalQuiz);
                  Button btn_dialog = (Button)dialog.findViewById(R.id.cancel);
                  btn_dialog.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View view) {
                          dialog.cancel();
                      }
                  });
                  Button btn_ok = (Button)dialog.findViewById(R.id.ok);
                  btn_ok.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View view) {
                           if(!(txt.getText().toString()).isEmpty()){
                              db.insertDataQuiz(nameQuiz.getText().toString(),Integer.parseInt(password.getText().toString()),idTeach
                                      ,Integer.parseInt(txt.getText().toString()));
                          Toast.makeText(getActivity(), "Add Quiz ", Toast.LENGTH_SHORT).show();
                          dialog.cancel();
                          nameQuiz.setText("");
                          password.setText("");
                          }else
                              Toast.makeText(getActivity(), "Add total time  ", Toast.LENGTH_SHORT).show();
                      }
                  });

                  dialog.show();

              }
            }
            else
                Toast.makeText(getActivity(), "Add nameQuiz or password :(", Toast.LENGTH_SHORT).show();


        }});


        return view;
    }

    @Override
    public void onPauseFragment() {

    }

    @Override
    public void onResumeFragment() {

    }

}
