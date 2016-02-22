package co.moonmonkeylabs.flowmortarexampleapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import co.moonmonkeylabs.flowmortarexampleapp.R;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by root on 2016/02/05.
 */

public class DriverHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @InjectView(R.id.text_driver_name)
    public TextView textDriverName;

    @InjectView(R.id.circle_image_driver)
    public CircleImageView circleImageDriver;

    SelectedItemListener mListener;

    public DriverHolder(View view) {
        super(view);
        ButterKnife.inject(this, view);
        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        mListener.onItemClick(getAdapterPosition());
        //view.setSelected(true);
    }

    public SelectedItemListener getListener() {
        return mListener;
    }

    public void setListener(SelectedItemListener mListener) {
        this.mListener = mListener;
    }

    public interface SelectedItemListener{
        void onItemClick(int position);
    }
}

