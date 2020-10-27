package org.sakuradon.springrpcdemo.controller;

import org.sakuradon.springrpcdemo.rpc.entity.HttpRpcRequestEntity;
import org.sakuradon.springrpcdemo.rpc.entity.HttpRpcResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author SakuraDon
 */
@RestController
public class RpcController {

    ApplicationContext applicationContext;

    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @PostMapping("/rpc")
    public HttpRpcResponseEntity rpc(@RequestBody HttpRpcRequestEntity request) {
        HttpRpcResponseEntity response = new HttpRpcResponseEntity();
        String service = request.getService();
        String methodName = request.getMethod();
        if (!applicationContext.containsBean(service)) {
            response.setCode(1);
            return response;
        }
        Object proxyObject = applicationContext.getBean(service);

        Method method = getMethod(proxyObject.getClass(), methodName, request.getArgs());
        if (method == null) {
            response.setCode(2);
            return response;
        }
        try {
            Object res = method.invoke(proxyObject, request.getArgs());
            response.setCode(0);
            response.setResponse(res);
            return response;
        } catch (IllegalAccessException | InvocationTargetException e) {
            response.setCode(3);
            return response;
        }
    }

    private Method getMethod(Class<?> proxyObject, String methodStr, Object[] args) {
        Method[] methods = proxyObject.getMethods();
        int argsLength = args == null ? 0 : args.length;

        for(Method method : methods) {
            if(method.getName().equalsIgnoreCase(methodStr) && method.getParameterCount() == argsLength) {
                return method;
            }
        }

        return null;
    }

}
