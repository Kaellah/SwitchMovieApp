package com.kaellah.switchmovieapp.view.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.kaellah.switchmovieapp.R;
import com.kaellah.switchmovieapp.other.Utils;
import com.kaellah.switchmovieapp.presenter.Presenter;
import com.kaellah.switchmovieapp.view.activities.BaseActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 * @since 15.03.17
 */

public abstract class BaseFragment extends Fragment
        implements View {

    @Bind(R.id.toolbar)
    protected Toolbar mToolbar;

    private SwipeRefreshLayout mRefreshLayout;

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

        android.view.View refreshLayout = ButterKnife.findById(v, R.id.refresh_layout);
        if (refreshLayout == null) {
            final Context context = getContext();
            if (context instanceof BaseActivity) {
                refreshLayout = ((BaseActivity) context).getRefreshLayout();
            }
        }
        if (refreshLayout instanceof SwipeRefreshLayout) {
            mRefreshLayout = (SwipeRefreshLayout) refreshLayout;

            Utils.style(mRefreshLayout);
            if (this instanceof SwipeRefreshLayout.OnRefreshListener) {
                mRefreshLayout.setOnRefreshListener((SwipeRefreshLayout.OnRefreshListener) this);
                mRefreshLayout.setEnabled(true);

            } else {
                mRefreshLayout.setOnRefreshListener(null);
                mRefreshLayout.setEnabled(false);
            }
        }
    }

    @Override
    public void onStart() {
        if (mToolbar != null) {
            mToolbar.setTitle(getToolbarTitle());

            final Context context = getContext();
            if (context instanceof AppCompatActivity) {
                ((AppCompatActivity) context).setSupportActionBar(mToolbar);
            }
        }

        if (hasSubscribe()) {
            EventBus.getDefault().register(this);
        }
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (getPresenter() != null) {
            getPresenter().onStop();
        }

        if (hasSubscribe()) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public void onDestroyView() {
        if (mRefreshLayout != null) {
            Utils.cleanUp(mRefreshLayout);
            mRefreshLayout = null;
        }
        super.onDestroyView();

        ButterKnife.unbind(this);
        try {
            onCleanUp();

        } catch (Exception ignore) {

        } finally {
            System.gc();
        }
    }

    public void setRefreshing(boolean refreshing) {
        if (mRefreshLayout == null) {
            final Context context = getContext();
            if (context instanceof BaseActivity) {
                ((BaseActivity) context).setRefreshing(refreshing);
            }

        } else {
            Utils.setRefreshing(mRefreshLayout, refreshing);
        }
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

    @LayoutRes
    protected abstract int getLayoutResId();

    protected abstract Presenter getPresenter();

    protected void startFragment(@NonNull BaseFragment fragment, boolean addToBackStack) {
        final Context context = getContext();
        if (context instanceof BaseActivity) {
            ((BaseActivity) context).startFragment(fragment, addToBackStack);
        }
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

    protected boolean hasSubscribe() {
        return false;
    }

    protected abstract void onCleanUp() throws Exception;
}
