package com.stelpolvo.wiki.controller;

import com.stelpolvo.wiki.annotation.Log;
import com.stelpolvo.wiki.domain.Resp;
import com.stelpolvo.wiki.domain.vo.BaseVo;
import com.stelpolvo.wiki.domain.vo.DocVo;
import com.stelpolvo.wiki.service.DocService;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;

@RestController
@RequestMapping("/doc")
public class DocController {
    @Resource
    private DocService docService;

    @GetMapping("/list")
    @Log("条件查询文档列表")
    public Resp list(BaseVo docVo) {
        return Resp.ok(docService.list(docVo));
    }

    @GetMapping("/all/{ebookId}")
    @Log("根据电子书id查询")
    public Resp all(@PathVariable Long ebookId) {
        return Resp.ok(docService.all(ebookId));
    }

    @PostMapping("/save")
    @Log("修改或新增文档")
    public Resp save(@RequestBody DocVo docVo) {
        if (ObjectUtils.isEmpty(docVo.getEbookId()) ||
                ObjectUtils.isEmpty(docVo.getParent()) ||
                ObjectUtils.isEmpty(docVo.getName()) ||
                ObjectUtils.isEmpty(docVo.getSort()) ||
                ObjectUtils.isEmpty(docVo.getContent())) {
            return Resp.error("请填写全部信息");
        }
        return docService.save(docVo) > 0 ? Resp.ok("保存成功") : Resp.error("保存失败");
    }

    @DeleteMapping("/delete/{ids}")
    @Log("批量删除文档")
    public Resp delete(@PathVariable String ids) {
        int delete = docService.delete(Arrays.asList(ids.split(",")));
        return delete > 0 ? Resp.ok("批量删除成功") : Resp.error("批量删除失败");
    }

    @GetMapping("/find-content/{id}")
    @Log("根据文章id查询文档内容")
    public Resp getContent(@PathVariable Long id) {
        return Resp.ok("查询成功", docService.findContent(id));
    }

    @GetMapping("/vote/{id}")
    @Log("文档点赞")
    public Resp updateVoteCount(@PathVariable Long id) {
        return docService.updateVoteCount(id) > 0 ? Resp.ok("点赞成功") : Resp.error("点赞失败");
    }
}
