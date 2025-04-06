package com.zt.eweb.modular.meta.adapter.mybatis;

import com.google.common.collect.Lists;
import com.zt.eweb.framework.common.spring.SpringContextHolder;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/meta/mybatis")
@Tag(name = "获取MyBatis执行ID")
public class MetaMyBatisInfoController {

    @GetMapping("/statementIdList")
    @ResponseBody
    public List<String> statementIdList() {
        List<String> statementIdList = Lists.newArrayList();
        SqlSessionFactory sqlSessionFactory = SpringContextHolder.getBean(SqlSessionFactory.class);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Collection<Class<?>> mapperInterfaces = sqlSessionFactory.getConfiguration().getMapperRegistry().getMappers();
        return statementIdList;
    }
}
