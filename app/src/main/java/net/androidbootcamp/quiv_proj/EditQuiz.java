package net.androidbootcamp.quiv_proj;


import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.database.DataSetObserver;
import android.os.Bundle;
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
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class EditQuiz extends Fragment implements FragmentLifecycle, ActionBar.OnNavigationListener {

    String answerQues = "";
    String correctAns = "";
   // Spinner spinner;
    Spinner spinnerNameQuiz;
    private String answerStr;
    private String answerCorr;
    HashMap answerQuestion = new HashMap();
    HashMap correctAnswer = new HashMap();
    int indexAns = 0;
    int indexCorrect = 0;
    String nameCourse;
    int idTeach;
    ExpandableListView listView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        nameCourse = getArguments().getString("namee");
        idTeach = getArguments().getInt("idTeach");
        setRetainInstance(true);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
        final View view = inflater.inflate(R.layout.edit_quiz, container, false);


        listView = (ExpandableListView) view.findViewById(R.id.exListView);


      //  spinner = (Spinner) view.findViewById(R.id.quesName);
        spinnerNameQuiz = (Spinner) view.findViewById(R.id.quizName);

        List<String> allNameQuiz = new DatabaseHelper(getActivity()).getAllNameQuiz(nameCourse, idTeach);
        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, allNameQuiz);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNameQuiz.setAdapter(dataAdapter);

        spinnerNameQuiz.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String nameQuiz = adapterView.getItemAtPosition(i).toString();

                ArrayList<String> listQuestion = databaseHelper.getQuestion(nameQuiz, nameCourse);

//                List<String> categories = databaseHelper.getAllQuestion(nameCourse, nameQuiz);
//                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories);
//                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                spinner.setAdapter(dataAdapter);

                ArrayList<String> listAnswer = databaseHelper.getQuestionAnswer(nameQuiz, nameCourse);
                ArrayList<ArrayList<String>> groups = new ArrayList<ArrayList<String>>();

                for (int j = 0; j < listAnswer.size(); j++) {
                    Log.d("listAnswerklf",listAnswer.get(j));

                    String[] a = (listAnswer.get(j)).split("~");
                    ArrayList<String> children = new ArrayList<String>();

                    for (int h = 0; h < a.length; h++) {

                        children.add(a[h]);

                    }
                    groups.add(children);
                }

                ExpandableListDataPump adapter = new ExpandableListDataPump(EditQuiz.this, groups, listQuestion, spinnerNameQuiz.getSelectedItem().toString(), nameCourse, idTeach);
                listView.setAdapter(adapter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }

        });


        final Dialog dialog = new Dialog(getActivity());

        view.findViewById(R.id.editQuiz).setOnClickListener(new View.OnClickListener() {
                                                                @Override
                                                                public void onClick(View view) {
                                                                    if (databaseHelper.getNameQuiz(spinnerNameQuiz.getSelectedItem().toString(), nameCourse, idTeach)) {
                                                                        if (databaseHelper.checkTime(spinnerNameQuiz.getSelectedItem().toString(), nameCourse, idTeach)) {
                                                                            dialog.setContentView(R.layout.edit_quiz_dialog);
                                                                            dialog.setTitle("Question");
                                                                            final EditText passwordQuiz = (EditText) dialog.findViewById(R.id.passwordQuiz);
                                                                            final EditText timeQuiz = (EditText) dialog.findViewById(R.id.timeQuiz);
                                                                            Button cancelQuiz = (Button) dialog.findViewById(R.id.cancelQuiz);
                                                                            cancelQuiz.setOnClickListener(new View.OnClickListener() {

                                                                                @Override
                                                                                public void onClick(View view) {
                                                                                    dialog.cancel();
                                                                                }
                                                                            });
                                                                            Button okQuiz = (Button) dialog.findViewById(R.id.okQuiz);
                                                                            okQuiz.setOnClickListener(new View.OnClickListener() {
                                                                                                          @Override
                                                                                                          public void onClick(View view) {
                                                                                                              if (!((passwordQuiz.getText().toString()).equals(""))) {
                                                                                                                  int a = databaseHelper.updatePasswordQuiz(nameCourse, spinnerNameQuiz.getSelectedItem().toString(),
                                                                                                                          Integer.parseInt(passwordQuiz.getText().toString()), idTeach);
                                                                                                                  Log.d("update password", "" + a);
                                                                                                              }
                                                                                                              if (!((timeQuiz.getText().toString()).equals(""))) {
                                                                                                                  int n = databaseHelper.updateTimeQuiz(nameCourse, spinnerNameQuiz.getSelectedItem().toString(),
                                                                                                                          Integer.parseInt(timeQuiz.getText().toString()), idTeach);
                                                                                                                  Log.d("update time", "" + n);
                                                                                                              }
                                                                                                              dialog.cancel();
                                                                                                          }
                                                                                                      }
                                                                            );
                                                                            dialog.show();
                                                                        } else {
                                                                            dialog.setContentView(R.layout.editpassworddialog);
                                                                            dialog.setTitle("Question");
                                                                            final EditText passwordQuiz = (EditText) dialog.findViewById(R.id.passwordques);
                                                                            Button cancelQuiz = (Button) dialog.findViewById(R.id.cancelpass);
                                                                            cancelQuiz.setOnClickListener(new View.OnClickListener() {
                                                                                                              @Override
                                                                                                              public void onClick(View view) {
                                                                                                                  dialog.cancel();
                                                                                                              }
                                                                                                          }
                                                                            );
                                                                            Button okQuiz = (Button) dialog.findViewById(R.id.okpass);
                                                                            okQuiz.setOnClickListener(new View.OnClickListener() {
                                                                                                          @Override
                                                                                                          public void onClick(View view) {
                                                                                                              if (!((passwordQuiz.getText().toString()).equals(""))) {
                                                                                                                  int a = databaseHelper.updatePasswordQuiz(nameCourse, spinnerNameQuiz.getSelectedItem().toString(),
                                                                                                                          Integer.parseInt(passwordQuiz.getText().toString()), idTeach);
                                                                                                                  Log.d("update password", "" + a);
                                                                                                              } else
                                                                                                                  Toast.makeText(getActivity(), "Add new password", Toast.LENGTH_LONG).show();
                                                                                                              dialog.cancel();
                                                                                                          }
                                                                                                      }
                                                                            );
                                                                            dialog.show();
                                                                        }

                                                                    } else
                                                                        Toast.makeText(getActivity(), "Quiz doesn't existed ", Toast.LENGTH_SHORT).show();


                                                                }
                                                            }
        );

//        view.findViewById(R.id.deleteQuestion).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                if (databaseHelper.getNameQuiz(spinnerNameQuiz.getSelectedItem().toString(), nameCourse, idTeach)) {
//                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                    builder.setMessage("Do you want to remove question?");
//                    builder.setCancelable(false);
//                    builder.setPositiveButton("Yes",
//                            new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int id) {
//                                    if (databaseHelper.getItemsQuestion(nameCourse, spinnerNameQuiz.getSelectedItem().toString()) == 1) {
//                                        if (databaseHelper.getNameQuiz(spinnerNameQuiz.getSelectedItem().toString(), nameCourse, idTeach)) {
//                                            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                                            builder.setMessage("Do you want to remove Quiz?");
//                                            builder.setCancelable(false);
//                                            builder.setPositiveButton("Yes",
//                                                    new DialogInterface.OnClickListener() {
//                                                        public void onClick(DialogInterface dialog, int id) {
//
//                                                            databaseHelper.deleteQuiz(spinnerNameQuiz.getSelectedItem().toString(), nameCourse, idTeach);
//                                                            databaseHelper.deleteQues(nameCourse,spinnerNameQuiz.getSelectedItem().toString());
//                                                            List<String> categories = databaseHelper.getAllQuestion(nameCourse, spinnerNameQuiz.getSelectedItem().toString());
//                                                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories);
//
//                                                            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//                                                            spinner.setAdapter(dataAdapter);
//                                                            ss();
//                                                            setSpinner();
//                                                            dd();
//
//                                                        }
//                                                    });
//
//                                            builder.setNegativeButton("No",
//                                                    new DialogInterface.OnClickListener() {
//                                                        public void onClick(DialogInterface dialog, int id) {
//                                                            dialog.cancel();
//                                                        }
//                                                    }
//                                            );
//
//                                            AlertDialog alertDialog = builder.create();
//                                            alertDialog.show();
//
//                                        }
//
//                                    }
//                                    int idQues = (int) spinner.getSelectedItemId() + 1;
//                                    String nameQuiz = spinnerNameQuiz.getSelectedItem().toString();
//                                    databaseHelper.deleteQues(idQues, nameCourse, nameQuiz);
//
//                                    for (int i = idQues; i <= databaseHelper.getItemsQuestion(nameCourse, nameQuiz); i++) {
//                                     int p=   databaseHelper.updateIdQuestion(i, i + 1, nameQuiz, nameCourse);
//                                        Log.d("p",p+"");
//
//                                    }
//
//                                    List<String> categories = databaseHelper.getAllQuestion(nameCourse, spinnerNameQuiz.getSelectedItem().toString());
//                                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories);
//
//                                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//                                    spinner.setAdapter(dataAdapter);
//                                    ArrayList<String> listAnswer = databaseHelper.getQuestionAnswer(spinnerNameQuiz.getSelectedItem().toString(), nameCourse);
//                                    ArrayList<ArrayList<String>> groups = new ArrayList<ArrayList<String>>();
//                                    ArrayList<String> listQuestion = databaseHelper.getQuestion(spinnerNameQuiz.getSelectedItem().toString(), nameCourse);
//
//                                    for (int j = 0; j < listAnswer.size(); j++) {
//
//                                        String[] a = (listAnswer.get(j)).split("~");
//                                        ArrayList<String> children = new ArrayList<String>();
//
//                                        for (int h = 0; h < a.length; h++) {
//                                            children.add(a[h]);
//
//                                        }
//
//                                        groups.add(children);
//                                    }
//                                    ExpandableListDataPump adapter = new ExpandableListDataPump(EditQuiz.this, groups, listQuestion, spinnerNameQuiz.getSelectedItem().toString(), nameCourse, idTeach);
//                                    listView.setAdapter(adapter);
//
//
//                                }
//                            }
//                    );
//
//                    builder.setNegativeButton("No",
//                            new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int id) {
//                                    dialog.cancel();
//                                }
//                            }
//                    );
//
//                    AlertDialog alertDialog = builder.create();
//                    alertDialog.show();
//
//
//                } else
//                    Toast.makeText(getActivity(), "Quiz doesn't existed", Toast.LENGTH_SHORT).show();
//
//
//            }
//        });
        view.findViewById(R.id.deleteQuiz).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (databaseHelper.getNameQuiz(spinnerNameQuiz.getSelectedItem().toString(), nameCourse, idTeach)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Do you want to remove Quiz?");
                    builder.setCancelable(false);
                    builder.setPositiveButton("Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    databaseHelper.deleteQuiz(spinnerNameQuiz.getSelectedItem().toString(), nameCourse, idTeach);
                                    databaseHelper.deleteQues(nameCourse,spinnerNameQuiz.getSelectedItem().toString());

                                    List<String> allNameQuiz = databaseHelper.getAllNameQuiz(nameCourse, idTeach);
                                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, allNameQuiz);
                                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnerNameQuiz.setAdapter(dataAdapter);

                                    listView.setAdapter(new ExpandableListAdapter() {
                                        @Override
                                        public void registerDataSetObserver(DataSetObserver dataSetObserver) {

                                        }

                                        @Override
                                        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

                                        }

                                        @Override
                                        public int getGroupCount() {
                                            return 0;
                                        }

                                        @Override
                                        public int getChildrenCount(int i) {
                                            return 0;
                                        }

                                        @Override
                                        public Object getGroup(int i) {
                                            return null;
                                        }

                                        @Override
                                        public Object getChild(int i, int i1) {
                                            return null;
                                        }

                                        @Override
                                        public long getGroupId(int i) {
                                            return 0;
                                        }

                                        @Override
                                        public long getChildId(int i, int i1) {
                                            return 0;
                                        }

                                        @Override
                                        public boolean hasStableIds() {
                                            return false;
                                        }

                                        @Override
                                        public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
                                            return null;
                                        }

                                        @Override
                                        public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
                                            return null;
                                        }

                                        @Override
                                        public boolean isChildSelectable(int i, int i1) {
                                            return false;
                                        }

                                        @Override
                                        public boolean areAllItemsEnabled() {
                                            return false;
                                        }

                                        @Override
                                        public boolean isEmpty() {
                                            return false;
                                        }

                                        @Override
                                        public void onGroupExpanded(int i) {

                                        }

                                        @Override
                                        public void onGroupCollapsed(int i) {

                                        }

                                        @Override
                                        public long getCombinedChildId(long l, long l1) {
                                            return 0;
                                        }

                                        @Override
                                        public long getCombinedGroupId(long l) {
                                            return 0;
                                        }
                                    });
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


                } else
                    Toast.makeText(getActivity(), "Quiz doesn't existed", Toast.LENGTH_SHORT).show();


            }
        });


//        view.findViewById(R.id.editQuestion).setOnClickListener(new View.OnClickListener() {
//                                                                    @Override
//                                                                    public void onClick(View view) {
//                                                                        if (databaseHelper.getNameQuiz(spinnerNameQuiz.getSelectedItem().toString(), nameCourse, idTeach)) {
//                                                                            if (databaseHelper.checkTime(spinnerNameQuiz.getSelectedItem().toString(), nameCourse, idTeach)) {
//                                                                                dialog.setContentView(R.layout.dialog_edit_question);
//                                                                                dialog.setTitle("Question");
//                                                                                final EditText statement = (EditText) dialog.findViewById(R.id.statement);
//                                                                                final EditText answer = (EditText) dialog.findViewById(R.id.answer);
//                                                                                Button addAnswer = (Button) dialog.findViewById(R.id.addanswer1);
//                                                                                final CheckBox correct = (CheckBox) dialog.findViewById(R.id.correct);
//                                                                                Button cancelQuiz = (Button) dialog.findViewById(R.id.cancelQuiz);
//                                                                                cancelQuiz.setOnClickListener(new View.OnClickListener() {
//                                                                                                                  @Override
//                                                                                                                  public void onClick(View view) {
//                                                                                                                      dialog.cancel();
//                                                                                                                  }
//                                                                                                              }
//                                                                                );
//
//                                                                                addAnswer.setOnClickListener(new View.OnClickListener() {
//
//                                                                                    @Override
//                                                                                    public void onClick(View view) {
//                                                                                        if (!((answer.getText().toString()).equals(""))) {
//                                                                                            answerQues = answerQues + (answer.getText().toString() + "~");
//                                                                                            if (correct.isChecked()) {
//                                                                                                if ((answerQues+"").equals("null"))
//                                                                                                     answerQues="null ";
//                                                                                                correctAns += correctAns + (answer.getText().toString() + "~");
//                                                                                            }
//                                                                                            answer.setText("");
//                                                                                            correct.setChecked(false);
//                                                                                        }
//                                                                                    }
//                                                                                });
//                                                                                Button okQuiz = (Button) dialog.findViewById(R.id.okQuiz);
//                                                                                okQuiz.setOnClickListener(new View.OnClickListener() {
//                                                                                    @Override
//                                                                                    public void onClick(View view) {
//                                                                                        if (!((statement.getText().toString()).equals(""))) {
//                                                                                            Log.d("idQuestionnnnn", ((int) spinner.getSelectedItemId() + 1) + "");
//                                                                                            int a = databaseHelper.updateStatementQuestion((int) spinner.getSelectedItemId() + 1, spinnerNameQuiz.getSelectedItem().toString(),
//                                                                                                    statement.getText().toString());
//                                                                                            Log.d("update question", "" + a);
//                                                                                        }
//
//                                                                                        if (!(answerQues.equals(""))) {
//                                                                                            String ans = databaseHelper.getAnswerforQues(spinnerNameQuiz.getSelectedItem().toString(), nameCourse, (int) spinner.getSelectedItemId() + 1);
//                                                                                            ans = ans + answerQues;
//                                                                                            databaseHelper.updateAnswer((int) spinner.getSelectedItemId() + 1, spinnerNameQuiz.getSelectedItem().toString(), ans);
//                                                                                            if (!(correctAns.equals(""))) {
//                                                                                                String corr = databaseHelper.getQuestionAnswerCorrect((int) spinner.getSelectedItemId() + 1, spinnerNameQuiz.getSelectedItem().toString(), nameCourse);
//                                                                                                corr = corr + correctAns;
//                                                                                                databaseHelper.updateCorrectAnswer((int) spinner.getSelectedItemId() + 1, spinnerNameQuiz.getSelectedItem().toString(), ans);
//
//                                                                                            }
//                                                                                            answerQues = "";
//                                                                                            correctAns = "";
//                                                                                        }
//                                                                                        dialog.cancel();
//                                                                                        ss();
//                                                                                        setSpinner();
//                                                                                    }
//                                                                                });
//                                                                                dialog.show();
//                                                                            } else {
//                                                                                dialog.setContentView(R.layout.dialog_edit_question_time);
//                                                                                dialog.setTitle("Question");
//                                                                                final EditText statement = (EditText) dialog.findViewById(R.id.statement);
//                                                                                final EditText answer = (EditText) dialog.findViewById(R.id.answer);
//                                                                                final EditText time = (EditText) dialog.findViewById(R.id.time);
//                                                                                Button addAnswer = (Button) dialog.findViewById(R.id.addanswer);
//                                                                                final CheckBox correct = (CheckBox) dialog.findViewById(R.id.correct);
//
//                                                                                Button cancelQuiz = (Button) dialog.findViewById(R.id.cancelQuiz);
//                                                                                cancelQuiz.setOnClickListener(new View.OnClickListener() {
//                                                                                                                  @Override
//                                                                                                                  public void onClick(View view) {
//                                                                                                                      dialog.cancel();
//                                                                                                                  }
//                                                                                                              }
//                                                                                );
//                                                                                addAnswer.setOnClickListener(new View.OnClickListener() {
//
//                                                                                    @Override
//                                                                                    public void onClick(View view) {
//                                                                                        if (!((answer.getText().toString()).equals(""))) {
//                                                                                            answerQues = answerQues + (answer.getText().toString() + "~");
//                                                                                            if (correct.isChecked()) {
//                                                                                                correctAns += correctAns + (answer.getText().toString() + "~");
//                                                                                            }
//                                                                                            answer.setText("");
//                                                                                            correct.setChecked(false);
//                                                                                        }
//                                                                                    }
//                                                                                });
//                                                                                Button okQuiz = (Button) dialog.findViewById(R.id.okQuiz);
//                                                                                okQuiz.setOnClickListener(new View.OnClickListener() {
//                                                                                                              @Override
//                                                                                                              public void onClick(View view) {
//                                                                                                                  if (!((statement.getText().toString()).equals(""))) {
//                                                                                                                      Log.d("idQuestionnnnn", ((int) spinner.getSelectedItemId() + 1) + "");
//                                                                                                                      int a = databaseHelper.updateStatementQuestion((int) spinner.getSelectedItemId() + 1, spinnerNameQuiz.getSelectedItem().toString(),
//                                                                                                                              statement.getText().toString());
//                                                                                                                      Log.d("update question", "" + a);
//                                                                                                                  }
//
//                                                                                                                  if (!(answerQues.equals(""))) {
//                                                                                                                      String ans = databaseHelper.getAnswerforQues(spinnerNameQuiz.getSelectedItem().toString(), nameCourse, (int) spinner.getSelectedItemId() + 1);
//                                                                                                                      ans = ans + answerQues;
//                                                                                                                      databaseHelper.updateAnswer((int) spinner.getSelectedItemId() + 1, spinnerNameQuiz.getSelectedItem().toString(), ans);
//                                                                                                                      if (!(correctAns.equals(""))) {
//                                                                                                                          String corr = databaseHelper.getQuestionAnswerCorrect((int) spinner.getSelectedItemId() + 1, spinnerNameQuiz.getSelectedItem().toString(), nameCourse);
//                                                                                                                          corr = corr + correctAns;
//                                                                                                                          databaseHelper.updateCorrectAnswer((int) spinner.getSelectedItemId() + 1, spinnerNameQuiz.getSelectedItem().toString(), ans);
//
//                                                                                                                      }
//                                                                                                                      answerQues = "";
//                                                                                                                      correctAns = "";
//                                                                                                                  }
//                                                                                                                  if (!(time.getText().toString().equals(""))) {
//                                                                                                                      databaseHelper.updateTimeQuestion((int) spinner.getSelectedItemId() + 1, spinnerNameQuiz.getSelectedItem().toString(), Integer.parseInt(time.getText().toString()));
//                                                                                                                  }
//
//                                                                                                                  dialog.cancel();
//                                                                                                                  ss();
//                                                                                                                  setSpinner();
//
//
//                                                                                                              }
//                                                                                                          }
//                                                                                );
//                                                                                dialog.show();
//
//                                                                            }
//                                                                        } else
//                                                                            Toast.makeText(getActivity(), "Quiz doesn't existed ", Toast.LENGTH_SHORT).show();
//
//
//                                                                    }
//                                                                }
//        );
        view.findViewById(R.id.addQues).setOnClickListener(new View.OnClickListener() {
                                                               @Override
                                                               public void onClick(View view) {

                                                                   if (databaseHelper.checkTime(spinnerNameQuiz.getSelectedItem().toString(), nameCourse, idTeach)) {
                                                                       dialog.setContentView(R.layout.add_ques_dialog);
                                                                       dialog.setTitle("Question");
                                                                       final EditText statement = (EditText) dialog.findViewById(R.id.statement);
                                                                       final EditText answer = (EditText) dialog.findViewById(R.id.answer);
                                                                       final CheckBox correct = (CheckBox) dialog.findViewById(R.id.correct);
                                                                       Button add = (Button) dialog.findViewById(R.id.addanswer);
                                                                       Button cancelQuiz = (Button) dialog.findViewById(R.id.cancelQuiz);
                                                                       cancelQuiz.setOnClickListener(new View.OnClickListener() {
                                                                                                         @Override
                                                                                                         public void onClick(View view) {
                                                                                                             dialog.cancel();
                                                                                                         }
                                                                                                     }
                                                                       );
                                                                       add.setOnClickListener(new View.OnClickListener() {
                                                                           @Override
                                                                           public void onClick(View view) {
                                                                               String answerQues = answer.getText().toString();
                                                                               if (!((statement.getText().toString()).isEmpty())) {
                                                                                   if (!(answerQues.isEmpty())) {
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

                                                                       Button okQuiz = (Button) dialog.findViewById(R.id.okQuiz);
                                                                       okQuiz.setOnClickListener(new View.OnClickListener() {

                                                                           @Override
                                                                           public void onClick(View view) {

                                                                               if (statement.getText().toString().isEmpty()) {
                                                                                   Toast.makeText(getActivity(), "Fill all fields", Toast.LENGTH_SHORT).show();

                                                                               } else {

                                                                                   if (answerQuestion.size() > 1) {
                                                                                       if (correctAnswer.size() > 0) {
                                                                                           Log.d("sizeCorr", correctAnswer.size() + "");
                                                                                           answerStr = "";
                                                                                           answerCorr = "";
                                                                                           for (int i = 0; i < indexAns; i++) {
                                                                                               if (indexAns == 1) {
                                                                                                   answerStr = answerQuestion.get(0).toString();
                                                                                               } else {
                                                                                                   answerStr += (answerQuestion.get(i) + "~");
                                                                                                   Log.d("answerQuestion", answerStr);

                                                                                               }
                                                                                           }
                                                                                           for (int i = 0; i < indexCorrect; i++) {
                                                                                               if (indexCorrect == 1) {
                                                                                                   answerCorr = correctAnswer.get(0).toString();
                                                                                               } else {
                                                                                                   answerCorr += (correctAnswer.get(i) + "~");
                                                                                                   Log.d("answerCorr", answerCorr);
                                                                                               }
                                                                                           }

                                                                                           correctAnswer.clear();
                                                                                           answerQuestion.clear();

                                                                                           boolean aa = databaseHelper.insertDataQuestion(databaseHelper.getItemsQuestion(nameCourse, spinnerNameQuiz.getSelectedItem().toString()) + 1
                                                                                                   , spinnerNameQuiz.getSelectedItem().toString(), statement.getText().toString(), answerStr, answerCorr, 0, nameCourse);

                                                                                           Log.d("duaa", aa + "");
                                                                                           ss();
                                                                                       //    setSpinner();
                                                                                           indexAns = 0;
                                                                                           indexCorrect = 0;
                                                                                           dialog.cancel();
                                                                                       } else
                                                                                           Toast.makeText(getActivity(), "write at least one correct answer ", Toast.LENGTH_SHORT).show();
                                                                                   } else
                                                                                       Toast.makeText(getActivity(), "write anther answer ", Toast.LENGTH_SHORT).show();

                                                                               }
                                                                           }
                                                                       });
                                                                       dialog.show();
                                                                   } else {
                                                                       dialog.setContentView(R.layout.add_ques_time_dialog);
                                                                       dialog.setTitle("Question");
                                                                       final EditText statement = (EditText) dialog.findViewById(R.id.statement);
                                                                       final EditText answer = (EditText) dialog.findViewById(R.id.answer);
                                                                       final CheckBox correct = (CheckBox) dialog.findViewById(R.id.correct);
                                                                       Button add = (Button) dialog.findViewById(R.id.addanswer);

                                                                       final EditText timeQuiz = (EditText) dialog.findViewById(R.id.time);
                                                                       Button cancelQuiz = (Button) dialog.findViewById(R.id.cancelQuiz);

                                                                       cancelQuiz.setOnClickListener(new View.OnClickListener() {
                                                                                                         @Override
                                                                                                         public void onClick(View view) {
                                                                                                             dialog.cancel();
                                                                                                         }
                                                                                                     }
                                                                       );
                                                                       add.setOnClickListener(new View.OnClickListener() {
                                                                           @Override
                                                                           public void onClick(View view) {
                                                                               String answerQues = answer.getText().toString();
                                                                               if (!(statement.getText().toString()).isEmpty()) {
                                                                                   if (!(answerQues.isEmpty())) {
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
                                                                       Button okQuiz = (Button) dialog.findViewById(R.id.okQuiz);
                                                                       okQuiz.setOnClickListener(new View.OnClickListener() {
                                                                                                     @Override
                                                                                                     public void onClick(View view) {
                                                                                                         if (timeQuiz.getText().toString().isEmpty() && statement.getText().toString().isEmpty()) {
                                                                                                             Toast.makeText(getActivity(), "Fill all fields", Toast.LENGTH_SHORT).show();

                                                                                                         } else {

                                                                                                             if (answerQuestion.size() > 1) {
                                                                                                                 if (correctAnswer.size() > 0) {
                                                                                                                     answerStr = "";
                                                                                                                     answerCorr = "";

                                                                                                                     for (int i = 0; i < indexAns; i++) {
                                                                                                                         if (indexAns == 1) {
                                                                                                                             answerStr = answerQuestion.get(0).toString();
                                                                                                                         } else {
                                                                                                                             answerStr += (answerQuestion.get(i) + "~");
                                                                                                                             Log.d("answerQuestion", answerStr);

                                                                                                                         }
                                                                                                                     }
                                                                                                                     for (int i = 0; i < indexCorrect; i++) {
                                                                                                                         if (indexCorrect == 1) {
                                                                                                                             answerCorr = correctAnswer.get(0).toString();
                                                                                                                         } else {
                                                                                                                             answerCorr += (correctAnswer.get(i) + "~");
                                                                                                                             Log.d("answerCorr", answerCorr);
                                                                                                                         }
                                                                                                                     }
                                                                                                                     correctAnswer.clear();
                                                                                                                     answerQuestion.clear();
                                                                                                                     boolean aa = databaseHelper.insertDataQuestion(databaseHelper.getItemsQuestion(nameCourse, spinnerNameQuiz.getSelectedItem().toString()) + 1
                                                                                                                             , spinnerNameQuiz.getSelectedItem().toString(), statement.getText().toString(), answerStr, answerCorr, 0, nameCourse);

                                                                                                                     Log.d("duaa", aa + "");
                                                                                                                     ss();
                                                                                                            //         setSpinner();
                                                                                                                     indexAns = 0;
                                                                                                                     indexCorrect = 0;
                                                                                                                     dialog.cancel();

                                                                                                                 }
                                                                                                             }
                                                                                                         }
                                                                                                     }

                                                                                                 }

                                                                       );
                                                                       dialog.show();
                                                                   }

                                                               }
                                                           }

        );
        return view;
    }

//    public void setSpinner() {
//
//        List<String> categories = new DatabaseHelper(getActivity()).getAllQuestion(nameCourse, spinnerNameQuiz.getSelectedItem().toString());
//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories);
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(dataAdapter);
//
//    }


    public void dd() {


        List<String> allNameQuiz = new DatabaseHelper(getActivity()).getAllNameQuiz(nameCourse, idTeach);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, allNameQuiz);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNameQuiz.setAdapter(dataAdapter);

    }

    public void ss() {

        ArrayList<String> listAnswer = new DatabaseHelper(getActivity()).getQuestionAnswer(spinnerNameQuiz.getSelectedItem().toString(), nameCourse);
        ArrayList<ArrayList<String>> groups = new ArrayList<ArrayList<String>>();
        for (int j = 0; j < listAnswer.size(); j++) {

            String[] a = (listAnswer.get(j)).split("~");
            ArrayList<String> children = new ArrayList<String>();

            for (int h = 0; h < a.length; h++) {


                children.add(a[h]);

            }
            groups.add(children);
        }
        ArrayList<String> listQuestion = new DatabaseHelper(getActivity()).getQuestion(spinnerNameQuiz.getSelectedItem().toString(), nameCourse);

        ExpandableListDataPump adapter = new ExpandableListDataPump(EditQuiz.this, groups, listQuestion, spinnerNameQuiz.getSelectedItem().toString(), nameCourse, idTeach);
        listView.setAdapter(adapter);

    }

    @Override
    public void onPauseFragment() {

    }

    @Override
    public void onResumeFragment() {


        List<String> allNameQuiz = new DatabaseHelper(getActivity()).getAllNameQuiz(nameCourse, idTeach);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, allNameQuiz);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNameQuiz.setAdapter(dataAdapter);

    }

    @Override
    public boolean onNavigationItemSelected(int i, long l) {
        return false;
    }
}
