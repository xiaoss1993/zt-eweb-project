
package com.zt.eweb.modular.binder.mapper;

import com.zt.eweb.framework.mybatis.core.mapper.BaseCrudMapper;
import com.zt.eweb.modular.binder.entity.Organization;
import org.apache.ibatis.annotations.Mapper;

/**
 * 单位Mapper
 *
 * @author mazc@dibo.ltd
 * @version 2018/12/22
 */
@Mapper
public interface OrganizationMapper extends BaseCrudMapper<Organization> {

}

