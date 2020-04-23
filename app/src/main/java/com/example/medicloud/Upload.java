package com.example.medicloud;

public class Upload {
    private String Name;
    private String ID;
    private String PhoneNumber;
    private String Email;
    private String Address;
    private String MedicalID;
    private String ImageID;

    public Upload() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getMedicalID() {
        return MedicalID;
    }

    public void setMedicalID(String medicalID) {
        MedicalID = medicalID;
    }

    public String getImageID() {
        return ImageID;
    }

    public void setImageID(String imageID) {
        ImageID = imageID;
    }
}
