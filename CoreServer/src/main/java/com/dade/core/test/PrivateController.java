package com.dade.core.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Dade on 2017/3/12.
 */
@RestController
public class PrivateController {

    @RequestMapping("/pri")
    String privateFun(){
        return "This is a private Function!";
    }
}
