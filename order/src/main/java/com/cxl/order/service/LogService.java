package com.cxl.order.service;

import com.cxl.order.dao.LogDao;
import com.cxl.order.entry.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogService {
    @Autowired
    private LogDao logDao;

    public int insertLog(Log log){
        return logDao.insertLog(log);
    }
}
