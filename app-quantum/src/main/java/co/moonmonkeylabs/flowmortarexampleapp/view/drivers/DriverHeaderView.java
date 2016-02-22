package co.moonmonkeylabs.flowmortarexampleapp.view.drivers;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import co.moonmonkeylabs.flowmortarexampleapp.R;
import co.moonmonkeylabs.flowmortarexampleapp.common.widget.CustomFrameLayout;
import co.moonmonkeylabs.flowmortarexampleapp.screen.drivers.DriversHeaderScreen;

/**
 * Created by root on 2016/02/03.
 */
public class DriverHeaderView extends CustomFrameLayout<DriversHeaderScreen.Presenter>{

    @Inject
    protected DriversHeaderScreen.Presenter presenter;

    @InjectView(R.id.text_driver_name)
    public TextView driverName;

    Drawable backgroundImage;

    public DriverHeaderView(Context parentContext, AttributeSet attrs) {
        super(parentContext, attrs);
    }


    @Override
    public DriversHeaderScreen.Presenter getPresenter() {
        return presenter;
    }

    @Override
    protected void onFinishInflate() {

        super.onFinishInflate();
        ButterKnife.inject(this);
        setBackgroundImage();

    }

    protected void setBackgroundImage(){

        backgroundImage = getResources().getDrawable(R.drawable.zozo);
        this.setBackground(backgroundImage);

    }

}
