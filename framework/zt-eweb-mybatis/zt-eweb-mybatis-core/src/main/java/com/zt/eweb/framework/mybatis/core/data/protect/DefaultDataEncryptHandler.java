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
package com.zt.eweb.framework.mybatis.core.data.protect;

import com.zt.eweb.framework.mybatis.core.util.Encryptor;

/**
 * 加密，解密 处理器的默认实现类
 *
 * @author JerryMa
 * @version v3.1.1
 * @date 2023/10/10
 */
public class DefaultDataEncryptHandler implements DataEncryptHandler {

  protected String getSeed() {
    return Encryptor.getDefaultKey();
  }

  @Override
  public String encrypt(String fieldVal) {
    return Encryptor.encrypt(fieldVal, getSeed());
  }

  @Override
  public String decrypt(String encryptedStr) {
    return Encryptor.decrypt(encryptedStr, getSeed());
  }

}
