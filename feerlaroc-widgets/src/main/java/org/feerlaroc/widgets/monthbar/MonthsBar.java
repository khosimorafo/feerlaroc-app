package org.feerlaroc.widgets.monthbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.feerlaroc.widgets.rangebar.PinData;
import org.feerlaroc.widgets.rangebar.RangeBar;

import java.util.List;


public class MonthsBar extends RangeBar {

    public static final float DEFAULT_TEXTVIEW_FONT_SP = 36;

    private static final int DEFAULT_TEXTVIEW_COLOR = Color.BLUE;

    private int mTextViewTextColor = DEFAULT_TEXTVIEW_COLOR;

    private float mTextViewTextSize = DEFAULT_TEXTVIEW_FONT_SP;

    float mDensity = getResources().getDisplayMetrics().density;

    private LinearLayout mLinearLayout;
    private TextView mTextView;


    private Double mAmountOutstanding;
    private Canvas mCanvas;

    public MonthsBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MonthsBar(Context context, List<PinData> pinData, Double amountOutstanding) {

        super(context, pinData);
        setAmountOutstanding(amountOutstanding);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        mCanvas = canvas;
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


    public void setPinData(List<PinData> pinData){
        mPinData = pinData;
        createPin();
    }

    public void setAmountOutstanding(Double amount){

        mAmountOutstanding = amount;

    }
}
