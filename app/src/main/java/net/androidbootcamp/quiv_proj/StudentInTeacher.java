package net.androidbootcamp.quiv_proj;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by مركز الخبراء on 07/04/2017.
 */

public  class StudentInTeacher extends Fragment  {

    DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
    Bundle arg;
    FragmentPagerAdapter adapterViewPager;
    AddStudent addStud = new AddStudent();
    ShowStudent showStud = new ShowStudent();

    int dd;
    String ff;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dd =  getArguments().getInt("idTeach");
        arg = new Bundle();
        arg.putInt("idTeach",dd);
        ff=getArguments().getString("namee");
        arg.putString("namee",ff);
        addStud.setArguments(arg);
        showStud.setArguments(arg);
        Log.d("name",ff);

    }
    ViewPager vpPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // setContentView(R.layout.student_in_teacher);
        View view = inflater.inflate(R.layout.student_in_teacher, container, false);
    vpPager = (ViewPager) view.findViewById(R.id.vpPager);

//vpPager.setOnPageChangeListener(this);


        adapterViewPager = new MyPagerAdapter(getChildFragmentManager(),getActivity());
        vpPager.setAdapter(adapterViewPager);
        vpPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            // This method will be invoked when a new page becomes selected.
            @Override
            public void onPageSelected(int position) {

            }


            // This method will be invoked when the current page is scrolled
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                // Code goes here
            }

            // Called when the scroll state changes:
            // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
            @Override
            public void onPageScrollStateChanged(int state) {
                // Code goes here
            }
        });




        return view;

    }

    public  class MyPagerAdapter extends FragmentPagerAdapter {
        private  int NUM_ITEMS = 2;
        Context ctxt=null;
        public MyPagerAdapter(FragmentManager fragmentManager,Context ctxt) {
            super(fragmentManager);
            this.ctxt=ctxt;

        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0: //  show FirstFragment
                    return addStud.newInstance(ff,dd,addStud);
                case 1: // show FirstFragment different title
                      //addStud.getArguments().getString(ff);
                   // return new ShowStudent();
                      return showStud;

                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0) {
                return "ADD / DELETE";
            }
            return "SHOW";
        }
    }
}
