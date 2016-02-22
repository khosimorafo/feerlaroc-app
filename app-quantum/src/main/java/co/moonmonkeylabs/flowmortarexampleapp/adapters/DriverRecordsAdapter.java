package co.moonmonkeylabs.flowmortarexampleapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import co.moonmonkeylabs.flowmortarexampleapp.R;
import co.moonmonkeylabs.flowmortarexampleapp.model.DriverRecord;

/**
 * Created by root on 2016/01/20.
 */
public class DriverRecordsAdapter extends RecyclerView.Adapter<DriverRecordsAdapter.ViewHolder> {

    private final Context context;
    private final List<DriverRecord> driverRecords;
    private final Listener listener;

    public DriverRecordsAdapter(Context context, List<DriverRecord> driverRecords, Listener listener) {
        this.context = context;
        this.driverRecords = driverRecords;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_driver_record, viewGroup, false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        DriverRecord record = driverRecords.get(i);
        viewHolder.textDriverName.setText("");
        //viewHolder.textDate.setText(record.getDate().toString());
        viewHolder.textCash.setText(record.getCash().toString());
        viewHolder.textFuel.setText("Fuel : R" + record.getFuel());
        viewHolder.textRepairs.setText("Repairs : R" + record.getRepairs());


    }

    @Override
    public int getItemCount() {
        return driverRecords.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

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

        private Listener listener;

        public ViewHolder(View view, Listener listener) {
            super(view);
            this.listener = listener;

            ButterKnife.inject(this, view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(getAdapterPosition());
        }
    }

    public interface Listener {
        void onItemClick(int position);
    }
}
