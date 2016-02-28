package com.feerlaroc.invoices.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.feerlaroc.invoices.R;
import com.feerlaroc.invoices.common.widget.CustomFrameLayout;
import com.feerlaroc.invoices.screen.CustomerDetailHeaderScreen;

import javax.inject.Inject;

import butterknife.InjectView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by root on 2016/02/27.
 */
public class CustomerDetailHeaderView extends CustomFrameLayout<CustomerDetailHeaderScreen.Presenter> {

    @InjectView(R.id.text_contact)
    TextView textContactName;

    @InjectView(R.id.image_contact)
    CircleImageView imageContact;

    @Inject
    protected CustomerDetailHeaderScreen.Presenter presenter;

    public CustomerDetailHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    public void setCustomerName(String name){
        textContactName.setText(name);
    }

    public void setImageContact(Drawable drawable){

    }

    @Override
    public CustomerDetailHeaderScreen.Presenter getPresenter() {
        return presenter;
    }
}
