package com.ruiji.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruiji.pojo.Category;
import com.ruiji.pojo.Dish;
import com.ruiji.service.impl.CategoryServiceImpl;
import com.ruiji.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryServiceImpl categoryService;

    /**
     * 分页查询分类
     *
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R page(@RequestParam Integer page,
                  @RequestParam Integer pageSize,
                  String name
    ) {
        Page<Category> pageInfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.like(name != null, Category::getName, name);

        categoryService.page(pageInfo, queryWrapper);
        return R.success(pageInfo);
    }

    /**
     * 插入分类
     *
     * @param category
     * @return
     */
    @PostMapping
    public R save(@RequestBody Category category) {
        return R.success(categoryService.save(category));
    }

    @DeleteMapping
    public R delete(@RequestParam Long ids) {
        boolean removeById = categoryService.removeById(ids);
        return R.success(removeById);
    }

    @PutMapping
    public R update(@RequestBody Category category) {
        return R.success(categoryService.updateById(category));
    }

    @GetMapping("list")
    public R list(Category category) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(category.getName() != null, Category::getType, category.getType());
        queryWrapper.orderByAsc(Category::getSort).orderByDesc(Category::getUpdateTime);
        List<Category> list = categoryService.list(queryWrapper);
        return R.success(list);
    }



}
