package com.dade.core.house.dto;

import java.util.List;

/**
 * Created by Dade on 2017/3/22.
 */
public class HouseSearchDto {

    List<HouseDto> res;

    public List<HouseDto> getRes() {
        return res;
    }

    public void setRes(List<HouseDto> res) {
        this.res = res;
    }

    @Override
    public String toString() {
        return "HouseSearchDto{" +
                "res=" + res +
                '}';
    }
}
