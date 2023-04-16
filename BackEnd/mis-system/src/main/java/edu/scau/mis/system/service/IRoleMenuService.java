package edu.scau.mis.system.service;

import edu.scau.mis.system.domain.RoleMenu;
import java.util.List;

/**
 * (RoleMenu)表服务接口
 *
 * @author CJW
 * @since 2023-03-04 00:48:47
 */
public interface IRoleMenuService {

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    RoleMenu selectById(Long roleId);

    /**
     * 查询指定行数据
     *
     * @param roleMenu 筛选条件
     * @return 查询结果
     */
    List<RoleMenu> selectList(RoleMenu roleMenu);

    /**
     * 新增数据
     *
     * @param roleMenu 实例对象
     * @return 实例对象
     */
    RoleMenu insert(RoleMenu roleMenu);

    /**
     * 修改数据
     *
     * @param roleMenu 实例对象
     * @return 实例对象
     */
    RoleMenu update(RoleMenu roleMenu);

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 是否成功
     */
    boolean deleteById(Long roleId);

}
