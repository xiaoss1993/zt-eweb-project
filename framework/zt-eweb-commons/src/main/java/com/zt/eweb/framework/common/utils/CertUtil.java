package com.zt.eweb.framework.common.utils;

import cn.hutool.core.codec.Base64Encoder;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.security.PublicKey;
import javax.security.cert.X509Certificate;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

/**
 * 证书工具类
 *
 * 
 * @since 2022/2/24
 */
@UtilityClass
public class CertUtil {

    /**
     * 根据证书文本获取证书
     */
    @SneakyThrows
    public String getCertByContent(String certContent) {
        X509Certificate cert = X509Certificate
            .getInstance(new ByteArrayInputStream(certContent.getBytes(StandardCharsets.UTF_8)));
        PublicKey publicKey = cert.getPublicKey();
        return Base64Encoder.encode(publicKey.getEncoded());
    }

}
