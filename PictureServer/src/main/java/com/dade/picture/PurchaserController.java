package com.dade.picture;

import com.dade.common.dto.ImageHeadDto;
import com.dade.common.utils.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dade on 2017/3/28.
 */
@RestController
@RequestMapping("/api/purchaser")
@CrossOrigin
public class PurchaserController {

    @Autowired
    PurchaserServices purchaserServices;

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/test")
    String test(){
        String uri = "http://127.0.0.1:8090/api/general/pic/test/";
        return restTemplate.getForEntity(uri,String.class).getBody();
    }

    @RequestMapping("/image_head")
    String imageHead(@RequestBody ImageHeadDto dto){
//        LogUtil.info(dto.getData());
//        LogUtil.info(dto.getPhone());
//        File newFile = new File("E:/ImageServer/test/test3.jpg");
//        dto.getFile().renameTo(newFile);

        return purchaserServices.imageHead(dto.getData(), dto.getFile(), dto.getPhone());
    }




}
