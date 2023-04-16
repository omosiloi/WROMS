package edu.scau.mis.system.controller;

import edu.scau.mis.system.domain.Log;
import edu.scau.mis.system.service.ILogService;
import edu.scau.mis.core.domain.AjaxResult;
import org.springframework.web.bind.annotation.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
/**
 * 操作日志(Log)表控制层
 *
 * @author CJW
 * @since 2023-03-03 20:46:23
 */
@RestController
@RequestMapping("log")
public class LogController {
    /**
     * 服务对象
     */
    @Autowired
    private ILogService logService;

    /**
     * 查询数据列表
     *
     * @param log 筛选条件
     * @return 查询结果
     */
    @GetMapping("/list")
    public AjaxResult list(Log log) {
        return AjaxResult.success(this.logService.selectList(log));
    }

    /**
     * 分页查询数据列表
     *
     * @param log 筛选条件
     * @return 查询结果
     */
    @GetMapping("/page")
    public AjaxResult page(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, Log log) {
        PageHelper.startPage(pageNum,pageSize);
        List<Log> list = this.logService.selectList(log);
        PageInfo<List> pageInfo = new PageInfo(list);
        return AjaxResult.success(pageInfo);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public AjaxResult getById(@PathVariable("id") Long id) {
        return AjaxResult.success(this.logService.selectById(id));
    }

    /**
     * 新增数据
     *
     * @param log 实体
     * @return 新增结果
     */
    @PostMapping
    public AjaxResult add(@RequestBody Log log) {
        return AjaxResult.success(this.logService.insert(log));
    }

    /**
     * 编辑数据
     *
     * @param log 实体
     * @return 编辑结果
     */
    @PutMapping
    public AjaxResult edit(@RequestBody Log log) {
        return AjaxResult.success(this.logService.update(log));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/delete/{id}")
    public AjaxResult deleteById(@PathVariable Long id) {
        return AjaxResult.success(this.logService.deleteById(id));
    }
}
