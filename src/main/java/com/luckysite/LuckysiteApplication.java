package com.luckysite;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;

import javax.servlet.MultipartConfigElement;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Import(FdfsClientConfig.class)
// 解决jmx重复注册bean的问题
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
@SpringBootApplication(scanBasePackages = "com.luckysite")
@Configuration
public class LuckysiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(LuckysiteApplication.class, args);
    }


    //文件上传大小设置
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 单个数据大小
        factory.setMaxFileSize("10240KB"); // KB,MB
        // 总上传数据大小
        factory.setMaxRequestSize("102400KB");
        return factory.createMultipartConfig();
    }

    @Bean(name = "crawlExecutorPool")
    public ExecutorService crawlExecutorPool() {
        // 获取Java虚拟机的可用的处理器数，最佳线程个数，处理器数*2。根据实际情况调整
        int curSystemThreads = Runtime.getRuntime().availableProcessors() * 2;
        System.out.println("------------系统可用线程池个数：" + curSystemThreads);
        // 创建线程池
        ExecutorService pool = Executors.newFixedThreadPool(curSystemThreads);
        return pool;
    }


}
