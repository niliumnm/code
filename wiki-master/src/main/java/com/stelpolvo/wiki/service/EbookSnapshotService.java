package com.stelpolvo.wiki.service;

import com.stelpolvo.wiki.domain.Statistic;
import com.stelpolvo.wiki.mapper.EbookSnapshotMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookSnapshotService {

    @Resource
    private EbookSnapshotMapper snapshotMapper;

    public void genSnapshot() {
        snapshotMapper.genSnapshot();
    }

    /**
     * 获取首页数值数据：总阅读数、总点赞数、今日阅读数、今日点赞数、今日预计阅读数、今日预计阅读增长
     */
    public List<Statistic> getStatistic() {
        return snapshotMapper.getStatistic();
    }

    /**
     * 30天数值统计
     */
    public List<Statistic> get30Statistic() {
        return snapshotMapper.get30Statistic();
    }

}