package com.dade.picture;

import com.dade.common.dto.ImageHeadDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Dade on 2017/3/28.
 */
@RestController
@RequestMapping("/api/house")
@CrossOrigin
public class HouseController {

    @Autowired
    HouseServices houseServices;

    @RequestMapping("upload")
    String upload(@RequestBody ImageHeadDto dto){
        return houseServices.upload(dto.getFile());
    }

}
