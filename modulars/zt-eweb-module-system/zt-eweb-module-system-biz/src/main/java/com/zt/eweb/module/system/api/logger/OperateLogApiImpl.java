package com.zt.eweb.module.system.api.logger;

import com.zt.eweb.framework.common.pojo.PageResult;
import com.zt.eweb.framework.common.util.object.BeanUtils;
import com.zt.eweb.module.system.api.logger.dto.OperateLogCreateReqDTO;
import com.zt.eweb.module.system.api.logger.dto.OperateLogPageReqDTO;
import com.zt.eweb.module.system.api.logger.dto.OperateLogRespDTO;
import com.zt.eweb.module.system.dal.dataobject.logger.OperateLogDO;
import com.zt.eweb.module.system.service.logger.OperateLogService;
import com.fhs.core.trans.anno.TransMethodResult;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

/**
 * 操作日志 API 实现类
 *
 * 
 */
@Service
@Validated
public class OperateLogApiImpl implements OperateLogApi {

    @Resource
    private OperateLogService operateLogService;

    @Override
    public void createOperateLog(OperateLogCreateReqDTO createReqDTO) {
        operateLogService.createOperateLog(createReqDTO);
    }

    @Override
    @TransMethodResult
    public PageResult<OperateLogRespDTO> getOperateLogPage(OperateLogPageReqDTO pageReqDTO) {
        PageResult<OperateLogDO> operateLogPage = operateLogService.getOperateLogPage(pageReqDTO);
        return BeanUtils.toBean(operateLogPage, OperateLogRespDTO.class);
    }

}
