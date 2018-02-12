package com.greathiit.im.dao.pojo;

import javax.websocket.Session;

/**
 * Created by pz on 16/11/23.
 */
public class SocketUser {
    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public String getUserId() {
        return userId;
    }

    public boolean isExist(){
    	if(this.userId!=null&&!this.userId.equals("")) {
    		return true;
    	}
        return false;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private Session session;
    private String userId;

    @Override
    public String toString() {
        return session.getId()+"_"+userId;
    }
}
