package com.cdut.ssmp.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cdut.ssmp.pojo.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookDaoTestCase {

    @Autowired
    private BookDao bookDao;

    @Test
    void testGetById() {
        String value="1";
        IPage page = new Page(1, 5);
        LambdaQueryWrapper<Book>  lambdaQueryWrapper= new LambdaQueryWrapper<Book>();
        lambdaQueryWrapper.like(value!=null,Book::getName, value);
        bookDao.selectPage(page, lambdaQueryWrapper);
    }
}
