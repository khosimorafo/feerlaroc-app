package co.moonmonkeylabs.quantum.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.InjectView;
import butterknife.OnClick;
import co.moonmonkeylabs.quantum.R;
import co.moonmonkeylabs.quantum.common.flow.HandlesBack;
import co.moonmonkeylabs.quantum.screen.Wizard1Screen;

public class Wizard1View extends CustomLinearLayout<Wizard1Screen.Presenter> implements HandlesBack {

  @Inject
  Wizard1Screen.Presenter presenter;

  @InjectView(R.id.wizard_1_count)
  TextView wizardCounter;

  public Wizard1View(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  public Wizard1Screen.Presenter getPresenter() {
    return presenter;
  }

  @OnClick(R.id.wizard_1_next_button)
  public void nextButtonClicked() {
    presenter.handleNextButtonClicked();
  }

  public void updateWizardCount(int count) {
    wizardCounter.setText("Wizard Step Counter: " + count);
  }

  @Override
  public boolean onBackPressed() {
    presenter.handleBackPressed();
    return false;
  }
}
