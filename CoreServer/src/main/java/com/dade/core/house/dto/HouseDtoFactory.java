package com.dade.core.house.dto;

import com.dade.core.house.House;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;

import java.text.SimpleDateFormat;

/**
 * Created by Dade on 2017/3/20.
 */
public class HouseDtoFactory {

    public static House getHouse(HouseRentOutInpDto dto){
        House house = new House();
        BeanUtils.copyProperties(dto, house);
        return house;
    }

    public static  HouseDto getHouseDto(House house){
        HouseDto dto = new HouseDto();
        BeanUtils.copyProperties(house, dto);
        if (house.getOnlineType() == House.ONLINE_RENT)
            dto.setType("出租");
        else
            dto.setType("出售");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");

        dto.setDate(sdf.format(house.getOnlineDate()));

        return dto;
    }

    public static HouseRentOutResDto getHouseRentOut(House house){
        HouseRentOutResDto dto = new HouseRentOutResDto();
        BeanUtils.copyProperties(house, dto);
        return dto;
    }


}
