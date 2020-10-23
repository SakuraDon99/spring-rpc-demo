package org.sakuradon.springrpcdemo.rpc.client;

import java.lang.annotation.*;

/**
 * @author SakuraDon
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface HttpRpcMethod {

}
