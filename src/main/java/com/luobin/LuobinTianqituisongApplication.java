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

    /**
     * 注意：不要频繁一直发程序，使用频率低一点，用的都是公共免费的接口，大家都一直刷的话免费接口就会越用越少。
     * 且用且珍惜~
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(LuobinTianqituisongApplication.class, args);
        try {
            Pusher.push();
            log.info("启动后第一次测试发送成功！");
        } catch (Exception e) {
            log.error("启动后第一次发送失败！！！");
            e.printStackTrace();
        }
        System.out.println("------------------------------- 启动成功 -------------------------------");
    }

    // 定时 早7点15分推送（0秒 15分 7时）
    @Scheduled(cron = "0 15 7 * * ?")
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
