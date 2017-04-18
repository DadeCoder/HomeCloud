package com.dade.core.message;

import com.dade.core.basic.BasicMongoDao;
import com.dade.core.user.purchaser.Purchaser;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Dade on 2017/4/18.
 */
@Component
public class MessageDao extends BasicMongoDao<Message> {
    @Override
    public Class<Message> getReturnClass() {
        return Message.class;
    }

    List<Message> getAllMessage(){
        Criteria criteria = Criteria.where(Message.FIELD_DELETED).ne(true);
        return mongoOperations.find(Query.query(criteria), Message.class);
    }

    Message getById(String id){
        Criteria criteria = Criteria.where(Message.FIELD_DELETED).ne(true)
                .and(Message.FIELD_ID).is(id);
        return mongoOperations.findOne(Query.query(criteria), Message.class);
    }

    void save(Message message){
        mongoOperations.save(message);
    }

}
