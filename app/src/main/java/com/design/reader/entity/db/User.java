package com.design.reader.entity.db;


import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

public class User extends DataSupport {

    @Column(nullable = false, unique = true)
    private String number;//电话号码

    private String name;//用户名

    private int status;//状态

    private double money;//账户余额

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
