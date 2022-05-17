package com.efty.innovativeconsultancysolutions;

public class Consultant {
    private String workbackground;
    private String name;
    private String phone;
    private String image;
    private String about;
    private String bloodgroup;
    private String date;
    private String gender;
    private String email;

    public Consultant(String workbackground, String name, String phone,String image) {
        this.workbackground = workbackground;
        this.name = name;
        this.phone = phone;
        this.image=image;
    }

    public Consultant(String workbackground, String name, String phone, String image, String about, String bloodgroup, String date, String gender) {
        this.workbackground = workbackground;
        this.name = name;
        this.phone = phone;
        this.image = image;
        this.about = about;
        this.bloodgroup = bloodgroup;
        this.date = date;
        this.gender = gender;
    }

    public Consultant(String workbackground, String name, String phone, String image, String about, String bloodgroup, String date, String gender, String email) {
        this.workbackground = workbackground;
        this.name = name;
        this.phone = phone;
        this.image = image;
        this.about = about;
        this.bloodgroup = bloodgroup;
        this.date = date;
        this.gender = gender;
        this.email = email;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Consultant(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public Consultant() {
    }

    public String getWorkbackground() {
        return workbackground;
    }

    public void setWorkbackground(String workbackground) {
        this.workbackground = workbackground;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
