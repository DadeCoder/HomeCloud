package com.dade.core.house;

import com.dade.core.basic.BasicMongoDao;
import org.springframework.stereotype.Component;

/**
 * Created by Dade on 2017/3/20.
 */
@Component
public class HouseDao extends BasicMongoDao<House> {
    @Override
    public Class<House> getReturnClass() {
        return House.class;
    }
}
