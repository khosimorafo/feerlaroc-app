package co.moonmonkeylabs.quantum.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.feerlaroc.moonmonkeylabs.common.widget.CustomLinearLayout;

import javax.inject.Inject;

import butterknife.InjectView;
import butterknife.OnClick;
import co.moonmonkeylabs.quantum.R;
import co.moonmonkeylabs.quantum.screen.Wizard2Screen;

public class Wizard2View extends CustomLinearLayout<Wizard2Screen.Presenter> {

  @Inject
  Wizard2Screen.Presenter presenter;

  @InjectView(R.id.wizard_2_count)
  TextView wizardCounter;

  public Wizard2View(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  public Wizard2Screen.Presenter getPresenter() {
    return presenter;
  }

  @OnClick(R.id.wizard_2_next_button)
  public void nextButtonClicked() {
    presenter.handleNextButtonClicked();
  }

  public void updateWizardCount(int count) {
    wizardCounter.setText("Wizard Step Counter: " + count);
  }
}
