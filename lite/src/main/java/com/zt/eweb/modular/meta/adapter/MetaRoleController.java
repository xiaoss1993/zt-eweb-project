package com.zt.eweb.modular.meta.adapter;

import com.alibaba.fastjson.JSONObject;
import com.zt.eweb.modular.rbac.dal.mapper.RbacRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 模块名 :
 * 文件名 :
 * 创建时间 : 2025/3/18 09:25
 * 实现功能 :
 * <p>
 * 作者 : xiaoss
 * 版本 : V1.0.0-SNAPSHOT
 *
 * @see
 * @since ----------------------------------------------------------------
 * 修改记录
 * 日 期     	版本     修改人  修改内容
 * 2025/3/18      V1.0.0  xiaoss   创建
 * ----------------------------------------------------------------
 */
@RestController
@RequestMapping("/rbac/role")
public class MetaRoleController {

    @Autowired
    private RbacRoleMapper  roleMapper;

    @GetMapping("/list")
    public JSONObject   list(){
      return null;
    }
}
