package com.dimples.runner;

import com.dimples.properties.DimplesProperties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhongyj <1126834403@qq.com><br/>
 * @date 2020/1/21
 */
@Slf4j
@Component
public class DimplesStartedUpRunner implements ApplicationRunner {

    public static final String DEV = "dev";
    public static final String WINDOWS = "windows";

    private ConfigurableApplicationContext context;
    private DimplesProperties properties;

    @Autowired
    public DimplesStartedUpRunner(ConfigurableApplicationContext context, DimplesProperties properties) {
        this.context = context;
        this.properties = properties;
    }

    @Value("${server.port:8080}")
    private String port;
    @Value("${server.servlet.context-path:}")
    private String contextPath;
    @Value("${spring.profiles.active}")
    private String active;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (context.isActive()) {
            InetAddress address = InetAddress.getLocalHost();
            String urlBase = String.format("http://%s:%s", address.getHostAddress(), port);
            String url = urlBase;
            if (StringUtils.isNotBlank(contextPath)) {
                url += contextPath;
            }
            log.info("   ,--.,--.                 ,--.");
            log.info(" ,-|  |`--',--,--,--. ,---. |  | ,---.  ,---.,-----. ,---.,--. ,--.,---.");
            log.info("' .-. |,--.|        || .-. ||  || .-. :(  .-''-----'(  .-' \\  '  /(  .-'");
            log.info("\\ `-' ||  ||  |  |  || '-' '|  |\\   --..-'  `)      .-'  `) \\   ' .-'  `)");
            log.info(" `---' `--'`--`--`--'|  |-' `--' `----'`----'       `----'.-'  /  `----'");
            log.info("                     `--'                                 `---'");
            log.info("DIMPLES 权限系统启动完毕，地址：{}", url);
            log.info("DIMPLES 文档地址：{}", urlBase + "/doc.html");

            boolean auto = properties.isAutoOpenBrowser();
            if (auto && StringUtils.equalsIgnoreCase(active, DEV)) {
                String os = System.getProperty("os.name");
                // 默认为 windows时才自动打开页面
                if (StringUtils.containsIgnoreCase(os, WINDOWS)) {
                    //使用默认浏览器打开系统登录页
                    Runtime.getRuntime().exec("cmd  /c  start " + url);
                }
            }
        }
    }
}

















