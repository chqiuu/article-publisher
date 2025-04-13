package com.chqiuu.publisher;

import com.chqiuu.publisher.common.util.BrowseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@EnableScheduling
@EnableTransactionManagement
@SpringBootApplication
public class ArticlePublisherApplication {

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext application = SpringApplication.run(ArticlePublisherApplication.class, args);
        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = null == env.getProperty("server.port") || "80".equals(env.getProperty("server.port")) ? "" : ":" + env.getProperty("server.port");
        String path = null == env.getProperty("server.servlet.context-path") ? "" : env.getProperty("server.servlet.context-path");
        String portPath = port + path;
        String delimiter = String.format("%100s", "").replaceAll("\\s", "=");
        log.info("\n{}\n【{}】项目已启动完成\n访问地址:\n\t" +
                        "Local: \t\thttp://localhost{}\n\t" +
                        "External: \thttp://{}{}\n\t" +
                        "Api: \t\thttp://localhost{}/swagger/doc.html\n{}"
                , delimiter
                , env.getProperty("spring.application.name"), portPath, ip, portPath, portPath
                , delimiter);
        BrowseUtil.openBrowseByUrl(String.format("http://localhost%s/static/page/index.html", portPath));
    }

}
