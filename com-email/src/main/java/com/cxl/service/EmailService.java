package com.cxl.service;

import com.cxl.pojo.Email;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmailService {
    void sendSimpleMail(String toMail, String title, String context);

    void sendHtmlMail(String toMail, String title, String context);

    void sendhtmlPhotoMail(String to, String title, String context, List<Email> resourceList);

    void sendFileMail(String to, String title, String context, String filePath);

    /**
     * 查询指定分类10分钟的数据(不传,返回当天数据)
     * @param day
     * @return
     */
     String getHtmlEmailByTime(String day);

}
