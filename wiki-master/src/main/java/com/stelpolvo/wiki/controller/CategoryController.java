package com.stelpolvo.wiki.controller;

import com.stelpolvo.wiki.annotation.Log;
import com.stelpolvo.wiki.domain.Category;
import com.stelpolvo.wiki.domain.Resp;
import com.stelpolvo.wiki.domain.vo.CategoryVo;
import com.stelpolvo.wiki.service.CategoryService;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @GetMapping("/all")
    @Log("查询全部分类")
    public Resp list(CategoryVo categoryVo) {
        return Resp.ok(categoryService.list(categoryVo));
    }

    @PostMapping("/save")
    @Log("保存分类")
    public Resp save(@RequestBody Category category) {
        if (ObjectUtils.isEmpty(category.getName())) {
            return Resp.error("分类名不能为空");
        }
        return categoryService.save(category) > 0 ? Resp.ok("保存成功") : Resp.error("保存失败");
    }

    @DeleteMapping("/delete/{id}")
    @Log("删除分类")
    public Resp delete(@PathVariable Long id) {
        return categoryService.delete(id) > 0 ? Resp.ok("删除成功") : Resp.error("删除失败");
    }
}
