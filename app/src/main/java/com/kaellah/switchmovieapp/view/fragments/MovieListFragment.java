package com.kaellah.switchmovieapp.view.fragments;

import android.support.annotation.CheckResult;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.kaellah.switchmovieapp.R;
import com.kaellah.switchmovieapp.presenter.MovieListPresenter;
import com.kaellah.switchmovieapp.presenter.Presenter;
import com.kaellah.switchmovieapp.presenter.vo.Movie;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 * @since 15.03.17
 */

public class MovieListFragment extends BaseFragment
        implements MovieListView {

    @Bind(R.id.recycler_view)
    protected RecyclerView recyclerView;

    @Bind(R.id.progress_bar)
    protected ProgressBar progressBar;

    @Inject
    protected MovieListPresenter mPresenter;

    @CheckResult
    public static MovieListFragment newInstance() {
        return new MovieListFragment();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Presenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_movies_list;
    }

    @Override
    protected int getToolbarTitleRes() {
        return R.string.Latest_movies;
    }

    @Override
    public void showMovieList(List<Movie> vo) {

    }

    @Override
    public void showEmptyList() {

    }

    @Override
    public void loadMore() {

    }

    @Override
    public void startMovieInfoFragment(Movie movie) {

    }
}
