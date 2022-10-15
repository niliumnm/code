package com.ruiji.mapper;

import com.ruiji.pojo.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Administrator
* @description 针对表【category(菜品及套餐分类)】的数据库操作Mapper
* @createDate 2022-10-12 16:20:04
* @Entity com.ruiji.pojo.Category
*/
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}




