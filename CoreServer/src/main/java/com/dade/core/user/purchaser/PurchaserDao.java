package com.dade.core.user.purchaser;

import com.dade.core.basic.BasicMongoDao;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Dade on 2017/3/12.
 */
@Component
public class PurchaserDao extends BasicMongoDao<Purchaser> {

    public Purchaser addOne(Purchaser purchaser){
        mongoOperations.insert(purchaser);
        return purchaser;
    }

    public Purchaser atomicCreate(Purchaser purchaser){
        Date date = new Date();
        purchaser.setCreateDate(date);
        purchaser.setModifyDate(date);
        purchaser.setDeleted(false);
        purchaser.setFocusHouseList(new ArrayList<>());
        purchaser.setHouseRecordList(new ArrayList<>());
        purchaser.setHouseScheduleList(new ArrayList<>());
        purchaser.setImageHeaderUrl("default.jpg");
        purchaser.setRole("USER");

        mongoOperations.insert(purchaser);

        return purchaser;
    }


    @Override
    public Class<Purchaser> getReturnClass() {
        return Purchaser.class;
    }
}
