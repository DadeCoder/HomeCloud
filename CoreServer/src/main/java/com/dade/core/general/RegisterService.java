package com.dade.core.general;

import com.dade.core.house.HouseDao;
import com.dade.core.user.purchaser.Purchaser;
import com.dade.core.user.purchaser.PurchaserDao;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

/**
 * Created by Dade on 2017/3/12.
 */
@Component
public class RegisterService {

    @Autowired
    PurchaserDao purchaserDao;

    @Autowired
    RegisterDao registerDao;

    @Autowired
    HouseDao houseDao;

    public IndexInfoDto getInfo(){

        IndexInfoDto dto = new IndexInfoDto();

        Long users = purchaserDao.getUsersCount();

        Long sellHouses =houseDao.getSellHousesCount();

        Long rentHouses = houseDao.getRentHousesCount();

        Integer doneCases = houseDao.getAllHousesCount();

        dto.setDoneCases(doneCases);
        dto.setRentHouses(rentHouses);
        dto.setSellHouses(sellHouses);
        dto.setUsers(users);

        return dto;
    }

    public Purchaser register(RegisterDto registerDto){
        Purchaser purchaser = new Purchaser();
        BeanUtils.copyProperties(registerDto, purchaser);
        purchaser.setName(registerDto.getUsername());
        return purchaserDao.atomicCreate(purchaser);
    }

    public Boolean isNameLegal(String name){
        Purchaser purchaser = registerDao.findByName(name);
        if (purchaser == null)
            return true;
        return false;
    }

}
