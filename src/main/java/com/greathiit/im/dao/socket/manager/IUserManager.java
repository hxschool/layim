package com.greathiit.im.dao.socket.manager;

import javax.websocket.Session;

import com.greathiit.im.dao.pojo.SocketUser;

/**
 * Created by pz on 16/11/23.
 */
public interface IUserManager {

    boolean addUser(SocketUser user);

    boolean removeUser(SocketUser user);

    int getOnlineCount();

    SocketUser getUser(String userId);

}
