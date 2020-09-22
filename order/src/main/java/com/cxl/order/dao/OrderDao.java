package com.cxl.order.dao;

import com.cxl.order.entry.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderDao {
    @Insert("insert into Order(id,orderSn,createTime,username,totalAmount,payAmount,status) values(#{id},#{orderSn},#{createTime},#{username},#{totalAmount},#{status})")
    public int insetOrder(Order order);

    @Select("select * from Order where username=#{username}")
    Order selectByUsername(String username);
}
