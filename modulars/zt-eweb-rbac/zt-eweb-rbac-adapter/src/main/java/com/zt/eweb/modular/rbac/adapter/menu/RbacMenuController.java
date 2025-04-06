package com.zt.eweb.modular.rbac.adapter.menu;

import com.zt.eweb.framework.common.base.result.Response;
import com.zt.eweb.modular.rbac.client.dto.MenuVo;
import com.zt.eweb.modular.rbac.client.manager.RbacMenuQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 模块名 : 文件名 : 创建时间 : 2025/4/4 22:05 实现功能 :
 * <p>
 * 作者 : xiaoss 版本 : V1.0.0-SNAPSHOT
 *
 * @see
 * @since ---------------------------------------------------------------- 修改记录 日 期     	版本     修改人
 * 修改内容 2025/4/4      V1.0.0  xiaoss   创建
 * ----------------------------------------------------------------
 */
@Controller
@RequestMapping("/sys/menu")
@AllArgsConstructor
public class RbacMenuController {
    private final RbacMenuQueryService menuQueryService;

    /**
     * 菜单树
     *
     * @return
     */
    @GetMapping("/tree")
    @ResponseBody
    public List<MenuVo> tree() {
        return menuQueryService.menu();
    }

    /**
     * 菜单列表树
     *
     * @return
     */
    @GetMapping("/selectTree")
    @ResponseBody
    public Response<List<MenuVo>> selectTree() {
        List<MenuVo> menuVos = menuQueryService.menu();
        return Response.ok(menuVos);
    }

}
