package com.fkit.fk1;

import java.io.Serializable;

public class Teacher implements Serializable {
    private String address;
    //private Person per;
    //transient关键字，序列化时忽略该变量
    transient private Person per;

    public Teacher(String address, Person per) {
        this.address = address;
        this.per = per;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Person getPer() {
        return per;
    }

    public void setPer(Person per) {
        this.per = per;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "address='" + address + '\'' +
                ", per=" + per +
                '}';
    }
}
