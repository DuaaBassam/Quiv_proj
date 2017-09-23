package net.androidbootcamp.quiv_proj;

import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class QuizStudent extends Fragment {

    DatabaseHelper db;

    private static final String FORMAT = "%02d:%02d";
    CountDownTimer timer1;
    long millisUntilFinishedSave;
    long transferValue;

    HashMap<Integer, String> hashMap = new HashMap();
    HashMap<Integer, String> hashMap1 = new HashMap();

    TextView numberQuestion;
    ListView listAnswer;
    TextView questionStatement;
    TextView textTimer;
    Button next;
    Button back;
    ProgressBar progressTime;

    ArrayList<String> questionArray;
    ArrayList<String> answer;

    int numberQues;
    int grade;
    String nameCourse;
    String nameQuiz;
    int idStudent;
    int index;
    int totalTime;
    String correctans;
    boolean checkIfTotalTime;
    String check;

    Context context;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        if (savedInstanceState != null) {

            transferValue = savedInstanceState.getLong("millis");
            Log.d("tra", transferValue + "");
            Log.d("haa", index + "");
                timer1.cancel();
                Log.d("timer1", "tttt");


        }

        nameCourse = getArguments().getString("nameCourse");
        nameQuiz = getArguments().getString("nameQuiz");
        idStudent = getArguments().getInt("idStudent");

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        final View view = inflater.inflate(R.layout.fragment_quiz_student, container, false);

        context = getActivity();
        db = new DatabaseHelper(context);

        back = (Button) view.findViewById(R.id.back);
        numberQuestion = (TextView) view.findViewById(R.id.numberQuestion);
        listAnswer = (ListView) view.findViewById(R.id.listAnswer);
        textTimer = (TextView) view.findViewById(R.id.timer);
        progressTime = (ProgressBar) view.findViewById(R.id.progressTime);
        questionStatement = (TextView) view.findViewById(R.id.question);

        totalTime = db.getTotalTime(nameCourse, nameQuiz);
        numberQues = db.getItemsQuestion(nameCourse, nameQuiz);
        answer = db.getQuestionAnswer(nameQuiz, nameCourse);

        checkIfTotalTime = db.checkTime(nameQuiz, nameCourse);
        correctans = db.getQuestionAnswerCorrect(1, nameQuiz, nameCourse);
        final String[] correctArray = correctans.split("~");

        back.setVisibility(View.INVISIBLE);
        questionArray = db.getQuestion(getArguments().getString("nameQuiz"), getArguments().getString("nameCourse"));
        questionStatement.setText(questionArray.get(index) + "  ?");
        numberQuestion.setText(1 + "/" + numberQues);

        if (checkIfTotalTime) {

            timerForAllQuestion();

        } else {

            timerForOneQuestion();
        }

        getAnswerForQuestion();

        next = (Button) view.findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (index < numberQues - 1) {

                    Resources r = getResources();
                    Drawable d = r.getDrawable(R.drawable.next);
                    next.setBackground(d);
                    putInHashMap();
                    if (index == (numberQues - 2)) {
                        next.setBackgroundColor(Color.GREEN);
                    }
                    index++;
                    getAnswerForQuestion();

                    if (checkIfTotalTime) {
                        back.setVisibility(View.VISIBLE);

                    } else {
                        timerForOneQuestion();
                    }

                } else {

                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Do you want to submit Quiz?");
                    builder.setCancelable(false);
                    builder.setPositiveButton("Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    timer1.cancel();
                                    submit();
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
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                putInHashMap();
                index--;
                getAnswerForQuestion();
                if (index == 0) {
                    back.setVisibility(View.INVISIBLE);
                }
                Resources r = getResources();
                Drawable d = r.getDrawable(R.drawable.next);
                next.setBackground(d);
            }
        });

        return view;
    }

    public void timerForAllQuestion() {


        long different = totalTime - transferValue;

        timer1 = new CountDownTimer(different * 60000, 1000) {

            public void onTick(long millisUntilFinished) {
                millisUntilFinishedSave = millisUntilFinished;
                progressTime.setProgress((int)( TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                textTimer.setText("" + String.format(FORMAT,
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            public void onFinish() {

                submit();

            }
        }.start();

    }


    public void timerForOneQuestion() {

         if(textTimer.getText().toString().isEmpty()){

         }else{

             timer1.cancel();
             }
        try {
            timer1.cancel();
            int questionTime = db.getQuesTime(index + 1, getArguments().getString("nameCourse"), getArguments().getString("nameQuiz"));
            long different = questionTime - transferValue;

            timer1 = new CountDownTimer(different * 60000, 1000) {

                public void onTick(long millisUntilFinished) {

                    millisUntilFinishedSave = millisUntilFinished;
                    progressTime.setProgress((int)( TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                    textTimer.setText("" + String.format(FORMAT,

                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                    TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));

                }

                public void onFinish() {

                    if (index < numberQues -1) {

                        Resources r = getResources();
                        Drawable d = r.getDrawable(R.drawable.next);
                        next.setBackground(d);
                        putInHashMap();
                        if (index == (numberQues - 2)) {
                            next.setBackgroundColor(Color.GREEN);
                        }
                        index++;
                        getAnswerForQuestion();
                        timerForOneQuestion();

                    } else {
                        submit();

                    }
                }
            }.start();
        }catch (Exception e) {


            int questionTime = db.getQuesTime(index + 1, getArguments().getString("nameCourse"), getArguments().getString("nameQuiz"));
            long different = questionTime - transferValue;

            timer1 = new CountDownTimer(different * 60000, 1000) {

                public void onTick(long millisUntilFinished) {

                    millisUntilFinishedSave = millisUntilFinished;
                    progressTime.setProgress((int) (TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                    textTimer.setText("" + String.format(FORMAT,

                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                    TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));

                }

                public void onFinish() {

                    if (index < numberQues - 1) {

                        Resources r = getResources();
                        Drawable d = r.getDrawable(R.drawable.next);
                        next.setBackground(d);
                        putInHashMap();
                        if (index == (numberQues - 2)) {
                            next.setBackgroundColor(Color.GREEN);
                        }
                        index++;
                        getAnswerForQuestion();
                        timerForOneQuestion();

                    } else {
                        submit();

                    }
                }
            }.start();
        }    }


    public void getAnswerForQuestion() {

        String correctans = db.getQuestionAnswerCorrect(index + 1, nameQuiz, nameCourse);
        final String[] corr = correctans.split("~");
        questionStatement.setText("" + questionArray.get(index));
        numberQuestion.setText(index + 1 + "/" + numberQues);

        final String[] answerOneQuestion = (answer.get(index)).split("~");

        check = hashMap1.get(index);
        if (corr.length == 1) {

            listAnswer.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_singlechoice, answerOneQuestion);
            listAnswer.setAdapter(adapter);
            if ((hashMap.get(index) + "").equals("null")) {

            } else {
                listAnswer.setItemChecked(Integer.parseInt(check), true);
            }

        } else {
            listAnswer.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_multichoice, answerOneQuestion);
            listAnswer.setAdapter(adapter);

            if ((hashMap.get(index) + "").equals("null")) {

            } else {
                String[] answerSolved = (check.split("~"));
                if (answerSolved[0] != "") {
                    for (int i = 0; i < answerSolved.length; i++) {
                        listAnswer.setItemChecked(Integer.parseInt(answerSolved[i]), true);
                    }
                }
            }}}


    public void putInHashMap() {

        if (listAnswer.getChoiceMode() == ListView.CHOICE_MODE_SINGLE) {

            String choice = (String) listAnswer.getItemAtPosition(listAnswer.getCheckedItemPosition());
            hashMap.put(index, choice + "");
            hashMap1.put(index, listAnswer.getCheckedItemPosition() + "");

        } else {
            String answerrr = "";
            String postion = "";

            SparseBooleanArray array = listAnswer.getCheckedItemPositions();
            for (int i = 0; i < listAnswer.getCount(); i++) {
                boolean choice = array.get(i);

                if (choice == true) {
                    if (array.size() == 1) {
                        answerrr = listAnswer.getItemAtPosition(i) + "";
                        postion = i + "";
                    } else {
                        answerrr += listAnswer.getItemAtPosition(i) + "~";
                        postion += i + "~";
                        Log.d("postion", i + "~");
                    }
                }
            }

            hashMap.put(index, answerrr);
            hashMap1.put(index, postion);
            answerrr = "";
            postion = "";

        }
    }

    public void gradeStudentFragment() {


        db.insertGradeStudent(nameCourse, nameQuiz, idStudent, grade);
        Fragment gradeStudent = new GradeStudent();
        Bundle args = new Bundle();
        args.putInt("grade", grade);
        args.putInt("count", numberQues);
        args.putInt("idStudent", idStudent);
        args.putString("nameCourse", nameCourse);
        args.putString("nameQuiz", nameQuiz);

        gradeStudent.setArguments(args);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.studentFrag, gradeStudent);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }


    public void submit() {

        timer1.cancel();
        putInHashMap();
        String answers = "";
        String indexAnswer = "";

        for (int i = 0; i < numberQues; i++) {

            if ((hashMap.get(i) + "").equals("null")) {
                answers += " " + "ّ";
                indexAnswer += " " + "ّ";

            } else {
                answers += hashMap.get(i) + "ّ";
                indexAnswer += hashMap1.get(i) + "ّ";
            }

        }

        db.insertStudentAnswer(nameCourse, nameQuiz, idStudent, answers, indexAnswer);

        for (int i = 0; i < numberQues; i++) {
            String correctAnswer = db.getQuestionAnswerCorrect(i + 1, nameQuiz, nameCourse);
            Log.d("correct Answer  ", correctAnswer);
            String studentAns = "";
            if ((hashMap.get(i) + "").equals("null")) {
                answers += " " + "ّ";
                indexAnswer += " " + "ّ";
            } else {
                studentAns = hashMap.get(i) + "";
                Log.d("StudentAns", studentAns);
            }
            if (studentAns.equals(correctAnswer)) {
                grade++;
            }
        }
        Log.d("grade", grade + "");
        gradeStudentFragment();

    }


    public void showNotification(String title, String message ,int time) {
        NotificationCompat.Builder b = new NotificationCompat.Builder(this.getActivity());

        b.setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_done_black_24dp)
                .setTicker("QuizSystem ")
                .setContentTitle(title)
                .setContentText(message)
                .setContentInfo("INFO");

        NotificationManager nm = (NotificationManager) this.getActivity().getSystemService(Context.NOTIFICATION_SERVICE);

        nm.notify(1, b.build());
       // nm.cancel(1);

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        savedInstanceState.putLong("millis", millisUntilFinishedSave);
        savedInstanceState.putString("check", check);
        getFragmentManager().putFragment(savedInstanceState, "fragment", this);

        super.onSaveInstanceState(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        setRetainInstance(true);
        if (savedInstanceState != null) {
            timer1.cancel();
        }
        if (checkIfTotalTime) {
            if (index > 0) {
                back.setVisibility(View.VISIBLE);
            }

            if (index == numberQues + 1) {
                timer1.cancel();
            }
        }

        if (index == (numberQues - 1)) {
            next.setBackgroundColor(Color.GREEN);
        }

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {

                if (keyEvent.getAction() == KeyEvent.ACTION_UP && i == KeyEvent.KEYCODE_BACK) {
                    getActivity().getSupportFragmentManager().popBackStack("git", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    return true;
                }

                return false;
            }
        });
    }

}
