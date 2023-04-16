package edu.scau.mis.system.service.impl;

import edu.scau.mis.system.domain.RoleMenu;
import edu.scau.mis.system.mapper.RoleMenuMapper;
import edu.scau.mis.system.service.IRoleMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (RoleMenu)表服务实现类
 *
 * @author CJW
 * @since 2023-03-04 00:48:47
 */
@Service("roleMenuService")
public class RoleMenuServiceImpl implements IRoleMenuService {

    @Override
    public RoleMenu selectById(Long roleId) {
        return null;
    }

    @Override
    public List<RoleMenu> selectList(RoleMenu roleMenu) {
        return null;
    }

    @Override
    public RoleMenu insert(RoleMenu roleMenu) {
        return null;
    }

    @Override
    public RoleMenu update(RoleMenu roleMenu) {
        return null;
    }

    @Override
    public boolean deleteById(Long roleId) {
        return false;
    }
}
