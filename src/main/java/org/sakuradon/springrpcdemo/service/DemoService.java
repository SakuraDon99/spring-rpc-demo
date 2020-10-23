package org.sakuradon.springrpcdemo.service;

/**
 * @author SakuraDon
 */
public interface DemoService {

    /**
     * 测试http rpc
     *
     * @param msg msg
     * @return string
     */
    String testHttpRpc(String msg);

}
