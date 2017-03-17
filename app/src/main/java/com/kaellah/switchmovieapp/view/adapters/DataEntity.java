package com.kaellah.switchmovieapp.view.adapters;

import android.support.annotation.NonNull;

public interface DataEntity<DATA, SELF> {
    void setData(@NonNull DATA data, @NonNull Object... objects);

    @NonNull
    SELF getSelf();
}