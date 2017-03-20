package com.dade.core.house.dto;

/**
 * Created by Dade on 2017/3/20.
 */
public class HouseRentOutResDto {

    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "HouseRentOutResDto{" +
                "info='" + info + '\'' +
                '}';
    }
}
