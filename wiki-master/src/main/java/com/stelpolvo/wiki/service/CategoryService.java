package com.stelpolvo.wiki.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stelpolvo.wiki.domain.Category;
import com.stelpolvo.wiki.domain.CategoryExample;
import com.stelpolvo.wiki.domain.RespPage;
import com.stelpolvo.wiki.domain.vo.CategoryVo;
import com.stelpolvo.wiki.mapper.CategoryMapper;
import com.stelpolvo.wiki.utils.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryService {
    private static final Logger LOG = LoggerFactory.getLogger(CategoryService.class);
    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private SnowFlake snowFlake;


    public List<Category> list(CategoryVo categoryVo) {
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.setOrderByClause("sort asc");
        return categoryMapper.selectByExample(categoryExample);
    }

    public int save(Category category) {
        if (ObjectUtils.isEmpty(category.getId())) {
            category.setId(snowFlake.nextId());
            return categoryMapper.insert(category);
        }
        return categoryMapper.updateByPrimaryKey(category);
    }

    public int delete(Long id) {
        return categoryMapper.deleteByPrimaryKey(id);
    }
}
