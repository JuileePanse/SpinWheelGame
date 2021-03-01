package com.spin_wheel.spinwheelgame.models;

import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("id")
    private int id;
    @SerializedName("value")
    private int value;
    @SerializedName("displayText")
    private String displayText;
    @SerializedName("currency")
    private String currency;

    public Item(int id, int value, String displayText, String currency)
    {
        this.id = id;
        this.value = value;
        this.displayText = displayText;
        this.currency = currency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDisplayText() {
        return displayText;
    }

    public void setDisplayText(String displayText) {
        this.displayText = displayText;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
