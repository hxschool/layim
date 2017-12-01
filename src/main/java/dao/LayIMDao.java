package dao;


import dao.operate.LayIMGetMemberIdsOperate;
import dao.operate.LayIMResultSetOperate;
import dao.db.SQLHelper;
import pojo.message.ToDBMessage;
import pojo.result.JsonResult;
import pojo.result.JsonResultHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pz on 16/11/22.
 */
public class LayIMDao {

    SQLHelper sqlHelper = new SQLHelper();
    //获取基本信息列表
    public JsonResult getBaseList(int userId) {

        if (userId == 0){
            return JsonResultHelper.createFailedResult("invalid userid");
        }
        Map params = new HashMap();
        params.put(1, userId);
        Object object = sqlHelper.QueryResult("SELECT A.gid,A.uid,A.nickname,A.sign,A.headphoto,IFNULL(b.uid,10) as ownerid   FROM  v_group_detail A left join V_group B on A.gid=B.gid and A.uid=B.uid where A.uid=?", params, new LayIMResultSetOperate());
        return (JsonResult)object;
    }

    //根据groupid获取群员列表
    public JsonResult getMemberList(int groupId){
        if (groupId == 0){
            return JsonResultHelper.createFailedResult("invalid groupId");
        }
        Map params = new HashMap();
        params.put(1,groupId);
        String sql = "SELECT A.gid,A.uid,A.nickname,A.sign,A.headphoto,IFNULL(b.uid,10) as ownerid   FROM  v_group_detail A left join V_group B on A.gid=B.gid and A.uid=B.uid where A.gid=?";
        Object object = sqlHelper.QueryResult(sql,params,new LayIMResultSetOperate());
        return (JsonResult)object;
    }

    //根据群id获取所有群员的id
    public List<String> getMemberListOnlyIds(int groupId){
        Map params = new HashMap();
        params.put(1,groupId);

        String sql = "select uid as userid from v_layim_group_detail where gid =?";
        Object object = sqlHelper.QueryResult(sql,params,new LayIMGetMemberIdsOperate());

        return (List<String>) object;
    }

    //添加聊天记录
    public boolean addMsgRecord(ToDBMessage message){
        String sql = "INSERT INTO v_layim_msg_history(fromuser, gid, msg, chattype, create_date, msgtype, message_date) VALUES(?, ?, ?, ?, now(), ?, ?)";
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
