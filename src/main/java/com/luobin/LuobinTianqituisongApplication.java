package com.luobin;

import com.luobin.controller.Pusher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@SpringBootApplication
@EnableScheduling // 开启定时任务
public class LuobinTianqituisongApplication {

    public static void main(String[] args) {
        SpringApplication.run(LuobinTianqituisongApplication.class, args);
        System.out.println("------------------------------- 启动成功 -------------------------------");
        try {
            Pusher.push();
            log.info("启动后第一次测试发送成功！");
        } catch (Exception e) {
            log.error("启动后第一次发送失败！！！");
            e.printStackTrace();
        }
    }

    // 定时 早7点推送  0秒 0分 7时
    @Scheduled(cron = "0 0 7 * * ?")
    public void goodMorning(){
        try {
            Pusher.push();
            log.info("定时发送成功");
        } catch (Exception e) {
            log.error("定时发送失败");
            e.printStackTrace();
        }
    }
}
