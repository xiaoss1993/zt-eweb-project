
package com.zt.eweb.framework.mybatis.core.holder.api;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

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
