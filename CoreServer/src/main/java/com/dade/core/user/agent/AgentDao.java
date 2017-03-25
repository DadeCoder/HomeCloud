package com.dade.core.user.agent;

import com.dade.core.basic.BasicMongoDao;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Dade on 2017/3/21.
 */
@Component
public class AgentDao extends BasicMongoDao<Agent> {
    @Override
    public Class<Agent> getReturnClass() {
        return Agent.class;
    }

    public List<Agent> getAllAgent(){
        List<Agent> agentList = mongoOperations.findAll(Agent.class);

        return agentList;
    }

    public Agent getByPhone(String phone){

        Criteria criteria = Criteria.where(Agent.FIELE_PHONE).is(phone)
                .and(Agent.FIELD_DELETED).ne(true);

        return mongoOperations.findOne(Query.query(criteria), Agent.class);

    }


}
