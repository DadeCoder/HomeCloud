package com.dade.core.house.dto;

import com.dade.common.utils.StringUtil;
import com.dade.core.house.House;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
        if (house.getOnlineType() == House.ONLINE_RENT){
            dto.setType("出租");
        }
        else{
            dto.setType("出售");
            dto.setRentPrice(house.getSellPrice());
        }


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");

        dto.setDate(sdf.format(house.getOnlineDate()));

        if (house.getAccess() == House.ACCESS_PASS)
            dto.setStatus("通过审核");

        if (house.getAccess() == House.ACCESS_DENY)
            dto.setStatus("未通过审核");

        if (house.getAccess() == House.ACCESS_DAFAULT)
            dto.setStatus("未审核");

        return dto;
    }

    public static List<HouseDto> getHouseDtoPrice(List<House> houseList) {

        List<HouseDto> houseDtoList = new ArrayList<>();

        for (House house : houseList) {
            HouseDto houseDto = new HouseDto();
            BeanUtils.copyProperties(house, houseDto);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");

            houseDto.setDate(sdf.format(house.getOnlineDate()));

            houseDtoList.add(houseDto);

            if (house.getOnlineType() == House.ONLINE_RENT) {
                houseDto.setPriceInfo("租金： " + house.getRentPrice());
                houseDto.setTypeInfo("出租");
            } else {
                houseDto.setPriceInfo("价格： " + house.getSellPrice());
                houseDto.setTypeInfo("出售");
            }

            if (StringUtil.isEmpty(house.getPicUrl())) {
                houseDto.setPicUrl("http://127.0.0.1:8089/default.jpg");
            }

            if (house.getAccess() == House.ACCESS_PASS)
                houseDto.setStatus("通过审核");

            if (house.getAccess() == House.ACCESS_DENY)
                houseDto.setStatus("未通过审核");

            if (house.getAccess() == House.ACCESS_DAFAULT)
                houseDto.setStatus("未审核");

        }

        return houseDtoList;
    }

        public static List<HouseDto> getHouseDto(List<House> houseList){

            List<HouseDto> houseDtoList = new ArrayList<>();

            for (House house : houseList){
                HouseDto houseDto = new HouseDto();
                BeanUtils.copyProperties(house, houseDto);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");

                houseDto.setDate(sdf.format(house.getOnlineDate()));

                houseDtoList.add(houseDto);

                if (StringUtil.isEmpty(house.getPicUrl())){
                    houseDto.setPicUrl("http://127.0.0.1:8089/default.jpg");
                }

                if (house.getAccess() == House.ACCESS_PASS)
                    houseDto.setStatus("通过审核");

                if (house.getAccess() == House.ACCESS_DENY)
                    houseDto.setStatus("未通过审核");

                if (house.getAccess() == House.ACCESS_DAFAULT)
                    houseDto.setStatus("未审核");

            }

        return houseDtoList;

    }

    public static HouseRentOutResDto getHouseRentOut(House house){
        HouseRentOutResDto dto = new HouseRentOutResDto();
        BeanUtils.copyProperties(house, dto);
        return dto;
    }


}
