package edu.scau.mis.sale.service.impl;

import edu.scau.mis.sale.domain.Dish;
import edu.scau.mis.sale.domain.Table;
import edu.scau.mis.sale.mapper.TableMapper;
import edu.scau.mis.sale.service.ITableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableServiceImpl implements ITableService {
    @Autowired
    private TableMapper tableMapper;

    @Override
    public Table selectTableById(Long tableId) {
        return tableMapper.selectTableById(tableId);
    }
    public List<Table> selectTableByName(String tableName) {
        return tableMapper.selectTableByName(tableName);
    }
    public List<Table> selectTableByStatus(String status) {
        return tableMapper.selectTableByStatus(status);
    }
    @Override
    public List<Table> selectAllTable(){
        return tableMapper.selectAllTable();
    }

    @Override
    public List<Table> selectTableList(Table table) {
        return tableMapper.selectTableList(table);
    }

    @Override
    public int deleteTableById(Long tableId) {
        return tableMapper.deleteTableById(tableId);
    }

    @Override
    public int insertTable(Table table) {
        return tableMapper.insertTable(table);
    }

    @Override
    public int updateTable(Table table) {
        return tableMapper.updateTable(table);
    }
}
