
package com.zt.eweb.framework.mybatis.core.data.protect;

import com.zt.eweb.framework.mybatis.core.util.S;

/**
 * 数据脱敏默认处理器
 *
 * @author wind
 * @version v2.5.0
 * @date 2022/03/25
 */
public class DefaultDataMaskHandler implements DataMaskHandler {

  @Override
  public String mask(String content) {
    if (S.isBlank(content)) {
      return S.EMPTY;
    }
    int length = content.length();
    switch (length) {
      case 11:
        // 11位手机号，保留前3位和后4位
        return S.replace(content, 3, length - 4, '*');
      case 18:
        // 18位身份证号，保留前6位和后4位
        return S.replace(content, 6, length - 4, '*');
      default:
        // 其他长度，保留前0位和后4位，长度小于5位不脱敏
        return S.replace(content, 0, length - 4, '*');
    }
  }

}
