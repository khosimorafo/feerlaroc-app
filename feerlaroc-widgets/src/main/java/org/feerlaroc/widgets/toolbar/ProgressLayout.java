package org.feerlaroc.widgets.toolbar;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import org.feerlaroc.widgets.R;

/**
 * Created by Maxim Dybarsky | maxim.dybarskyy@gmail.com
 * on 13.01.15 at 17:34
 */
public class ProgressLayout extends FrameLayout {

    private static final int DEFAULT_COUNT = 5;
    private static final int DEFAULT_SPOTS_COLOR = Color.BLACK;
    private int mSpotsCount;
    private int mSpotsColor;

    public ProgressLayout(Context context) {
        this(context, null);
    }

    public ProgressLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ProgressLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs, defStyleAttr, defStyleRes);
    }

    private void init(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.ProgressToolbar,
                defStyleAttr, defStyleRes);

        mSpotsCount = a.getInt(R.styleable.ProgressToolbar_progressBarSpotCount, DEFAULT_COUNT);
        mSpotsColor = a.getInt(R.styleable.ProgressToolbar_progressBarSpotColor, DEFAULT_SPOTS_COLOR);

        a.recycle();
    }

    public int getSpotsCount() {
        return mSpotsCount;
    }


}
