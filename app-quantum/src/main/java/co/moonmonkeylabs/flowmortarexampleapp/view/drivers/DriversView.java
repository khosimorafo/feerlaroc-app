package co.moonmonkeylabs.flowmortarexampleapp.view.drivers;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import co.moonmonkeylabs.flowmortarexampleapp.R;
import co.moonmonkeylabs.flowmortarexampleapp.common.widget.ControllableAppBarLayout;
import co.moonmonkeylabs.flowmortarexampleapp.common.widget.ControllableCoordinatorLayout;
import co.moonmonkeylabs.flowmortarexampleapp.common.widget.CustomLinearLayout;
import co.moonmonkeylabs.flowmortarexampleapp.listeners.OnDBTaskComplete;
import co.moonmonkeylabs.flowmortarexampleapp.screen.drivers.DriversScreen;

/**
 * Created by root on 2016/02/02.
 */
public class DriversView extends CustomLinearLayout<DriversScreen.Presenter> implements OnDBTaskComplete {

    @Inject
    protected DriversScreen.Presenter presenter;

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.appbar)
    ControllableAppBarLayout appbar;

    @InjectView(R.id.nested_scroll_view)
    NestedScrollView nestedScrollView;

    @InjectView(R.id.coordinatorLayout)
    ControllableCoordinatorLayout coordinatorLayout;

    DriverHeaderView driverHeaderView;
    DriverEditView driverEditView;
    DriverGridView driverGridView;

    Toolbar toolbarDriverGrid;
    TextView addNewDriver;

    ActionMenuItemView edit;
    ActionMenuItemView save;
    ActionMenuItemView delete;


    public DriversView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public DriversScreen.Presenter getPresenter() {
        return presenter;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.inject(this);

        driverHeaderView = (DriverHeaderView) findViewById(R.id.driver_header_layout);
        driverEditView = (DriverEditView) View.inflate(getContext(), R.layout.layout_driver_edit_view, null);
        driverGridView = (DriverGridView) View.inflate(getContext(), R.layout.layout_driver_grid, null);


        presenter.setDriverModelListeners(driverHeaderView.presenter,
                driverEditView.presenter, driverGridView.presenter);

        inflateGridLayout();

        appbar.setOnStateChangeListener(new ControllableAppBarLayout.OnStateChangeListener() {

            @Override
            public void onStateChange(ControllableAppBarLayout.State toolbarChange) {
                switch (toolbarChange) {

                    case COLLAPSED:
                        setPersistToolBar();
                        break;
                    case EXPANDED:
                        setEditView();
                        break;
                    case IDLE: // Just fired once between switching states
                        break;
                }
            }
        });
        setEditView();

    }

    void setEditView(){

        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.menu_toolbar_driver);
        edit    = (ActionMenuItemView) toolbar.findViewById(R.id.action_edit);
        delete  = (ActionMenuItemView) toolbar.findViewById(R.id.action_delete);

        edit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                inflateEditLayout();
                appbar.setExpanded(false);
                appbar.setScrollingEnabled(false);
                handleEditButtonClick();
                setPersistToolBar();
            }
        });

        delete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                appbar.setExpanded(true);
                appbar.setScrollingEnabled(true);
                handleDeleteButtonClick();
                setFocusToPreviousItem();
            }
        });
    }

    void setPersistToolBar(){

        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.menu_toolbar_edit_driver);
        save = (ActionMenuItemView) toolbar.findViewById(R.id.action_save);

        save.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                inflateGridLayout();
                appbar.setExpanded(true);
                appbar.setScrollingEnabled(true);
                handleSaveButtonClick();
                setEditView();
            }
        });

    }

    void inflateEditLayout(){

        nestedScrollView.removeAllViews();
        nestedScrollView.addView(driverEditView);

        coordinatorLayout.setScrollingEnabled(true);

    }

    void inflateGridLayout(){

        driverGridView.setParentView(this);

        nestedScrollView.removeAllViews();
        nestedScrollView.addView(driverGridView);
        coordinatorLayout.setScrollingEnabled(false);

        //Set up grid layout toolbar
        toolbarDriverGrid = (Toolbar) driverGridView.findViewById(R.id.toolbar_drivers);
        addNewDriver = (TextView) toolbarDriverGrid.findViewById(R.id.text_add_new_driver);
        addNewDriver.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.createNewDriver();
                inflateEditLayout();
                driverEditView.presenter.updateTextInputs();
                appbar.setExpanded(false);
                setPersistToolBar();
            }
        });

    }

    void handleEditButtonClick(){
        if(driverEditView!=null) {
            driverEditView.presenter.updateTextInputs();
        }
    }

    void handleSaveButtonClick() {

        if(driverEditView!=null){
            driverEditView.readInputValues();
            driverEditView.presenter.save(this);
        }

    }


    private void handleDeleteButtonClick() {

        driverGridView.presenter.remove();

    }


    private void setFocusToPreviousItem() {

    }


    @OnClick(R.id.fab_add_record)
    public void click() {
        //presenter.getIntent();
    }


    @Override
    public void onSuccess() {
        setEditView();
    }
}
