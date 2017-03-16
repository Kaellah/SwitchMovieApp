package com.kaellah.switchmovieapp.other;

import android.support.annotation.NonNull;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 * @since 16.03.17
 */

public class Utils {

    @NonNull
    public static String getTag(@NonNull Object object) {
        return object.getClass().getSimpleName();
    }
}
