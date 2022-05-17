package com.efty.innovativeconsultancysolutions;

public class User {
    private String name;
    private String phone;
    private String workbackground;
    private String about;
    private String bloodgroup;
    private String date;
    private String email;
    private String gender;
    private String image;

    public User() {
    }

    public User(String name, String phone, String workbackground, String about, String bloodgroup, String date, String email, String gender, String image) {
        this.name = name;
        this.phone = phone;
        this.workbackground = workbackground;
        this.about = about;
        this.bloodgroup = bloodgroup;
        this.date = date;
        this.email = email;
        this.gender = gender;
        this.image = image;
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

    public String getWorkbackground() {
        return workbackground;
    }

    public void setWorkbackground(String workbackground) {
        this.workbackground = workbackground;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
