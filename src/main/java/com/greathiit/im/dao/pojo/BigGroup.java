package com.greathiit.im.dao.pojo;

import com.greathiit.im.dao.pojo.Group;

/**
 * Created by pz on 16/11/23.
 */
public class BigGroup extends Group {

    private String avatar;

    public  void setAvatar(String avatar){
        this.avatar = avatar;
    }

    public String getAvatar(){
        return this.avatar;
    }
}
