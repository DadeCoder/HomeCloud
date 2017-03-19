package com.dade.core.user.purchaser;

import com.dade.common.utils.ImageUtil;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by Dade on 2017/3/19.
 */
@Component
public class PurchaserService {

    @Autowired
    PurchaserDao purchaserDao;

    public String getInfo(String phone){
        Purchaser purchaser = purchaserDao.getByPhoneNumber(phone);
        String info = purchaser.getName();
        return info;
    }

    public String getImage(String phone){
        Purchaser purchaser = purchaserDao.getByPhoneNumber(phone);
        String info = purchaser.getImageHeaderUrl();
        return info;
    }


    /**
     * user change their headimage
     * TODO how to bind user id
     * @param src
     * @param data
     * @param file
     * @return
     */
    public String imageHead(String src, String data, MultipartFile file, String phone){

        JSONObject joData = new JSONObject(data);
        // 用户经过剪辑后的图片的大小
        double x = joData.getDouble("x");
        double y = joData.getDouble("y");
        double w =  joData.getDouble("width");
        double h =  joData.getDouble("height");

        String imageHeadUrl = null;

        try {
            DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String name = df.format(new Date());

            Random random = new Random();
            for(int i = 0 ;i<3 ;i++){
                name += random.nextInt(10);
            }

            // 文件后缀名称
            String ext = FilenameUtils.getExtension(file.getOriginalFilename());
//            String url = "E:/foreground/homeplus/angular-seed/app/upload/";
            String url = "E:/ImageServer/resources/";
//            String url = "http://127.0.0.1/";

            String path = name + "." + ext;
            File pic = new File(url, path);
            if(!pic.exists()){
                pic.mkdirs();
                InputStream is = file.getInputStream();
                ImageUtil.cut(is, pic, (int)x, (int)y, (int)w, (int)h);
                is.close();
            }
//            imageHeadUrl = ".\\upload\\" + path;

//            imageHeadUrl = path;
            imageHeadUrl = "http://127.0.0.1:8089/" + path;

            purchaserDao.saveImage(path, phone);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return imageHeadUrl;
    }


}
