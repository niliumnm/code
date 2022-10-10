package com.stelpolvo.wiki.mapper;

import com.stelpolvo.wiki.domain.EbookSnapshot;
import com.stelpolvo.wiki.domain.EbookSnapshotExample;

import java.util.List;

import com.stelpolvo.wiki.domain.Statistic;
import org.apache.ibatis.annotations.Param;

public interface EbookSnapshotMapper {
    long countByExample(EbookSnapshotExample example);

    int deleteByExample(EbookSnapshotExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EbookSnapshot record);

    int insertSelective(EbookSnapshot record);

    List<EbookSnapshot> selectByExample(EbookSnapshotExample example);

    EbookSnapshot selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EbookSnapshot record, @Param("example") EbookSnapshotExample example);

    int updateByExample(@Param("record") EbookSnapshot record, @Param("example") EbookSnapshotExample example);

    int updateByPrimaryKeySelective(EbookSnapshot record);

    int updateByPrimaryKey(EbookSnapshot record);

    void genSnapshot();

    List<Statistic> getStatistic();

    List<Statistic> get30Statistic();
}