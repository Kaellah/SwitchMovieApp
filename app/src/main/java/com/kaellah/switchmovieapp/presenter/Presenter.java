package com.kaellah.switchmovieapp.presenter;

import android.os.Bundle;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 * @since 16.03.17
 */

public interface Presenter {

    void onStop();

    void onViewCreated(Bundle b);

    void onSaveInstanceState(Bundle outState);
}
