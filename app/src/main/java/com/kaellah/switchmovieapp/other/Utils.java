package com.kaellah.switchmovieapp.other;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.Pools;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.kaellah.switchmovieapp.model.api.ApiConstant;

import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 * @since 16.03.17
 */

public class Utils {

    private static final SimpleDateFormat FORMAT_INCOMING = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    private static final SimpleDateFormat FORMAT_FINISH = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);

    @NonNull
    public static String getTag(@NonNull Object object) {
        return object.getClass().getSimpleName();
    }

    public static void showMessage(@Nullable View view, @Nullable CharSequence message) {
        if (!TextUtils.isEmpty(message) && view != null && view.getParent() != null) {
            Toast.makeText(view.getContext(), message, Toast.LENGTH_LONG).show();
        }
    }

    public static String getCorrectImageUrl(String posterPath, int width) {
        return String.format(Locale.getDefault(), ApiConstant.FORMAT_IMAGE_URL, width, posterPath);
    }

    @ColorInt
    private static int[] sRefreshColors;

    public static void style(@NonNull SwipeRefreshLayout layout) {
        if (sRefreshColors == null) {
            sRefreshColors = new int[3];
            sRefreshColors[0] = Color.RED;
            sRefreshColors[1] = Color.GREEN;
            sRefreshColors[2] = Color.BLUE;
        }
        layout.setColorSchemeColors(sRefreshColors);
    }

    public static void cleanUp(@NonNull View view) {
        view.destroyDrawingCache();
        view.clearAnimation();

        cleanUp(view.getBackground());

        view.setBackground(null);
        view.setOnTouchListener(null);
        view.setOnClickListener(null);
        view.setOnLongClickListener(null);
    }

    public static void cleanUp(@NonNull TextView view) {
        cleanUp((View) view);
        view.setCompoundDrawables(null, null, null, null);
    }

    public static void cleanUp(@NonNull EditText view) {
        cleanUp((TextView) view);
        view.setOnEditorActionListener(null);
    }

    public static void cleanUp(@NonNull ViewGroup view) {
        view.destroyDrawingCache();
        view.clearAnimation();

        cleanUp(view.getBackground());

        view.setBackground(null);
        view.setOnTouchListener(null);
        view.setOnLongClickListener(null);

        final int size = view.getChildCount();
        for (int i = 0; i < size; i++) {
            cleanUp(view.getChildAt(i));
        }
        view.removeAllViewsInLayout();
    }

    public static void cleanUp(@NonNull ListView view) {
        cleanUp((ViewGroup) view);
        view.setAdapter(null);
        view.setOnScrollListener(null);
        view.setOnItemClickListener(null);
    }

    public static void cleanUp(@NonNull SwipeRefreshLayout view) {
        view.setOnRefreshListener(null);
        view.setRefreshing(false);
        view.setEnabled(false);

        view.destroyDrawingCache();
        view.clearAnimation();
    }

    @SuppressWarnings("deprecation")
    public static void cleanUp(@NonNull ViewPager view) {
        cleanUp((ViewGroup) view);
        view.setAdapter(null);
        view.setOnPageChangeListener(null);
    }

    public static void cleanUp(@NonNull ImageView view) {
        cleanUp((View) view);

        cleanUp(view.getDrawable());
        view.setImageDrawable(null);
    }

    public static void cleanUp(@NonNull SeekBar view) {
        cleanUp((View) view);

        cleanUp(view.getIndeterminateDrawable());
        cleanUp(view.getProgressDrawable());
        cleanUp(view.getThumb());
    }

    public static void cleanUp(@Nullable Drawable drawable) {
        if (drawable != null) {
            drawable.setCallback(null);
        }
    }

    public static void cleanUp(@Nullable Bitmap bitmap) {
        if (bitmap != null) {
            bitmap.recycle();
        }
    }


    public static String convertDate(@NonNull String dateString) {
        Date date;
        try {
            date = FORMAT_INCOMING.parse(dateString);

        } catch (ParseException e) {
            e.printStackTrace();
            return dateString;
        }

        return FORMAT_FINISH.format(date);
    }

    public static void setRefreshing(@Nullable SwipeRefreshLayout view, boolean refreshing) {
        if (view != null && view.getParent() != null) {
            RefreshingRunnable.post(view, refreshing);
        }
    }

    public static Point getScreenSize(@NonNull Context context) {
        final Point point = new Point();
        final WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getSize(point);
        return point;
    }

    private static final class RefreshingRunnable implements Runnable {

        private static final Pools.SynchronizedPool<RefreshingRunnable> POOL = new Pools.SynchronizedPool<>(4);

        private WeakReference<SwipeRefreshLayout> mView;
        private boolean mRefreshing;

        public static void post(@NonNull SwipeRefreshLayout view, boolean refreshing) {
            RefreshingRunnable run = POOL.acquire();
            if (run == null) {
                run = new RefreshingRunnable();
            }
            run.mView = new WeakReference<>(view);
            run.mRefreshing = refreshing;

            view.post(run);
        }

        @Override
        public void run() {
            try {
                final SwipeRefreshLayout view = mView.get();
                if (view != null && view.getParent() != null && view.isRefreshing() != mRefreshing) {
                    view.setRefreshing(mRefreshing);
                }

            } finally {
                mView.clear();
                POOL.release(this);
            }
        }
    }

    public static boolean isLand(@NonNull Context context) {
        return Configuration.ORIENTATION_LANDSCAPE == context.getResources().getConfiguration().orientation;
    }
}
