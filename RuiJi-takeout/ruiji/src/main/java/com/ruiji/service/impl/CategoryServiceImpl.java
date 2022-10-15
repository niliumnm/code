package com.ruiji.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruiji.common.CustomException;
import com.ruiji.pojo.Category;
import com.ruiji.pojo.Dish;
import com.ruiji.pojo.Setmeal;
import com.ruiji.service.CategoryService;
import com.ruiji.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【category(菜品及套餐分类)】的数据库操作Service实现
* @createDate 2022-10-12 16:20:04
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{
    @Autowired
    DishServiceImpl dishService;
    @Autowired
    SetmealServiceImpl setmealService;

    public boolean removeById(Long id) {

        LambdaQueryWrapper<Dish> dishQueryWrapper = new LambdaQueryWrapper<>();
        dishQueryWrapper.eq(Dish::getCategoryId, id);
        int count1 = dishService.count(dishQueryWrapper);
        if (count1 > 0) {
            throw new CustomException("当前类关联了菜品");
        }
        LambdaQueryWrapper<Setmeal> setmealQueryWrapper = new LambdaQueryWrapper<>();
        setmealQueryWrapper.eq(Setmeal::getCategoryId, id);
        int count2 = setmealService.count(setmealQueryWrapper);

        if (count2 > 0) {
            throw new CustomException("当前类关联了菜品");
        }
        return super.removeById(id);
    }

}




