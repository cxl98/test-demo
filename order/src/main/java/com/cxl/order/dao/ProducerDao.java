package com.cxl.order.dao;

import com.cxl.order.entry.Producer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducerDao {
    @Insert("insert into Producer(id,producerName,introduce) values(#{id},#{producerName},#{introduce})")
    int insertProducer(Producer producer);

    @Select("select producer.repertory from producer ")
    int allRepertory();

    @Update("update producer set repertory=#{repertory}")
    int updateRepertory(long repertory);

}
