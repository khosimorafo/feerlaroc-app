package com.feerlaroc.moonmonkeylabs.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import butterknife.ButterKnife;
import co.moonmonkeylabs.flowmortarexampleapp.common.dagger.ObjectGraphService;
import mortar.Presenter;

/**
 * Custom {@link FrameLayout} that has support for {@link mortar.Mortar} and
 * {@link ButterKnife}.
 */
public abstract class CustomFrameLayout<T extends Presenter> extends FrameLayout {

  public CustomFrameLayout(Context context, AttributeSet attrs) {
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
