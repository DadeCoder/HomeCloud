package com.dade.core.general;

import com.dade.core.basic.BasicMongoDao;
import com.dade.core.user.purchaser.Purchaser;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

/**
 * Created by Dade on 2017/3/19.
 */
@Component
public class RegisterDao extends BasicMongoDao<Purchaser> {
    @Override
    public Class<Purchaser> getReturnClass() {
        return Purchaser.class;
    }

    public Purchaser findByName(String name){
        Criteria criteria = Criteria.where(Purchaser.FIELD_NAME).is(name);
//                .and(Purchaser.FIELD_DELETED).ne(true);
        return mongoOperations.findOne(Query.query(criteria), Purchaser.class);
    }

}
