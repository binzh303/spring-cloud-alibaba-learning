package website.abin.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableFeignClients // 开启feign
public class AlibabaClientFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlibabaClientFeignApplication.class, args);
    }

    @RestController
    class TestController{

        @Autowired
        Client client;

        @GetMapping("/test")
        String test(){

            return client.hello("hahaha");
        }
    }

    @FeignClient(name="nacos-discovery-server")
    interface Client{
        @GetMapping("/hello")
        String hello(@RequestParam String name);
    }

    @RestController
    class producer implements Client{

        @GetMapping("/hello")
        @Override
        public String hello(@RequestParam("name") String name) {
            return name;
        }
    }
}
