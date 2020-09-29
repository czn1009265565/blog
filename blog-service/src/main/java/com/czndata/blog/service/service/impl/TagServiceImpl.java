package com.czndata.blog.service.service.impl;

import com.czndata.blog.mbg.dao.TagMapper;
import com.czndata.blog.mbg.entity.Tag;
import com.czndata.blog.mbg.entity.TagExample;
import com.czndata.blog.service.dto.tag.TagDto;
import com.czndata.blog.service.dto.tag.TagParam;
import com.czndata.blog.service.enums.ResultEnum;
import com.czndata.blog.service.exception.BlogException;
import com.czndata.blog.service.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public int create(String tag) {
        // 去重判断
        TagExample tagExample = new TagExample();
        tagExample.or().andTagEqualTo(tag);
        List<Tag> tags = tagMapper.selectByExample(tagExample);
        if (tags.size() > 0) {
            log.info("tag:{} 已存在", tag);
            throw new BlogException(ResultEnum.EXIST_TAG);
        }
        // insert
        Tag tagEntity = new Tag();
        tagEntity.setTag(tag);
        return tagMapper.insertSelective(tagEntity);
    }

    @Override
    public int delete(Integer tagId) {
        return tagMapper.deleteByPrimaryKey(tagId);
    }

    @Override
    public int update(TagParam tagParam) {
        Tag tag = new Tag();
        BeanUtils.copyProperties(tagParam, tag);
        tag.setUpdateTime(new Date());
        return tagMapper.updateByPrimaryKeySelective(tag);
    }

    @Override
    public List<TagDto> list() {
        List<Tag> tags = tagMapper.selectByExample(new TagExample());
        return tags.stream()
                .map(i -> {
                    TagDto tagDto = new TagDto();
                    BeanUtils.copyProperties(i, tagDto);
                    return tagDto;
                })
                .collect(Collectors.toList());
    }
}
