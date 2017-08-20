package net.androidbootcamp.quiv_proj;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;
import android.widget.TextView;


public class Teacher extends Fragment {
    private int teacherId;
//    ProgressDialog progressDialog;
//    private int progress = 0;
//    private Handler progressHandler = new Handler();
//    private long fileSize = 0;

    @Override

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        teacherId = getArguments().getInt("id");

        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.teacher, container, false);


        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
        String s = databaseHelper.getNameTeacher(teacherId);
        TextView nameTec = (TextView) view.findViewById(R.id.namePerson);
        nameTec.setText("Name : " + s);
        TextView idTec = (TextView) view.findViewById(R.id.idStud);
        idTec.setText("ID : " + teacherId);

        view.findViewById(R.id.Home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int x = getFragmentManager().getBackStackEntryCount();
                for (int i = 0; i < x; i++) {

                    getFragmentManager().popBackStack();
                }

                ProgressDialog progressDialog = new ProgressDialog(view.getContext()).show(getContext(),"","",true);
            /*    progressDialog.setCancelable(true);
                progressDialog.setMessage("Duaa");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setProgress(0);
                progressDialog.setMax(100);

                new Thread(new Runnable() {
                    public void run() {
                        while (progress < 100) {
                            // performing operation
                            progress = doOperation();
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            // Updating the progress bar
                            progressHandler.post(new Runnable() {
                                public void run() {
                                    progressDialog.setProgress(progress);
                                }
                            });
                        }
                        // performing operation if file is downloaded,
                        if (progress >= 100) {
                            // sleeping for 1 second after operation completed
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            // close the progress bar dialog
                            progressDialog.dismiss();
                        }
                    }
                }).start();

              /*  Fragment fragment = new Login();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frag, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();*/

            }//end of onClick method
        });


        return view;

    }

  /*  public int doOperation() {
        //The range of ProgressDialog starts from 0 to 10000
        while (fileSize <= 10000) {
            fileSize++;
            if (fileSize == 1000) {
                return 10;
            } else if (fileSize == 2000) {
                return 20;
            } else if (fileSize == 3000) {
                return 30;
            } else if (fileSize == 4000) {
                return 40;//you can add more else if
            } else {
                return 100;
            }
        }//end of while
        return 100;
    }//end of doOperation*/
}
