package com.dade.core.user.purchaser;

import com.dade.core.basic.BasicMongoDao;
import com.dade.core.test.HunterUser;
import org.springframework.stereotype.Component;

/**
 * Created by Dade on 2017/3/12.
 */
@Component
public class PurchaserDao extends BasicMongoDao<Purchaser> {

    public Purchaser addOne(Purchaser purchaser){
        mongoOperations.insert(purchaser);
        return purchaser;
    }


    @Override
    public Class<Purchaser> getReturnClass() {
        return Purchaser.class;
    }
}
