package net.androidbootcamp.quiv_proj;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import static net.androidbootcamp.quiv_proj.R.layout.new_frag;


public class NewFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_frag, container, false);
        Button button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Do something
                //
                //
                } });
        return view;
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2" };
        ArrayAdapter<String> adapter = new
            ArrayAdapter <String>(getActivity(), android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter); }

    public void setListAdapter(ArrayAdapter<String> listAdapter) {
        listAdapter = listAdapter;
    }
}