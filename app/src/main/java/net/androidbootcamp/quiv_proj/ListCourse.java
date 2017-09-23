package net.androidbootcamp.quiv_proj;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

/**
 * Created by Hanan Dawod on 05/07/17.
 */

public class ListCourse extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.listcourse, container, false);

        ListView listView = (ListView) view.findViewById(R.id.ListCourseTec);
        final ListViewCourse adapter = new ListViewCourse(this, getArguments().getInt("id"));
        listView.setAdapter(adapter);
        return view;
}
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_UP && i == KeyEvent.KEYCODE_BACK) {
                    getActivity().finish();
                    System.exit(0);
                    return true;
                }
                return false;
            }
        });

    }
}
