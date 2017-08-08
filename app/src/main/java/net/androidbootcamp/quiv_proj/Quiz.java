package net.androidbootcamp.quiv_proj;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;


public  class Quiz extends Fragment {

    Bundle arg;
    FragmentPagerAdapter adapterViewPager;
    AddQuiz addQuiz = new AddQuiz();
    EditQuiz editQuiz = new EditQuiz();
    int dd;
    String ff;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        dd = getArguments().getInt("idTeach");
        Log.d("dd",dd+"");
        arg = new Bundle();
        arg.putInt("idTeach", dd);
        ff = getArguments().getString("namee");
        arg.putString("namee", ff);
        addQuiz.setArguments(arg);
        editQuiz.setArguments(arg);

        setRetainInstance(true);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.quiz_teacher, container, false);
        adapterViewPager = new MyPagerAdapter(getChildFragmentManager());
        ViewPager pager = (ViewPager) view.findViewById(R.id.vpPager1);
        pager.setAdapter(adapterViewPager);

        pager.setOnPageChangeListener(pageChangeListener);
        return view;
    }

    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {

        int currentPosition = 0;

        @Override
        public void onPageSelected(int newPosition) {

            FragmentLifecycle fragmentToHide = (FragmentLifecycle) adapterViewPager.getItem(currentPosition);
            fragmentToHide.onPauseFragment();

            FragmentLifecycle fragmentToShow = (FragmentLifecycle) adapterViewPager.getItem(newPosition);
            fragmentToShow.onResumeFragment();

            currentPosition = newPosition;
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        public void onPageScrollStateChanged(int arg0) {
        }

    };
    public class MyPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments;

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
            this.fragments = new ArrayList<Fragment>();
            fragments.add(addQuiz);
            fragments.add(editQuiz);

        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
        @Override
        public CharSequence getPageTitle(int position){
            if (position==0){
                return "ADD";
            }
            return "EDIT/DELETE";
        }
    }

}

