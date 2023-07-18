package edu.scau.mis.system.service;

import edu.scau.mis.system.domain.RoleMenu;
import java.util.List;

public interface IRoleMenuService {

    RoleMenu selectById(Long roleId);

    List<RoleMenu> selectList(RoleMenu roleMenu);

    RoleMenu insert(RoleMenu roleMenu);

    RoleMenu update(RoleMenu roleMenu);

    boolean deleteById(Long roleId);

}
