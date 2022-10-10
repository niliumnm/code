package com.stelpolvo.wiki.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stelpolvo.wiki.domain.Ebook;
import com.stelpolvo.wiki.domain.EbookExample;
import com.stelpolvo.wiki.domain.RespPage;
import com.stelpolvo.wiki.domain.vo.EbookVo;
import com.stelpolvo.wiki.mapper.EbookMapper;
import com.stelpolvo.wiki.utils.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookService {
    private static final Logger LOG = LoggerFactory.getLogger(EbookService.class);
    @Resource
    private EbookMapper ebookMapper;

    @Resource
    private SnowFlake snowFlake;

    public RespPage<Ebook> list(EbookVo ebookVo) {
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        if (!ObjectUtils.isEmpty(ebookVo.getName())) {
            criteria.andNameLike("%" + ebookVo.getName() + "%");
        }
        PageHelper.startPage(ebookVo.getPage(), ebookVo.getSize());
        List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);
        PageInfo<Ebook> ebookPageInfo = new PageInfo<>(ebookList);
        return new RespPage<>(ebookPageInfo.getTotal(), ebookList);
    }

    public int save(Ebook ebook) {
        if (ObjectUtils.isEmpty(ebook.getId())) {
            ebook.setId(snowFlake.nextId());
            return ebookMapper.insert(ebook);
        }
        return ebookMapper.updateByPrimaryKey(ebook);
    }

    public int delete(Long id) {
        return ebookMapper.deleteByPrimaryKey(id);
    }
}
