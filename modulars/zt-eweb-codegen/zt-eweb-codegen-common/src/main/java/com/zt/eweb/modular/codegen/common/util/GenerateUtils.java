package com.zt.eweb.modular.codegen.common.util;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.StrUtil;
import org.apache.commons.lang.WordUtils;

import java.io.File;
import java.util.Map;

/**
 * 模块名 :
 * 文件名 :
 * 创建时间 : 2025/4/7 11:05
 * 实现功能 :
 * <p>
 * 作者 : xiaoss
 * 版本 : V1.0.0-SNAPSHOT
 *
 * @see
 * @since ----------------------------------------------------------------
 * 修改记录
 * 日 期     	版本     修改人  修改内容
 * 2025/4/7      V1.0.0  xiaoss   创建
 * ----------------------------------------------------------------
 */
public class GenerateUtils {
    /**
     * 路径拼接
     * @param parentPath 父级路径
     * @param subPath 子路径
     * @return 完整路径
     */
    public static String concatFilePath(String parentPath, String subPath) {
        if (CharSequenceUtil.isEmpty(parentPath)) {
            return subPath;
        }
        return parentPath.endsWith(File.separator) ? parentPath + subPath : parentPath + File.separator + subPath;
    }

    /**
     * 获取真实的文件全路径
     * @param filePathMaker 文件路径模板
     * @param map 模板属性
     * @return filePath 文件路径
     */
    public static String evaluateRealPath(String filePathMaker, Map<String, Object> map) {
        // 占位符替换
        String realFilePath = StrUtil.format(filePathMaker, map);
        if (CharSequenceUtil.isEmpty(realFilePath)) {
            return realFilePath;
        }
        // 用 . 标识文件夹合并，所以需要替换成 /
        realFilePath = realFilePath.replace(StrPool.DOT, File.separator);
        // 防止多写了 /
        realFilePath = realFilePath.replace(File.separator + File.separator, File.separator);

        return realFilePath;
    }

    /**
     * 根据类名生成表别名
     * @param className 类名
     * @return 表别名
     */
    public static String prodAlias(String className) {
        StringBuilder sb = new StringBuilder();
        for (char c : className.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                sb.append(Character.toLowerCase(c));
            }
        }
        return sb.toString();
    }

    /**
     * 列名转换成Java属性名
     */
    public static String underlineToCamel(String underlineStr) {
        return WordUtils.capitalizeFully(underlineStr, new char[] { '_' }).replace("_", "");
    }

}
