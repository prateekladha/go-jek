package com.gojek.vehicle;

public abstract class Vehicle {

    protected String registrationNumber;
    protected String color;

    public String getRegistrationNumber(){
        return this.registrationNumber;
    }

    public String getColor(){
        return this.color;
    }

}
