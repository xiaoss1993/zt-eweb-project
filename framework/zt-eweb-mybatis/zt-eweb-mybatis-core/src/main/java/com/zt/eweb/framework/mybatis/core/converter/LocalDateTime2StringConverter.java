/*
 * Copyright (c) 2015-2029, www.dibo.ltd (service@dibo.ltd).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * https://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.zt.eweb.framework.mybatis.core.converter;

import com.zt.eweb.framework.mybatis.core.converter.annotation.CollectThisConvertor;
import com.zt.eweb.framework.mybatis.core.util.D;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;

/**
 * LocalDateTime - Date 转换器
 *
 * @author JerryMa
 * @version v2.6.0
 * @date 2022/5/11 Copyright © diboot.com
 */
@CollectThisConvertor
public class LocalDateTime2StringConverter implements Converter<LocalDateTime, String> {

  @Override
  public String convert(LocalDateTime source) {
    return source.format(D.FORMATTER_DATETIME_Y4MDHMS);
  }

}
