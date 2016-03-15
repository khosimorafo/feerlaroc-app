package org.feerlaroc.widgets.rangebar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import org.feerlaroc.widgets.R;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by root on 2016/03/06.
 */
public class RangeBar extends View {

    // Member Variables ////////////////////////////////////////////////////////

    private static final String TAG = "RangeBar";

    // Default values for variables
    private static final float DEFAULT_TICK_START = 0;

    private static final float DEFAULT_TICK_END = 3;

    private static final float DEFAULT_TICK_INTERVAL = 1;

    private static final float DEFAULT_TICK_HEIGHT_DP = 1;

    private static final float DEFAULT_PIN_PADDING_DP = 16;

    public static final float DEFAULT_MIN_PIN_FONT_SP = 8;

    public static final float DEFAULT_MAX_PIN_FONT_SP = 24;

    private static final float DEFAULT_BAR_WEIGHT_PX = 2;

    private static final int DEFAULT_BAR_COLOR = Color.LTGRAY;

    private static final int DEFAULT_TEXT_COLOR = Color.WHITE;

    private static final int DEFAULT_TICK_COLOR = Color.BLACK;

    // Corresponds to material indigo 500.
    private static final int DEFAULT_PIN_COLOR = 0xff3f51b5;

    private static final float DEFAULT_CONNECTING_LINE_WEIGHT_PX = 4;

    // Corresponds to material indigo 500.
    private static final int DEFAULT_CONNECTING_LINE_COLOR = 0xff3f51b5;

    private static final float DEFAULT_EXPANDED_PIN_RADIUS_DP = 15;

    private static final float DEFAULT_CIRCLE_SIZE_DP = 5;

    private static final float DEFAULT_BAR_PADDING_BOTTOM_DP = 24;

    // Instance variables for all of the customizable attributes

    private float mTickHeightDP = DEFAULT_TICK_HEIGHT_DP;

    private float mTickStart = DEFAULT_TICK_START;

    private float mTickEnd = DEFAULT_TICK_END;

    private float mTickInterval = DEFAULT_TICK_INTERVAL;

    private float mBarWeight = DEFAULT_BAR_WEIGHT_PX;

    private int mBarColor = DEFAULT_BAR_COLOR;

    private int mPinColor = DEFAULT_PIN_COLOR;

    private int mTextColor = DEFAULT_TEXT_COLOR;

    private float mConnectingLineWeight = DEFAULT_CONNECTING_LINE_WEIGHT_PX;

    private int mConnectingLineColor = DEFAULT_CONNECTING_LINE_COLOR;

    private float mThumbRadiusDP = DEFAULT_EXPANDED_PIN_RADIUS_DP;

    private int mTickColor = DEFAULT_TICK_COLOR;

    private float mExpandedPinRadius = DEFAULT_EXPANDED_PIN_RADIUS_DP;

    private int mCircleColor = DEFAULT_CONNECTING_LINE_COLOR;

    private float mCircleSize = DEFAULT_CIRCLE_SIZE_DP;

    private float mMinPinFont = DEFAULT_MIN_PIN_FONT_SP;

    private float mMaxPinFont = DEFAULT_MAX_PIN_FONT_SP;

    // setTickCount only resets indices before a thumb has been pressed or a
    // setThumbIndices() is called, to correspond with intended usage
    private boolean mFirstSetTickCount = true;

    private int mDefaultWidth = 500;

    private int mDefaultHeight = 200;

    private int mTickCount = (int) ((mTickEnd - mTickStart) / mTickInterval) + 1;

    protected Bar mBar;

    private ConnectingLine mConnectingLine;

    private OnRangeBarChangeListener mListener;

    private OnRangeBarTextListener mPinTextListener;

    private HashMap<Float, String> mTickMap;

    private int mLeftIndex;

    private int mRightIndex;

    private int mMiddleIndex;


    private boolean mIsRangeBar = false;

    private float mPinPadding = DEFAULT_PIN_PADDING_DP;

    private float mBarPaddingBottom = DEFAULT_BAR_PADDING_BOTTOM_DP;

    private int mActiveConnectingLineColor;

    private int mActiveBarColor;

    private int mActiveTickColor;

    private int mActiveCircleColor;

    //Used for ignoring vertical moves
    private int mDiffX;

    private int mDiffY;

    private float mLastX;

    private float mLastY;

    private PinView mRightThumb[] = new PinView[4];

    protected List<PinData> mPinData = null;

    private IRangeBarFormatter mFormatter;

    private boolean drawTicks = true;

    private boolean mArePinsTemporary = false;

    public boolean mIsViewMeasured = false;

    private PinTextFormatter mPinTextFormatter = new PinTextFormatter() {
        @Override
        public String getText(String value) {
            if (value.length() > 4) {
                return value.substring(0, 4);
            } else {
                return value;
            }
        }
    };
    private int mH;
    private int mW;
    private Canvas mCanvas;
    private OnRangeBarCreated mBarCreatedListener;


    public RangeBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);

    }

    public RangeBar(Context context, List<PinData> pinData) {
        super(context, null);
        mPinData = pinData;
        init(context, null);
    }

    public RangeBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);

    }


    // View Methods ////////////////////////////////////////////////////////////

    @Override
    public Parcelable onSaveInstanceState() {

        Bundle bundle = new Bundle();

        bundle.putParcelable("instanceState", super.onSaveInstanceState());

        bundle.putInt("TICK_COUNT", mTickCount);
        bundle.putFloat("TICK_START", mTickStart);
        bundle.putFloat("TICK_END", mTickEnd);
        bundle.putFloat("TICK_INTERVAL", mTickInterval);
        bundle.putInt("TICK_COLOR", mTickColor);

        bundle.putFloat("TICK_HEIGHT_DP", mTickHeightDP);
        bundle.putFloat("BAR_WEIGHT", mBarWeight);
        bundle.putInt("BAR_COLOR", mBarColor);
        bundle.putFloat("CONNECTING_LINE_WEIGHT", mConnectingLineWeight);
        bundle.putInt("CONNECTING_LINE_COLOR", mConnectingLineColor);

        bundle.putFloat("CIRCLE_SIZE", mCircleSize);
        bundle.putInt("CIRCLE_COLOR", mCircleColor);
        bundle.putFloat("THUMB_RADIUS_DP", mThumbRadiusDP);
        bundle.putFloat("EXPANDED_PIN_RADIUS_DP", mExpandedPinRadius);
        bundle.putFloat("PIN_PADDING", mPinPadding);
        bundle.putFloat("BAR_PADDING_BOTTOM", mBarPaddingBottom);
        bundle.putBoolean("IS_RANGE_BAR", mIsRangeBar);
        bundle.putBoolean("ARE_PINS_TEMPORARY", mArePinsTemporary);
        bundle.putInt("LEFT_INDEX", mLeftIndex);
        bundle.putInt("RIGHT_INDEX", mRightIndex);

        bundle.putBoolean("FIRST_SET_TICK_COUNT", mFirstSetTickCount);

        bundle.putFloat("MIN_PIN_FONT", mMinPinFont);
        bundle.putFloat("MAX_PIN_FONT", mMaxPinFont);

        return bundle;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {

        if (state instanceof Bundle) {

            Bundle bundle = (Bundle) state;

            mTickCount = bundle.getInt("TICK_COUNT");
            mTickStart = bundle.getFloat("TICK_START");
            mTickEnd = bundle.getFloat("TICK_END");
            mTickInterval = bundle.getFloat("TICK_INTERVAL");
            mTickColor = bundle.getInt("TICK_COLOR");
            mTickHeightDP = bundle.getFloat("TICK_HEIGHT_DP");
            mBarWeight = bundle.getFloat("BAR_WEIGHT");
            mBarColor = bundle.getInt("BAR_COLOR");
            mCircleSize = bundle.getFloat("CIRCLE_SIZE");
            mCircleColor = bundle.getInt("CIRCLE_COLOR");
            mConnectingLineWeight = bundle.getFloat("CONNECTING_LINE_WEIGHT");
            mConnectingLineColor = bundle.getInt("CONNECTING_LINE_COLOR");

            mThumbRadiusDP = bundle.getFloat("THUMB_RADIUS_DP");
            mExpandedPinRadius = bundle.getFloat("EXPANDED_PIN_RADIUS_DP");
            mPinPadding = bundle.getFloat("PIN_PADDING");
            mBarPaddingBottom = bundle.getFloat("BAR_PADDING_BOTTOM");
            mIsRangeBar = bundle.getBoolean("IS_RANGE_BAR");
            mArePinsTemporary = bundle.getBoolean("ARE_PINS_TEMPORARY");

            mLeftIndex = bundle.getInt("LEFT_INDEX");
            mRightIndex = bundle.getInt("RIGHT_INDEX");
            mFirstSetTickCount = bundle.getBoolean("FIRST_SET_TICK_COUNT");

            mMinPinFont = bundle.getFloat("MIN_PIN_FONT");
            mMaxPinFont = bundle.getFloat("MAX_PIN_FONT");

            setRangePinsByIndices(mLeftIndex, mRightIndex);
            super.onRestoreInstanceState(bundle.getParcelable("instanceState"));

        } else {
            super.onRestoreInstanceState(state);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int width;
        int height;

        // Get measureSpec mode and size values.
        final int measureWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        final int measureHeightMode = MeasureSpec.getMode(heightMeasureSpec);
        final int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        final int measureHeight = MeasureSpec.getSize(heightMeasureSpec);

        // The RangeBar width should be as large as possible.
        if (measureWidthMode == MeasureSpec.AT_MOST) {
            width = measureWidth;
        } else if (measureWidthMode == MeasureSpec.EXACTLY) {
            width = measureWidth;
        } else {
            width = mDefaultWidth;
        }

        // The RangeBar height should be as small as possible.
        if (measureHeightMode == MeasureSpec.AT_MOST) {
            height = Math.min(mDefaultHeight, measureHeight);
        } else if (measureHeightMode == MeasureSpec.EXACTLY) {
            height = measureHeight;
        } else {
            height = mDefaultHeight;
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        super.onSizeChanged(w, h, oldw, oldh);

        mH = h;
        mW = w;
        mIsViewMeasured = true;
        createPin();


    }


    protected void createPin(){

        final Context ctx = getContext();

        // This is the initial point at which we know the size of the View.

        // Create the two thumb objects and position line in view
        float density = getResources().getDisplayMetrics().density;
        float expandedPinRadius = mExpandedPinRadius / density;

        final float yPos = mH - mBarPaddingBottom;

        // Create the underlying bar.
        final float marginLeft = Math.max(mExpandedPinRadius, mCircleSize);

        final float barLength = mW - (2 * marginLeft);
        mBar = new Bar(ctx, marginLeft, yPos, barLength, mTickCount, mTickHeightDP, mTickColor,
                mBarWeight, mBarColor);

        List list = Arrays.asList(mRightThumb);

        for (int i=0; i<list.size(); i++){

            mRightThumb[i] = new PinView(ctx);
            mRightThumb[i].setFormatter(mFormatter);

            mPinColor = mPinData.get(i).getColor();
            mRightThumb[i].init(ctx, yPos, expandedPinRadius, mPinColor, mTextColor, mCircleSize,
                    mCircleColor, mMinPinFont, mMaxPinFont, mArePinsTemporary);

            mRightThumb[i].setX((Float) mBar.getTickCoordinates().get(i).getX());
            mRightThumb[i].setXValue(mPinData.get(i).getXValue());
            mRightThumb[i].setTag(String.valueOf(mPinData.get(i).getTag()));
            mRightThumb[i].setId(mPinData.get(i).ID());
            //mRightThumb[i].setOnClickListener(this);

        }

        notifyBarCreatedListeners();

    }

    public void setPinListener(OnPinClick listener){

        List list = Arrays.asList(mRightThumb);

        for (int i=0; i<list.size(); i++){

            //mRightThumb[i].

        }
    }

    public void drawPins(){

        List list = Arrays.asList(mRightThumb);

        for (int i=0; i<list.size(); i++){

            mRightThumb[i].invalidate();
            mRightThumb[i].draw(mCanvas);

        }

    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        mBar.draw(canvas);
        if(drawTicks) {
            mBar.drawTicks(canvas);
        }
        mCanvas = canvas;

        drawPins();

    }

    public void updatePin(int position){

        createPin();
        mRightThumb[position].setXValue("Y");
        mRightThumb[position].invalidate();
        invalidate();
    }

    private void init(Context context, AttributeSet attrs){

        if (mTickMap == null) {
            mTickMap = new HashMap<Float, String>();
        }

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RangeBar, 0, 0);

        try {

            // Sets the values of the user-defined attributes based on the XML
            // attributes.
            final float tickStart = ta
                    .getFloat(R.styleable.RangeBar_tickStart, DEFAULT_TICK_START);
            final float tickEnd = ta
                    .getFloat(R.styleable.RangeBar_tickEnd, DEFAULT_TICK_END);
            final float tickInterval = ta
                    .getFloat(R.styleable.RangeBar_tickInterval, DEFAULT_TICK_INTERVAL);
            int tickCount = (int) ((tickEnd - tickStart) / tickInterval) + 1;

            if (isValidTickCount(tickCount)) {

                // Similar functions performed above in setTickCount; make sure
                // you know how they interact
                mTickCount = tickCount;
                mTickStart = tickStart;
                mTickEnd = tickEnd;
                mTickInterval = tickInterval;
                mLeftIndex = 0;
                mRightIndex = mTickCount - 1;
                mMiddleIndex = mTickCount - 3;

                if (mListener != null) {
                    mListener.onRangeChangeListener(this, mLeftIndex, mRightIndex,
                            getPinValue(mLeftIndex),
                            getPinValue(mRightIndex));
                }

            } else {

                Log.e(TAG, "tickCount less than 2; invalid tickCount. XML input ignored.");
            }

            mTickHeightDP = ta
                    .getDimension(R.styleable.RangeBar_tickHeight, DEFAULT_TICK_HEIGHT_DP);
            mBarWeight = ta.getDimension(R.styleable.RangeBar_barWeight, DEFAULT_BAR_WEIGHT_PX);
            mBarColor = ta.getColor(R.styleable.RangeBar_rangeBarColor, DEFAULT_BAR_COLOR);
            mTextColor = ta.getColor(R.styleable.RangeBar_textColor, DEFAULT_TEXT_COLOR);
            mPinColor = ta.getColor(R.styleable.RangeBar_pinColor, DEFAULT_PIN_COLOR);

            mActiveBarColor = mBarColor;
            mCircleSize = ta.getDimension(R.styleable.RangeBar_selectorSize,
                    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_CIRCLE_SIZE_DP,
                            getResources().getDisplayMetrics())
            );
            mCircleColor = ta.getColor(R.styleable.RangeBar_selectorColor,
                    DEFAULT_CONNECTING_LINE_COLOR);
            mActiveCircleColor = mCircleColor;
            mTickColor = ta.getColor(R.styleable.RangeBar_tickColor, DEFAULT_TICK_COLOR);
            mActiveTickColor = mTickColor;
            mConnectingLineWeight = ta.getDimension(R.styleable.RangeBar_connectingLineWeight,
                    DEFAULT_CONNECTING_LINE_WEIGHT_PX);
            mConnectingLineColor = ta.getColor(R.styleable.RangeBar_connectingLineColor,
                    DEFAULT_CONNECTING_LINE_COLOR);
            mActiveConnectingLineColor = mConnectingLineColor;
            mExpandedPinRadius = ta
                    .getDimension(R.styleable.RangeBar_pinRadius, TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP,
                            DEFAULT_EXPANDED_PIN_RADIUS_DP, getResources().getDisplayMetrics()));
            mPinPadding = ta.getDimension(R.styleable.RangeBar_pinPadding, TypedValue
                    .applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_PIN_PADDING_DP,
                            getResources().getDisplayMetrics()));
            mBarPaddingBottom = ta.getDimension(R.styleable.RangeBar_rangeBarPaddingBottom,
                    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                            DEFAULT_BAR_PADDING_BOTTOM_DP, getResources().getDisplayMetrics()));
            mIsRangeBar = ta.getBoolean(R.styleable.RangeBar_rangeBar, true);
            mArePinsTemporary = ta.getBoolean(R.styleable.RangeBar_temporaryPins, false);

            float density = getResources().getDisplayMetrics().density;
            mMinPinFont = ta.getDimension(R.styleable.RangeBar_pinMinFont,
                    DEFAULT_MIN_PIN_FONT_SP * density);
            mMaxPinFont = ta.getDimension(R.styleable.RangeBar_pinMaxFont,
                    DEFAULT_MAX_PIN_FONT_SP * density);

            mIsRangeBar = ta.getBoolean(R.styleable.RangeBar_rangeBar, true);

        } finally {
            ta.recycle();
            createBar();

        }


    }

    //Child Views
    /**
     * Creates a new mBar
     */
    private void createBar() {
        mBar = new Bar(getContext(),
                getMarginLeft(),
                getYPos(),
                getBarLength(),
                mTickCount,
                mTickHeightDP,
                mTickColor,
                mBarWeight,
                mBarColor);
        invalidate();
    }

    /**
     * Sets the start tick in the RangeBar.
     *
     * @param tickStart Integer specifying the number of ticks.
     */
    public void setTickStart(float tickStart) {
        int tickCount = (int) ((mTickEnd - tickStart) / mTickInterval) + 1;
        if (isValidTickCount(tickCount)) {
            mTickCount = tickCount;
            mTickStart = tickStart;

            // Prevents resetting the indices when creating new activity, but
            // allows it on the first setting.
            if (mFirstSetTickCount) {
                mLeftIndex = 0;
                mRightIndex = mTickCount - 1;

                if (mListener != null) {
                    mListener.onRangeChangeListener(this, mLeftIndex, mRightIndex,
                            getPinValue(mLeftIndex),
                            getPinValue(mRightIndex));
                }
            }
            if (indexOutOfRange(mLeftIndex, mRightIndex)) {
                mLeftIndex = 0;
                mRightIndex = mTickCount - 1;

                if (mListener != null) {
                    mListener.onRangeChangeListener(this, mLeftIndex, mRightIndex,
                            getPinValue(mLeftIndex),
                            getPinValue(mRightIndex));
                }
            }

            createBar();
            //createPins();
        } else {
            Log.e(TAG, "tickCount less than 2; invalid tickCount.");
            throw new IllegalArgumentException("tickCount less than 2; invalid tickCount.");
        }
    }

    /**
     * Sets the start tick in the RangeBar.
     *
     * @param tickInterval Integer specifying the number of ticks.
     */
    public void setTickInterval(float tickInterval) {
        int tickCount = (int) ((mTickEnd - mTickStart) / tickInterval) + 1;
        if (isValidTickCount(tickCount)) {
            mTickCount = tickCount;
            mTickInterval = tickInterval;

            // Prevents resetting the indices when creating new activity, but
            // allows it on the first setting.
            if (mFirstSetTickCount) {
                mLeftIndex = 0;
                mRightIndex = mTickCount - 1;

                if (mListener != null) {
                    mListener.onRangeChangeListener(this, mLeftIndex, mRightIndex,
                            getPinValue(mLeftIndex), getPinValue(mRightIndex));
                }
            }
            if (indexOutOfRange(mLeftIndex, mRightIndex)) {
                mLeftIndex = 0;
                mRightIndex = mTickCount - 1;

                if (mListener != null) {
                    mListener.onRangeChangeListener(this, mLeftIndex, mRightIndex,
                            getPinValue(mLeftIndex), getPinValue(mRightIndex));
                }
            }

            createBar();
            //createPins();
        } else {
            Log.e(TAG, "tickCount less than 2; invalid tickCount.");
            throw new IllegalArgumentException("tickCount less than 2; invalid tickCount.");
        }
    }

    /**
     * Sets the end tick in the RangeBar.
     *
     * @param tickEnd Integer specifying the number of ticks.
     */
    public void setTickEnd(float tickEnd) {
        int tickCount = (int) ((tickEnd - mTickStart) / mTickInterval) + 1;
        if (isValidTickCount(tickCount)) {
            mTickCount = tickCount;
            mTickEnd = tickEnd;

            // Prevents resetting the indices when creating new activity, but
            // allows it on the first setting.
            if (mFirstSetTickCount) {
                mLeftIndex = 0;
                mRightIndex = mTickCount - 1;
                mMiddleIndex = mTickCount - 3;

                if (mListener != null) {
                    mListener.onRangeChangeListener(this, mLeftIndex, mRightIndex,
                            getPinValue(mLeftIndex), getPinValue(mRightIndex));
                }
            }
            if (indexOutOfRange(mLeftIndex, mRightIndex)) {
                mLeftIndex = 0;
                mRightIndex = mTickCount - 1;
                mMiddleIndex = mTickCount - 3;

                if (mListener != null) {
                    mListener.onRangeChangeListener(this, mLeftIndex, mRightIndex,
                            getPinValue(mLeftIndex), getPinValue(mRightIndex));
                }
            }

            createBar();
            //createPins();
        } else {
            Log.e(TAG, "tickCount less than 2; invalid tickCount.");
            throw new IllegalArgumentException("tickCount less than 2; invalid tickCount.");
        }
    }

    /**
     * Sets the height of the ticks in the range bar.
     *
     * @param tickHeight Float specifying the height of each tick mark in dp.
     */
    public void setTickHeight(float tickHeight) {

        mTickHeightDP = tickHeight;
        createBar();
    }

    /**
     * Set the weight of the bar line and the tick lines in the range bar.
     *
     * @param barWeight Float specifying the weight of the bar and tick lines in
     *                  px.
     */
    public void setBarWeight(float barWeight) {

        mBarWeight = barWeight;
        createBar();
    }

    /**
     * Set the color of the bar line and the tick lines in the range bar.
     *
     * @param barColor Integer specifying the color of the bar line.
     */
    public void setBarColor(int barColor) {
        mBarColor = barColor;
        createBar();
    }

    /**
            * Sets the location of the pins according by the supplied index.
    * Numbered from 0 to mTickCount - 1 from the left.
    *
            * @param leftPinIndex  Integer specifying the index of the left pin
    * @param rightPinIndex Integer specifying the index of the right pin
    */
    public void setRangePinsByIndices(int leftPinIndex, int rightPinIndex) {
        if (indexOutOfRange(leftPinIndex, rightPinIndex)) {
            Log.e(TAG,
                    "Pin index left " + leftPinIndex + ", or right " + rightPinIndex
                            + " is out of bounds. Check that it is greater than the minimum ("
                            + mTickStart + ") and less than the maximum value ("
                            + mTickEnd + ")");
            throw new IllegalArgumentException(
                    "Pin index left " + leftPinIndex + ", or right " + rightPinIndex
                            + " is out of bounds. Check that it is greater than the minimum ("
                            + mTickStart + ") and less than the maximum value ("
                            + mTickEnd + ")");
        } else {

            if (mFirstSetTickCount) {
                mFirstSetTickCount = false;
            }
            mLeftIndex = leftPinIndex;
            mRightIndex = rightPinIndex;
           /* createPins();
*/
            if (mListener != null) {
                mListener.onRangeChangeListener(this, mLeftIndex, mRightIndex,
                        getPinValue(mLeftIndex), getPinValue(mRightIndex));
            }
        }

        invalidate();
        requestLayout();
    }

    /**
     * Set the value on the thumb pin, either from map or calculated from the tick intervals
     * Integer check to format decimals as whole numbers
     *
     * @param tickIndex the index to set the value for
     */
    private String getPinValue(int tickIndex) {
        if (mPinTextListener != null) {
            return mPinTextListener.getPinValue(this, tickIndex);
        }
        float tickValue = (tickIndex == (mTickCount - 1))
                ? mTickEnd
                : (tickIndex * mTickInterval) + mTickStart;
        String xValue = mTickMap.get(tickValue);
        if (xValue == null) {
            if (tickValue == Math.ceil(tickValue)) {
                xValue = String.valueOf((int) tickValue);
            } else {
                xValue = String.valueOf(tickValue);
            }
        }
        return mPinTextFormatter.getText(xValue);
    }

    /**
     * Get marginLeft in each of the public attribute methods.
     *
     * @return float marginLeft
     */
    private float getMarginLeft() {
        return Math.max(mExpandedPinRadius, mCircleSize);
    }

    /**
     * Get yPos in each of the public attribute methods.
     *
     * @return float yPos
     */
    private float getYPos() {
        return (getHeight() - mBarPaddingBottom);
    }

    /**
     * Get barLength in each of the public attribute methods.
     *
     * @return float barLength
     */
    private float getBarLength() {
        return (getWidth() - 2 * getMarginLeft());
    }

    /**
     * Returns if either index is outside the range of the tickCount.
     *
     * @param leftThumbIndex  Integer specifying the left thumb index.
     * @param rightThumbIndex Integer specifying the right thumb index.
     * @return boolean If the index is out of range.
     */
    private boolean indexOutOfRange(int leftThumbIndex, int rightThumbIndex) {
        return (leftThumbIndex < 0 || leftThumbIndex >= mTickCount
                || rightThumbIndex < 0
                || rightThumbIndex >= mTickCount);
    }

    /**
     * Returns if either value is outside the range of the tickCount.
     *
     * @param leftThumbValue  Float specifying the left thumb value.
     * @param rightThumbValue Float specifying the right thumb value.
     * @return boolean If the index is out of range.
     */
    private boolean valueOutOfRange(float leftThumbValue, float rightThumbValue) {
        return (leftThumbValue < mTickStart || leftThumbValue > mTickEnd
                || rightThumbValue < mTickStart || rightThumbValue > mTickEnd);
    }

    /**
     * If is invalid tickCount, rejects. TickCount must be greater than 1
     *
     * @param tickCount Integer
     * @return boolean: whether tickCount > 1
     */
    private boolean isValidTickCount(int tickCount) {
        return (tickCount > 1);
    }


    // Inner Classes ///////////////////////////////////////////////////////////

    /**
     * A callback that notifies clients when the RangeBar has changed. The
     * listener will only be called when either thumb's index has changed - not
     * for every movement of the thumb.
     */
    public interface OnRangeBarChangeListener {

        void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex,
                                   int rightPinIndex, String leftPinValue, String rightPinValue);
    }

    public interface PinTextFormatter {

        String getText(String value);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // If this View is not enabled, don't allow for touch interactions.
        if (!isEnabled()) {
            return false;
        }

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                mDiffX = 0;
                mDiffY = 0;

                mLastX = event.getX();
                mLastY = event.getY();
                //onActionDown(event.getX(), event.getY());
                return true;

            case MotionEvent.ACTION_UP:
                this.getParent().requestDisallowInterceptTouchEvent(false);
                onActionUp(event.getX(), event.getY());
                return true;

            case MotionEvent.ACTION_CANCEL:
                this.getParent().requestDisallowInterceptTouchEvent(false);
                onActionUp(event.getX(), event.getY());
                return true;

            case MotionEvent.ACTION_MOVE:
                //onActionMove(event.getX());
                this.getParent().requestDisallowInterceptTouchEvent(true);
                final float curX = event.getX();
                final float curY = event.getY();
                mDiffX += Math.abs(curX - mLastX);
                mDiffY += Math.abs(curY - mLastY);
                mLastX = curX;
                mLastY = curY;

                if (mDiffX < mDiffY) {
                    //vertical touch
                    getParent().requestDisallowInterceptTouchEvent(false);
                    return false;
                } else {
                    //horizontal touch (do nothing as it is needed for RangeBar)
                }
                return true;

            default:
                return false;
        }
    }


    private void onActionUp(float x, float y){

        List list = Arrays.asList(mRightThumb);

        for (int i=0; i<list.size(); i++){

            if (mRightThumb[i].isInTargetZone(x, y)) {
                notifyPinClickedListeners(mRightThumb[i]);
                return;
            }

        }

    }

    OnPinClick mOnPinClick;

    public interface OnPinClick {
        void onPinClicked(PinView pin);
    }

    public void setOnPinClickedListener(OnPinClick listener) {
        mOnPinClick = listener;
    }

    protected void notifyPinClickedListeners(PinView pin) {
        if (mOnPinClick != null) {
            mOnPinClick.onPinClicked(pin);
        }
    }
    /**
     * @author robmunro
     *         A callback that allows getting pin text exernally
     */
    public interface OnRangeBarTextListener {

        String getPinValue(RangeBar rangeBar, int tickIndex);
    }

    public void setOnBarCreatedListener(OnRangeBarCreated listener) {
        mBarCreatedListener = listener;
    }

    protected void notifyBarCreatedListeners() {
        if (mBarCreatedListener != null) {
            mBarCreatedListener.onBarCreated();
        }
    }

    public interface OnRangeBarCreated {
        void onBarCreated();
    }
}
