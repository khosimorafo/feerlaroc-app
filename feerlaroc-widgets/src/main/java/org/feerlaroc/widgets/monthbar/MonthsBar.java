package org.feerlaroc.widgets.monthbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.feerlaroc.widgets.R;
import org.feerlaroc.widgets.rangebar.PinView;
import org.feerlaroc.widgets.rangebar.RangeBar;
import org.feerlaroc.widgets.utils.Month;
import org.feerlaroc.widgets.utils.Pair;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class MonthsBar extends RangeBar {

    public static final float DEFAULT_TEXTVIEW_FONT_SP = 36;

    private static final int DEFAULT_TEXTVIEW_COLOR = Color.BLUE;

    private int mTextViewTextColor = DEFAULT_TEXTVIEW_COLOR;

    private float mTextViewTextSize = DEFAULT_TEXTVIEW_FONT_SP;

    float mDensity = getResources().getDisplayMetrics().density;

    private LinearLayout mLinearLayout;
    private TextView mTextView;
    private List<Month> mMonths = new ArrayList<>();
    protected List<Thumb> mThumbList = new ArrayList<>();


    private PinView mThumbTemp;
    private Double mAmountOutstanding;
    private Canvas mCanvas;

    public MonthsBar(Context context) {
        super(context);
    }

    public MonthsBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initMonthBar(context, attrs);
    }

    public MonthsBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initMonthBar(context, attrs);
    }

    /**
     * Create thumbs based of a number of Months. 4 is the default
     *
     *
     */
    public void init(List<Month> months, Double amountOutstanding) {

        //Set tick range
        setTickStart(0);
        setTickEnd(3);
        mMonths = months;
        mAmountOutstanding = amountOutstanding;

    }

    private void createThumbs(){

        if(mMonths != null && mMonths.size() > 0) {

            mThumbTemp = createGenericThumb();
            List<Pair> mBarTickCoordinates = mBar.getTickCoordinates();
            Iterator iterator = mBarTickCoordinates.iterator();

            mThumbList.clear();
            int mMonthLocation = 0;
            Pair pair;


            while (iterator.hasNext()) {

                Thumb thumb = new Thumb();

                pair = (Pair) iterator.next();
                mThumbTemp.setX((Float) pair.getX());
                mThumbTemp.setY((Float) pair.getY());
                mThumbTemp.setXValue(String.valueOf(mMonths.get(mMonthLocation).mShortName));

                thumb.setMonth(mMonths.get(mMonthLocation));
                thumb.setCoordinates(pair);
                thumb.setStatus(mMonths.get(mMonthLocation).getStatus());

                mThumbList.add(thumb);
                mMonthLocation++;

            }

        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mCanvas = canvas;

        createThumbs();
        drawThumbs();
        drawText();

    }

    private void drawText(){

        mLinearLayout = new LinearLayout(getContext());
        mLinearLayout.measure(mCanvas.getWidth(), (int) (100 * mDensity));
        mLinearLayout.layout(0, 0, mCanvas.getWidth(), (int) (100 * mDensity));

        mTextView = new TextView(getContext());
        mTextView.setVisibility(View.VISIBLE);
        mTextView.setText(String.valueOf(mAmountOutstanding));
        mTextView.setTextColor(mTextViewTextColor);
        mTextView.setTextSize(mTextViewTextSize);
        mTextView.measure(mLinearLayout.getWidth(), mLinearLayout.getHeight());
        mTextView.layout(0, 0, mCanvas.getWidth(), (int) (100 * mDensity));

        mLinearLayout.addView(mTextView);
        mTextView.setTextAlignment(TEXT_ALIGNMENT_VIEW_END);

        mLinearLayout.draw(mCanvas);

    }

    private void drawThumbs() {
        int x = 0;

        for (Thumb thumb : mThumbList){
            x++;
            PinView view = mRightThumb;
            view.setX((Float) thumb.getCoordinates().getX());
            view.setXValue(String.valueOf(thumb.getMonth().mShortName));
            view.draw(mCanvas);
        }

    }

    private void initMonthBar(Context context, AttributeSet attrs){

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MonthsBar, 0, 0);

        try {

            mTextViewTextColor = ta.getColor(R.styleable.MonthsBar_textViewTextColor, DEFAULT_TEXTVIEW_COLOR);
            mTextViewTextSize = ta.getDimension(R.styleable.MonthsBar_textViewTextSize,
                    DEFAULT_TEXTVIEW_FONT_SP * mDensity);

        } finally {
            ta.recycle();
        }


    }

    public void setAmountOutstanding(Double amount){

        mAmountOutstanding = amount;

    }
}
