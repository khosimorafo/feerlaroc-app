package co.moonmonkeylabs.quantum.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.ImageView;

import javax.inject.Inject;

import butterknife.InjectView;
import butterknife.OnClick;
import co.moonmonkeylabs.quantum.R;
import co.moonmonkeylabs.quantum.screen.PhotoDisplayScreen;

public class PhotoDisplayView extends CustomFrameLayout<PhotoDisplayScreen.Presenter> {

  @Inject
  PhotoDisplayScreen.Presenter presenter;

  @InjectView(R.id.photo_display_image)
  ImageView photoDisplayImage;

  public PhotoDisplayView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  public PhotoDisplayScreen.Presenter getPresenter() {
    return presenter;
  }

  @Override
  protected void onFinishInflate() {
      super.onFinishInflate();
  }

  @OnClick(R.id.photo_display_pick_button)
  public void photoDisplayPickButtonClicked() {
    presenter.handlePhotoDisplayPickButtonClicked();
  }

  public void setImage(Bitmap selectedImage) {
    photoDisplayImage.setImageBitmap(selectedImage);
  }
}
