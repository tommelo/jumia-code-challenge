package com.jumia.customer.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = CustomerEntity.TABLE_NAME)
public class CustomerEntity {

    public static final String TABLE_NAME = "customer";
    public static final String PHONE_COLUMN_NAME = "phone";

    @Id
    private int id;
    private String name;
    private String phone;

    public CustomerEntity() {

    }

    public CustomerEntity(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

}