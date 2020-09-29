package com.czndata.blog.service.service;

import com.czndata.blog.service.dto.introduction.IntroductionDto;
import com.czndata.blog.service.dto.introduction.IntroductionParam;

public interface IntroductionService {

    int create(IntroductionParam introductionParam);

    int delete(Integer introductionId);

    IntroductionDto detail(Integer userId);
}
