package com.greathiit.im.dao.operate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.greathiit.im.dao.db.IResultSetOperate;
import com.greathiit.im.dao.util.log.LayIMLog;

/**
 * Created by pz on 16/11/28.
 */
public class LayIMGetMemberIdsOperate implements IResultSetOperate {

    public Object getObject(ResultSet resultSet) {
        List<String> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                list.add(resultSet.getString(1));
            }
        } catch (SQLException ex) {
            LayIMLog.error(ex);
        }
        return list;
    }

    public Object getObject(Statement statement) {
        return null;
    }
}
