package com.dade.core.user.purchaser;

import com.dade.core.basic.BasicMongoDao;
import com.sun.deploy.net.proxy.pac.PACFunctions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Queue;

/**
 * Created by Dade on 2017/3/12.
 */
@Component
public class PurchaserDao extends BasicMongoDao<Purchaser> {

    public Purchaser addOne(Purchaser purchaser){
        mongoOperations.insert(purchaser);
        return purchaser;
    }

    public void saveImage(String path, String phone){
        Criteria criteria = Criteria.where(Purchaser.FIELD_PHONE_NUMBER).is(phone)
                .and(Purchaser.FIELD_DELETED).ne(true);
        Query query = Query.query(criteria);

        Purchaser purchaser = mongoOperations.findOne(query, Purchaser.class);
        purchaser.setImageHeaderUrl(path);
        Update update = Update.update(Purchaser.FIELD_IMAGE_URL, path);
        mongoOperations.updateFirst(query, update, Purchaser.class);

    }

    public Purchaser getByPhoneNumber(String phone){
        Criteria criteria = Criteria.where(Purchaser.FIELD_PHONE_NUMBER).is(phone)
                .and(Purchaser.FIELD_DELETED).ne(true);
        return mongoOperations.findOne(Query.query(criteria), Purchaser.class);
    }

    public Purchaser atomicCreate(Purchaser purchaser){
        Date date = new Date();
        purchaser.setCreateDate(date);
        purchaser.setModifyDate(date);
        purchaser.setDeleted(false);
        purchaser.setFocusHouseList(new ArrayList<>());
        purchaser.setHouseRecordList(new ArrayList<>());
        purchaser.setHouseScheduleList(new ArrayList<>());
        purchaser.setBuyHouseList(new ArrayList<>());
        purchaser.setSellHouseList(new ArrayList<>());
        purchaser.setRentHouseList(new ArrayList<>());
        purchaser.setRentOutHouseList(new ArrayList<>());
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
