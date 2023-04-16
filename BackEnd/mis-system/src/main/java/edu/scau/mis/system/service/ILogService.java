package edu.scau.mis.system.service;

import edu.scau.mis.system.domain.Log;
import java.util.List;

/**
 * 操作日志(Log)表服务接口
 *
 * @author CJW
 * @since 2023-03-03 20:46:23
 */
public interface ILogService {

    /**
     * 通过ID查询单条数据
     *
     * @param logId 主键
     * @return 实例对象
     */
    Log selectById(Long logId);

    /**
     * 查询指定行数据
     *
     * @param log 筛选条件
     * @return 查询结果
     */
    List<Log> selectList(Log log);

    /**
     * 新增数据
     *
     * @param log 实例对象
     * @return 实例对象
     */
    Log insert(Log log);

    /**
     * 修改数据
     *
     * @param log 实例对象
     * @return 实例对象
     */
    Log update(Log log);

    /**
     * 通过主键删除数据
     *
     * @param logId 主键
     * @return 是否成功
     */
    boolean deleteById(Long logId);

}
