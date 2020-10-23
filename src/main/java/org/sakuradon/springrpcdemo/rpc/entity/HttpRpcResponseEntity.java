package org.sakuradon.springrpcdemo.rpc.entity;

/**
 * @author SakuraDon
 */
public class HttpRpcResponseEntity {

    int code;

    Object response;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

}
