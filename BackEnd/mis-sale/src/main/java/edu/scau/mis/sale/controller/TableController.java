package edu.scau.mis.sale.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.scau.mis.core.domain.AjaxResult;
import edu.scau.mis.sale.domain.Table;
import edu.scau.mis.sale.service.ITableService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("table")
public class TableController {
    @Autowired
    private ITableService tableService;

    @ApiOperation("分页查询餐桌")
    @GetMapping("/page")
    public AjaxResult page(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, Table table) {
        PageHelper.startPage(pageNum,pageSize);
        List<Table> list = tableService.selectTableList(table);
        PageInfo<Table> pageInfo = new PageInfo<>(list);
        return AjaxResult.success(pageInfo);
    }

    @ApiOperation("查询所有餐桌")
    @GetMapping("/listAll")
    public AjaxResult listAll(){
        return AjaxResult.success(tableService.selectAllTable());
    }

    @ApiOperation("根据ID查询餐桌")
    @GetMapping("/{tableId}")
    public AjaxResult getById(@PathVariable("tableId") Long tableId) {
        return AjaxResult.success(tableService.selectTableById(tableId));
    }

    @ApiOperation("新增餐桌")
    @PostMapping
    public AjaxResult add(@RequestBody Table table) {
        return AjaxResult.success(tableService.insertTable(table));
    }

    @ApiOperation("编辑餐桌")
    @PutMapping
    public AjaxResult edit(@RequestBody Table table) {
        return AjaxResult.success(tableService.updateTable(table));
    }

    @ApiOperation("删除餐桌")
    @DeleteMapping("/delete/{tableId}")
    public AjaxResult deleteById(@PathVariable Long tableId) {
        return AjaxResult.success(tableService.deleteTableById(tableId));
    }
}
