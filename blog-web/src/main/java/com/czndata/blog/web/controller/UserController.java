package com.czndata.blog.web.controller;

import com.czndata.blog.service.dto.user.UserDto;
import com.czndata.blog.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/about")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ModelAndView about(
            @RequestParam(value = "userId", required = false) Integer userId,
            Model model) {

        UserDto userDto = userService.detail(userId);
        model.addAttribute("userDto", userDto);
        return new ModelAndView("about");
    }
}
