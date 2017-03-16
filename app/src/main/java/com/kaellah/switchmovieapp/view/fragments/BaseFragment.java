package com.kaellah.switchmovieapp.view.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.kaellah.switchmovieapp.R;
import com.kaellah.switchmovieapp.presenter.Presenter;
import com.kaellah.switchmovieapp.view.ActivityCallback;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 * @since 15.03.17
 */

public abstract class BaseFragment extends Fragment
        implements View {

    protected ActivityCallback mActivityCallback;

    protected abstract Presenter getPresenter();

    @Bind(R.id.toolbar)
    protected Toolbar mToolbar;

    @Nullable
    @Override
    public android.view.View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final android.view.View view = inflater.inflate(getLayoutResId(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(android.view.View v, @Nullable Bundle b) {
        super.onViewCreated(v, b);
    }

    @Override
    public void onStart() {
        super.onStart();

//        final Context context = getContext();
//        final ActionBar bar = context instanceof AppCompatActivity ? ((AppCompatActivity) context).getSupportActionBar() : null;
//
//        if (bar != null) {
//            onActionBarReady(bar);
//        }
        // FIXME
//        if (mToolbar != null) {
//            final Context context = getContext();
//            if (context instanceof AppCompatActivity) {
//                onActionBarReady(mToolbar);
//                ((AppCompatActivity) context).setSupportActionBar(mToolbar);
//            }
//        }
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (getPresenter() != null) {
            getPresenter().onStop();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mActivityCallback = (ActivityCallback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement activityCallback");
        }
    }

    @Override
    public void showLoading() {
        mActivityCallback.showProgressBar();
    }

    @Override
    public void hideLoading() {
        mActivityCallback.hideProgressBar();
    }

    @Nullable
    protected CharSequence getToolbarTitle() {
        @StringRes final int stringRes = getToolbarTitleRes();
        return stringRes != 0 ? super.getText(stringRes) : null;
    }

    @StringRes
    protected int getToolbarTitleRes() {
        return 0;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected final void onBackPressed() {
        final Context context = getContext();
        if (context instanceof Activity) {
            ((Activity) context).onBackPressed();
        }
    }

    @DrawableRes
    protected int getToolbarIndicator() {
        return 0;
    }

    @CallSuper
    protected void onActionBarReady(@NonNull ActionBar bar) {
        bar.setTitle(getToolbarTitle());

        @DrawableRes final int indicator = getToolbarIndicator();
        if (indicator == 0) {
            bar.setDisplayHomeAsUpEnabled(false);
            bar.setDisplayShowHomeEnabled(false);
        } else {
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setDisplayShowHomeEnabled(true);
            bar.setHomeAsUpIndicator(indicator);
        }
    }

    @LayoutRes
    protected abstract int getLayoutResId();
}