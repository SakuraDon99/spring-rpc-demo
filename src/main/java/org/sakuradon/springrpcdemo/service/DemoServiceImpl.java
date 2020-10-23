package org.sakuradon.springrpcdemo.service;


import org.springframework.stereotype.Service;

/**
 * @author SakuraDon
 */
@Service("demoService")
public class DemoServiceImpl implements DemoService {

    @Override
    public String testHttpRpc(String msg) {
        return "hello ! " + msg;
    }

}
