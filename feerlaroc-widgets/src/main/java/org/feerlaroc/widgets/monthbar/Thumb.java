package org.feerlaroc.widgets.monthbar;

import org.feerlaroc.widgets.rangebar.PinView;
import org.feerlaroc.widgets.utils.Month;
import org.feerlaroc.widgets.utils.Pair;

/**
 * Created by root on 2016/03/01.
 */
public class Thumb {

    private Month mMonth;
    private String mStatus;
    private Pair mCoordinates;
    private PinView mThumb;

    public Month getMonth() {
        return mMonth;
    }

    public void setMonth(Month mMonth) {
        this.mMonth = mMonth;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public Pair getCoordinates() {
        return mCoordinates;
    }

    public void setCoordinates(Pair mCoordinates) {
        this.mCoordinates = mCoordinates;
    }

    public PinView getThumb() {
        return mThumb;
    }

    public void setThumb(PinView thumb) {
        this.mThumb = thumb;
    }
}
