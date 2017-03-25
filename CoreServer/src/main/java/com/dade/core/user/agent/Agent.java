package com.dade.core.user.agent;


import com.dade.core.basic.BasicModelObject;

/**
 * Created by Dade on 2017/3/21.
 */
public class Agent  extends BasicModelObject {

    private String id;

    private String name;
    private String password;
    private String phone;

    public static final String FIELE_PHONE = "phone";

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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

    @Override
    public String toString() {
        return "Agent{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
