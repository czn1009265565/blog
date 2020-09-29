package com.czndata.blog.web.service.impl;

import com.czndata.blog.service.service.TagService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TagServiceImplTest {

    @Autowired
    private TagService tagService;

    @Test
    void delete(){
        tagService.delete(1);
    }
}
