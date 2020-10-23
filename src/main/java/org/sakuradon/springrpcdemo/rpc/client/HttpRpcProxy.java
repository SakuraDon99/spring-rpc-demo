package org.sakuradon.springrpcdemo.rpc.client;

import org.sakuradon.springrpcdemo.rpc.entity.HttpRpcRequestEntity;
import org.sakuradon.springrpcdemo.rpc.entity.HttpRpcResponseEntity;
import org.sakuradon.springrpcdemo.rpc.exception.HttpRpcExecException;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.URI;

/**
 * @author SakuraDon
 */
public class HttpRpcProxy implements InvocationHandler {

    private final String httpRpcServerBaseUrl;

    private final String service;

    private final RestTemplate restTemplate;

    public HttpRpcProxy(RestTemplate restTemplate,String httpRpcServerBaseUrl, String service) {
        this.httpRpcServerBaseUrl = httpRpcServerBaseUrl;
        this.restTemplate = restTemplate;
        this.service = service;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        HttpRpcRequestEntity req = new HttpRpcRequestEntity();
        req.setService(service);
        req.setMethod(method.getName());
        req.setArgs(args);
        HttpRpcResponseEntity res;
        try {
            res = restTemplate.postForObject(URI.create(httpRpcServerBaseUrl), req, HttpRpcResponseEntity.class);
        } catch (Exception e) {
            throw new HttpRpcExecException("http request failed");
        }
        if (res == null) {
            throw new HttpRpcExecException("http request failed");
        }
        if (res.getCode() == 1) {
            throw new HttpRpcExecException("service not found");
        }
        if (res.getCode() == 2) {
            throw new HttpRpcExecException("method not found");
        }
        if ( res.getCode() == 3) {
            throw new HttpRpcExecException("method exec exception");
        }
        return res.getResponse();
    }

}
