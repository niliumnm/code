package com.ruiji.service;

import com.ruiji.dto.DishDto;
import com.ruiji.pojo.Dish;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Administrator
* @description 针对表【dish(菜品管理)】的数据库操作Service
* @createDate 2022-10-13 15:55:46
*/
public interface DishService extends IService<Dish> {
    public void saveWithFlavor(DishDto dishDto);

    void removeDish(Long ids);
}
