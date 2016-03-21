package org.feerlaroc.widgets.toolbar;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * Created by root on 2016/03/20.
 */
public class FeerlarocToolBar extends Toolbar {

    FeerlarocToolbarClicked mListener;

    public FeerlarocToolBar(Context context) {
        super(context);
    }

    public FeerlarocToolBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FeerlarocToolBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }


    public void setListener(FeerlarocToolbarClicked listener){

        mListener = listener;

    }

    public void setMenuItemListeners(){

        int count = getChildCount();

        for (int h = 0; h < count; h++) {

            RelativeLayout layout = (RelativeLayout) getChildAt(h);

            for (int i = 0; i <layout.getChildCount(); i++) {

                View child = layout.getChildAt(i);

                if(child instanceof ActionMenuView){

                    for (int j = 0; j < ((ActionMenuView) child).getChildCount(); j++) {

                        final MenuItem item = ((ActionMenuView) child).getMenu().getItem(j);
                        //child.setO
                        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                mListener.onMenuItemViewClicked(item);
                                return false;
                            }
                        });

                    }

                }
                else if (child instanceof Button){
                    child.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mListener.onActionButtonClicked();
                        }
                    });
                }

            }

        }

    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        return super.onTouchEvent(ev);

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    public interface FeerlarocToolbarClicked {

        void onMenuItemViewClicked(MenuItem item);
        void onActionButtonClicked();

    }
}
