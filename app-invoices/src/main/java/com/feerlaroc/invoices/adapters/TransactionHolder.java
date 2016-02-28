package com.feerlaroc.invoices.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by root on 2016/02/28.
 */
public class TransactionHolder  extends RecyclerView.ViewHolder implements View.OnClickListener {



    SelectedItemListener mListener;

    public TransactionHolder(View view) {
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
