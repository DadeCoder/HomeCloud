package com.dade.core.user.purchaser;

import com.dade.common.utils.LogUtil;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dade on 2017/3/12.
 */
@RestController
@RequestMapping("/api/purchaser")
@CrossOrigin
public class PurchaserController {

    @Autowired
    PurchaserService purchaserService;

    @RequestMapping("/getInfo")
    String getInfo(Principal principal){
        String info = purchaserService.getInfo(principal.getName());
        return info;
    }

    @RequestMapping("/getImage")
    String getImage(Principal principal){
        String info = purchaserService.getImage(principal.getName());
        LogUtil.info(principal.getName());
        LogUtil.info(info);
        return info;
    }

    /**
     * 用户更换头像
     * @param src
     * @param data
     * @param file
     * @return
     */
    @RequestMapping(value = "/image_head", method = RequestMethod.POST)
    Map imageHead(@RequestParam("avatar_src") String src,
                  @RequestParam("avatar_data") String data,
                  @RequestParam("avatar_file") MultipartFile file,
                  Principal principal){

        //判断文件的MIMEtype
        String type = file.getContentType();

        LogUtil.info(principal.getName());

        String imageHeadUrl = purchaserService.imageHead(src, data, file, principal.getName());
        if(type==null || !type.toLowerCase().startsWith("image/") || imageHeadUrl == null)
            return  new HashMap<String, Object>(){
                {
                    put("state", 403);
                }
            };
        else
            return  new HashMap<String, Object>(){
                {
                    put("state", 200);
                    put("message","message");
                    put("result", imageHeadUrl);
                }
            };

    }

}
