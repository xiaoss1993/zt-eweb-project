
package com.zt.eweb.framework.mybatis.core.data.access;

import java.lang.annotation.*;

/**
 * 数据权限检查点 - 添加在entity/dto字段上的注解，可以支持自动检查数据权限
 *
 * @author
 * @version v2.1
 * @date 2020/04/23
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface DataAccessCheckpoint {

}
