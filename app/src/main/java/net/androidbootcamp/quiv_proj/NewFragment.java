package net.androidbootcamp.quiv_proj;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static net.androidbootcamp.quiv_proj.R.layout.new_frag;

/**
 * Created by Hanan Dawod on 11/06/17.
 */

public class NewFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.new_frag,container,false);
    }
}
