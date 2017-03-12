package com.dade.core.test;

import com.dade.common.utils.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Dade on 2017/3/12.
 */
@RestController
@RequestMapping("/api/test/init")
public class TestInitController {

    @Autowired
    HunterUserDao hunterUserDao;

    @RequestMapping("/hello")
    String hello(){
        return "hello";
    }

    @RequestMapping("/log")
    String log(){
        LogUtil.info("log!");
        return "log!";
    }

    @RequestMapping(value = "/addOne", method = RequestMethod.POST)
    HunterUser addOne(@RequestBody HunterUser hunterUser){
        return hunterUserDao.insert(hunterUser);
    }

    @RequestMapping("/find/{mobile}")
    HunterUser find(@PathVariable String mobile){
        return hunterUserDao.findByPhoneNumber(mobile);
    }

}
