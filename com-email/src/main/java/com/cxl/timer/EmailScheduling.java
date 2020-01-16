package com.cxl.timer;

import com.cxl.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@EnableScheduling
public class EmailScheduling {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailScheduling.class);

    @Autowired
    private EmailService emailService;

    //定时
    @Value(value = "${sendEmailTime1}")
    private boolean send1;

    @Value(value = "${sendEmailTime2}")
    private boolean send2;

    //邮箱地址
    @Value(value = "${sendEmail.host}")
    private String email;


    /**
     * 定时1
     */
    @Scheduled(cron = "${interval.sendEmail}")
    public void sendEmail1() {
        times();
        if (send1) {
            send(email);
        }else{
            LOGGER.error("此时间不发邮件");
        }
    }
 /**
     * 定时2
     */
    @Scheduled(cron = "${interval2.sendEmail}")
    public void sendEmail2() {
        times();
        if (send2) {
            send(email);
        }else{
            LOGGER.error("此时间不发邮件");
        }
    }

    /**
     * 发送邮件
     *
     * @param email
     */
    private void send(String email) {
        String emailByTime = emailService.getHtmlEmailByTime(null);

        String[] splitEmail = email.split(",");

        for (String itemEmail : splitEmail) {
            emailService.sendHtmlMail(itemEmail, "定时任务", emailByTime);

        }
    }

    private void times() {
        SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String format1 = format.format(date);
        LOGGER.info(format1 + "发送邮件,邮件地址:{}", email);
    }

    public static void main(String[] args) {
        EmailScheduling emailScheduling = new EmailScheduling();
        emailScheduling.sendEmail1();
    }
}
