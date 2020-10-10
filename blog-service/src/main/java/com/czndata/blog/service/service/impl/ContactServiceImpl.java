package com.czndata.blog.service.service.impl;

import com.czndata.blog.mbg.dao.ContactMapper;
import com.czndata.blog.mbg.entity.Contact;
import com.czndata.blog.mbg.entity.ContactExample;
import com.czndata.blog.service.constant.BlogConstant;
import com.czndata.blog.service.dto.contact.ContactParam;
import com.czndata.blog.service.service.ContactService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactMapper contactMapper;

    @Override
    public int create(ContactParam contactParam) {
        Contact contact = new Contact();
        BeanUtils.copyProperties(contactParam, contact);
        return contactMapper.insertSelective(contact);
    }

    @Override
    public int delete(Integer contactId) {
        if (contactId == null)return 0;
        return contactMapper.deleteByPrimaryKey(contactId);
    }

    @Override
    public PageInfo<Contact> list(Integer pageNum, Integer pageSize) {
        if (pageNum == null){
            pageNum = BlogConstant.PAGE_NUM;
        }
        if (pageSize == null){
            pageSize = BlogConstant.PAGE_SIZE;
        }

        ContactExample contactExample = new ContactExample();
        PageHelper.startPage(pageNum,pageSize);
        List<Contact> contactList = contactMapper.selectByExample(contactExample);
        return new PageInfo<>(contactList);
    }
}
