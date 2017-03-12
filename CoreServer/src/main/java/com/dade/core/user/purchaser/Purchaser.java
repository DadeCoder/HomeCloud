package com.dade.core.user.purchaser;

import com.dade.core.basic.BasicModelObject;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 用户模型
 * 买房 卖饭 租房用户统一
 * Created by Dade on 2017/3/12.
 */
@Document
public class Purchaser extends BasicModelObject {

    private String id;
    private String name;

    private Integer age;

    private String phoneNumber;
    private String password;
    private String role;

    private String imageHeaderUrl;

    public static final String FIELD_PHONE_NUMBER = "phoneNumber";


    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getImageHeaderUrl() {
        return imageHeaderUrl;
    }

    public void setImageHeaderUrl(String imageHeaderUrl) {
        this.imageHeaderUrl = imageHeaderUrl;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getId() {
        return id;
    }


    @Override
    public String toString() {
        return "Purchaser{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", imageHeaderUrl='" + imageHeaderUrl + '\'' +
                '}';
    }
}
