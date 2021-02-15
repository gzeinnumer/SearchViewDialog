package com.gzeinnumer.svd.model;

public class SearchViewModel {
    private int realIndex;
    private String name;
    private boolean isChecked;

    public SearchViewModel(int realIndex, String name, boolean isChecked) {
        this.realIndex = realIndex;
        this.name = name;
        this.isChecked = isChecked;
    }

    public SearchViewModel(int realIndex, String name) {
        this.realIndex = realIndex;
        this.name = name;
    }

    //getRealIndex()
    public int getPosition() {
        return realIndex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRealIndex(int realIndex) {
        this.realIndex = realIndex;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
