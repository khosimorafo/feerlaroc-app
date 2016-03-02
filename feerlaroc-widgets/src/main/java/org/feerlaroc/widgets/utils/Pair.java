package org.feerlaroc.widgets.utils;

public class Pair<X, Y> {

    private final X mX;
    private final Y mY;

    public Pair(X left, Y right) {
        this.mX = left;
        this.mY = right;
    }

    public X getX() { return mX; }
    public Y getY() { return mY; }

    @Override
    public int hashCode() { return mX.hashCode() ^ mY.hashCode(); }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pair)) return false;
        Pair pairo = (Pair) o;
        return this.mX.equals(pairo.getX()) &&
                this.mY.equals(pairo.getY());
    }

}
