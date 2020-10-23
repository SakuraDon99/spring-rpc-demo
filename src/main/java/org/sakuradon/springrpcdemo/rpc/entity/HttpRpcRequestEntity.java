package org.sakuradon.springrpcdemo.rpc.entity;

/**
 * @author SakuraDon
 */
public class HttpRpcRequestEntity {

    private String service;

    private String method;

    private Object[] args;

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}
