package com.kaellah.switchmovieapp.fragment;

import android.os.Bundle;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 * @since 15.03.17
 */

public class MoviesListFragment extends BaseFragment {

    public static MoviesListFragment newInstance() {
        Bundle args = new Bundle();

        MoviesListFragment fragment = new MoviesListFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
