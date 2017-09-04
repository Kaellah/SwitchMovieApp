package com.kaellah.switchmovieapp.view.fragments;

import android.os.Bundle;
import android.support.annotation.CheckResult;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kaellah.switchmovieapp.R;
import com.kaellah.switchmovieapp.other.RecyclerScrollListener;
import com.kaellah.switchmovieapp.other.Utils;
import com.kaellah.switchmovieapp.other.di.view.DaggerViewComponent;
import com.kaellah.switchmovieapp.other.di.view.ViewComponent;
import com.kaellah.switchmovieapp.other.di.view.ViewDynamicModule;
import com.kaellah.switchmovieapp.presenter.MovieListPresenter;
import com.kaellah.switchmovieapp.presenter.Presenter;
import com.kaellah.switchmovieapp.presenter.vo.Movie;
import com.kaellah.switchmovieapp.view.adapters.MoviesAdapter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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

    @Inject
    protected MovieListPresenter mPresenter;

    private ViewComponent mViewComponent;

    // ADAPTER
    private MoviesAdapter mAdapter;

    @OnClick(R.id.tv_load_more)
    public void onClickLoadMore(android.view.View v) {
        if (mPresenter != null) {
            mPresenter.loadMovies(-1);
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

        mAdapter = new MoviesAdapter();
    }

    @Override
    public void onViewCreated(android.view.View v, @Nullable Bundle b) {
        super.onViewCreated(v, b);

        mPresenter.onViewCreated(b);

        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), Utils.isLand(getContext()) ? 4 : 2);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        final RecyclerScrollListener recyclerScrollListener = new RecyclerScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                mPresenter.loadMovies(page);
            }
        };

        mRecyclerView.addOnScrollListener(recyclerScrollListener);
    }

    @Override
    public void showError(String error) {
        Utils.showMessage(mRecyclerView, error);
    }

    @Override
    public void showLoading() {
        setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        setRefreshing(false);
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
    public void loadMore(int page) {
        mPresenter.loadMovies(page);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mPresenter.onSaveInstanceState(outState);
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Movie movie) {
        if (movie != null) {
            startFragment(MovieInfoFragment.newInstance(movie), true);
        }
    }

    @Override
    protected boolean hasSubscribe() {
        return true;
    }

    @Override
    protected void onCleanUp() throws Exception {
        Utils.cleanUp(mRecyclerView);
    }
}
