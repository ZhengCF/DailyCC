package com.example.dailycc.Class;

public class Month_4 {

    private long id;
    private double zhiFuBao;
    private double jingDong;
    private double weiXin;
    private double meiTuan;
    private double sum;

    public Month_4(double zhiFuBao, double jingDong, double weiXin, double meiTuan, double sum) {
        this.zhiFuBao = zhiFuBao;
        this.jingDong = jingDong;
        this.weiXin = weiXin;
        this.meiTuan = meiTuan;
        this.sum = sum;
    }

    public Month_4(long id, double zhiFuBao, double jingDong, double weiXin, double meiTuan, double sum) {
        this.id = id;
        this.zhiFuBao = zhiFuBao;
        this.jingDong = jingDong;
        this.weiXin = weiXin;
        this.meiTuan = meiTuan;
        this.sum = sum;
    }

    public double getJingDong() {
        return jingDong;
    }

    public void setJingDong(double jingDong) {
        this.jingDong = jingDong;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getZhiFuBao() {
        return zhiFuBao;
    }

    public void setZhiFuBao(double zhiFuBao) {
        this.zhiFuBao = zhiFuBao;
    }

    public double getWeiXin() {
        return weiXin;
    }

    public void setWeiXin(double weiXin) {
        this.weiXin = weiXin;
    }

    public double getMeiTuan() {
        return meiTuan;
    }

    public void setMeiTuan(double meiTuan) {
        this.meiTuan = meiTuan;
    }

    public double getSum() {
        return this.jingDong+this.meiTuan+this.weiXin+this.zhiFuBao;
    }

    @Override
    public String toString() {
        return "Month_4{" +
                "id=" + id +
                ", zhiFuBao=" + zhiFuBao +
                ", jingDong=" + jingDong +
                ", weiXin=" + weiXin +
                ", meiTuan=" + meiTuan +
                ", sum=" + sum +
                '}';
    }
}
