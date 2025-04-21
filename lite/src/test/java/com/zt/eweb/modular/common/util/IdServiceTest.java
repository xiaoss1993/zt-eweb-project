package com.zt.eweb.modular.common.util;

import cn.hutool.core.util.HexUtil;
import com.baomidou.mybatisplus.core.toolkit.Sequence;

import java.net.InetAddress;

/**
 * 模块名 : 文件名 : 创建时间 : 2025/4/4 09:12 实现功能 :
 * <p>
 * 作者 : xiaoss 版本 : V1.0.0-SNAPSHOT
 *
 * @see
 * @since ---------------------------------------------------------------- 修改记录 日 期     	版本     修改人
 * 修改内容 2025/4/4      V1.0.0  xiaoss   创建
 * ----------------------------------------------------------------
 */
public class IdServiceTest {
  public static void main(String[] args) throws Exception {
    System.out.println("场景一：毫秒内固定起始值开始");
    testSequence();
    System.out.println("场景二：毫秒内随机起始值开始");
    testRandomSequence();
  }

  /**
   * 场景一：毫秒内固定起始值开始
   */
  private static void testSequence() throws Exception {
    Sequence sequence = new Sequence(InetAddress.getLocalHost());
    for (int i = 0; i < 100; i++) {
      Thread.sleep(1);
      System.out.println(sequence.nextId());
      System.out.println(HexUtil.toHex(sequence.nextId()));
    }
  }

  /**
   * 场景二：毫秒内随机起始值开始
   */
  private static void testRandomSequence() throws Exception {
    Sequence sequence = new Sequence(InetAddress.getLocalHost());
    for (int i = 0; i < 100; i++) {
      Thread.sleep(1);
      System.out.println(sequence.nextId());
      System.out.println(HexUtil.toHex(sequence.nextId()));

    }
  }

}
