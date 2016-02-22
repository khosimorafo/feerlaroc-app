package co.moonmonkeylabs.flowmortarexampleapp.screen.drivers;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.feerlaroc.core.app.App;
import com.feerlaroc.core.listeners.NetworkCompletionListener;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.moonmonkeylabs.flowmortarexampleapp.ActivityModule;
import co.moonmonkeylabs.flowmortarexampleapp.R;
import co.moonmonkeylabs.flowmortarexampleapp.adapters.DriverHolder;
import co.moonmonkeylabs.flowmortarexampleapp.adapters.DriversAdapter;
import co.moonmonkeylabs.flowmortarexampleapp.application.AppDataHolder;
import co.moonmonkeylabs.flowmortarexampleapp.application.QuantumApp;
import co.moonmonkeylabs.flowmortarexampleapp.common.flow.Layout;
import co.moonmonkeylabs.flowmortarexampleapp.common.mortarscreen.WithModule;
import co.moonmonkeylabs.flowmortarexampleapp.firebase.FirebaseAdapterService;
import co.moonmonkeylabs.flowmortarexampleapp.listeners.OnModelDataChangedListener;
import co.moonmonkeylabs.flowmortarexampleapp.model.Driver;
import co.moonmonkeylabs.flowmortarexampleapp.view.drivers.DriverGridView;
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
            OnModelDataChangedListener, NetworkCompletionListener {

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
            setSelectedDriver((Driver) entity);
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
        public void onComplete() {
            String x = "";
        }
    }
}
