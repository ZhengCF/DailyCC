package com.example.dailycc.Class;

public class Month_4 {

    private long id;
    private int date; //202101,6位数，年份加月
    private double zhiFuBao;
    private double jingDong;
    private double meiTuan;
    private double weiXin;
    private double yunShanFu;
    private double sum;

    public Month_4() {
    }


    public Month_4(int date, double zhiFuBao, double jingDong, double meiTuan, double weiXin, double yunShanFu, double sum) {
        this.date = date;
        this.zhiFuBao = zhiFuBao;
        this.jingDong = jingDong;
        this.meiTuan = meiTuan;
        this.weiXin = weiXin;
        this.yunShanFu = yunShanFu;
        this.sum = sum;
    }

    public Month_4(long id, int date, double zhiFuBao, double jingDong, double meiTuan, double weiXin, double yunShanFu, double sum) {
        this.id = id;
        this.date = date;
        this.zhiFuBao = zhiFuBao;
        this.jingDong = jingDong;
        this.meiTuan = meiTuan;
        this.weiXin = weiXin;
        this.yunShanFu = yunShanFu;
        this.sum = sum;
    }

    public Month_4(long id, int date, double zhiFuBao, double jingDong, double meiTuan, double weiXin, double yunShanFu) {
        this.id = id;
        this.date = date;
        this.zhiFuBao = zhiFuBao;
        this.jingDong = jingDong;
        this.meiTuan = meiTuan;
        this.weiXin = weiXin;
        this.yunShanFu = yunShanFu;
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

    public double getMeiTuan() {
        return meiTuan;
    }

    public void setMeiTuan(double meiTuan) {
        this.meiTuan = meiTuan;
    }

    public double getWeiXin() {
        return weiXin;
    }

    public void setWeiXin(double weiXin) {
        this.weiXin = weiXin;
    }

    public double getSum() {
        return this.jingDong + this.meiTuan + this.weiXin + this.zhiFuBao+this.getYunShanFu();
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public double getYunShanFu() {
        return yunShanFu;
    }

    public void setYunShanFu(double yunShanFu) {
        this.yunShanFu = yunShanFu;
    }

    @Override
    public String toString() {
        return "Month_4{" +
                "id=" + id +
                ", date=" + date +
                ", zhiFuBao=" + zhiFuBao +
                ", jingDong=" + jingDong +
                ", meiTuan=" + meiTuan +
                ", weiXin=" + weiXin +
                ", yunShanFu=" + yunShanFu +
                ", sum=" + sum +
                '}';
    }


}
