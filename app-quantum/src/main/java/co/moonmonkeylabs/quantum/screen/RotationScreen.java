package co.moonmonkeylabs.quantum.screen;

import android.os.Bundle;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.moonmonkeylabs.quantum.ActivityModule;
import co.moonmonkeylabs.quantum.R;
import co.moonmonkeylabs.quantum.common.flow.Layout;
import co.moonmonkeylabs.quantum.common.mortarscreen.WithModule;
import co.moonmonkeylabs.quantum.view.RotationView;
import flow.path.Path;
import mortar.ViewPresenter;

@Layout(R.layout.rotation_layout)
@WithModule(RotationScreen.Module.class)
public class RotationScreen extends Path {

  @dagger.Module(injects = RotationView.class, addsTo = ActivityModule.class)
  public class Module {
  }

  @Singleton
  public static class Presenter extends ViewPresenter<RotationView> {

    private int onLoadCount = 0;

    @Inject
    public Presenter() {
    }

    @Override
    protected void onLoad(Bundle savedInstanceState) {
      super.onLoad(savedInstanceState);

      if (onLoadCount == 0 && savedInstanceState != null) {
        onLoadCount = savedInstanceState.getInt("onLoadCount", 0);
      }

      getView().updateCount(++onLoadCount);
    }


    @Override
    protected void onSave(Bundle outState) {
      outState.putInt("onLoadCount", onLoadCount);
    }
  }
}