package com.onproject.controller;

import com.onproject.entity.Member;
import com.onproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MemberController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/joinUs.do", method = RequestMethod.POST)
    public String joinUs(Member me) {

        userService.joinUser(me);
        return "login";
    }

    @GetMapping("/login")
    public String postloginpage() {
        return "login";
    }

    @GetMapping("/join")
    public String postjoinpage() {
        return "join";
    }

    @RequestMapping(value = "/login.do",method = RequestMethod.POST)
    public String login(Member me, Model model) {
        System.out.println("me = " + me);
        Member member=userService.loginmember(me.getId(), me.getPassword());
        if(member==null) {
            model.addAttribute("loginMessage", "아이디 혹은 비밀번호가 일치하지 않습니다!");
            return "login";
        }
        model.addAttribute("name", member.getName());
        return "main";
    }
}