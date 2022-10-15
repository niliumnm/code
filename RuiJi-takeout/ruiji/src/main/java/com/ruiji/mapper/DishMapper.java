package com.ruiji.mapper;

import com.ruiji.pojo.Dish;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Administrator
* @description 针对表【dish(菜品管理)】的数据库操作Mapper
* @createDate 2022-10-13 15:55:46
* @Entity com.ruiji.pojo.Dish
*/
@Mapper
public interface DishMapper extends BaseMapper<Dish> {

}




