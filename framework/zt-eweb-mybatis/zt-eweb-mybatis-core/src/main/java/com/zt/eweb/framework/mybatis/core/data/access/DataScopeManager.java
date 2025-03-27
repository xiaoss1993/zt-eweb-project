
package com.zt.eweb.framework.mybatis.core.data.access;

import java.io.Serializable;
import java.util.List;

/**
 * 数据权限校验扩展接口
 *
 * @author
 * @version v2.1
 * @date 2020/04/24
 */
public interface DataScopeManager {

  /**
   * <h3>可访问的对象ID</h3>
   * <br/>
   * <table border="10">
   * <caption>添加条件规则</caption>
   * <tr>
   * <th>返回值</th>
   * <th>SQL</th>
   * <th>说明</th>
   * </tr>
   * <tr>
   * <td>null</td>
   * <td>-</td>
   * <td>为null不加入条件</td>
   * </tr>
   * <td>[]</td>
   * <td>IS NULL</td>
   * <td></td>
   * </tr>
   * <tr>
   * <td>[10001]</td>
   * <td>= '10001'</td>
   * <td>长度等于1的列表</td>
   * </tr>
   * <tr>
   * <td>[10001, 10002] &nbsp</td>
   * <td>IN ('10001', '10002') &nbsp</td>
   * <td>长度大于1的列表</td>
   * </tr>
   * </table>
   */
  default List<? extends Serializable> getAccessibleIds(Class<?> entityClass, String fieldName) {
    return getAccessibleIds(entityClass.getSimpleName(), fieldName);
  }

  List<? extends Serializable> getAccessibleIds(String entityClassName, String fieldName);

  /**
   * 显示标题
   *
   * @return
   */
  default String getTitle() {
    return this.getClass().getSimpleName();
  }
}
