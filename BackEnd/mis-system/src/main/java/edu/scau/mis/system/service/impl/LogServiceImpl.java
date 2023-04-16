package edu.scau.mis.system.service.impl;

import edu.scau.mis.system.domain.Log;
import edu.scau.mis.system.mapper.LogMapper;
import edu.scau.mis.system.service.ILogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 操作日志(Log)表服务实现类
 *
 * @author CJW
 * @since 2023-03-03 20:46:23
 */
@Service("logService")
public class LogServiceImpl implements ILogService {
    @Resource
    private LogMapper logMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param logId 主键
     * @return 实例对象
     */
    @Override
    public Log selectById(Long logId) {
        return this.logMapper.selectById(logId);
    }

    /**
     * 查询指定行
     *
     * @param log 筛选条件
     * @return 查询结果
     */
    @Override
    public List<Log> selectList(Log log) {
        return this.logMapper.selectList(log);
    }

    /**
     * 新增数据
     *
     * @param log 实例对象
     * @return 实例对象
     */
    @Override
    public Log insert(Log log) {
        this.logMapper.insert(log);
        return log;
    }

    /**
     * 修改数据
     *
     * @param log 实例对象
     * @return 实例对象
     */
    @Override
    public Log update(Log log) {
        this.logMapper.update(log);
        return this.selectById(log.getLogId());
    }

    /**
     * 通过主键删除数据
     *
     * @param logId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long logId) {
        return this.logMapper.deleteById(logId) > 0;
    }
}
