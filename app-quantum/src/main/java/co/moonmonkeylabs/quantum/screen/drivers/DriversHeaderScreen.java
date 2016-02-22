package co.moonmonkeylabs.quantum.screen.drivers;

import android.graphics.drawable.Drawable;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.moonmonkeylabs.quantum.ActivityModule;
import co.moonmonkeylabs.quantum.R;
import co.moonmonkeylabs.quantum.common.flow.Layout;
import co.moonmonkeylabs.quantum.common.mortarscreen.WithModule;
import co.moonmonkeylabs.quantum.listeners.OnModelDataChangedListener;
import co.moonmonkeylabs.quantum.listeners.OnModelPictureChangedListener;
import co.moonmonkeylabs.quantum.model.Driver;
import co.moonmonkeylabs.quantum.view.drivers.DriverHeaderView;
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
