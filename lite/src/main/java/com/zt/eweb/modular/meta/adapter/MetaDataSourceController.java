package com.zt.eweb.modular.meta.adapter;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.creator.DataSourceProperty;
import com.baomidou.dynamic.datasource.creator.DefaultDataSourceCreator;
import com.baomidou.dynamic.datasource.creator.druid.DruidConfig;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 模块名 : 文件名 : 创建时间 : 2025/4/2 16:19 实现功能 :
 * <p>
 * 作者 : xiaoss 版本 : V1.0.0-SNAPSHOT
 *
 * @see
 * @since ---------------------------------------------------------------- 修改记录 日 期     	版本     修改人
 * 修改内容 2025/4/2      V1.0.0  xiaoss   创建
 * ----------------------------------------------------------------
 */
@RestController
@RequestMapping("/meta/datasource")
public class MetaDataSourceController {
  @Autowired
  DataSource dataSource;

  @Autowired
  DefaultDataSourceCreator dataSourceCreator;


  @GetMapping("/list")
  public List<String> list(){
    DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
    List<String>  list =new ArrayList<>();
    Map<String, DataSource> dataSources = ds.getDataSources();

    for(String  item :dataSources.keySet()){
      DataSource source = dataSources.get(item);
    }
    return list;
  }
  @GetMapping("/add")
  public String add(){
    DruidConfig druidConfig = new DruidConfig();
    DataSourceProperty dataSourceProperty = new DataSourceProperty();
    dataSourceProperty.setDruid(druidConfig);
    dataSourceProperty.setPoolName("slave_1");
    dataSourceProperty.setUsername("root");
    dataSourceProperty.setPassword("123456");
    dataSourceProperty.setType(DruidDataSource.class);
    dataSourceProperty.setUrl("jdbc:mysql://127.0.0.1:3306/datavines?serverTimezone=Asia/Shanghai&characterEncoding=utf8&useUnicode=true&useSSL=false&autoReconnect=true&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&nullCatalogMeansCurrent=true&allowPublicKeyRetrieval=true");
    dataSourceProperty.setDriverClassName("com.mysql.cj.jdbc.Driver");
    DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
    // async destroy datasource
    ds.setGraceDestroy(true);
    ds.addDataSource(dataSourceProperty.getPoolName(), dataSourceCreator.createDataSource(dataSourceProperty));
    return JSONObject.toJSONString(dataSourceProperty);
  }
}
