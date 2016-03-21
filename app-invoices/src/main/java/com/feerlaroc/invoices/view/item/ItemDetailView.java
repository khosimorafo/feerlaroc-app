package com.feerlaroc.invoices.view.item;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.widget.EditText;

import com.feerlaroc.invoices.R;
import com.feerlaroc.invoices.application.AppDataHolder;
import com.feerlaroc.invoices.common.widget.CustomRelativeLayout;
import com.feerlaroc.invoices.screen.item.ItemDetailScreen;

import org.feerlaroc.force.schema.helper.Constants;
import org.feerlaroc.widgets.toolbar.ProgressToolbar;

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
    ProgressToolbar mProgressToolbar;


    public ItemDetailView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {

        super.onFinishInflate();

        AppCompatActivity activity = (AppCompatActivity) AppDataHolder.getInstance().getActivityContext();

        mProgressToolbar = new ProgressToolbar(activity, R.layout.progress_toolbar);
        //mProgressToolbar = (ProgressToolbar) findViewById(R.id.progress_toolbar);
        //mProgressToolbar.start();
        //mProgressToolbar.inflateMenu(this, R.menu.toolbar_menu);

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
