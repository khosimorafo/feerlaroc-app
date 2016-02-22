package co.moonmonkeylabs.flowmortarexampleapp.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import co.moonmonkeylabs.flowmortarexampleapp.utils.Misc;

/**
 * Created by root on 2016/02/05.
 */

public class Driver extends AbstractDBEntity {

    @JsonIgnore
    private final String DB_KEY = "drivers";

    String name;
    String RSAIDNumber;
    String PDPExpiryDate;
    String bitmapString;
    Bitmap bitmap;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRSAIDNumber() {
        return RSAIDNumber;
    }

    public void setRSAIDNumber(String RSAIDNumber) {
        this.RSAIDNumber = RSAIDNumber;
    }

    public String getPDPExpiryDate() {
        return PDPExpiryDate;
    }

    public void setPDPExpiryDate(String PDPExpiryDate) {
        this.PDPExpiryDate = PDPExpiryDate;
    }

    public String getBitmapString() {
        return bitmapString;
    }

    public void setBitmapString(String bitmapString) {
        this.bitmapString = bitmapString;
        bitmap = Misc.getInstance().decodeBitmap(getBitmapString());
    }

    @JsonIgnore
    public Drawable getDrawable(Context context){
        Drawable drawable = new BitmapDrawable(context.getResources(), bitmap);
        return drawable;
    }

    @Override
    @JsonIgnore
    public String DBKey() {
        return DB_KEY;
    }

}
