package com.feerlaroc.invoices.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.feerlaroc.invoices.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.hdodenhof.circleimageview.CircleImageView;


public class ContactHolder  extends RecyclerView.ViewHolder implements View.OnClickListener {

    @InjectView(R.id.text_contact_name) public TextView textContactName;
    @InjectView(R.id.circle_image_contact) public CircleImageView circleImageDriver;
    @InjectView(R.id.text_amount_outstanding) public TextView textOutstandingAmount;

    SelectedItemListener mListener;

    public ContactHolder(View view) {
        super(view);
        ButterKnife.inject(this, view);
        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        mListener.onItemClick(getAdapterPosition());
    }

    public void setListener(SelectedItemListener mListener) {
        this.mListener = mListener;
    }

    public interface SelectedItemListener{
        void onItemClick(int position);
    }
}
