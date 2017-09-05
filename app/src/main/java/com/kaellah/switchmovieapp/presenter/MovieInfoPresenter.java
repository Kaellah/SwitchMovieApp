package com.kaellah.switchmovieapp.presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.kaellah.switchmovieapp.other.AConstant;
import com.kaellah.switchmovieapp.other.App;
import com.kaellah.switchmovieapp.other.ImageUtils;
import com.kaellah.switchmovieapp.other.Utils;
import com.kaellah.switchmovieapp.presenter.vo.Movie;
import com.kaellah.switchmovieapp.view.fragments.MovieInfoView;
import com.kaellah.switchmovieapp.view.fragments.View;

import javax.inject.Inject;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 * @since 16.03.17
 */

public class MovieInfoPresenter extends BasePresenter {

    private MovieInfoView mView;
    private Movie mMovie;
    private final RequestManager mGlide;
    private final Context mContext;

    @Inject
    public MovieInfoPresenter(MovieInfoView view, @NonNull Context context) {
        App.getComponent().inject(this);
        mView = view;
        mGlide = Glide.with(context);
        mContext = context;
    }

    @Override
    public void onViewCreated(Bundle b) {
        if (b != null) {
            mMovie = b.getParcelable(AConstant.EXTRA_MOVIE);
            mView.showMovieInfo(mMovie);

        } else {
            mView.showError("No data");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (mMovie != null) {
            outState.putParcelable(AConstant.EXTRA_MOVIE, mMovie);
        }
    }

    public Movie getMovie() {
        return mMovie;
    }

    @Override
    protected View getView() {
        return mView;
    }

    public void loadImage(Drawable placeholder, ImageView ivMovie) {
        mGlide
                .load(Utils.getCorrectImageUrl(mMovie.getPosterPath(), AConstant.IMAGE_WIDTH))
                .asBitmap()
                .placeholder(placeholder)
                .listener(mRequestListener)
                .into(ivMovie);
    }

    private final RequestListener<String, Bitmap> mRequestListener = new RequestListener<String, Bitmap>() {
        @Override
        public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
            mView.showError("No data");
            return false;
        }

        @Override
        public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
            final Bitmap blurBitmap = ImageUtils.fastBlur(resource);
            final BitmapDrawable ob = new BitmapDrawable(mContext.getResources(), blurBitmap);
            mView.setBackground(ob);
            return false;
        }
    };

    @NonNull
    public LinearLayout.LayoutParams getLayoutParams(@NonNull LinearLayout.LayoutParams params) {
        params.weight = Utils.isLand(mContext) ? 3f : 1.5f;

        final int screenHeight = Utils.getScreenSize(mContext).y;
        final int scH = (int) (screenHeight / (Utils.isLand(mContext) ? 1.66 : 3.25));
        params.height = scH < AConstant.IMAGE_HEIGHT ? scH : AConstant.IMAGE_HEIGHT;

        return params;
    }
}
