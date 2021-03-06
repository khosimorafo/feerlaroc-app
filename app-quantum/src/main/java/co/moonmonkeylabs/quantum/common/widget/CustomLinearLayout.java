package co.moonmonkeylabs.quantum.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import butterknife.ButterKnife;
import co.moonmonkeylabs.quantum.common.dagger.ObjectGraphService;
import mortar.Presenter;

/**
 * Custom {@link LinearLayout} that has support for {mortar.Mortar} and
 * {@link ButterKnife}.
 */
public abstract class CustomLinearLayout<T extends Presenter> extends LinearLayout {

  public CustomLinearLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
    ObjectGraphService.inject(context, this);
  }

  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();
    ButterKnife.inject(this);
  }

  @Override
  protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    getPresenter().takeView(this);
  }

  @Override
  protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    getPresenter().dropView(this);
  }

  public abstract T getPresenter();
}
