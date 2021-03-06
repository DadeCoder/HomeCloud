package com.dade.core.user.purchaser;

import com.dade.common.utils.LogUtil;
import com.dade.core.basic.BasicMongoDao;
import com.sun.deploy.net.proxy.pac.PACFunctions;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by Dade on 2017/3/12.
 */
@Component
public class PurchaserDao extends BasicMongoDao<Purchaser> {

    public List<Purchaser> getAllUsers(){
        Criteria criteria = Criteria.where(Purchaser.FIELD_DELETED).ne(true)
                .and(Purchaser.FIELD_ROLE).ne("AGENT");

        return find(Query.query(criteria));
    }

    public Long getUsersCount(){

        Criteria criteria = Criteria.where(Purchaser.FIELD_DELETED).ne(true);

        return mongoOperations.count(Query.query(criteria), Purchaser.class);

    }

    public boolean isFocus(String houseId, String phone){
        Purchaser purchaser = getByPhoneNumber(phone);

        List<PurchaserHouse> focusList = purchaser.getFocusHouseList();

        for (PurchaserHouse purchaserHouse : focusList){
            if (purchaserHouse.getHouseId().equals(houseId))
                return true;
        }

//        if (focusList.contains(houseId) == true)
//            return true;

        return false;
    }

    public void focus(String houseId, String purchaserId){
        Purchaser purchaser = getByPhoneNumber(purchaserId);

        LogUtil.info(purchaser.toString());

        List<PurchaserHouse> focusHouseList = purchaser.getFocusHouseList();
        PurchaserHouse purchaserHouse = new PurchaserHouse();
        purchaserHouse.setHouseId(houseId);
        purchaserHouse.setTime(new Date());

        focusHouseList.add(purchaserHouse);

        purchaser.setFocusHouseList(focusHouseList);

        mongoOperations.save(purchaser);
    }

    public void cancelFocus(String houseId, String purchaserId){
        Purchaser purchaser = getByPhoneNumber(purchaserId);

        LogUtil.info(purchaser.toString());

        List<PurchaserHouse> focusHouseList = purchaser.getFocusHouseList();

        if (focusHouseList == null)
            return;

        Iterator<PurchaserHouse> iter = focusHouseList.iterator();
        while(iter.hasNext()){
            PurchaserHouse ph = iter.next();
            if(ph.getHouseId().equals(houseId)){
                iter.remove();
            }
        }

//        for (PurchaserHouse ph: focusHouseList){
//            if (ph.getHouseId().equals(houseId))
//                focusHouseList.remove(ph);
//        }

        purchaser.setFocusHouseList(focusHouseList);

        mongoOperations.save(purchaser);
    }



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

    public void atomicUpdate(Purchaser purchaser){
        Date date = new Date();
        purchaser.setModifyDate(date);

        mongoOperations.save(purchaser);
    }

    public Purchaser atomicCreateAgent(Purchaser purchaser){
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
        purchaser.setRole("AGENT");

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
        purchaser.setBuyHouseList(new ArrayList<>());
        purchaser.setSellHouseList(new ArrayList<>());
        purchaser.setRentHouseList(new ArrayList<>());
        purchaser.setRentOutHouseList(new ArrayList<>());
        purchaser.setImageHeaderUrl("default.jpg");
        purchaser.setRole("USER");

        mongoOperations.insert(purchaser);

        return purchaser;
    }

    public void save(Purchaser purchaser){
        mongoOperations.save(purchaser);
    }


    @Override
    public Class<Purchaser> getReturnClass() {
        return Purchaser.class;
    }
}
