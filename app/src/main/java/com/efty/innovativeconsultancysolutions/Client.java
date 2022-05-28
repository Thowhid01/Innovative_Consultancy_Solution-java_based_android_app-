package com.efty.innovativeconsultancysolutions;

public class Client {
    private String consultantEmail;
    private String consultantName;
    private String consultantPhone;
    private String consultantType;
    private String consultatDetails;
    private String consultationDate;
    private String userEmail;
    private String userName;

    public Client() {
    }

    public Client(String consultantEmail, String consultantName, String consultantPhone, String consultantType, String consultatDetails, String consultationDate, String userEmail, String userName) {
        this.consultantEmail = consultantEmail;
        this.consultantName = consultantName;
        this.consultantPhone = consultantPhone;
        this.consultantType = consultantType;
        this.consultatDetails = consultatDetails;
        this.consultationDate = consultationDate;
        this.userEmail = userEmail;
        this.userName = userName;
    }

    public String getConsultantEmail() {
        return consultantEmail;
    }

    public void setConsultantEmail(String consultantEmail) {
        this.consultantEmail = consultantEmail;
    }

    public String getConsultantName() {
        return consultantName;
    }

    public void setConsultantName(String consultantName) {
        this.consultantName = consultantName;
    }

    public String getConsultantPhone() {
        return consultantPhone;
    }

    public void setConsultantPhone(String consultantPhone) {
        this.consultantPhone = consultantPhone;
    }

    public String getConsultantType() {
        return consultantType;
    }

    public void setConsultantType(String consultantType) {
        this.consultantType = consultantType;
    }

    public String getConsultatDetails() {
        return consultatDetails;
    }

    public void setConsultatDetails(String consultatDetails) {
        this.consultatDetails = consultatDetails;
    }

    public String getConsultationDate() {
        return consultationDate;
    }

    public void setConsultationDate(String consultationDate) {
        this.consultationDate = consultationDate;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
