package com.example.demo.configurations;

import com.example.demo.service.impl.QuartzService;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail rssJobDetail() {
        return JobBuilder.newJob(QuartzService.class)
                .withIdentity("rssJob")
                .storeDurably()  // Job sẽ không bị xóa khi hoàn thành, có thể tái sử dụng
                .build();
    }

    @Bean
    public Trigger rssJobTrigger() {
        // Lên lịch chạy job mỗi 24 giờ (tức là hàng ngày)
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInMinutes(5)  // Mỗi 24 giờ chạy 1 lần
                .repeatForever();  // Lặp vô hạn

        return TriggerBuilder.newTrigger()
                .forJob(rssJobDetail())  // Gắn với JobDetail đã tạo
                .withIdentity("rssTrigger")
                .withSchedule(scheduleBuilder)
                .build();
    }
}
