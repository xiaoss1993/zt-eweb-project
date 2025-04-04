package com.zt.eweb.modular.rbac.service;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.beanutils.BasicDynaClass;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.PropertyUtils;

/**
 * 模块名 : 文件名 : 创建时间 : 2025/4/1 16:27 实现功能 :
 * <p>
 * 作者 : xiaoss 版本 : V1.0.0-SNAPSHOT
 *
 * @see
 * @since ---------------------------------------------------------------- 修改记录 日 期     	版本     修改人
 * 修改内容 2025/4/1      V1.0.0  xiaoss   创建
 * ----------------------------------------------------------------
 */
public class DynamicBeanTest {

  public static void main(String[] args)
      throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {

    //定义动态属性
    DynaProperty[] props = new DynaProperty[]{
        new DynaProperty("username", String.class),
        new DynaProperty("address", java.util.Map.class)
    };

    //动态类
    BasicDynaClass dynaClass = new BasicDynaClass("person", null, props);
    //动态bean
    DynaBean person = dynaClass.newInstance();
    person.set("username", "jhlishero");//设置值
    Map<String, String> maps = new HashMap<String, String>();
    maps.put("key1", "value1");
    maps.put("key2", "value2");
    person.set("address", maps);//设置值
    person.set("address", "key3", "value3");//第二种方法设置map中的值

    System.err.println(person.get("username"));//获取字符串值
    System.err.println(person.get("address", "key1"));//获取map中值
    System.err.println(person.get("address", "key2"));
    System.err.println(person.get("address", "key3"));
    //使用PropertyUtils工具获取属性值
    System.out.println(PropertyUtils.getSimpleProperty(person, "username"));

  }
}
