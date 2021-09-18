package com.cihan.hayatver;

public class KanVerenHelperClass {
    String name,phone,city,district,bloodType,gender,onay;

    public KanVerenHelperClass() {

    }

    public void setOnay(String onay) {
        this.onay = onay;
    }

    public String getOnay() {
        return onay;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getCity() {
        return city;
    }

    public String getDistrict() {
        return district;
    }

    public String getBloodType() {
        return bloodType;
    }

    public KanVerenHelperClass(String name, String phone, String city, String district,
                               String bloodType,String gender,String onay) {
        this.name = name;
        this.phone = phone;
        this.city = city;
        this.district = district;
        this.bloodType = bloodType;
        this.gender = gender;
        this.onay = onay;
    }
}
