package com.kaellah.switchmovieapp.view.fragments;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kaellah.switchmovieapp.R;
import com.kaellah.switchmovieapp.other.AConstant;
import com.kaellah.switchmovieapp.other.Utils;
import com.kaellah.switchmovieapp.other.di.view.DaggerMovieViewComponent;
import com.kaellah.switchmovieapp.other.di.view.MovieViewComponent;
import com.kaellah.switchmovieapp.other.di.view.MovieViewDynamicModule;
import com.kaellah.switchmovieapp.presenter.MovieInfoPresenter;
import com.kaellah.switchmovieapp.presenter.Presenter;
import com.kaellah.switchmovieapp.presenter.vo.Movie;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.BindDrawable;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 * @since 20.03.17
 */

public class MovieInfoFragment extends BaseFragment
        implements MovieInfoView {

    @Bind(R.id.ll_container)
    protected View mContainer;
    @Bind(R.id.iv_movie)
    protected ImageView mIvMovie;
    @Bind(R.id.tv_score)
    protected TextView mTvScore;
    @Bind(R.id.tv_rating)
    protected TextView mTvRating;
    @Bind(R.id.tv_release_date)
    protected TextView mTvReleaseDate;
    @Bind(R.id.tv_title)
    protected TextView mTvTitle;
    @Bind(R.id.tv_overview)
    protected TextView mTvOverview;
    @Bind(R.id.parent)
    protected View mBackground;
    @Bind(R.id.tv_error)
    protected TextView mTvError;
    @BindDrawable(R.drawable.placeholder)
    protected Drawable mPlaceholder;

    @Inject
    protected MovieInfoPresenter mPresenter;

    private MovieViewComponent mViewComponent;

    public static MovieInfoFragment newInstance(@NonNull Movie movie) {
        final Bundle args = new Bundle(1);
        args.putParcelable(AConstant.EXTRA_MOVIE, movie);

        final MovieInfoFragment fragment = new MovieInfoFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle b) {
        if (mViewComponent == null) {
            mViewComponent = DaggerMovieViewComponent.builder()
                    .movieViewDynamicModule(new MovieViewDynamicModule(this, getContext()))
                    .build();
        }
        mViewComponent.inject(this);
        super.onCreate(b);
    }

    @Override
    public void onViewCreated(View v, @Nullable Bundle b) {
        super.onViewCreated(v, b);

        mContainer.setLayoutParams(mPresenter.getLayoutParams((LinearLayout.LayoutParams) mContainer.getLayoutParams()));

        mPresenter.onViewCreated(b == null ? getArguments() : b);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_movie_info;
    }

    @Override
    protected Presenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected int getToolbarTitleRes() {
        return R.string.Latest_movies;
    }

    @Override
    public void showMovieInfo(Movie movie) {
        mTvError.setVisibility(View.GONE);

        mPresenter.loadImage(mPlaceholder, mIvMovie);

        mTvTitle.setText(movie.getTitle());
        mTvScore.setText(String.valueOf(movie.getVoteAverage()));
        mTvRating.setText(movie.getAdult() ? getString(R.string.Rating) : getString(R.string.PG));
        mTvReleaseDate.setText(Utils.convertDate(movie.getReleaseDate()));
        mTvOverview.setText(movie.getOverview());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mPresenter.onSaveInstanceState(outState);
    }

    @Override
    protected void onCleanUp() throws Exception {
        Utils.cleanUp(mIvMovie);
        Utils.cleanUp(mTvScore);
        Utils.cleanUp(mTvRating);
        Utils.cleanUp(mTvReleaseDate);
        Utils.cleanUp(mTvTitle);
        Utils.cleanUp(mTvOverview);
    }

    @Nullable
    @Override
    protected CharSequence getToolbarTitle() {
        return mPresenter.getMovie().getTitle();
    }

    @Override
    public void showError(String error) {
        mTvError.setVisibility(View.VISIBLE);
    }

    @Override
    public void setBackground(BitmapDrawable ob) {
        mBackground.setBackground(ob);
    }
}
