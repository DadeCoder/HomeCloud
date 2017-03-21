package com.dade.core.house;

import com.dade.core.house.dto.HouseDto;
import com.dade.core.house.dto.HouseDtoFactory;
import com.dade.core.house.dto.HouseRentOutInpDto;
import com.dade.core.house.dto.HouseRentOutResDto;
import com.dade.core.user.agent.Agent;
import com.dade.core.user.agent.AgentDao;
import com.dade.core.user.purchaser.Purchaser;
import com.dade.core.user.purchaser.PurchaserDao;
import com.dade.core.user.purchaser.PurchaserService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by Dade on 2017/3/20.
 */
@Component
public class HouseServices {

    @Autowired
    HouseDao houseDao;

    @Autowired
    PurchaserService purchaserService;

    @Autowired
    AgentDao agentDao;

    public HouseDto getById(String houseId){
        House house = houseDao.findById(houseId);
        HouseDto dto = HouseDtoFactory.getHouseDto(house);

        List<Agent> agentList = new ArrayList<>();

        for (int i=0;i<house.getAgentList().size();i++){
            Agent agent = agentDao.findById(house.getAgentList().get(i));
            agentList.add(agent);
        }

        dto.setRentWay(House.RENT_WAY_DEFAULT_ZH);

        dto.setAgentList(agentList);
        return dto;
    }

    public void savePicUrl(String houseId ,String url){
        houseDao.savePicUrl(houseId, url);
    }

    public HouseRentOutResDto rentOut(HouseRentOutInpDto dto, String phone){
        House inHouse = HouseDtoFactory.getHouse(dto);

        inHouse.setOwnerId(phone);
        inHouse.setOnlineType(House.ONLINE_RENT);

        House dbHouse = houseDao.atomicCreate(inHouse);
        purchaserService.updateHouseCreate(phone, dbHouse.getId());

        HouseRentOutResDto res = HouseDtoFactory.getHouseRentOut(dbHouse);

        res.setInfo(dbHouse.getId());

        return res;
    }

    public HouseRentOutResDto sellOut(HouseRentOutInpDto dto, String phone){
        House inHouse = HouseDtoFactory.getHouse(dto);

        inHouse.setOwnerId(phone);
        inHouse.setOnlineType(House.ONLINE_SELL);

        House dbHouse = houseDao.atomicCreate(inHouse);

        // 更新到purchaser
        purchaserService.updateHouseCreateSell(phone, dbHouse.getId());

        HouseRentOutResDto res = HouseDtoFactory.getHouseRentOut(dbHouse);

        res.setInfo(dbHouse.getId());

        return res;
    }

    public String savePic(MultipartFile file) {

        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String name = df.format(new Date());

        Random random = new Random();
        for(int i = 0 ;i<3 ;i++){
            name += random.nextInt(10);
        }
//        // 文件后缀名称
        String ext = FilenameUtils.getExtension(file.getOriginalFilename());
        String fileName = name + "." + ext;


        OutputStream os = null;
        InputStream fis = null;
        try {

            fis = file.getInputStream();

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
