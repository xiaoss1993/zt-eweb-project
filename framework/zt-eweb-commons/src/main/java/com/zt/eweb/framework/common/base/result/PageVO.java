package com.zt.eweb.framework.common.base.result;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * 查询参数
 */
@Data
public class PageVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 每页显示条数，默认 10
     */
    private long size = 10;
    /**
     * 当前页，默认1，第一页
     */
    private long current = 1;

    /**
     * 排序字段信息
     * 排序字段和排序类型
     * create_time desc,user_no asc.
     */
    private String orderBy;


    public Page toPage() {
        Page page = new Page();
        page.setCurrent(current);
        page.setSize(size);
        if (CharSequenceUtil.isNotBlank(orderBy)) {
            List<OrderItem> orders = new ArrayList<>();
            List<String> orderItems = CharSequenceUtil.split(orderBy, ",");
            for (String orderItemStr : orderItems) {
                List<String> orderAndSort = CharSequenceUtil.split(orderItemStr, " ");
                if (CollUtil.size(orderAndSort) != 2) {
                    continue;
                }
                String order = orderAndSort.get(0);
                String sort = orderAndSort.get(1);
                OrderItem orderItem = new OrderItem();
                orderItem.setAsc( CharSequenceUtil.equalsIgnoreCase("ASC", sort));
                orderItem.setColumn(order);
                orders.add(orderItem);
            }
            page.setOrders(orders);
        }
        return page;
    }
}
