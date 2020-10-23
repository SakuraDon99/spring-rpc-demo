package org.sakuradon.springrpcdemo.client;

import org.sakuradon.springrpcdemo.rpc.client.HttpRpcFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author SakuraDon
 */
@Component
public class RpcClientConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public DemoServiceClient demoServiceClient(HttpRpcFactory httpRpcFactory) {
        return httpRpcFactory.refer(DemoServiceClient.class);
    }

}
