package com.zt.eweb.modular.rbac.adapter.user;

import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zt.eweb.framework.common.base.result.PageResponse;
import com.zt.eweb.framework.common.base.result.PageVO;
import com.zt.eweb.modular.rbac.client.dto.SysUserDTO;
import com.zt.eweb.modular.rbac.client.manager.UserApplicationService;
import com.zt.eweb.modular.rbac.client.manager.UserQueryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 模块名 : 文件名 : 创建时间 : 2025/4/4 22:08 实现功能 :
 * <p>
 * 作者 : xiaoss 版本 : V1.0.0-SNAPSHOT
 *
 * @see
 * @since ---------------------------------------------------------------- 修改记录 日 期     	版本     修改人
 * 修改内容 2025/4/4      V1.0.0  xiaoss   创建
 * ----------------------------------------------------------------
 */
@RestController
@RequestMapping("/sys/user")
@AllArgsConstructor
public class RbacUserController {


    private final UserApplicationService userApplicationService;
    private final UserQueryService userQueryService;

    /**
     * 分页查询
     *
     * @param page
     * @param limit
     * @param deptId
     * @param keyWord
     * @return
     */
    @GetMapping
    public PageResponse<List<SysUserDTO>> pageAll(@RequestParam(required = false, defaultValue = "1") long page,
                                                  @RequestParam(required = false, defaultValue = "10") long limit,
                                                  Long deptId,
                                                  String keyWord) {
        Page<SysUserDTO> roadPage = new Page<>(page, limit);

        QueryWrapper<SysUserDTO> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(deptId != null, SysUserDTO::getDeptId, deptId)
                .and(CharSequenceUtil.isNotBlank(keyWord), i ->
                        i.like(CharSequenceUtil.isNotBlank(keyWord), SysUserDTO::getUserName, keyWord)
                                .or()
                                .like(CharSequenceUtil.isNotBlank(keyWord), SysUserDTO::getNickName, keyWord));

        Page<SysUserDTO> pageList = userQueryService.page(roadPage, queryWrapper);
        return PageResponse.ok(pageList.getRecords(), pageList.getTotal());
    }

    /**
     * 复杂分页查询示例
     *
     * @param page
     * @param userDto
     * @return
     */
    @GetMapping("/pageComplexAll")
    public PageResponse<List<SysUserDTO>> pageComplexAll(PageVO page, SysUserDTO userDto) {
        Page<SysUserDTO> roadPage = page.toPage();
        Page<SysUserDTO> pageList = userQueryService.page(roadPage, userDto.queryWrapper());
        return PageResponse.ok(pageList.getRecords(), pageList.getTotal());
    }


}
