package com.stelpolvo.wiki.controller;

import com.stelpolvo.wiki.annotation.Log;
import com.stelpolvo.wiki.domain.Ebook;
import com.stelpolvo.wiki.domain.vo.EbookVo;
import com.stelpolvo.wiki.domain.Resp;
import com.stelpolvo.wiki.service.EbookService;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/ebook")
public class EbookController {
    /* 由于基于字段的情况@Autowired使用set方式,如果有构造方法使用注入的属性的话则会报控制值异常
     静态代码块>代码块>构造器>执行顺序，而Spring会等待类完全被加载完的时候才会进行注入。
     所以为了避免这种情况的空指针我们可以选择使用@Resource或者使用@Autowire的构造器注入。
     为了规范化代码之后我都会使用@Resource来注入变量 */
    @Resource
    private EbookService ebookService;

    @GetMapping("/list")
    @Log("条件查询电子书列表")
    public Resp list(EbookVo ebookVo) {
        return Resp.ok(ebookService.list(ebookVo));
    }

    @PostMapping("/save")
    @Log("修改或保存电子书信息")
    public Resp save(@RequestBody Ebook ebook) {
        if (ObjectUtils.isEmpty(ebook.getName())) {
            return Resp.error("书名不能为空");
        }
        return ebookService.save(ebook) > 0 ? Resp.ok("保存成功") : Resp.error("保存失败");
    }

    @DeleteMapping("/delete/{id}")
    @Log("删除电子书")
    public Resp delete(@PathVariable Long id) {
        return ebookService.delete(id) > 0 ? Resp.ok("删除成功") : Resp.error("删除失败");
    }
}
