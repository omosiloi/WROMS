package edu.scau.mis.sale.service;

import edu.scau.mis.sale.domain.Table;

import java.util.List;

/**
 * 餐桌表(Table)表服务接口
 *
 * @author CJW
 * @since 2023-03-04 00:36:27
 */
public interface ITableService {

    Table selectTableById(Long tableId);

    List<Table> selectTableList(Table table);

    List<Table> selectAllTable();

    int deleteTableById(Long tableId);

    int insertTable(Table table);

    int updateTable(Table table);

}
