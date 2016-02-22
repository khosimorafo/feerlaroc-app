package co.moonmonkeylabs.flowmortarexampleapp.view.drivers;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;
import android.widget.EditText;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import co.moonmonkeylabs.flowmortarexampleapp.R;
import co.moonmonkeylabs.flowmortarexampleapp.common.widget.CustomFrameLayout;
import co.moonmonkeylabs.flowmortarexampleapp.screen.drivers.DriverEditScreen;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by root on 2016/02/03.
 */
public class DriverEditView extends CustomFrameLayout<DriverEditScreen.Presenter> {

    @Inject
    protected DriverEditScreen.Presenter presenter;

    @InjectView(R.id.input_layout_driver_name)
    protected TextInputLayout inputLayoutDriverName;
    @InjectView(R.id.edit_driver_name)
    protected EditText editDriverName;

    @InjectView(R.id.input_layout_id_number)
    protected TextInputLayout inputLayoutIDNumber;
    @InjectView(R.id.edit_id_number)
    protected EditText editIDNumber;

    @InjectView(R.id.input_layout_pdp_expiry_date)
    protected TextInputLayout inputLayoutPDPExpiryDate;
    @InjectView(R.id.edit_pdp_expiry_date)
    protected EditText editPDPExpiryDate;

    @InjectView(R.id.button_add_driver_image)
    protected CircleImageView driverImage;

    public DriverEditView(Context parentContext, AttributeSet attrs) {
        super(parentContext, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.inject(this);

    }

    @Override
    public DriverEditScreen.Presenter getPresenter() {
        return presenter;
    }

    @OnClick(R.id.button_add_driver_image)
    public void photoDisplayPickButtonClicked() {
        presenter.handlePhotoDisplayPickButtonClicked();
    }

    protected void readInputValues(){
        presenter.getDriver().setName(editDriverName.getText().toString());
        presenter.getDriver().setRSAIDNumber(editIDNumber.getText().toString());
        presenter.getDriver().setPDPExpiryDate(editPDPExpiryDate.getText().toString());
    }

    public void setImage(Drawable selectedImage) {
        driverImage.setImageDrawable(selectedImage);
    }

    public EditText getEditDriverName() {
        return editDriverName;
    }

    public EditText getEditIDNumber() {
        return editIDNumber;
    }

    public CircleImageView getDriverImage() {
        return driverImage;
    }

    public EditText getEditPDPExpiryDate() {
        return editPDPExpiryDate;
    }

}
