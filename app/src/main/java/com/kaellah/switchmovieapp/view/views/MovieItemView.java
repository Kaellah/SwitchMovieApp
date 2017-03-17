package com.kaellah.switchmovieapp.view.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.kaellah.switchmovieapp.R;
import com.kaellah.switchmovieapp.other.Utils;
import com.kaellah.switchmovieapp.presenter.MovieListPresenter;
import com.kaellah.switchmovieapp.presenter.vo.Movie;
import com.kaellah.switchmovieapp.view.adapters.DataEntity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 * @since 17.03.17
 */

@SuppressLint("ViewConstructor")
public class MovieItemView extends FrameLayout
        implements DataEntity<Movie, MovieItemView> {

    // VALUES
    private final RequestManager mGlide;
    private Movie mMovie;

    // VIEW
    @Bind(R.id.iv_movie)
    protected ImageView mImageView;

    // PRESENTER
    private final MovieListPresenter mPresenter;

    public MovieItemView(Context context, MovieListPresenter presenter) {
//    public MovieItemView(Context context) {
        super(context);

        {
            inflate(context, R.layout.view_item_movie, this);
        }
        ButterKnife.bind(this);

        mGlide = isInEditMode() ? null : Glide.with(context);
        mPresenter = presenter;

        this.setOnClickListener(view -> mPresenter.clickMovie(mMovie));
    }

    @Override
    public void setLayoutParams(ViewGroup.LayoutParams params) {
        params.width = LayoutParams.MATCH_PARENT;
        params.height = 1000; // TODO

        super.setLayoutParams(params);
    }

    @Override
    public void setData(@NonNull Movie movie, @NonNull Object... objects) {
        mMovie = movie;

        final String url = Utils.getCorrectImageUrl(movie.getPosterPath());
//        mGlide
////                .load(url).asBitmap().centerCrop().into(new BitmapImageViewTarget(this) {
////            @Override
////            protected void setResource(Bitmap resource) {
////                final RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(getContext().getResources(), resource);
////                circularBitmapDrawable.setCircular(true);
////                setImageDrawable(circularBitmapDrawable);
////            }
////        });
//                .load(url)
//                .into(this);

        mGlide
                .load(url)
                .into(mImageView);
    }

    @NonNull
    @Override
    public MovieItemView getSelf() {
        return this;
    }
}
