package co.moonmonkeylabs.flowmortarexampleapp.firebase;


import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.feerlaroc.firebase.command.FirebaseServiceCommand;
import com.feerlaroc.firebase.core.FirebaseReference;
import com.feerlaroc.firebase.service.FirebaseService;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.ui.FirebaseRecyclerAdapter;

import java.text.SimpleDateFormat;

import co.moonmonkeylabs.flowmortarexampleapp.R;
import co.moonmonkeylabs.flowmortarexampleapp.adapters.DriverHolder;
import co.moonmonkeylabs.flowmortarexampleapp.adapters.DriverRecordHolder;
import co.moonmonkeylabs.flowmortarexampleapp.adapters.DriversAdapter;
import co.moonmonkeylabs.flowmortarexampleapp.model.Driver;
import co.moonmonkeylabs.flowmortarexampleapp.model.DriverRecord;
import co.moonmonkeylabs.flowmortarexampleapp.utils.DateService;
import co.moonmonkeylabs.flowmortarexampleapp.utils.Misc;

/**
 * Created by root on 2016/01/27.
 */
public class FirebaseAdapterService extends FirebaseServiceCommand {

    String DRIVERS_ENDPOINT = "drivers";
    String CARS_ENDPOINT = "cars";
    String RECORDS_ENDPOINT = "records";

    Context mContext;

    public FirebaseAdapterService(Context context){
        mContext = context;
    }

    public DriversAdapter getDriversAdapter(DriverHolder.SelectedItemListener listener){

        firebaseReference = (FirebaseReference) getArgument(FirebaseService.FIREBASE_BASE_REFERENCE);

        return new DriversAdapter(Driver.class,
                R.layout.row_driver, DriverHolder.class, firebaseReference.get(DRIVERS_ENDPOINT), listener){

            @Override
            protected void populateViewHolder(DriverHolder viewHolder, Driver driver, int position) {

                viewHolder.textDriverName.setText(new Misc().getFirstWord(driver.getName()));
                Drawable drawable = new BitmapDrawable(mContext.getResources(),
                        Misc.getInstance().decodeBitmap(driver.getBitmapString()));
                viewHolder.circleImageDriver.setImageDrawable(drawable);

            }

        };

    }

    public FirebaseRecyclerAdapter getRecordsAdapter(){

        firebaseReference = (FirebaseReference) getArgument(FirebaseService.FIREBASE_BASE_REFERENCE);

        return new FirebaseRecyclerAdapter<DriverRecord, DriverRecordHolder>(DriverRecord.class,
                R.layout.row_driver_record, DriverRecordHolder.class, firebaseReference.get(RECORDS_ENDPOINT)){
            @Override
            protected void populateViewHolder(DriverRecordHolder viewHolder, DriverRecord record, int position) {

                SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy");

                viewHolder.textDriverName.setText("");
                viewHolder.textDate.setText(new DateService(format).getDate(record.getDate()));
                viewHolder.textCash.setText(record.getCash().toString());
                viewHolder.textFuel.setText("Fuel : R" + record.getFuel());
                viewHolder.textRepairs.setText("Repairs : R" + record.getRepairs());

            }

        };

    }

    @Override
    public void onComplete(FirebaseError firebaseError, Firebase firebase) {

    }

    @Override
    public <T> void execute(T entity) {

    }
}
