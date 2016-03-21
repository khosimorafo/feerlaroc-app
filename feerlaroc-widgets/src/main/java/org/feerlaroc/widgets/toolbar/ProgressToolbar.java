package org.feerlaroc.widgets.toolbar;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ActionMenuView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import org.feerlaroc.widgets.R;

/**
 * Created by root on 2016/03/19.
 */
public class ProgressToolbar extends LinearLayout {

    private static final int PROGRESS_BAR_WIDTH               = 250;
    private static final int DELAY                            = 150;
    private static final int DURATION                         = 1500;
    private static final int PROGRESS_SPOTS_COLOR             = Color.LTGRAY;
    private static final int PROGRESS_BACKGROUND_COLOR        = Color.TRANSPARENT;
    private static final int PROGRESS_SPOTS_SIZE              = 6;

    private static final int TOOLBAR_HEIGHT                   = 250;

    private int mBarWidth;
    private float mDelay;
    private float mDuration;
    private int mSpotsColor;
    private int mBackgroundColor;
    private int mSpotsSize;

    private int mToolbarHeight;

    private boolean mIsProgressBar;

    private AnimatedView[] mSpots;
    private AnimatorPlayer mAnimator;

    FeerlarocToolBar mToolbar;
    ProgressLayout mProgressLayout;

    FeerlarocToolBar.FeerlarocToolbarClicked mListener;


    Context mContext;

    AttributeSet mAttrs = null;
    int mDefStyleAttr = 0;
    private Button mButton;

    public ProgressToolbar(Context context) {
        super(context); mContext = context;
        init();
    }

    public ProgressToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mAttrs = attrs;

        init();
    }

    public ProgressToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        mAttrs = attrs;
        mDefStyleAttr = defStyleAttr;

        init();

    }

    private void createToolbar(){

        LayoutParams params
                = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params.setMargins(16, 16, 16, 16);
        params.gravity = Gravity.CENTER;

        mToolbar = (FeerlarocToolBar) findViewById(R.id.toolbar);
        mToolbar.setLayoutParams(params);

        mToolbar.setBackgroundColor(mBackgroundColor);
        mToolbar.setVisibility(View.VISIBLE);
        mToolbar.setListener(mListener);

    }

    private void createProgress(){

        LayoutParams params
                = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        params.setMargins(16, 16, 16, 16);
        params.gravity = Gravity.CENTER;

        mProgressLayout = (ProgressLayout) findViewById(R.id.progress_layout);
        mProgressLayout.setLayoutParams(params);

        mSpotsSize = mProgressLayout.getSpotsCount();

        mSpots = new AnimatedView[mSpotsSize];
        int size = getContext().getResources().getDimensionPixelSize(R.dimen.spot_size);
        int progressWidth = getContext().getResources().getDimensionPixelSize(R.dimen.progress_width);
        for (int i = 0; i < mSpots.length; i++) {
            AnimatedView v = new AnimatedView(getContext());
            v.setBackgroundColor(Color.TRANSPARENT);
            v.setTarget(progressWidth);
            v.setXFactor(-1f);
            mProgressLayout.addView(v, size, size);
            mSpots[i] = v;
        }

    }

    private Animator[] createAnimations() {

        Animator[] animators = new Animator[mSpotsSize];
        for (int i = 0; i < mSpots.length; i++) {
            Animator move = ObjectAnimator.ofFloat(mSpots[i], "xFactor", 0, 1);
            move.setDuration((long) mDuration);
            move.setInterpolator(new HesitateInterpolator());
            move.setStartDelay((long) (mDelay * i));
            animators[i] = move;
        }

        return animators;
    }


    public void setListener(FeerlarocToolBar.FeerlarocToolbarClicked listener){
        mListener = listener;
        if(mToolbar!=null)
            mToolbar.setListener(mListener);
    }

    public void inflateMenu(Context context, int menu){

        ActionMenuView actionMenuViewLeft = (ActionMenuView) findViewById(R.id.action_menu_view_left);
        actionMenuViewLeft.getMenu().clear();
        ((AppCompatActivity)context).getMenuInflater().inflate(menu, actionMenuViewLeft.getMenu());


        if(mToolbar !=null){

            mToolbar.setMenuItemListeners();

        }

    }

    public void start() {

        mAnimator = new AnimatorPlayer(createAnimations());
        mAnimator.play();
    }

    public void stop() {

        mAnimator.stop();

    }



    private void init(){

        TypedArray ta = mContext.obtainStyledAttributes(mAttrs, R.styleable.ProgressToolbar, 0, 0);

        try {

            mBarWidth = ta.getInt(R.styleable.ProgressToolbar_progressBarWidth, PROGRESS_BAR_WIDTH);
            mSpotsSize = ta.getInt(R.styleable.ProgressToolbar_progressBarSpotCount, PROGRESS_SPOTS_SIZE);
            mBackgroundColor = ta.getColor(R.styleable.ProgressToolbar_backgroundColor, PROGRESS_BACKGROUND_COLOR);
            mSpotsColor = ta.getColor(R.styleable.ProgressToolbar_progressBarSpotColor, PROGRESS_SPOTS_COLOR);

            mDuration = ta.getFloat(R.styleable.ProgressToolbar_duration, DURATION);
            mDelay = ta.getFloat(R.styleable.ProgressToolbar_delay, DELAY);

            mIsProgressBar = ta.getBoolean(R.styleable.ProgressToolbar_progressBar, true);

            mToolbarHeight = ta.getInt(R.styleable.ProgressToolbar_toolBarHeight, TOOLBAR_HEIGHT);

        } finally {

            inflate(getContext(), R.layout.progress_toolbar, this);

            ta.recycle();

            createToolbar();

            if(mIsProgressBar)
                createProgress();
        }

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

}
