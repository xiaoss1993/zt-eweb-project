
package com.zt.eweb.framework.mybatis.core.holder.api;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * 接口参数
 *
 * @author JerryMa
 * @version v2.2.1
 * @date 2021/4/23 Copyright © diboot.com
 */
@Getter
@Setter
public class Param implements Serializable {

  private static final long serialVersionUID = 5243604861569425968L;

  String type;

  String name;

}
