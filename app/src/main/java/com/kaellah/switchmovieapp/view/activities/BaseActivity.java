package com.kaellah.switchmovieapp.view.activities;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.kaellah.switchmovieapp.other.Utils;
import com.kaellah.switchmovieapp.view.fragments.BaseFragment;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 * @since 15.03.17
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle b) {
        super.onCreate(b);
    }

    @IdRes
    public abstract int getFragmentContainerId();

    public boolean startFragment(@NonNull BaseFragment fragment, boolean addToBackStack) {
        return startFragmentSimple(getFragmentContainerId(), fragment, addToBackStack);
    }

    protected final boolean startFragmentSimple(int containerId, @NonNull Fragment fragment, boolean addToBackStack) {
        final FragmentManager manager = getSupportFragmentManager();
        if (!addToBackStack) {
            manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        final FragmentTransaction transaction = manager.beginTransaction();

        final String tag = Utils.getTag(fragment);
        if (addToBackStack) {
            transaction.addToBackStack(tag);
        }
        transaction.replace(containerId, fragment, tag).commit();
        return true;
    }
}
