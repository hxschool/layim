package dao.operate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.db.IResultSetOperate;
import pojo.BigGroup;
import pojo.FriendGroup;
import pojo.JsonResultType;
import pojo.StatusUser;
import pojo.User;
import pojo.result.BaseDataResult;
import pojo.result.GroupMemberResult;
import pojo.result.JsonResult;
import pojo.result.JsonResultHelper;
import util.log.LayIMLog;

/**
 * Created by pz on 16/11/25.
 */
public class LayIMResultSetOperate implements IResultSetOperate {

    //返回MemberList
    public Object getObject(ResultSet resultSet) {
        GroupMemberResult groupMemberResult = new GroupMemberResult();
        List<User> users = new ArrayList<>();
        User owner = new User();


        try {
            String ownerid = "0";
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getString(1));
                user.setUsername(resultSet.getString(2));
                user.setSign(resultSet.getString(3));
                user.setAvatar(resultSet.getString(4));
                ownerid = resultSet.getString(5);
                //群主
                if(ownerid.equals(user.getId())){
                    owner = user;
                }
                users.add(user);
            }

            groupMemberResult.setList(users);
            groupMemberResult.setOwner(owner);

            return JsonResultHelper.createSuccessResult("",groupMemberResult);
        } catch (SQLException e) {
            e.printStackTrace();
            return JsonResultHelper.createFailedResult("程序异常");
        }

    }

    //获取基本信息列表的方法，比较繁琐，应该有更好的业务处理方式
    public Object getObject(Statement statement) {
    	PreparedStatement jtdsPreparedStatement = (PreparedStatement) statement;
        JsonResult jsonResult = new JsonResult();
        BaseDataResult result = new BaseDataResult();
        try {
            //friend
            List<FriendGroup> friendGroups = null;
            List<User> users = null;
            //group
            List<BigGroup> groups = null;
            //查询多结果集
            if (jtdsPreparedStatement.execute()) {
				ResultSet mineRs = jtdsPreparedStatement.getResultSet();
				StatusUser mine = getStatusUser(mineRs);
				if (mine.getId() == null || mine.getId().equals("") || mine.getId().equals("0")) {
					return JsonResultHelper.createFailedResult("用户不存在");
				}
				result.setMine(mine);
				String sql = "select  a.id,a.groupname from chat_group a left join chat_user_group b on a.id=b.gid left join chat_user c on b.uid=c.id where b.uid="+ mine.getId();
				Connection connection = statement.getConnection();

				ResultSet resultSet = connection.createStatement().executeQuery(sql);
				friendGroups = getFriendGroupList(resultSet);
				sql = "select a.fgid,a.id,a.username,a.sign,a.avatar from chat_user a where a.id not in (select uid from chat_friend where uid='"+ mine.getId() + "')";
				resultSet = connection.createStatement().executeQuery(sql);
				users = getFriendGroupDetailUser(resultSet);
				sql = "SELECT a.id,a.groupname,a.avatar FROM chat_group a left join chat_user_group b on a.id=b.gid where b.uid="+ mine.getId();
				resultSet = connection.createStatement().executeQuery(sql);
				groups = getGroups(resultSet);
				friendGroups = getFriendGroupList(friendGroups, users);
				result.setFriend(friendGroups);
				result.setGroup(groups);

				jsonResult.setCode(JsonResultType.typeSuccess);
				jsonResult.setData(result);
				return jsonResult;
            }
            jsonResult.setCode(JsonResultType.typeSuccess);
            return jsonResult;

        } catch (SQLException e) {

            e.printStackTrace();
            LayIMLog.error(e);

           return JsonResultHelper.createFailedResult("程序异常");
        }
    }

    //转换成mine
    private StatusUser getStatusUser(ResultSet rs) throws SQLException {
        StatusUser user = new StatusUser();
        while (rs.next()) {
            user.setId(rs.getString(1));
            user.setUsername(rs.getString(2));
            user.setSign(rs.getString(3));
            user.setAvatar(rs.getString(4));
        }
        return user;
    }

    //转换成组
    private List<FriendGroup> getFriendGroupList(ResultSet rsGroup) throws SQLException {

        List<FriendGroup> friendGroups = new ArrayList<>();
        while (rsGroup.next()) {
            FriendGroup friendGroup = new FriendGroup();

            String gid = rsGroup.getString(1);
            friendGroup.setId(gid);
            friendGroup.setGroupname(rsGroup.getString(2));

            friendGroups.add(friendGroup);
        }
        return friendGroups;
    }

    private List<User> getFriendGroupDetailUser(ResultSet rsDetail) throws SQLException {
        List<User> friends = new ArrayList<>();

        while (rsDetail.next()) {
            User u = new User();
            u.setFgid(rsDetail.getString(1));
            u.setId(rsDetail.getString(2));
            u.setUsername(rsDetail.getString(3));
            u.setSign(rsDetail.getString(4));
            u.setAvatar(rsDetail.getString(5));
            friends.add(u);
        }
        return friends;
    }
    //处理分组和好友间的关系
    private List<FriendGroup> getFriendGroupList(List<FriendGroup> friendGroup, List<User> friends) {
        List<FriendGroup> list = new ArrayList<>();

        for (FriendGroup fg : friendGroup){
            List<User> users = new ArrayList<>();
            for (User u : friends){
                if (!fg.getId().equals(u.getFgid())) {
                    users.add(u);
                }
            }

            fg.setList(users);

            list.add(fg);
        }
        return list;
    }

    private List<BigGroup> getGroups(ResultSet rsGroup) throws SQLException {
        List<BigGroup> bigGroups = new ArrayList<>();
        while (rsGroup.next()) {
            BigGroup group = new BigGroup();
            group.setId(rsGroup.getString(1));
            group.setGroupname(rsGroup.getString(2));
            group.setAvatar(rsGroup.getString(3));
            bigGroups.add(group);
        }
        return bigGroups;
    }
}
