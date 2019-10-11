package com.study.android.ex18;

public class SingerItem {

    private String name;
    private String age;
    private int resId;

    public SingerItem(String name, String age, int resId) {
        this.name = name;
        this.age = age;
        this.resId = resId;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public int getResId() {
        return resId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}
