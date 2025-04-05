package com.zt.eweb.framework.common.base.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * Created by zhacker.
 * Time 2018/1/12 下午6:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page<T> {

  private Long total;

  private List<T> data;
}
