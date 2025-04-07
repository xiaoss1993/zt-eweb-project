package com.zt.eweb.modular.rbac.client.manager;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zt.eweb.modular.rbac.client.dto.RoleDTO;

import java.util.List;
import java.util.Map;

/**
 * 模块名 : 文件名 : 创建时间 : 2025/4/5 14:29 实现功能 :
 * <p>
 * 作者 : xiaoss 版本 : V1.0.0-SNAPSHOT
 *
 * @see
 * @since ---------------------------------------------------------------- 修改记录 日 期     	版本     修改人
 * 修改内容 2025/4/5      V1.0.0  xiaoss   创建
 * ----------------------------------------------------------------
 */
public interface RoleQueryService {

    Page queryPage(Map<String, Object> params);

    List<RoleDTO> listAll();

    RoleDTO getById(String id);
}
