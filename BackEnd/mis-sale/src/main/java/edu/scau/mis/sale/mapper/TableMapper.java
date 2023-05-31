package edu.scau.mis.sale.mapper;

import edu.scau.mis.sale.domain.Dish;
import edu.scau.mis.sale.domain.Table;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * TableMapper
 */
@Mapper
public interface TableMapper {

    Table selectTableById(Long id);

    List<Table> selectTableByName(String tableName);

    List<Table> selectTableByStatus(String status);

//    List<Table> selectTablesByOrderId(Long orderId);
//
//    List<Table> selectTablesByOrderName(String orderName);
//
//    List<Long> selectTableIdListByOrderId(Long orderId);

    List<Table> selectTableList(Table table);

    List<Table> selectAllTable();

    int deleteTableById(Long tableId);

    int deleteTableByIds(Long[] tableIds);

    int insertTable(Table table);

    int updateTable(Table table);

}

