package com.example.template2.firebase;

public class Helper {
    private String Name;
    private String Last_name;
    private Integer Age;
    private Long phone;
    public Helper()
    {
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public String getLast_name() {
        return Last_name;
    }
    public void setLast_name(String last_name) {
        Last_name = last_name;
    }
    public Integer getAge() {
        return Age;
    }
    public void setAge(Integer age) {
        Age = age;
    }
    public Long getPhone() {
        return phone;
    }
    public void setPhone(Long phone) {
        this.phone = phone;
    }
}
