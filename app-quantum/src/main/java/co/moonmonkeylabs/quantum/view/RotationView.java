package co.moonmonkeylabs.quantum.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.feerlaroc.moonmonkeylabs.common.widget.CustomFrameLayout;

import javax.inject.Inject;

import butterknife.InjectView;
import co.moonmonkeylabs.quantum.R;
import co.moonmonkeylabs.quantum.screen.RotationScreen;


public class RotationView extends CustomFrameLayout<RotationScreen.Presenter> {

  @Inject
  RotationScreen.Presenter presenter;

  @InjectView(R.id.rotation_count)
  TextView counterText;

  public RotationView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  public RotationScreen.Presenter getPresenter() {
    return presenter;
  }

  public void updateCount(int count) {
    counterText.setText("onLoad count: " + count);
  }
}
