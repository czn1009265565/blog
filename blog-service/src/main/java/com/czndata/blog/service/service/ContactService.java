package com.czndata.blog.service.service;

import com.czndata.blog.mbg.entity.Contact;
import com.czndata.blog.service.dto.contact.ContactParam;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ContactService {
    /**
     * 新建留言
     * @param contactParam contactParam
     * @return count
     */
    int create(ContactParam contactParam);

    /**
     * 删除留言
     * @param contactId contactId
     * @return count
     */
    int delete(Integer contactId);

    /**
     * 留言列表
     * @return List<Contact>
     */
    PageInfo<Contact> list(Integer pageNum, Integer pageSize);
}
