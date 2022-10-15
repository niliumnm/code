package com.ruiji.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruiji.dto.DishDto;
import com.ruiji.pojo.Dish;
import com.ruiji.service.impl.DishServiceImpl;
import com.ruiji.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dish")
@Slf4j
public class DishController {
    @Autowired
    DishServiceImpl dishService;

    @GetMapping("/page")
    public R page(@RequestParam Integer page,
                  @RequestParam Integer pageSize,
                  String name) {

        Page<Dish> pageInfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<Dish> dishWrapper = new LambdaQueryWrapper<>();
        dishWrapper.like(name != null, Dish::getName, name);
        dishWrapper.orderByDesc(Dish::getUpdateTime);
        dishService.page(pageInfo, dishWrapper);
        return R.success(pageInfo);
    }

    @RequestMapping("/{id}")
    public R get(@RequestParam Long id) {
        return R.success(dishService.getById(id));
    }

    @PostMapping
    public R save(@RequestBody DishDto dishDto) {
        log.info(dishDto.toString());
        dishService.saveWithFlavor(dishDto);

        return R.success("新增菜品成功");
    }

    @DeleteMapping
    public R remove(@RequestParam Long ids) {
        dishService.removeDish(ids);
        return R.success("删除菜品成功");
    }

//    @PostMapping("/status/{status}")
//    public R ban(@PathVariable Integer status, @RequestParam Long ids) {
//
//        dishService.update()
//    }
}
