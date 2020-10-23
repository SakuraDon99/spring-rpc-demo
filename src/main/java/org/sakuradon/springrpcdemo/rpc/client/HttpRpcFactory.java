package org.sakuradon.springrpcdemo.rpc.client;

import org.sakuradon.springrpcdemo.rpc.exception.HttpRpcProxyException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Proxy;

/**
 * @author SakuraDon
 */
@Component
public class HttpRpcFactory implements ApplicationContextAware {

    ApplicationContext applicationContext;

    private RestTemplate restTemplate;

    @Value("${httpRpc.server.rpcUrl}")
    private String httpRpcServerBaseUrl;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @SuppressWarnings("unchecked")
    public <T> T refer(Class<T> stubClass) {
        HttpRpcService httpRpcService = stubClass.getAnnotation(HttpRpcService.class);
        if (httpRpcService == null) {
            throw new HttpRpcProxyException("proxy service is not a http rpc service");
        }
        HttpRpcProxy p = new HttpRpcProxy(restTemplate, httpRpcServerBaseUrl, httpRpcService.value());
        return (T) Proxy.newProxyInstance(stubClass.getClassLoader(), new Class[]{stubClass}, p);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
