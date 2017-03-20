package com.dade.core.house;

import com.dade.core.house.dto.HouseDto;
import com.dade.core.house.dto.HouseDtoFactory;
import com.dade.core.house.dto.HouseRentOutInpDto;
import com.dade.core.house.dto.HouseRentOutResDto;
import com.dade.core.user.purchaser.Purchaser;
import com.dade.core.user.purchaser.PurchaserDao;
import com.dade.core.user.purchaser.PurchaserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Dade on 2017/3/20.
 */
@Component
public class HouseServices {

    @Autowired
    HouseDao houseDao;

    @Autowired
    PurchaserService purchaserService;

    public HouseDto getById(String houseId){
        House house = houseDao.findById(houseId);
        HouseDto dto = HouseDtoFactory.getHouseDto(house);

        return dto;
    }

    public HouseRentOutResDto rentOut(HouseRentOutInpDto dto, String phone){
        House inHouse = HouseDtoFactory.getHouse(dto);

        inHouse.setOwnerId(phone);
        inHouse.setOnlineType(House.ONLINE_RENT);

        House dbHouse = houseDao.atomicCreate(inHouse);
        purchaserService.updateHouseCreate(phone, dbHouse.getId());

        HouseRentOutResDto res = HouseDtoFactory.getHouseRentOut(dbHouse);

        res.setInfo(dbHouse.getId());

        return res;
    }

}
