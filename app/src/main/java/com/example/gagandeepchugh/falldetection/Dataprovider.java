package com.example.gagandeepchugh.falldetection;

/**
 * Created by Gagandeep Chugh on 22-05-2018.
 */

public class Dataprovider {

    private  String id;
    private  String name;
    private  String phone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Dataprovider(String id, String name, String phone)
    {
        this.id=id;
        this.name=name;
        this.phone=phone;

    }
}
