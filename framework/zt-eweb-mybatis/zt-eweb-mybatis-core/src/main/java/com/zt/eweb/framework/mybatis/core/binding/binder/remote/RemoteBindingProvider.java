
package com.zt.eweb.framework.mybatis.core.binding.binder.remote;

import com.zt.eweb.framework.mybatis.core.vo.JsonResult;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 远程绑定Provider接口
 *
 * @author JerryMa
 * @version v2.4.0
 * @date 2021/11/3 Copyright © diboot.com
 */
public interface RemoteBindingProvider {

  /**
   * 加载请求数据
   *
   * @param remoteBindDTO
   * @return
   */
  @PostMapping("/common/remote-binding")
  JsonResult<String> loadBindingData(RemoteBindDTO remoteBindDTO);

}
