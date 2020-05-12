package website.abin.sentinel.demo;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.sun.deploy.security.BlockedException;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author zhbin
 * @Description
 * @Date 2020-05-12 22:23
 */
public class MySentinelResource {

    @SentinelResource(value = "message",blockHandler = "blockHandler",fallback = "fallback")
    public String message(String str){
        if(StringUtils.isBlank(str)){
            throw new RuntimeException();
        }
        return str;
    }

    /**
     * 限流处理
     * @param str
     * @param ex
     * @return
     */
    public String blockHandler(String str, BlockedException ex){

        return str + "--"+ ex;
    }

    /**
     * 降级处理
     * @param str
     * @return
     */
    public String fallback(String str){
        return null;
    }
}
