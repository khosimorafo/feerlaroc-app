package co.moonmonkeylabs.quantum.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import co.moonmonkeylabs.quantum.R;

/**
 * Created by root on 2016/01/27.
 */
public class DriverRecordHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @InjectView(R.id.driver_name)
    public TextView textDriverName;

    @InjectView(R.id.date)
    public TextView textDate;

    @InjectView(R.id.cash)
    public TextView textCash;

    @InjectView(R.id.fuel)
    public TextView textFuel;

    @InjectView(R.id.repairs)
    public TextView textRepairs;


    public DriverRecordHolder(View view) {
        super(view);

        ButterKnife.inject(this, view);
        view.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {

    }
}
