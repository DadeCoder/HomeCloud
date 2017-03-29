package com.dade.core.general;

import com.dade.core.user.purchaser.PurchaserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by Dade on 2017/3/28.
 */
@RestController
@RequestMapping("/api/general/pic")
public class PictureServerController {

    @Autowired
    PurchaserService purchaserService;

//    @RequestMapping("/whoami")
//    String whoami(Principal principal){
//        return principal.getName();
//    }
//
//    @RequestMapping("/test")
//    String test(){
//        return "security!";
//    }
//
//    @RequestMapping("/save_image")
//    void saveImage(@RequestParam String path, @RequestParam String phone){
//
//        LogUtil.info(path + path);
//
//        purchaserService.saveImage(path, phone);
//    }

}
