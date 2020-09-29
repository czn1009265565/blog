package com.czndata.blog.service.service;

import com.czndata.blog.service.dto.tag.TagDto;
import com.czndata.blog.service.dto.tag.TagParam;

import java.util.List;

public interface TagService {
    /**
     * 创建tag
     * @param tag tag
     * @return count
     */
    int create(String tag);

    /**
     * 删除指定tag
     * @param tagId tag id
     * @return count
     */
    int delete(Integer tagId);

    /**
     * 更新tag
     * @param tagParam tagParam
     * @return count
     */
    int update(TagParam tagParam);

    /**
     * tag列表
     * @return List<TagDto>
     */
    List<TagDto> list();
}
