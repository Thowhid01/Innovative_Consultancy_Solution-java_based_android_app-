package com.efty.innovativeconsultancysolutions;


public class Information {
    String name,date,phone,workbackground,bloodgroup;

    public Information(String name, String date, String phone, String workbackground, String bloodgroup) {
        this.name = name;
        this.date = date;
        this.phone = phone;
        this.workbackground = workbackground;
        this.bloodgroup = bloodgroup;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWorkbackground() {
        return workbackground;
    }

    public void setWorkbackground(String workbackground) {
        this.workbackground = workbackground;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }



    public Information() {

    }
}
