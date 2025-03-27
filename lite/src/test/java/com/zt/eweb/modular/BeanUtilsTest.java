package com.zt.eweb.modular;

import com.sun.management.OperatingSystemMXBean;
import com.zt.eweb.ZTEwebLiteApplication;
import java.lang.management.ManagementFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * BeanUtils测试
 *
 * @author mazc@dibo.ltd
 * @version v2.0
 * @date 2019/06/02
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ZTEwebLiteApplication.class})
public class BeanUtilsTest {

  private static OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();


  /**
   * 测试注解拷贝
   */
  @Test
  public void testAcceptAnnoCopy() {

  }

}
