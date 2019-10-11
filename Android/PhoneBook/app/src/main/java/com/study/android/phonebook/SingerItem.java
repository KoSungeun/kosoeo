package com.study.android.phonebook;

public class SingerItem {

    private String name;
    private String telNum;
    private String gender;
    private int resId;

    public SingerItem() {
    }

    public SingerItem(String name, String telNum, String gender, int resId) {
        this.name = name;
        this.telNum = telNum;
        this.gender = gender;
        this.resId = resId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}
