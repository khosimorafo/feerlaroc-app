package co.moonmonkeylabs.flowmortarexampleapp.application;


import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

import co.moonmonkeylabs.flowmortarexampleapp.listeners.OnModelDataChangedListener;
import co.moonmonkeylabs.flowmortarexampleapp.listeners.OnModelPictureChangedListener;
import co.moonmonkeylabs.flowmortarexampleapp.model.Driver;


public class AppDataHolder {

    private List<OnModelDataChangedListener> listeners = new ArrayList<>();
    private List<OnModelPictureChangedListener> pictureChangedListeners = new ArrayList<>();

    Driver driver = new Driver();
    Drawable drawable;

    private static final AppDataHolder holder = new AppDataHolder();
    public static AppDataHolder getInstance() {return holder;}

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
        notifyDataListeners();
    }


    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
        notifyPictureChangedListener();
    }

    public void addDataChangedListener(OnModelDataChangedListener listener) {
        this.listeners.add(listener);
    }

    public void addPictureChangedListener(OnModelPictureChangedListener listener){
        this.pictureChangedListeners.add(listener);
    }

    public void notifyDataListeners(){
        for(OnModelDataChangedListener listener : listeners){
            listener.onModelDataChanged(driver);
        }
    }

    public void notifyPictureChangedListener(){
        for (OnModelPictureChangedListener listener : pictureChangedListeners){
            listener.onModelPictureChanged(drawable);
        }
    }

}
