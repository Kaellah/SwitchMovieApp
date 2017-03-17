package com.kaellah.switchmovieapp.other;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.kaellah.switchmovieapp.model.api.ApiConstant;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 * @since 16.03.17
 */

public class Utils {

    @NonNull
    public static String getTag(@NonNull Object object) {
        return object.getClass().getSimpleName();
    }

    public static void showMessage(@Nullable View view, @Nullable CharSequence message) {
        if (!TextUtils.isEmpty(message) && view != null && view.getParent() != null) {
            Toast.makeText(view.getContext(), message, Toast.LENGTH_LONG).show();
        }
    }

    public static String getCorrectImageUrl(String posterPath) {
        return String.format(ApiConstant.FORMAT_IMAGE_URL, posterPath);
    }
}
