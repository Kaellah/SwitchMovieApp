package com.kaellah.switchmovieapp.view.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.CheckResult;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.kaellah.switchmovieapp.R;
import com.kaellah.switchmovieapp.other.Utils;
import com.kaellah.switchmovieapp.other.di.view.DaggerViewComponent;
import com.kaellah.switchmovieapp.other.di.view.ViewComponent;
import com.kaellah.switchmovieapp.other.di.view.ViewDynamicModule;
import com.kaellah.switchmovieapp.presenter.MovieListPresenter;
import com.kaellah.switchmovieapp.presenter.Presenter;
import com.kaellah.switchmovieapp.presenter.vo.Movie;
import com.kaellah.switchmovieapp.view.ActivityCallback;
import com.kaellah.switchmovieapp.view.adapters.MoviesAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 * @since 15.03.17
 */

public class MovieListFragment extends BaseFragment
        implements MovieListView {

    @Bind(R.id.recycler_view)
    protected RecyclerView mRecyclerView;

    @Bind(R.id.progress_bar)
    protected ProgressBar progressBar;

    @Inject
    protected MovieListPresenter mPresenter;

    private ActivityCallback activityCallback;

    private MoviesAdapter mAdapter;

    private ViewComponent mViewComponent;

    @OnClick(R.id.tv_load_more)
    public void onClickLoadMore(View v) {
        if (mPresenter != null) {
            mPresenter.loadMovies(1);
        }
    }

    @CheckResult
    public static MovieListFragment newInstance() {
        return new MovieListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle b) {
        if (mViewComponent == null) {
            mViewComponent = DaggerViewComponent.builder()
                    .viewDynamicModule(new ViewDynamicModule(this))
                    .build();
        }
        mViewComponent.inject(this);
        super.onCreate(b);

        mAdapter = new MoviesAdapter(mPresenter);
    }

    @Nullable
    @Override
    public android.view.View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(android.view.View v, @Nullable Bundle b) {
        super.onViewCreated(v, b);

        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mRecyclerView.setAdapter(mAdapter);

        mPresenter.onViewCreated(b);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            activityCallback = (ActivityCallback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement activityCallback");
        }
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
    public void showMovieList(List<Movie> movies) {
        mAdapter.addData(movies);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showEmptyList() {
        Utils.showMessage(mRecyclerView, getString(R.string.err_empty_list));
    }

    @Override
    public void loadMore() {
        mPresenter.loadMovies(1);
    }

    @Override
    public void startMovieInfoFragment(Movie movie) {
        activityCallback.startMovieInfoFragment(movie);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mPresenter.onSaveInstanceState(outState);
    }
}
