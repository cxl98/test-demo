package com.cxl.order.dao;

import com.cxl.order.entry.Producer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducerDao {
    @Insert("insert into Producer(id,producerName,introduce) values(#{id},#{producerName},#{introduce})")
    public int insertProducer(Producer producer);

}
