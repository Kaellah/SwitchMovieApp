package com.kaellah.switchmovieapp.view.activities;

import android.os.Bundle;

import com.kaellah.switchmovieapp.R;
import com.kaellah.switchmovieapp.view.fragments.MovieListFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        super.setContentView(R.layout.activity_main);

        if (b == null) {
            startFragment(MovieListFragment.newInstance(), false);
        }
    }

    @Override
    public int getFragmentContainerId() {
        return R.id.container;
    }
}
