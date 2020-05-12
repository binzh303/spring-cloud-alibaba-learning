package website.abin.sentinel;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.context.ContextUtil;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@SpringBootApplication
public class SentinelApplication {

    public static void main(String[] args) {
        SpringApplication.run(SentinelApplication.class, args);
    }

}

@RestController
class TestController{

    @GetMapping("/test")
    public String test(){
        return "hello! sentinel!";
    }

    @GetMapping("/myTest")
    @SentinelResource("test3")
    public String test123(String name,String age){

        return  name + "----"+ age;
    }
    @Component
    class requestOrigin implements RequestOriginParser{

        @Override
        public String parseOrigin(HttpServletRequest httpServletRequest) {

            String server = httpServletRequest.getParameter("server");
            return server;
        }
    }
}