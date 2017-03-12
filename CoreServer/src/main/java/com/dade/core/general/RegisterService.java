package com.dade.core.general;

import com.dade.core.user.purchaser.Purchaser;
import com.dade.core.user.purchaser.PurchaserDao;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Dade on 2017/3/12.
 */
@Component
public class RegisterService {

    @Autowired
    PurchaserDao purchaserDao;

    public Purchaser register(RegisterDto registerDto){
        Purchaser purchaser = new Purchaser();
        BeanUtils.copyProperties(registerDto, purchaser);
        return purchaserDao.atomicCreate(purchaser);
    }

}
