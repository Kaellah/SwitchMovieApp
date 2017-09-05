package com.kaellah.switchmovieapp.view.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.kaellah.switchmovieapp.R;
import com.kaellah.switchmovieapp.other.di.view.DaggerMovieItemViewComponent;
import com.kaellah.switchmovieapp.other.di.view.MovieItemViewComponent;
import com.kaellah.switchmovieapp.other.di.view.MovieItemViewDynamicModule;
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

    @Inject
    protected MovieItemPresenter mPresenter;

    private MovieItemViewComponent mViewComponent;

    @BindDrawable(R.drawable.placeholder)
    protected Drawable mPlaceholder;

    @Bind(R.id.iv_movie)
    protected ImageView mImageView;

    @OnClick(R.id.iv_movie)
    public void onClickMovie(View v) {
        EventBus.getDefault().post(mPresenter.getMovie());
    }

    public MovieItemView(Context context) {
        super(context);

        if (mViewComponent == null) {
            mViewComponent = DaggerMovieItemViewComponent.builder()
                    .movieItemViewDynamicModule(new MovieItemViewDynamicModule(this, context))
                    .build();
        }
        mViewComponent.inject(this);

        {
            inflate(context, R.layout.view_item_movie, this);
        }
        ButterKnife.bind(this);
    }

    @Override
    public void setLayoutParams(ViewGroup.LayoutParams params) {
        super.setLayoutParams(mPresenter.getLayoutParams(params));
    }

    @Override
    public void setData(@NonNull Movie movie, @NonNull Object... objects) {
        mPresenter.setData(movie, mPlaceholder, mImageView);
    }

    @Override
    public boolean isInEditMode() {
        return super.isInEditMode();
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
}
