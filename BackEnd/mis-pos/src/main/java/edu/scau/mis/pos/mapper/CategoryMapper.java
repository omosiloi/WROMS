package edu.scau.mis.pos.mapper;

import edu.scau.mis.pos.domain.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    public Category getCategoryById(Long categoryId);

    public List<Category> listAllCategory();

    public Category selectCategoryById(Long categoryId);

    public List<Category> selectCategoryList(Category category);

    public int insertCategory(Category category);
    public int updateCategory(Category category);

    public int deleteCategoryById(Long categoryId);
}