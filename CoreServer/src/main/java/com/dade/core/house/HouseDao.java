package com.dade.core.house;

import com.dade.core.basic.BasicMongoDao;
import com.dade.core.user.purchaser.Purchaser;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by Dade on 2017/3/20.
 */
@Component
public class HouseDao extends BasicMongoDao<House> {
    @Override
    public Class<House> getReturnClass() {
        return House.class;
    }

    public House atomicCreate(House house){
        Date date = new Date();
        house.setOnlineDate(date);

        mongoOperations.insert(house);

        return house;
    }


}
