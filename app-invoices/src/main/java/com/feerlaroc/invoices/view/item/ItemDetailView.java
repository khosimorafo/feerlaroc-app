package com.feerlaroc.invoices.view.item;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.EditText;

import com.feerlaroc.invoices.R;
import com.feerlaroc.invoices.common.widget.CustomRelativeLayout;
import com.feerlaroc.invoices.screen.item.ItemDetailScreen;

import org.feerlaroc.force.schema.helper.Constants;

import javax.inject.Inject;

import butterknife.InjectView;

/**
 * Created by root on 2016/03/17.
 */
public class ItemDetailView extends CustomRelativeLayout<ItemDetailScreen.Presenter> {

    @Inject
    ItemDetailScreen.Presenter mPresenter;

    @InjectView(R.id.input_layout_item_name)
    TextInputLayout mTextInputLayoutItemName;
    @InjectView(R.id.input_layout_item_description)
    TextInputLayout mTextInputLayoutItemDescription;
    @InjectView(R.id.input_layout_item_rate)
    TextInputLayout mTextInputLayoutItemRate;

    @InjectView(R.id.input_item_name)
    EditText mEditTextItemName;
    @InjectView(R.id.input_item_description)
    EditText mEditTextItemDescription;
    @InjectView(R.id.input_item_rate)
    EditText mEditTextItemRate;

    //@InjectView(R.id.save_item_btn)
    Button mButton;


    public ItemDetailView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        /*mButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().createItem();
            }
        });*/
    }

    @Override
    public ItemDetailScreen.Presenter getPresenter() {
        return mPresenter;
    }


    public String getItemName(){

        return mEditTextItemName.getText().toString();

    }

    public String getItemDescription(){

        return mEditTextItemDescription.getText().toString();

    }

    public Double getItemRate(){

        return Double.valueOf(mEditTextItemRate.getText().toString());

    }

    public String getItemSKU(){

        return Constants.SALESFORCE.EMPTY_STRING;

    }

    public String getItemStatus(){

        return Constants.SALESFORCE.EMPTY_STRING;

    }
}
