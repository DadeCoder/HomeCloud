package com.dade.core.general;

/**
 * Created by Dade on 2017/3/12.
 */
public class RegisterDto {

    private String name;                                // 姓名
    private String password;                            // 密码
    private String phoneNumber;                         // 手机号

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
