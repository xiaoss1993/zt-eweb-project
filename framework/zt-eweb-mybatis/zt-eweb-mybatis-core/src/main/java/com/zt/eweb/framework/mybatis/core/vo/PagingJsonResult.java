
package com.zt.eweb.framework.mybatis.core.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zt.eweb.framework.mybatis.core.config.Cons;
import com.zt.eweb.framework.mybatis.core.util.S;
import com.zt.eweb.framework.mybatis.core.util.V;
import java.util.ArrayList;
import java.util.List;

/**
 * JSON返回结果
 *
 * @author
 * @version v2.0
 * @date 2019/01/01
 */
public class PagingJsonResult<T> extends JsonResult<T> {

  private static final long serialVersionUID = 1002L;

  /***
   * 分页相关信息
   */
  private Pagination page;

  public PagingJsonResult() {
  }

  public PagingJsonResult(T data) {
    this.data(data);
  }

  /**
   * 默认成功，无返回数据
   */
  public PagingJsonResult(JsonResult<T> jsonResult, Pagination pagination) {
    super(jsonResult.getCode(), jsonResult.getMsg(), jsonResult.getData());
    this.page = pagination;
  }

  /**
   * 基于IPage<T>转换为PagingJsonResult
   *
   * @param iPage
   * @param
   */
  public PagingJsonResult(IPage<?> iPage) {
    Pagination pagination = new Pagination();
    pagination.setPageIndex((int) iPage.getCurrent());
    pagination.setPageSize((int) iPage.getSize());
    pagination.setTotalCount(iPage.getTotal());
    if (V.notEmpty(iPage.orders())) {
      List<String> orderByList = new ArrayList<>();
      iPage.orders().stream().forEach(o -> {
        if (o.isAsc()) {
          orderByList.add(o.getColumn());
        } else {
          orderByList.add(o.getColumn() + ":" + Cons.ORDER_DESC);
        }
      });
      pagination.setOrderBy(S.join(orderByList));
    }
    this.page = pagination;
    T data = (T) iPage.getRecords();
    this.data(data);
  }

  public Pagination getPage() {
    return page;
  }

  public PagingJsonResult setPage(Pagination pagination) {
    this.page = pagination;
    return this;
  }

}