package com.efty.innovativeconsultancysolutions;

public class Consultant {
    private String workbackground;
    private String name;
    private  String phone;
    private String image;

    public Consultant(String workbackground, String name, String phone,String image) {
        this.workbackground = workbackground;
        this.name = name;
        this.phone = phone;
        this.image=image;
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
}
