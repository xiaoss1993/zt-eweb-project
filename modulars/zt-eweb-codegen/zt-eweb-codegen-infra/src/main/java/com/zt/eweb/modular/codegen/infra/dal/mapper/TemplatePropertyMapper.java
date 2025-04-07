package com.zt.eweb.modular.codegen.infra.dal.mapper;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zt.eweb.framework.mybatis.core.mapper.BaseCrudMapper;
import com.zt.eweb.modular.codegen.infra.dal.entity.TemplateProperty;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 模板属性配置
 *
 * @author hccake 2020-06-22 15:46:39
 */
@Mapper
public interface TemplatePropertyMapper extends BaseCrudMapper<TemplateProperty> {


	/**
	 * 根据模板组标识获取模板组的所有配置
	 * @param templateGroupKey 模板组标识
	 * @return List<TemplateProperty> 配置列表
	 */
	default List<TemplateProperty> listByTemplateGroupKey(String templateGroupKey) {
		return this.selectList(Wrappers.<TemplateProperty>lambdaQuery()
			.eq(TemplateProperty::getGroupKey, templateGroupKey)
			.orderByAsc(TemplateProperty::getOrderValue));

	}

	/**
	 * 根据模板组标识 删除模板属性
	 * @param templateGroupKey 模板组标识
	 */
	default void removeByGroupKey(String templateGroupKey) {
		this.delete(Wrappers.lambdaQuery(TemplateProperty.class).eq(TemplateProperty::getGroupKey, templateGroupKey));
	}

}