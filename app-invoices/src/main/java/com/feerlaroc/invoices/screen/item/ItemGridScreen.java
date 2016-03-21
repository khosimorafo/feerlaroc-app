package com.feerlaroc.invoices.screen.item;

import com.feerlaroc.invoices.ActivityModule;
import com.feerlaroc.invoices.R;
import com.feerlaroc.invoices.common.flow.Layout;
import com.feerlaroc.invoices.common.mortarscreen.WithModule;
import com.feerlaroc.invoices.view.item.ItemGridView;

import javax.inject.Inject;
import javax.inject.Singleton;

import flow.path.Path;
import mortar.ViewPresenter;

/**
 * Created by root on 2016/03/17.
 */

@Layout(R.layout.layout_item_list)
@WithModule(ItemGridScreen.Module.class)
public class ItemGridScreen extends Path {

    @dagger.Module(injects = ItemGridView.class, addsTo = ActivityModule.class)
    public class Module {
    }

    @Singleton
    public static class Presenter extends ViewPresenter<ItemGridView> {

        @Inject
        public Presenter() {}

    }

}
