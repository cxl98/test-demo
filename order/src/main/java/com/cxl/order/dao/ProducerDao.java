package com.cxl.order.dao;

import com.cxl.order.entry.Producer;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProducerDao {
    @Insert("insert into producer(id,producerName,introduce,repertory) values(#{id},#{producerName},#{introduce},#{repertory})")
    int insertProducer(Producer producer);

    @Select("select producer.id,producer.repertory from producer")
    List<Producer> allRepertory();

    @Update("update producer set repertory=#{repertory} where id=#{id}")
    int updateRepertory(long id);

    @Select("select repertory from producer where id=#{id}")
    int getRepertoryById(long id);

}
