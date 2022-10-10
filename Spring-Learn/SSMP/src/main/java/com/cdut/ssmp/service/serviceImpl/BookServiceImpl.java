package com.cdut.ssmp.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cdut.ssmp.dao.BookDao;
import com.cdut.ssmp.pojo.Book;
import com.cdut.ssmp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl extends ServiceImpl<BookDao, Book> implements BookService {
    @Autowired
    private BookDao bookDao;


    public List<Book> getAllBook() {
        return bookDao.selectList(null);
    }
}
