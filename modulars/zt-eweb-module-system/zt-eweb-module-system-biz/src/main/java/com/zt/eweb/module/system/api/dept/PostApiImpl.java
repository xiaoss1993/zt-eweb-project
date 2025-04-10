package com.zt.eweb.module.system.api.dept;

import com.zt.eweb.framework.common.util.object.BeanUtils;
import com.zt.eweb.module.system.api.dept.dto.PostRespDTO;
import com.zt.eweb.module.system.dal.dataobject.dept.PostDO;
import com.zt.eweb.module.system.service.dept.PostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * 岗位 API 实现类
 *
 * 
 */
@Service
public class PostApiImpl implements PostApi {

    @Resource
    private PostService postService;

    @Override
    public void validPostList(Collection<Long> ids) {
        postService.validatePostList(ids);
    }

    @Override
    public List<PostRespDTO> getPostList(Collection<Long> ids) {
        List<PostDO> list = postService.getPostList(ids);
        return BeanUtils.toBean(list, PostRespDTO.class);
    }

}
