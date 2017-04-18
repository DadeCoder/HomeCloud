package com.dade.core.message;

/**
 * Created by Dade on 2017/4/18.
 */
public class MessageDto {

    String message;
    String name;
    String phone;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
