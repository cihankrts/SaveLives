package com.cihan.hayatver;

public class Donor {

    private String bloodType;
    private String city;
    private String district;
    private String gender;
    private String name;
    private String onay;
    private String phone;

    public Donor() {
    }

    public Donor(String bloodType, String city, String district, String gender, String name, String onay, String phone) {
        this.bloodType = bloodType;
        this.city = city;
        this.district = district;
        this.gender = gender;
        this.name = name;
        this.onay = onay;
        this.phone = phone;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOnay() {
        return onay;
    }

    public void setOnay(String onay) {
        this.onay = onay;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
