package org.sakuradon.springrpcdemo.rpc.client;

import java.lang.annotation.*;

/**
 * @author SakuraDon
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface HttpRpcService {
    String value();
}
