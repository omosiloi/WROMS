package edu.scau.mis.system.controller;

import edu.scau.mis.system.domain.Log;
import edu.scau.mis.system.service.ILogService;
import edu.scau.mis.core.domain.AjaxResult;
import org.springframework.web.bind.annotation.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
@RestController
@RequestMapping("log")
public class LogController {
    @Autowired
    private ILogService logService;

    @GetMapping("/list")
    public AjaxResult list(Log log) {
        return AjaxResult.success(this.logService.selectList(log));
    }

    @GetMapping("/page")
    public AjaxResult page(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, Log log) {
        PageHelper.startPage(pageNum,pageSize);
        List<Log> list = this.logService.selectList(log);
        PageInfo<List> pageInfo = new PageInfo(list);
        return AjaxResult.success(pageInfo);
    }

    @GetMapping("{id}")
    public AjaxResult getById(@PathVariable("id") Long id) {
        return AjaxResult.success(this.logService.selectById(id));
    }

    @PostMapping
    public AjaxResult add(@RequestBody Log log) {
        return AjaxResult.success(this.logService.insert(log));
    }

    @PutMapping
    public AjaxResult edit(@RequestBody Log log) {
        return AjaxResult.success(this.logService.update(log));
    }

    @DeleteMapping("/delete/{id}")
    public AjaxResult deleteById(@PathVariable Long id) {
        return AjaxResult.success(this.logService.deleteById(id));
    }
}
