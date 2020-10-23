package org.sakuradon.springrpcdemo;

import org.junit.jupiter.api.Test;
import org.sakuradon.springrpcdemo.client.DemoServiceClient;
import org.sakuradon.springrpcdemo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringRpcDemoApplicationTests {

    @Autowired
    private DemoServiceClient demoServiceClient;

    @Test
    void contextLoads() {

        String res = demoServiceClient.testHttpRpc("test");

        System.out.println(res);
    }

}
