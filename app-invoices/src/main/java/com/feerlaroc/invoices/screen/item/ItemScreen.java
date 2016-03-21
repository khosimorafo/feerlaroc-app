package com.feerlaroc.invoices.screen.item;

import com.feerlaroc.invoices.ActivityModule;
import com.feerlaroc.invoices.R;
import com.feerlaroc.invoices.common.flow.Layout;
import com.feerlaroc.invoices.common.mortarscreen.WithModule;
import com.feerlaroc.invoices.view.item.ItemView;

import javax.inject.Inject;
import javax.inject.Singleton;

import flow.path.Path;
import mortar.ViewPresenter;

/**
 * Created by root on 2016/03/17.
 */


@Layout(R.layout.screen_items)
@WithModule(ItemScreen.Module.class)
public class ItemScreen extends Path {

    @dagger.Module(injects = ItemView.class, addsTo = ActivityModule.class)
    public class Module {
    }

    @Singleton
    public static class Presenter extends ViewPresenter<ItemView>{

        @Inject
        public Presenter() {}

    }
}
