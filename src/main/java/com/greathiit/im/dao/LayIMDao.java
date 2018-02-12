package com.greathiit.im.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.greathiit.im.dao.db.SQLHelper;
import com.greathiit.im.dao.operate.LayIMGetMemberIdsOperate;
import com.greathiit.im.dao.operate.LayIMResultSetOperate;
import com.greathiit.im.dao.pojo.User;
import com.greathiit.im.dao.pojo.message.ToDBMessage;
import com.greathiit.im.dao.pojo.result.JsonResult;
import com.greathiit.im.dao.pojo.result.JsonResultHelper;

/**
 * Created by pz on 16/11/22.
 */
public class LayIMDao {

    SQLHelper sqlHelper = new SQLHelper();
    //获取基本信息列表
    public JsonResult getBaseList(String userId) {

        if (userId==null||userId.equals("")||userId.equals("0")){
            return JsonResultHelper.createFailedResult("invalid userid");
        }
        Map params = new HashMap();
        params.put(1, userId);
        Object object = sqlHelper.QueryManyResult("SELECT A.id,A.username,A.sign,A.Avatar  FROM  chat_user A WHERE A.id=?", params, new LayIMResultSetOperate());
        return (JsonResult)object;
    }

    //根据groupid获取群员列表
    public JsonResult getMemberList(String groupId){
        if (groupId==null ||  groupId.equals("") || groupId.equals("0")){
            return JsonResultHelper.createFailedResult("invalid groupId");
        }
        Map params = new HashMap();
        params.put(1,groupId);
        //String sql = "SELECT A.gid,A.uid,A.nickname,A.sign,A.headphoto,IFNULL(b.uid,0) as ownerid   FROM  v_group_detail A left join V_group B on A.gid=B.gid and A.uid=B.uid where A.gid=?";
        String sql = "select a.id,a.username,a.sign,a.avatar,a.fgid from chat_user a left join chat_user_group b on a.id=b.uid where b.gid=?";
        
        Object object = sqlHelper.QueryResult(sql,params,new LayIMResultSetOperate());
        return (JsonResult)object;
    }

    //根据群id获取所有群员的id
    public List<String> getMemberListOnlyIds(String groupId){
        Map params = new HashMap();
        params.put(1,groupId);

        String sql = "select uid as userid from chat_user_group where gid =?";
        Object object = sqlHelper.QueryResult(sql,params,new LayIMGetMemberIdsOperate());

        return (List<String>) object;
    }

    //添加聊天记录
    public boolean addMsgRecord(ToDBMessage message){
        String sql = "INSERT INTO chat_msg_history(fromuser, gid, msg, chattype, create_date, msgtype, message_date) VALUES(?, ?, ?, ?, now(), ?, ?)";
        Map<Integer,Object> param = new HashMap<>();
        //加入参数
        param.put(1,message.getSendUserId());
        param.put(2,message.getGroupId());
        param.put(3,message.getMsg());
        param.put(4,message.getChatType());
        param.put(5,message.getMsgType());
        param.put(6,message.getAddtime());

        return sqlHelper.ExecuteNonquery(sql,param);
    }

}
