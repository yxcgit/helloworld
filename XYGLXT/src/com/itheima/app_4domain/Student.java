package com.itheima.app_4domain;

public class Student {
    private int id;
    private String stuid;
    private String stuname;
    private int stuage;
    private String stuXL;
    private String stusex;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", stuid='" + stuid + '\'' +
                ", stuname='" + stuname + '\'' +
                ", stuage=" + stuage +
                ", stuXL='" + stuXL + '\'' +
                ", stusex='" + stusex + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid;
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }

    public int getStuage() {
        return stuage;
    }

    public void setStuage(int stuage) {
        this.stuage = stuage;
    }

    public String getStuXL() {
        return stuXL;
    }

    public void setStuXL(String stuXL) {
        this.stuXL = stuXL;
    }

    public String getStusex() {
        return stusex;
    }

    public void setStusex(String stusex) {
        this.stusex = stusex;
    }
}
