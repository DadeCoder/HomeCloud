package com.dade.core.mongo;

import com.dade.core.test.HunterUser;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Dade on 2017/3/12.
 */
public interface HunterUserRepository extends CrudRepository<HunterUser, String> {

    HunterUser findByPhoneNumber(String phoneNumber);

}
