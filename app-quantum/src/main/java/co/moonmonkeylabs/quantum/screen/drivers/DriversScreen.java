package co.moonmonkeylabs.quantum.screen.drivers;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.moonmonkeylabs.quantum.ActivityModule;
import co.moonmonkeylabs.quantum.R;
import co.moonmonkeylabs.quantum.application.AppDataHolder;
import co.moonmonkeylabs.quantum.common.flow.ActivityHelper;
import co.moonmonkeylabs.quantum.common.flow.Layout;
import co.moonmonkeylabs.quantum.common.mortarscreen.WithModule;
import co.moonmonkeylabs.quantum.listeners.OnModelDataChangedListener;
import co.moonmonkeylabs.quantum.listeners.OnModelPictureChangedListener;
import co.moonmonkeylabs.quantum.model.Driver;
import co.moonmonkeylabs.quantum.view.drivers.DriverEditView;
import co.moonmonkeylabs.quantum.view.drivers.DriverGridView;
import co.moonmonkeylabs.quantum.view.drivers.DriverHeaderView;
import co.moonmonkeylabs.quantum.view.drivers.DriversView;
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
