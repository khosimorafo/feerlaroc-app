package co.moonmonkeylabs.quantum.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

import com.feerlaroc.moonmonkeylabs.common.widget.CustomLinearLayout;

import javax.inject.Inject;

import butterknife.InjectView;
import butterknife.OnClick;
import co.moonmonkeylabs.quantum.R;
import co.moonmonkeylabs.quantum.screen.SettingScreen;

public class SettingView extends CustomLinearLayout<SettingScreen.Presenter> {

  @Inject
  SettingScreen.Presenter presenter;

  @InjectView(R.id.settings_name_input)
  EditText settingsNameInput;

  public SettingView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  public SettingScreen.Presenter getPresenter() {
    return presenter;
  }

  @OnClick(R.id.settings_update_name_button)
  public void settingsUpdateNameButtonClicked() {
    presenter.handleSaveUserPreferredName(settingsNameInput.getText().toString());
  }

  public void updatePreferredName(String userPreferredName) {
    settingsNameInput.setText(userPreferredName);
  }
}
