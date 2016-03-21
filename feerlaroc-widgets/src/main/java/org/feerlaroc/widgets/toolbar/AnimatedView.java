package org.feerlaroc.widgets.toolbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import org.feerlaroc.widgets.R;

/**
 * Created by Maxim Dybarsky | maxim.dybarskyy@gmail.com
 * on 13.01.15 at 14:17
 */
public class AnimatedView extends View {

    private int target;
    private int mSpotColor = 0;

    public AnimatedView(Context context) {
        super(context);
    }

    public float getXFactor() {
        return getX() / target;
    }

    public void setXFactor(float xFactor) {
        setX(target * xFactor);
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public int getTarget() {
        return target;
    }

    public void setSpotColor(int color){

        mSpotColor = color;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int x = getWidth();
        int y = getHeight();
        int radius;
        radius = 6;
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.TRANSPARENT);
        canvas.drawPaint(paint);
        // Use Color.parseColor to define HTML colors

        if (mSpotColor != 0) {

            paint.setColor(mSpotColor);

        } else{

            paint.setColor(Color.parseColor(String.valueOf(R.color.progress_spots_dialog_color)));

        }


        canvas.drawCircle(x / 2, y / 2, radius, paint);

    }
}
