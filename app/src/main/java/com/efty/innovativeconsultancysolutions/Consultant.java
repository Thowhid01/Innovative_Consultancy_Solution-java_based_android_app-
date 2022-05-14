package com.efty.innovativeconsultancysolutions;

public class Consultant {
    private String date;
    private String name;
    private  String phone;

    public Consultant(String date, String name, String phone) {
        this.date = date;
        this.name = name;
        this.phone = phone;
    }

    public Consultant(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public Consultant() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
