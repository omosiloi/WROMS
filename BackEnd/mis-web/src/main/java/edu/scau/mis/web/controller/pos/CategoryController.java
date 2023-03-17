package edu.scau.mis.web.controller.pos;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.scau.mis.core.domain.AjaxResult;
import edu.scau.mis.pos.domain.Category;
import edu.scau.mis.pos.service.ICategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping("getById/{categoryId}")
    @ApiOperation("根据ID找产品种类")
    public AjaxResult getById(@PathVariable("categoryId") Long categoryId){
        return AjaxResult.success(categoryService.getCategoryById(categoryId));
    }

    @GetMapping("/listAll")
    @ApiOperation("列出所有产品种类")
    public AjaxResult listAll(){
        return AjaxResult.success(categoryService.listAllCategory());
    }

    @GetMapping("/list")
    @ApiOperation("查询类别列表")
    public AjaxResult list(Category category) {
        List<Category> list = categoryService.selectCategoryList(category);
        return AjaxResult.success(list);
    }

    @GetMapping(value = "/{categoryId}")
    @ApiOperation("查询类别详情")
    public AjaxResult get(@PathVariable("categoryId") Long categoryId) {
        return AjaxResult.success(categoryService.selectCategoryById(categoryId));
    }

    @PostMapping
    @ApiOperation("新增类别")
    public AjaxResult add(@RequestBody Category category) {
        int rows = categoryService.insertCategory(category);
        return rows > 0 ? AjaxResult.success("添加类别成功") : AjaxResult.error("添加类别失败");
    }

    @PutMapping
    @ApiOperation("修改类别")
    public AjaxResult edit(@RequestBody Category category) {
        int rows = categoryService.updateCategory(category);
        return rows > 0 ? AjaxResult.success("修改类别成功") : AjaxResult.error("修改类别失败");
    }

    @DeleteMapping("/{categoryId}")
    @ApiOperation("删除类别")
    public AjaxResult remove(@PathVariable Long categoryId) {
        int rows = categoryService.deleteCategoryById(categoryId);
        return rows > 0 ? AjaxResult.success("删除类别成功") : AjaxResult.error("删除类别失败");
    }

    @GetMapping("/page")
    @ApiOperation("分页查询")
    public AjaxResult page(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, Category category) {
        PageHelper.startPage(pageNum,pageSize);
        List<Category> list = categoryService.selectCategoryList(category);
        PageInfo<List> pageInfo = new PageInfo(list);
        // 以下写法也可以
        // PageInfo<ProductDescription> pageInfo = new PageInfo<>(list)
        return AjaxResult.success(pageInfo);
    }
}