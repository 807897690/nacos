package com.wzy.nacous_datasource;

import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.WritableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.transport.util.WritableDataSourceRegistry;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @ClassName MyDataSourceInit
 * Description  TODO
 * @Author Administrator
 * @Date 2020/12/11 20:29
 * @Version 1.0
 */
@Component
public class MyDataSourceInit {

    @Value("${spring.cloud.nacos.discovery.server-addr}")
    private String serverAddr;
    @Value("${spring.application.name}")
    private String dataId;
    @Value("${spring.cloud.nacos.group}")
    private String group;

    @PostConstruct
    public void init(){
        System.out.println("ip:"+serverAddr+";dataId:"+dataId+";group:"+group);
        ReadableDataSource<String, List<FlowRule>> ds =
                new NacosDataSource<>(serverAddr, group, dataId,
                source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {})
        );
        // 将可读数据源注册至 FlowRuleManager.
        FlowRuleManager.register2Property(ds.getProperty());

        WritableDataSource<List<FlowRule>> wds = new MyNacosWritableDataSource(serverAddr, group, dataId);
        // 将可写数据源注册至 transport 模块的 WritableDataSourceRegistry 中.
        // 这样收到控制台推送的规则时，Sentinel 会先更新到内存，然后将规则写入到文件中.
        WritableDataSourceRegistry.registerFlowDataSource(wds);
    }
}
