package co.moonmonkeylabs.flowmortarexampleapp.screen.drivers;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.moonmonkeylabs.flowmortarexampleapp.ActivityModule;
import co.moonmonkeylabs.flowmortarexampleapp.R;
import co.moonmonkeylabs.flowmortarexampleapp.application.AppDataHolder;
import co.moonmonkeylabs.flowmortarexampleapp.common.flow.ActivityHelper;
import co.moonmonkeylabs.flowmortarexampleapp.common.flow.Layout;
import co.moonmonkeylabs.flowmortarexampleapp.common.mortarscreen.WithModule;
import co.moonmonkeylabs.flowmortarexampleapp.listeners.OnModelDataChangedListener;
import co.moonmonkeylabs.flowmortarexampleapp.listeners.OnModelPictureChangedListener;
import co.moonmonkeylabs.flowmortarexampleapp.model.Driver;
import co.moonmonkeylabs.flowmortarexampleapp.view.drivers.DriverEditView;
import co.moonmonkeylabs.flowmortarexampleapp.view.drivers.DriverGridView;
import co.moonmonkeylabs.flowmortarexampleapp.view.drivers.DriverHeaderView;
import co.moonmonkeylabs.flowmortarexampleapp.view.drivers.DriversView;
import flow.path.Path;
import mortar.ViewPresenter;


@Layout(R.layout.screen_drivers)
@WithModule(DriversScreen.Module.class)
public class DriversScreen extends Path {

    @dagger.Module(injects = {DriversView.class, DriverHeaderView.class, DriverGridView.class,
            DriverEditView.class}, addsTo = ActivityModule.class)
    public class Module {
    }

    @Singleton
    public static class Presenter extends ViewPresenter<DriversView>{

        private final ActivityHelper activityHelper;

        @Inject
        public Presenter(ActivityHelper activityHelper) {
            this.activityHelper = activityHelper;
        }

        public void setDriverModelListeners(OnModelDataChangedListener... presenters){

            for (OnModelDataChangedListener presenter : presenters) {

                AppDataHolder.getInstance().addDataChangedListener(presenter);
                if(presenter.getClass() == DriversHeaderScreen.Presenter.class){
                    AppDataHolder.getInstance().addPictureChangedListener((OnModelPictureChangedListener) presenter);
                }

            }
        }

        public void createNewDriver() {
            AppDataHolder.getInstance().setDriver(new Driver());
        }

    }
}
