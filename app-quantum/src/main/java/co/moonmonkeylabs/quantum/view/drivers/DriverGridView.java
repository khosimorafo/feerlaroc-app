package co.moonmonkeylabs.quantum.view.drivers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.ButterKnife;
import co.moonmonkeylabs.quantum.R;
import co.moonmonkeylabs.quantum.common.widget.CustomFrameLayout;
import co.moonmonkeylabs.quantum.screen.drivers.DriversGridScreen;

/**
 * Created by root on 2016/02/03.
 */
public class DriverGridView extends CustomFrameLayout<DriversGridScreen.Presenter> {

    @Inject
    public DriversGridScreen.Presenter presenter;

    RecyclerView recyclerView;
    ViewGroup parentView;


    public DriverGridView(Context parentContext, AttributeSet attrs) {
        super(parentContext, attrs);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.inject(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_drivers);


    }

    @Override
    public DriversGridScreen.Presenter getPresenter() {
        return presenter;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public ViewGroup getParentView() {
        return parentView;
    }

    public void setParentView(ViewGroup parentView) {
        this.parentView = parentView;
    }
}
