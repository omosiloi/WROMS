package edu.scau.mis.sale.service;

import edu.scau.mis.sale.domain.Dish;
import edu.scau.mis.sale.domain.Table;

import java.util.List;

public interface ITableService {

    Table selectTableById(Long tableId);
    List<Table> selectTableByName(String tableName);
    List<Table> selectTableByStatus(String status);
    List<Table> selectTableList(Table table);

    List<Table> selectAllTable();

    int deleteTableById(Long tableId);

    int insertTable(Table table);

    int updateTable(Table table);

}
