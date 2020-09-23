package com.cxl.order.service;

import com.cxl.order.dao.ProducerDao;
import com.cxl.order.entry.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {
    @Autowired
    private ProducerDao producerDao;

    public int addProducer(Producer producer){
        if (null!=producer){
            return producerDao.insertProducer(producer);
        }
        return 0;
    }
}
