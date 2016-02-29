package com.feerlaroc.invoices.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.feerlaroc.invoices.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by root on 2016/02/28.
 */
public class TransactionHolder  extends RecyclerView.ViewHolder implements View.OnClickListener {

    @InjectView(R.id.text_date_of_payment) TextView textDateOfPayment;
    @InjectView(R.id.text_invoice_description) TextView textInvoiceDescription;
    @InjectView(R.id.text_amount_paid) TextView textAmountPaid;
    @InjectView(R.id.text_amount_outstanding_on_invoice) TextView textAmountOutstanding;
    @InjectView(R.id.text_invoice_amount) TextView textInvoiceAmount;

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
