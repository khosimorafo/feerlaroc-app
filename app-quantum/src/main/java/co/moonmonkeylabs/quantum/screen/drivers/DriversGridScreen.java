package co.moonmonkeylabs.quantum.screen.drivers;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.feerlaroc.core.app.App;
import com.feerlaroc.core.listeners.FrameworkCompletionListener;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.moonmonkeylabs.quantum.ActivityModule;
import co.moonmonkeylabs.quantum.R;
import co.moonmonkeylabs.quantum.adapters.DriverHolder;
import co.moonmonkeylabs.quantum.adapters.DriversAdapter;
import co.moonmonkeylabs.quantum.application.AppDataHolder;
import co.moonmonkeylabs.quantum.application.QuantumApp;
import co.moonmonkeylabs.quantum.common.flow.Layout;
import co.moonmonkeylabs.quantum.common.mortarscreen.WithModule;
import co.moonmonkeylabs.quantum.firebase.FirebaseAdapterService;
import co.moonmonkeylabs.quantum.listeners.OnModelDataChangedListener;
import co.moonmonkeylabs.quantum.model.Driver;
import co.moonmonkeylabs.quantum.view.drivers.DriverGridView;
import flow.path.Path;
import mortar.ViewPresenter;

/**
 * Created by root on 2016/02/03.
 */
@Layout(R.layout.layout_driver_grid)
@WithModule(DriversGridScreen.Module.class)
public class DriversGridScreen extends Path{

    @dagger.Module(injects = DriverGridView.class, addsTo = ActivityModule.class)
    public class Module {
    }

    @Singleton
    public static class Presenter extends ViewPresenter<DriverGridView> implements DriverHolder.SelectedItemListener,
            OnModelDataChangedListener, FrameworkCompletionListener {

        App app = new QuantumApp();

        private DriversAdapter mRecycleViewAdapter;
        Driver selectedDriver;

        public int recycleViewPosition = -1;

        @Inject
        public Presenter() {}

        @Override
        protected void onLoad(Bundle savedInstanceState) {

            LinearLayoutManager layoutManager = new LinearLayoutManager(getView().getContext());
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

            if(getView() == null) return;

            getView().getRecyclerView().setLayoutManager(layoutManager);
            mRecycleViewAdapter = new FirebaseAdapterService(getView().getContext()).getDriversAdapter(this);
            getView().getRecyclerView().setAdapter(mRecycleViewAdapter);

        }

        @Override
        public void onItemClick(int position) {
            /*Clear current selected items*/
            mRecycleViewAdapter.removeSelectedItems();
            /**/
            selectedDriver = mRecycleViewAdapter.getItem(position);
            mRecycleViewAdapter.toggleSelection(position);
            AppDataHolder.getInstance().setDriver(selectedDriver);
            recycleViewPosition = position;

            //Get a count of selected item
            int selectedItem = mRecycleViewAdapter.getSelectedItemCount();

            String x = "";

        }

        public void setSelectedDriver(Driver selectedDriver) {
            this.selectedDriver = selectedDriver;
        }

        @Override
        public void onModelDataChanged(Driver entity) {
            setSelectedDriver(entity);
        }


        public void remove() {

            try {

                int countBeforeDelete = mRecycleViewAdapter.getItemCount();

                app.remove(Driver.class, AppDataHolder.getInstance().getDriver().id(), this);

                if(countBeforeDelete > 1){
                    if(recycleViewPosition > 1) {
                        onItemClick(recycleViewPosition - 1);
                    }else if(recycleViewPosition == 1){
                        onItemClick(mRecycleViewAdapter.getItemCount() - 1);
                    } else {
                        onItemClick(recycleViewPosition + 1);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onSuccess() {
            String x = "";
        }

        @Override
        public void onError() {

        }
    }
}
