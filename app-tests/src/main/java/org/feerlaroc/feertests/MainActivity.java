package org.feerlaroc.feertests;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import org.feerlaroc.widgets.monthbar.MonthsBar;
import org.feerlaroc.widgets.utils.Month;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    // Sets the initial values such that the image will be drawn
    private static final int INDIGO_500 = 0xff3f51b5;

    // Sets variables to save the colors of each attribute
    private int mBarColor;

    private int mConnectingLineColor;

    private int mPinColor;
    private int mTextColor;

    private int mTickColor;

    // Initializes the RangeBar in the application
    private MonthsBar mMonthsBar;

    private int mSelectorColor;

    // Saves the state upon rotating the screen/restarting the activity
    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("BAR_COLOR", mBarColor);
        bundle.putInt("CONNECTING_LINE_COLOR", mConnectingLineColor);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Removes title bar and sets content view
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        // Gets the RangeBar
        mMonthsBar = (MonthsBar) findViewById(R.id.months_bar);
        mMonthsBar.init(getMonths(), 300.0);

    }

    private List<Month> getMonths(){

        List<Month> months = new ArrayList<>();
        months.add(new Month("January", "2016", "Jan", true));
        months.add(new Month("December", "2015", "Dec", true));
        months.add(new Month("November", "2015", "Nov", false));
        months.add(new Month("October", "2015", "Oct", false));
        return months;

    }
}
