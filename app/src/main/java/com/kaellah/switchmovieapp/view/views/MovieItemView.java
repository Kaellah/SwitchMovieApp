package com.kaellah.switchmovieapp.view.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.kaellah.switchmovieapp.R;
import com.kaellah.switchmovieapp.other.AConstant;
import com.kaellah.switchmovieapp.other.Utils;
import com.kaellah.switchmovieapp.other.di.view.MovieViewComponent;
import com.kaellah.switchmovieapp.presenter.MovieItemPresenter;
import com.kaellah.switchmovieapp.presenter.vo.Movie;
import com.kaellah.switchmovieapp.view.adapters.DataEntity;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.BindDrawable;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 * @since 17.03.17
 */

@SuppressLint("ViewConstructor")
public class MovieItemView extends FrameLayout
        implements DataEntity<Movie, MovieItemView>, IMovieItemView {

    // VALUES
    private final RequestManager mGlide;
    private final int mHeight;
    private Movie mMovie;

    @Inject
    protected MovieItemPresenter mPresenter;
    private MovieViewComponent mViewComponent;

    @BindDrawable(R.drawable.placeholder)
    protected Drawable mPlaceholder;
    @Bind(R.id.iv_movie)
    protected ImageView mImageView;
    @OnClick(R.id.iv_movie)
    public void onClickMovie(View v) {
        EventBus.getDefault().post(mMovie);
    }

    public MovieItemView(Context context) {
        super(context);
        {
            inflate(context, R.layout.view_item_movie, this);
        }
        ButterKnife.bind(this);

        mGlide = isInEditMode() ? null : Glide.with(context);

        final int screenHeight = Utils.getScreenSize(context).y;
        final int scH = screenHeight / 3;
        mHeight = scH < AConstant.IMAGE_HEIGHT ? scH : AConstant.IMAGE_HEIGHT;
    }

    @Override
    public void setLayoutParams(ViewGroup.LayoutParams params) {
        params.width = LayoutParams.MATCH_PARENT;
        params.height = mHeight;

        super.setLayoutParams(params);
    }

    @Override
    public void setData(@NonNull Movie movie, @NonNull Object... objects) {
        mMovie = movie;

        final String url = Utils.getCorrectImageUrl(movie.getPosterPath(), AConstant.IMAGE_WIDTH);
        mGlide
                .load(url)
                .placeholder(mPlaceholder)
                .into(mImageView);
    }

    @NonNull
    @Override
    public MovieItemView getSelf() {
        return this;
    }

    @Override
    public void showError(String error) {
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {
    }

    @Override
    public void showMovieInfo(@NonNull Movie movie) {
    }
}
