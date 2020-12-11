package com.wzy.nacous_datasource;

import com.alibaba.csp.sentinel.datasource.WritableDataSource;
import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;

import java.util.Properties;

/**
 * @ClassName MyNacosWritableDataSource
 * Description  TODO
 * @Author Administrator
 * @Date 2020/12/11 20:32
 * @Version 1.0
 */
public class MyNacosWritableDataSource implements WritableDataSource {

    private String serverAddr;
    private String dataId;
    private String group;

    public MyNacosWritableDataSource(String serverAddr, String group, String dataId) {
        this.serverAddr = serverAddr;
        this.dataId = dataId;
        this.group = group;
    }

    @Override
    public void write(Object o) throws Exception {
        try {
            System.out.println("write:  ip:"+serverAddr+";dataId:"
                    +dataId+";group:"+group);
//            String serverAddr = "localhost:8848";
//            String dataId = "nacos-client";
//            String group = "DEFAULT_GROUP";
            Properties properties = new Properties();
            properties.put("serverAddr", serverAddr);
            String content = encodeJson(o);
            ConfigService configService = NacosFactory.createConfigService(properties);
            configService.publishConfig(dataId, group, content);
        } catch (NacosException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {

    }

    private <T> String encodeJson(T t) {
        return JSON.toJSONString(t);
    }
}
