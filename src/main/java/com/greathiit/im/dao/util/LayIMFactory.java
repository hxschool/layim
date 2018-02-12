package com.greathiit.im.dao.util;

import com.greathiit.im.dao.socket.manager.IUserManager;
import com.greathiit.im.dao.socket.manager.UserManager;
import com.greathiit.im.dao.util.serializer.FastJsonSerializer;
import com.greathiit.im.dao.util.serializer.IJsonSerializer;

/**
 * Created by pz on 16/11/23.
 */
public class LayIMFactory {
    //创建序列化器
    public static IJsonSerializer createSerializer(){
        return new FastJsonSerializer();
    }

    //创建在线人员管理工具
    public static IUserManager createManager(){
        return UserManager.getInstance();
    }



}
