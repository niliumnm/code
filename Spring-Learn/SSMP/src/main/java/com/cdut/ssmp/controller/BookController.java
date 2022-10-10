package com.cdut.ssmp.controller;
import com.cdut.ssmp.service.serviceImpl.BookServiceImpl;
import com.cdut.ssmp.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class BookController {
    @Autowired
    private BookServiceImpl bookService;

    @RequestMapping("/book/all")
    public R getAllBook() {
        log.info("info");
        log.warn("warn");
        log.debug("debug");

        return R.success(bookService.getAllBook());
    }
}
