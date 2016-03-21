package org.feerlaroc.feertests;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;

import org.feerlaroc.widgets.toolbar.AnimatedView;
import org.feerlaroc.widgets.toolbar.AnimatorPlayer;
import org.feerlaroc.widgets.toolbar.FeerlarocToolBar;
import org.feerlaroc.widgets.toolbar.ProgressToolbar;

public class MainActivity extends AppCompatActivity{

    private static final int DELAY = 150;
    private static final int DURATION = 1500;

    private int size;
    private AnimatedView[] spots;
    private AnimatorPlayer animator;
    private CharSequence message;

    ProgressToolbar mProgressToolbar;
    Button mButton;

    Context mContext;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mContext = this;

        // creating LinearLayout
        LinearLayout linLayout = new LinearLayout(this);
        // specifying vertical orientation
        linLayout.setOrientation(LinearLayout.VERTICAL);
        // creating LayoutParams
        LinearLayout.LayoutParams linLayoutParam
                = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        // set LinearLayout as a root element of the screen
        setContentView(linLayout, linLayoutParam);
        //setContentView(R.layout.main);



        LinearLayout.LayoutParams lpView
                = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        //mProgressToolbar = (ProgressToolbar) findViewById(R.id.progress_toolbar);//
        mProgressToolbar = new ProgressToolbar(this, R.menu.toolbar_menu);
        //mProgressToolbar.inflateMenu(this, R.menu.toolbar_menu);

        linLayout.addView(mProgressToolbar, lpView);


        mProgressToolbar.setListener(new FeerlarocToolBar.FeerlarocToolbarClicked() {
            @Override
            public void onMenuItemViewClicked(MenuItem item) {
                String x = "";
            }

            @Override
            public void onActionButtonClicked() {
                String y = "";
            }
        });


        mProgressToolbar.start();

    }




}

