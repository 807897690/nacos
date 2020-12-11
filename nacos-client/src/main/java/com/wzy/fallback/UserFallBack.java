package com.wzy.fallback;

import com.wzy.client.UserClient;
import org.springframework.stereotype.Component;

/**
 * @ClassName UserFallBack
 * Description  user服务的降级处理
 * @Author Administrator
 * @Date 2020/12/10 20:46
 * @Version 1.0
 */
@Component
public class UserFallBack implements UserClient {

    @Override
    public String getUser(String name) {
        return "降级方法";
    }
}
