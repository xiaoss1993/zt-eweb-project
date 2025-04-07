package com.zt.eweb.modular.codegen.infra.dal.mapper;


import com.zt.eweb.framework.mybatis.core.mapper.BaseCrudMapper;
import com.zt.eweb.modular.codegen.infra.dal.entity.TemplateGroup;
import org.apache.ibatis.annotations.Mapper;

/**
 * 模板组
 *
 * @author hccake 2020-06-19 19:11:41
 */
@Mapper
public interface TemplateGroupMapper extends BaseCrudMapper<TemplateGroup> {


}