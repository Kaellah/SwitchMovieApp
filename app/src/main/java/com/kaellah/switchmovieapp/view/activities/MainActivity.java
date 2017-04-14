package com.kaellah.switchmovieapp.view.activities;

import android.os.Bundle;
import android.util.Log;

import com.kaellah.switchmovieapp.R;
import com.kaellah.switchmovieapp.view.fragments.MovieListFragment;

public class MainActivity extends BaseActivity {

    public static final String LOG_TAG = "LOG_TAG";

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        super.setContentView(R.layout.activity_main);

        Log.e(LOG_TAG, "MainActivity - onCreate");

        if (b == null) {
            startFragment(MovieListFragment.newInstance(), false);
        }
    }

    @Override
    public int getFragmentContainerId() {
        return R.id.container;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(LOG_TAG, "MainActivity - onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(LOG_TAG, "MainActivity - onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.e(LOG_TAG, "MainActivity - onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.e(LOG_TAG, "MainActivity - onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.e(LOG_TAG, "MainActivity - onDestroy");
    }
}
