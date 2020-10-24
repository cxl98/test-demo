package com.cxl.order.dao;

import com.cxl.order.entry.Log;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

@Repository
public interface LogDao {

    @Insert("insert into log(id,logOp,logType,userId,createTime,url) values(#{id},#{logOp},#{logType},#{userId},#{createTime},#{url})")
    public int insertLog(Log log);
}
