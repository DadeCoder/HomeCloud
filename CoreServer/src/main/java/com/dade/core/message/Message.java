package com.dade.core.message;

import com.dade.core.basic.BasicModelObject;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by Dade on 2017/4/18.
 */
@Document
public class Message extends BasicModelObject {

    String id;

    String Message;
    String name;
    String phone;

    Date createDate;

    Boolean deleted;

    public static final String FIELD_ID="id";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Message{" +
                "Message='" + Message + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
