
package net.androidbootcamp.quiv_proj;

import android.app.Dialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static android.R.layout.simple_spinner_item;

public class AddQuiz extends Fragment implements FragmentLifecycle, AdapterView.OnItemSelectedListener {
    TextView noQues;
    RadioButton radioButton1;
    RadioButton radioButton2;
    DatabaseHelper db;
    private String nameCourse;
    private int idTeach;
    private String answer;
    int indexAns = 0;
    int indexCorrect = 0;
    private String answerStr;
    private String answerCorr;
    HashMap answerQuestion = new HashMap();
    HashMap correctAnswer = new HashMap();
    int indexQues = 0;
    HashMap<Integer, String> no_ques = new HashMap();
    HashMap<Integer, String> ques_quiz = new HashMap();
    HashMap<Integer, String> time_ques = new HashMap();
    HashMap<Integer, String> answer_ques = new HashMap();
    HashMap<Integer, String> correct_ques = new HashMap();
    String number ="";
    boolean state ;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
           setRetainInstance(true);
        if (savedInstanceState!=null ){

            number = savedInstanceState.getString("numberQuestion");
            noQues.setText(number);
            state=savedInstanceState.getBoolean("state");
            radioButton1.setEnabled(state);
            radioButton2.setEnabled(state);

        }
    }

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
        final EditText question = (EditText) view.findViewById(R.id.question);
        final EditText answer = (EditText) view.findViewById(R.id.answer);
        noQues =(TextView) view.findViewById(R.id.quesNo);




        radioButton1 = (RadioButton) view.findViewById(R.id.radioButton1);
        radioButton2 = (RadioButton) view.findViewById(R.id.radioButton2);
        final CheckBox correct = (CheckBox) view.findViewById(R.id.checkBox2);
        db = new DatabaseHelper(getActivity());

        final Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.time_array, simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinner.setAdapter(adapter);

        addAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String answerQues = answer.getText().toString();

          //      if (!(nameQuiz.getText().toString()).isEmpty()&&!(password.getText().toString()).isEmpty() )
                if (!(question.getText().toString()).isEmpty()) {
                    if (!(answerQues.isEmpty())) {
                        Log.d("answerQues",answerQues+"   "+indexAns);
                        answerQuestion.put(indexAns, answerQues);
                        indexAns++;
                        answer.setText("");
                        if (correct.isChecked()) {
                            correctAnswer.put(indexCorrect, answerQues);
                            indexCorrect++;
                            correct.setChecked(false);
                        }
                    } else {
                        Toast.makeText(getActivity(), "Write answer.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Write question.", Toast.LENGTH_SHORT).show();
                }

            }
        });


        final Dialog dialog = new Dialog(getActivity());

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                answerStr = answerQuestion.get(0) + "~";
                for (int i = 1; i < indexAns; i++) {
                    answerStr += (answerQuestion.get(i) + "~");
                    Log.d("answerQuestion",answerStr);


                }

                answerCorr = correctAnswer.get(0) + "~";
                for (int i = 1; i < indexCorrect; i++) {
                    answerCorr += (correctAnswer.get(i) + "~");
                    Log.d("answerCorr",answerCorr);
                }


                if(radioButton1.isChecked()||radioButton2.isChecked()){
                    if (!(nameQuiz.getText().toString()).isEmpty() && !(password.getText().toString()).isEmpty()
                             &&
                            !(question.getText().toString()).isEmpty()) {
                        if (!answerCorr.isEmpty()){
                        if (answerQuestion.size()>1){
                            answerQuestion.clear();
                            correctAnswer.clear();
                            indexAns=0;
                            indexCorrect=0;
                        radioButton1.setEnabled(false);
                        radioButton2.setEnabled(false);

                        int aa = (Integer.parseInt(noQues.getText().toString()));
                        Log.d("aa ", aa + "");
                        String ques = question.getText().toString();
                        Log.d("ques",ques);
                        int time = Integer.parseInt(spinner.getSelectedItem().toString());
                        question.setText("");
                        answer.setText("");
                        no_ques.put(indexQues, aa + "");
                        ques_quiz.put(indexQues, ques);
                        time_ques.put(indexQues, time + "");
                        correct_ques.put(indexQues, answerCorr);
                        answer_ques.put(indexQues, answerStr);
                        indexQues++;
                        noQues.setText((aa + 1) + "");

                    }
                        else{
                            Toast.makeText(getActivity(), "you are write one answer write anther", Toast.LENGTH_SHORT).show();
                        }}else
                            Toast.makeText(getActivity(), "Check one correct answer at least", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(getActivity(), "Fill all fields", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getActivity(), "Choose Time for Quiz Or Time for Question", Toast.LENGTH_SHORT).show();


            }

        });


        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((!(nameQuiz.getText().toString()).isEmpty()) && (!(password.getText().toString()).isEmpty())
                         && !no_ques.isEmpty()
                        && !ques_quiz.isEmpty() && !(noQues.getText().toString().equals("1") )) {
                    if (!(db.getNameQuiz(nameQuiz.getText().toString(), nameCourse, idTeach))) {
                        if (radioButton1.isChecked()) {

                            dialog.setContentView(R.layout.submit);
                            dialog.setTitle("Question");
                            Button btn_dialog = (Button) dialog.findViewById(R.id.cancel_submit);
                            btn_dialog.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    dialog.cancel();
                                }
                            });
                            Button btn_ok = (Button) dialog.findViewById(R.id.ok_submit);

                            btn_ok.setOnClickListener(new View.OnClickListener() {
                                @Override

                                public void onClick(View view) {
                                    db.insertDataQuiz(nameQuiz.getText().toString(), Integer.parseInt(password.getText().toString()),
                                            idTeach, 0, nameCourse);
                                    for (int i = 0; i < indexQues; i++) {
                                        Log.d("data ", Integer.parseInt(no_ques.get(i).toString()) + "    " + nameQuiz.getText().toString() + "     " +
                                                ques_quiz.get(i) + "    " + answer_ques.get(i) + "    " + correct_ques.get(i) + "     " + Integer.parseInt(time_ques.get(i).toString()));
                                        // //db.insertDataQuestion(aa,name,ques,answerStr  ,answerCorr,time);
                                        db.insertDataQuestion(Integer.parseInt(no_ques.get(i).toString()), nameQuiz.getText().toString(), ques_quiz.get(i),
                                                answer_ques.get(i), correct_ques.get(i), Integer.parseInt(time_ques.get(i).toString()), nameCourse);
                                    }

                                    dialog.cancel();
                                    nameQuiz.setText("");
                                    password.setText("");
                                    noQues.setText("1");
                                    indexQues = 0;
                                    indexAns = 0;
                                    no_ques.clear();
                                    ques_quiz.clear();
                                    time_ques.clear();
                                    answer_ques.clear();
                                    correct_ques.clear();
                                    indexCorrect = 0;
                                    radioButton1.setEnabled(true);
                                    radioButton2.setEnabled(true);
                                }
                            });

                            dialog.show();
                        } else if (radioButton2.isChecked()) {
                            dialog.setContentView(R.layout.dialog);
                            dialog.setTitle("Question");
                            final EditText txt = (EditText) dialog.findViewById(R.id.timeTotalQuiz);
                            Button btn_dialog = (Button) dialog.findViewById(R.id.cancel);
                            btn_dialog.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    dialog.cancel();
                                }
                            });
                            Button btn_ok = (Button) dialog.findViewById(R.id.ok);
                            btn_ok.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    if (!(txt.getText().toString()).isEmpty()) {

                                        db.insertDataQuiz(nameQuiz.getText().toString(), Integer.parseInt(password.getText().toString()), idTeach
                                                , Integer.parseInt(txt.getText().toString()), nameCourse);
                                        Toast.makeText(getActivity(), "Add Quiz ", Toast.LENGTH_SHORT).show();

                                        for (int i = 0; i < indexQues; i++) {

                                            db.insertDataQuestion(Integer.parseInt(no_ques.get(i).toString()), nameQuiz.getText().toString(), ques_quiz.get(i),
                                                    answer_ques.get(i), correct_ques.get(i), nameCourse);
                                        }
                                        dialog.cancel();
                                        nameQuiz.setText("");
                                        password.setText("");
                                        noQues.setText("1");
                                        indexQues = 0;
                                        indexAns = 0;
                                        indexCorrect = 0;
                                        radioButton1.setEnabled(true);
                                        radioButton2.setEnabled(true);



                                    } else
                                        Toast.makeText(getActivity(), "Add total time  ", Toast.LENGTH_SHORT).show();
                                }
                            });
                            dialog.show();
                        }}
                else {

                        Toast.makeText(getActivity(), "Name Quiz is existed", Toast.LENGTH_SHORT).show();
                        nameQuiz.setText("");
                        password.setText("");
                        noQues.setText("1");
                        indexQues = 0;
                        indexAns = 0;
                        indexCorrect = 0;
                        radioButton1.setEnabled(true);
                        radioButton2.setEnabled(true);
                    }
                }
                     else
                        Toast.makeText(getActivity(), "Fill all fields :(", Toast.LENGTH_SHORT).show();
                     }
        });


        return view;
    }

    @Override
    public void onPauseFragment() {


    }

    @Override
    public void onResumeFragment() {

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        noQues = (TextView) getView().findViewById(R.id.quesNo);
        savedInstanceState.putString("numberQuestion",noQues.getText().toString());

        if (radioButton1.isEnabled()==true)
            savedInstanceState.putBoolean("state",true);
        else
            savedInstanceState.putBoolean("state",false);

        super.onSaveInstanceState(savedInstanceState);

    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {

        super.onViewStateRestored(savedInstanceState);


    }

}