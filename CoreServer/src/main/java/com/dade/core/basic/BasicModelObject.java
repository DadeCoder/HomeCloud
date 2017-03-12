package com.dade.core.basic;

import org.apache.log4j.Logger;

/**
 * Created by Dade on 2016/12/24.
 */
public abstract class BasicModelObject implements MongoObject {

    public abstract String getId();

    protected final static Logger log = Logger.getLogger(BasicModelObject.class.getName());

    public static final String FIELD_DELETED = "deleted";

}
