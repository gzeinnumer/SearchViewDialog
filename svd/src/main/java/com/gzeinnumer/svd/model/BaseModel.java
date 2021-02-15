package com.gzeinnumer.svd.model;

public class BaseModel<T> {
    private T T;

    private boolean isChecked;

    public BaseModel(T t, boolean isChecked) {
        T = t;
        this.isChecked = isChecked;
    }

    public BaseModel(T t) {
        T = t;
    }

    public T getT() {
        return T;
    }

    public void setT(T t) {
        T = t;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    @Override
    public String toString() {
        return T.toString();
    }
}