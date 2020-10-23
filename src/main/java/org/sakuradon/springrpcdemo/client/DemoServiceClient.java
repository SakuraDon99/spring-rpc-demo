package org.sakuradon.springrpcdemo.client;

import org.sakuradon.springrpcdemo.rpc.client.HttpRpcMethod;
import org.sakuradon.springrpcdemo.rpc.client.HttpRpcService;

/**
 * @author SakuraDon
 */
@HttpRpcService("demoService")
public interface DemoServiceClient {

    @HttpRpcMethod
    String testHttpRpc(String msg);

}
