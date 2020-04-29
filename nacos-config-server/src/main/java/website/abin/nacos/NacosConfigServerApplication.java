package website.abin.nacos;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class NacosConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosConfigServerApplication.class, args);
    }
    @RestController
    @RefreshScope
    class TestController {

        @Value("${config.info}")
        private String config;

        @Value("${log.level}")
        private String log;

        @GetMapping("/test")
        public String hello() {
            return "log.lelve="+log;
        }

    }
}
