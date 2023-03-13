
package edu.scau.mis.pos.service;

import edu.scau.mis.pos.domain.Category;

import java.util.List;

public interface ICategoryService {
    /**
     * 根据ID找类别
     * @param categoryId 类别ID
     * @return 类别对象
     */
    Category getCategoryById(Long categoryId);


    List<Category> listAllCategory();

    public Category selectCategoryById(Long categoryId);

    public List<Category> selectCategoryList(Category category);


    public int insertCategory(Category category);

    public int updateCategory(Category category);

    public int deleteCategoryById(Long categoryId);

}