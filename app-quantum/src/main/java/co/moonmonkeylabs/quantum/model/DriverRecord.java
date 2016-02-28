package co.moonmonkeylabs.quantum.model;


import javax.inject.Inject;

import co.moonmonkeylabs.quantum.utils.DateService;

/**
 * Created by root on 2016/01/20.
 */
//@DaggerScope(MyTaxiApp.Component.class)
public class DriverRecord extends AbstractDBEntity {

    private final String DB_KEY = "record";

    String date = new DateService().getCurrentDate();
    Double cash = 0.0;
    Double fuel = 0.0;
    Double repairs = 0.0;

    @Inject
    public DriverRecord(double cash, double fuel, double repairs) {
        setCash(cash); setFuel(fuel); setRepairs(repairs);
    }

    @Override
    public String DBKey() {
        return DB_KEY;
    }

    @Override
    public <T> T getValue(Class<T> clazz) {
        return null;
    }

    public DriverRecord() {}

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getCash() {
        return cash;
    }

    public void setCash(Double cash) {
        this.cash = cash;
    }

    public Double getFuel() {
        return fuel;
    }

    public void setFuel(Double fuel) {
        this.fuel = fuel;
    }

    public Double getRepairs() {
        return repairs;
    }

    public void setRepairs(Double repairs) {
        this.repairs = repairs;
    }

}
