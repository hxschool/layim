package com.greathiit.im.dao.pojo.result;

import java.util.List;

import com.greathiit.im.dao.pojo.User;

/**
 * Created by pz on 16/11/28.
 */
public class GroupMemberResult {
    private User owner;
    private List<User> list;
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<User> getList() {
        return list;
    }

    public void setList(List<User> list) {
        this.list = list;
    }
}
