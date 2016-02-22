package co.moonmonkeylabs.flowmortarexampleapp.screen.drivers;

import android.graphics.drawable.Drawable;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.moonmonkeylabs.flowmortarexampleapp.ActivityModule;
import co.moonmonkeylabs.flowmortarexampleapp.R;
import co.moonmonkeylabs.flowmortarexampleapp.common.flow.Layout;
import co.moonmonkeylabs.flowmortarexampleapp.common.mortarscreen.WithModule;
import co.moonmonkeylabs.flowmortarexampleapp.listeners.OnModelDataChangedListener;
import co.moonmonkeylabs.flowmortarexampleapp.listeners.OnModelPictureChangedListener;
import co.moonmonkeylabs.flowmortarexampleapp.model.Driver;
import co.moonmonkeylabs.flowmortarexampleapp.view.drivers.DriverHeaderView;
import flow.path.Path;
import mortar.ViewPresenter;

/**
 * Created by root on 2016/02/03.
 */
@Layout(R.layout.layout_driver_header)
@WithModule(DriversHeaderScreen.Module.class)
public class DriversHeaderScreen extends Path{

    @dagger.Module(injects = DriverHeaderView.class, addsTo = ActivityModule.class)
    public class Module {
    }

    @Singleton
    public static class Presenter extends ViewPresenter<DriverHeaderView>
            implements OnModelDataChangedListener, OnModelPictureChangedListener {

        Driver mDriver = new Driver();

        @Inject
        public Presenter() {}

        public Driver getDriver() {
            return mDriver;
        }

        public void setDriver(Driver driver) {
            this.mDriver = driver;
        }

        void updateView(){

            if(getView() == null) return;

            getView().driverName.setText(mDriver.getName());
            setDriverImage(mDriver.getDrawable(getView().getContext()));

        }

        void setDriverImage(Drawable drawable){

            if(getView() == null) return;

            getView().setBackground(drawable);

        }

        @Override
        public void onModelDataChanged(Driver entity) {
            setDriver((Driver) entity);
            updateView();
        }

        @Override
        public void onModelPictureChanged(Drawable drawable) {
            setDriverImage(drawable);
        }
    }

}
