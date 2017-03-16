package com.kaellah.switchmovieapp.view.activities;

import android.os.Bundle;

import com.kaellah.switchmovieapp.R;
import com.kaellah.switchmovieapp.presenter.vo.Movie;
import com.kaellah.switchmovieapp.view.ActivityCallback;
import com.kaellah.switchmovieapp.view.fragments.MovieListFragment;

public class MainActivity extends BaseActivity
        implements ActivityCallback {

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);

        if (b == null) {
            startFragment(MovieListFragment.newInstance(), false);
        }
    }

    @Override
    public int getFragmentContainerId() {
        return R.id.container;
    }

    @Override
    public void startMovieInfoFragment(Movie movie) {

    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }
}
