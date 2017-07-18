package net.androidbootcamp.quiv_proj;

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

/**
 * Created by مركز الخبراء on 07/04/2017.
 */

public class StudentInTeacher extends Fragment {

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

        // setContentView(R.layout.activity_main);

        dd =  getArguments().getInt("idTeach");
        arg = new Bundle();
        arg.putInt("idTeach",dd);
        ff=getArguments().getString("nameCourse");
        arg.putString("nameCourse",ff);
        addStud.setArguments(arg);

        showStud.setArguments(arg);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // setContentView(R.layout.student_in_teacher);
        View view = inflater.inflate(R.layout.student_in_teacher, container, false);
        ViewPager vpPager = (ViewPager) view.findViewById(R.id.vpPager);

        adapterViewPager = new MyPagerAdapter(getFragmentManager());
        vpPager.setAdapter(adapterViewPager);
        vpPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            // This method will be invoked when a new page becomes selected.
            @Override
            public void onPageSelected(int position) {

//                PagerTabStrip mPagerTabStrip = (PagerTabStrip) findViewById(R.id.pager_header);
//                for (int i = 0; i < mPagerTabStrip.getChildCount(); ++i) {
//                    View nextChild = mPagerTabStrip.getChildAt(i);
//                    TextView textViewToConvert;
//                    if (nextChild instanceof TextView) {
//                        textViewToConvert = (TextView) nextChild;
//                        //textViewToConvert.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
//                        textViewToConvert.setTextColor(Color.RED);
//                    }else
//                        textViewToConvert = (TextView) nextChild;
//                    //textViewToConvert.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
//                    textViewToConvert.setTextColor(Color.BLUE);
                //  }
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



        //final Button addd = (Button)view.findViewById(R.id.addd);
        //final Button showw = (Button)view.findViewById(R.id.showw);


/*
        addd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                showw.setBackgroundColor(Color.WHITE);
                addd.setBackgroundColor(Color.RED);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.add_delete_show, addStud);
                fragmentTransaction.commit();

            }



        });

        showw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                showw.setBackgroundColor(Color.RED);
                addd.setBackgroundColor(Color.WHITE);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.add_delete_show, showStud);
               // fragmentTransaction.addToBackStack(null); ما بنكتبها عشان ما يرجع عليها كمان مرة لما نعمل back
                fragmentTransaction.commit();

            }

        });
*/


        return view;
    }
    public  class MyPagerAdapter extends FragmentPagerAdapter {
        private  int NUM_ITEMS = 2;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment
                    return AddStudent.newInstance(ff,dd);
                case 1: // Fragment # 0 - This will show FirstFragment different title
                      return ShowStudent.newInstance(ff,dd);

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


   /* FragmentPagerAdapter adapterViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.student_in_MyPagerAdapterer, container, false);


        Log.d("name", "      " + getArguments().getString("nameCourse"));


        ViewPager vpPager = (ViewPager) view.findViewById(R.id.vpPager);
        adapterViewPager = new MyPagerAdapter(getActivity().getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);
// Attach the page change listener inside the activity
        vpPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            // This method will be invoked when a new page becomes selected.
            @Override
            public void onPageSelected(int position) {

//                PagerTabStrip mPagerTabStrip = (PagerTabStrip) findViewById(R.id.pager_header);
//                for (int i = 0; i < mPagerTabStrip.getChildCount(); ++i) {
//                    View nextChild = mPagerTabStrip.getChildAt(i);
//                    TextView textViewToConvert;
//                    if (nextChild instanceof TextView) {
//                        textViewToConvert = (TextView) nextChild;
//                        //textViewToConvert.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
//                        textViewToConvert.setTextColor(Color.RED);
//                    }else
//                        textViewToConvert = (TextView) nextChild;
//                    //textViewToConvert.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
//                    textViewToConvert.setTextColor(Color.BLUE);
                //  }
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

    public class MyPagerAdapter extends FragmentPagerAdapter {
        private int NUM_ITEMS = 2;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment
                    Fragment fragment = new AddStudent();
                    Bundle bundle = new Bundle();
                    bundle.putString("name", getArguments().getString("nameCourse"));
                    fragment.setArguments(bundle);

                    return AddStudent.newInstance(0, "Page # 1");

                case 1: // Fragment # 0 - This will show FirstFragment different title
                    return ShowStudent.newInstance(1, "Page # 2");

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

 DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
    Bundle arg;
    FragmentPagerAdapter adapterViewPager;
    Fragment addStud = new AddStudent();
    Fragment showStud = new ShowStudent();
    int dd;
   @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       dd =  getArguments().getInt("idMyPagerAdapter");
       arg = new Bundle();
       arg.putInt("idTeach",dd);
       arg.putString("nameCourse",getArguments().getString("nameCourse"));
       addStud.setArguments(arg);

       showStud.setArguments(arg);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       // setContentView(R.layout.student_in_teacher);
        View view = inflater.inflate(R.layout.student_in_teacher, container, false);
        final Button addd = (Button)view.findViewById(R.id.addd);
        final Button showw = (Button)view.findViewById(R.id.showw);
        if(savedInstanceState == null){
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.add_delete_show,addStud).commit();
        }



        addd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                showw.setBackgroundColor(Color.WHITE);
                addd.setBackgroundColor(Color.RED);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.add_delete_show, addStud);
                fragmentTransaction.commit();

            }

        });

        showw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                showw.setBackgroundColor(Color.RED);
                addd.setBackgroundColor(Color.WHITE);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.add_delete_show, showStud);
               // fragmentTransaction.addToBackStack(null); ما بنكتبها عشان ما يرجع عليها كمان مرة لما نعمل back
                fragmentTransaction.commit();

            }

        });



        return view;
    }

}
*/