package net.androidbootcamp.quiv_proj;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by مركز الخبراء on 07/04/2017.
 */

public class StudentInTeacher extends Fragment {
    FragmentPagerAdapter adapterViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        Log.d("name","      " + getArguments().getString("nameCourse"));


        View view =inflater.inflate(R.layout.student_in_teacher, container, false);
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
                    Fragment fragment =new AddStudent();
                    Bundle bundle=new Bundle();
                    bundle.putString("name",getArguments().getString("nameCourse"));
                    fragment.setArguments(bundle);

                    return  AddStudent.newInstance(0, "Page # 1");

                case 1: // Fragment # 0 - This will show FirstFragment different title
                    return ShowStudent.newInstance(1, "Page # 2");

                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            if(position==0){
                return "ADD / DELETE" ;}
            return "SHOW";
        }


    }

}

