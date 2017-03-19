package com.dade.core.general;

import com.dade.core.user.purchaser.Purchaser;
import com.dade.core.user.purchaser.PurchaserDao;
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
