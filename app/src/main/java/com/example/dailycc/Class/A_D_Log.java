package com.example.dailycc.Class;

public class A_D_Log {
    private long id;
    private String item;
    private double pre_value;
    private double now_value;
    private double change;
    private String time;

    public A_D_Log() {
    }

    public A_D_Log(long id, String item, double pre_value, double now_value, double change, String time) {
        this.id = id;
        this.item = item;
        this.pre_value = pre_value;
        this.now_value = now_value;
        this.change = change;
        this.time = time;
    }

    public A_D_Log(String item, double pre_value, double now_value, double change, String time) {
        this.item = item;
        this.pre_value = pre_value;
        this.now_value = now_value;
        this.change = change;
        this.time = time;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getPre_value() {
        return pre_value;
    }

    public void setPre_value(double pre_value) {
        this.pre_value = pre_value;
    }

    public double getNow_value() {
        return now_value;
    }

    public void setNow_value(double now_value) {
        this.now_value = now_value;
    }

    public double getChange() {
        return change;
    }

    public void setChange(double pre_value, double now_value) {
        this.change = pre_value - now_value;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
