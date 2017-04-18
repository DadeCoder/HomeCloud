package com.dade.core.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * Created by Dade on 2017/4/18.
 */
@RestController
@RequestMapping("/api/general/message")
@CrossOrigin
public class MessageController {

    @Autowired
    MessageServices messageServices;

    @RequestMapping(value = "/putMes", method = RequestMethod.POST)
    void putMessage(@RequestBody MessageDto dto){
        messageServices.putMessage(dto.getMessage(), dto.getName(), dto.getPhone());
    }

    @RequestMapping("/getMes")
    List<Message> getAllMessage(){
        return messageServices.getAllMessage();
    }

    @RequestMapping("/deleteMes")
    List<Message> deleteMes(@RequestParam String id){
        return messageServices.deleteMes(id);
    }

}
