package com.stelpolvo.wiki.controller;

import com.stelpolvo.wiki.domain.Resp;
import com.stelpolvo.wiki.domain.Statistic;
import com.stelpolvo.wiki.service.EbookSnapshotService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/ebook-snapshot")
public class EbookSnapshotController {
    @Resource
    private EbookSnapshotService ebookSnapshotService;

    @GetMapping("/get-statistic")
    public Resp getStatistic() {
        List<Statistic> statisticResp = ebookSnapshotService.getStatistic();
        return Resp.ok("查询成功", statisticResp);
    }

    @GetMapping("/get-30-statistic")
    public Resp get30Statistic() {
        List<Statistic> statisticResp = ebookSnapshotService.get30Statistic();
        return Resp.ok("查询成功", statisticResp);
    }
}
