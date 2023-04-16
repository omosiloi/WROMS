package edu.scau.mis.system.mapper;

import edu.scau.mis.system.domain.Log;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 操作日志(Log)表数据库访问层
 *
 * @author CJW
 * @since 2023-03-03 20:46:23
 */
 @Mapper
 @Repository
public interface LogMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param logId 主键
     * @return 实例对象
     */
    Log selectById(Long logId);

    /**
     * 查询指定行数据(含筛选条件)
     *
     * @param log 查询条件
     * @return 对象列表
     */
    List<Log> selectList(Log log);

    /**
     * 统计总行数
     *
     * @param log 查询条件
     * @return 总行数
     */
    long count(Log log);

    /**
     * 新增数据
     *
     * @param log 实例对象
     * @return 影响行数
     */
    int insert(Log log);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Log> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Log> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Log> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Log> entities);

    /**
     * 修改数据
     *
     * @param log 实例对象
     * @return 影响行数
     */
    int update(Log log);

    /**
     * 通过主键删除数据
     *
     * @param logId 主键
     * @return 影响行数
     */
    int deleteById(Long logId);

}
