package com.dade.picture;

import com.dade.common.utils.ImageUtil;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by Dade on 2017/3/28.
 */
@Component
public class PurchaserServices {

    @Autowired
    RestTemplate restTemplate;

    /**
     * user change their headimage
     * @param data
     * @param file
     * @return
     */
    public String imageHead(String data, File file, String phone){

        JSONObject joData = new JSONObject(data);
        // 用户经过剪辑后的图片的大小
        double x = joData.getDouble("x");
        double y = joData.getDouble("y");
        double w =  joData.getDouble("width");
        double h =  joData.getDouble("height");

        String imageHeadUrl = null;
        String path = "";

        try {
            DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String name = df.format(new Date());

            Random random = new Random();
            for(int i = 0 ;i<3 ;i++){
                name += random.nextInt(10);
            }

            // 文件后缀名称
            String fileName = file.getName();
            String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
            String url = "E:/ImageServer/resources/";

            path = name + "." + ext;
            File pic = new File(url, path);
            if(!pic.exists()){
                pic.mkdirs();
                InputStream is = new FileInputStream(file);
                ImageUtil.cut(is, pic, (int)x, (int)y, (int)w, (int)h);
                is.close();
            }

            imageHeadUrl = "http://127.0.0.1:8089/" + path;


        } catch (Exception e) {
            e.printStackTrace();
        }

        return path;
    }


}
