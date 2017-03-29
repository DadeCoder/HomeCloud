package com.dade.picture;

import org.springframework.stereotype.Component;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by Dade on 2017/3/29.
 */
@Component
public class HouseServices {

    String upload(File file){
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String name = df.format(new Date());

        Random random = new Random();
        for(int i = 0 ;i<3 ;i++){
            name += random.nextInt(10);
        }

        // 文件后缀名称
        String oldName = file.getName();
        String ext = oldName.substring(oldName.lastIndexOf(".") + 1);
        String fileName = name + "." + ext;

        OutputStream os = null;
        InputStream fis = null;
        try {

            fis = new FileInputStream(file);

            String path = "E:/ImageServer/resources/";
            // 2、保存到临时文件
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流保存到本地文件

            File tempFile = new File(path);
            if (!tempFile.exists()) {
                tempFile.mkdirs();
            }
            os = new FileOutputStream(tempFile.getPath() + File.separator + fileName);
            // 开始读取
            while ((len = fis.read(bs)) != -1) {
                os.write(bs, 0, len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 完毕，关闭所有链接
            try {
                os.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return fileName;

    }

}
