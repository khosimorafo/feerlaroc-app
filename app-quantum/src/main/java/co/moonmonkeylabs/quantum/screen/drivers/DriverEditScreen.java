package co.moonmonkeylabs.quantum.screen.drivers;

import android.app.Activity;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.feerlaroc.core.app.App;
import com.feerlaroc.core.error.FrameworkException;
import com.feerlaroc.core.listeners.FrameworkCompletionListener;

import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.moonmonkeylabs.quantum.ActivityModule;
import co.moonmonkeylabs.quantum.R;
import co.moonmonkeylabs.quantum.application.AppDataHolder;
import co.moonmonkeylabs.quantum.application.QuantumApp;
import co.moonmonkeylabs.quantum.common.flow.ActivityHelper;
import co.moonmonkeylabs.quantum.common.flow.Layout;
import co.moonmonkeylabs.quantum.common.lifecycle.LifecycleOwner;
import co.moonmonkeylabs.quantum.common.lifecycle.LifecycleViewPresenter;
import co.moonmonkeylabs.quantum.common.mortarscreen.WithModule;
import co.moonmonkeylabs.quantum.listeners.OnModelDataChangedListener;
import co.moonmonkeylabs.quantum.model.Driver;
import co.moonmonkeylabs.quantum.utils.Misc;
import co.moonmonkeylabs.quantum.view.drivers.DriverEditView;
import co.moonmonkeylabs.quantum.view.drivers.DriversView;
import flow.path.Path;

/**
 * Created by root on 2016/02/03.
 */
@Layout(R.layout.layout_driver_edit_view)
@WithModule(DriverEditScreen.Module.class)
public class DriverEditScreen extends Path{

    @dagger.Module(injects = DriverEditView.class, addsTo = ActivityModule.class)
    public class Module {
    }

    @Singleton
    public static class Presenter extends LifecycleViewPresenter<DriverEditView>
            implements OnModelDataChangedListener, FrameworkCompletionListener {

        private static final int SELECT_PICTURE = 1;

        App app = new QuantumApp();
        Driver mDriver = new Driver();
        DriversView driversView;

        private final ActivityHelper activityHelper;

        @Inject
        public Presenter(LifecycleOwner lifecycleOwner, ActivityHelper activityHelper) {
            super(lifecycleOwner);
            this.activityHelper = activityHelper;
        }

        public void save(LinearLayout caller) {

            driversView = (DriversView) caller;

            try {

                app.create(Driver.class, mDriver, this);

            } catch (FrameworkException e) {
                e.printStackTrace();
            }

        }

        public void updateTextInputs() {

            if(getView() == null) return;

            getView().getEditDriverName().setText(mDriver.getName());
            getView().getEditIDNumber().setText(mDriver.getRSAIDNumber());
            getView().getEditPDPExpiryDate().setText(mDriver.getPDPExpiryDate());
            if(mDriver.getBitmapString() == null || mDriver.getBitmapString().equals("")){
                getView().getDriverImage().setImageResource(R.drawable.ic_account_grey600_48dp);
            } else{
                getView().getDriverImage().setImageDrawable(mDriver.getDrawable(getView().getContext()));
            }

        }

        public void handlePhotoDisplayPickButtonClicked() {

            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            Activity activity = activityHelper.findActivity((ContextWrapper) getView().getContext());
            activity.startActivityForResult(
                    Intent.createChooser(intent, "Select Picture"),
                    SELECT_PICTURE);

        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {

            if (requestCode == SELECT_PICTURE && data != null) {

                try {

                    final Uri imageUri = data.getData();
                    Activity activity = activityHelper.findActivity((ContextWrapper) getView().getContext());
                    final InputStream imageStream =
                            activity.getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

                    String encodedImageString = Misc.getInstance()
                            .encodeBitmap(Misc.doGreyscale(selectedImage));
                    mDriver.setBitmapString(encodedImageString);

                    Drawable drawable = new BitmapDrawable(activity.getResources(),
                            Misc.getInstance().decodeBitmap(mDriver.getBitmapString()));

                    getView().setImage(drawable);
                    AppDataHolder.getInstance().setDrawable(drawable);


                } catch (FileNotFoundException e) {
                    Toast.makeText(getView().getContext(), "Failed to load image", Toast.LENGTH_SHORT).show();
                }
            }
        }

        public Driver getDriver() {
            return mDriver;
        }

        public void setDriver(Driver driver) {
            this.mDriver = driver;
        }

        @Override
        public void onModelDataChanged(Driver entity) {
            setDriver(entity);
        }

        @Override
        public void onSuccess() {
            driversView.onSuccess();
        }
    }
}
