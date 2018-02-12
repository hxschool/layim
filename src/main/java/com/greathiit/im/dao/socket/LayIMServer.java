package com.greathiit.im.dao.socket;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.greathiit.im.dao.pojo.SocketUser;
import com.greathiit.im.dao.pojo.message.ToServerTextMessage;
import com.greathiit.im.dao.socket.manager.OnLineUserManager;
import com.greathiit.im.dao.socket.sender.MessageSender;
import com.greathiit.im.dao.util.LayIMFactory;

/**
 * Created by pz on 16/11/23.
 */
@ServerEndpoint("/websocket/{uid}")
public class LayIMServer {

    @OnOpen
    public  void open(Session session, @PathParam("uid") String uid){
        SocketUser user = new SocketUser();
        user.setSession(session);
        user.setUserId(uid);

        //保存在线列表
        LayIMFactory.createManager().addUser(user);
        print("当前在线用户："+LayIMFactory.createManager().getOnlineCount());
        print("缓存中的用户个数："+new OnLineUserManager().getOnLineUsers().size());
    }

    @OnMessage
    public void receiveMessage(String message,Session session){
        //try {

            ToServerTextMessage toServerTextMessage =
                    LayIMFactory.createSerializer().toObject(message,ToServerTextMessage.class);

            //得到接收的对象
            MessageSender sender = new MessageSender();

            sender.sendMessage(toServerTextMessage);

        //}catch (Exception e){
          //  e.printStackTrace();
        //}
    }

    @OnError
    public void error(Throwable t) {
        print(t.getMessage());
    }

    @OnClose
    public void close(Session session){

        SocketUser user = new SocketUser();
        user.setSession(session);
        user.setUserId("");
        print("用户掉线");
        //移除该用户
        LayIMFactory.createManager().removeUser(user);
       //print("当前在线用户："+LayIMFactory.createManager().getOnlineCount());

    }

    private void print(String msg){
        System.out.println(msg);
    }
}
