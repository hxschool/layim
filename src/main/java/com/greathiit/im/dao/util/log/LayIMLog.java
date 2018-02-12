package com.greathiit.im.dao.util.log;

import org.apache.log4j.Logger;

/**
 * Created by pz on 16/11/23.
 */
public  class LayIMLog {

    static Logger logger = Logger.getLogger(LayIMLog.class);

    public static void error(Object msg){
        logger.error(msg);

    }

    public static void info(Object msg){
        logger.info(msg);
    }


}
