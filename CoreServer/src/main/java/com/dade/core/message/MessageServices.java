package com.dade.core.message;

import com.dade.common.utils.StringUtil;
import com.dade.core.user.purchaser.Purchaser;
import com.dade.core.user.purchaser.PurchaserService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by Dade on 2017/4/18.
 */
@Component
public class MessageServices {

    @Autowired
    MessageDao messageDao;

    @Autowired
    PurchaserService purchaserService;

    public void putMessage(String message, String name, String phone){
        if (StringUtil.isEmpty(message)  || StringUtil.isEmpty(name) || StringUtil.isEmpty(phone))
            return;

        Message mes = new Message();
        mes.setCreateDate(new Date());
        mes.setMessage(message);
        mes.setName(name);
        mes.setPhone(phone);

        messageDao.insert(mes);
    }

    public List<Message> getAllMessage(){
        return messageDao.getAllMessage();
    }

    public List<Message> deleteMes(String id){
        Message message = messageDao.getById(id);
        message.setDeleted(true);

        messageDao.save(message);

        return getAllMessage();
    }


}
