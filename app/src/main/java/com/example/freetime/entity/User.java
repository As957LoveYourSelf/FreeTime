package com.example.freetime.entity;

import java.util.Date;

public class User {
    private String uid;
    private String uname;
    private String email;
    private String password;
    private int uage;
    private String uphone;
    private Date birthday;
    private Byte state;
    private String rcode;
    private Byte online;
    private String registerTime;
    private String utype;
    private String fid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUage() {
        return uage;
    }

    public void setUage(int uage) {
        this.uage = uage;
    }

    public String getUphone() {
        return uphone;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public String getRcode() {
        return rcode;
    }

    public void setRcode(String rcode) {
        this.rcode = rcode;
    }

    public Byte getOnline() {
        return online;
    }

    public void setOnline(Byte online) {
        this.online = online;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getUtype() {
        return utype;
    }

    public void setUtype(String utype) {
        this.utype = utype;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", uname='" + uname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", uage=" + uage +
                ", uphone='" + uphone + '\'' +
                ", birthday=" + birthday +
                ", state=" + state +
                ", rcode='" + rcode + '\'' +
                ", online=" + online +
                ", registerTime='" + registerTime + '\'' +
                ", utype='" + utype + '\'' +
                ", fid='" + fid + '\'' +
                '}';
    }
}
