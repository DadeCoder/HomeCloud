package com.dade.core.mongo;

import com.dade.core.test.HunterUser;
import com.dade.core.user.purchaser.Purchaser;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Dade on 2017/3/12.
 */
public interface PurchaserRepository extends CrudRepository<Purchaser, String> {

    Purchaser findByPhoneNumber(String phoneNumber);

}
