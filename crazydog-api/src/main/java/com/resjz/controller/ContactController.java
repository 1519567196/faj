package com.resjz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class ContactController {
    //	联系我们
    @RequestMapping
    public String contact(){
        return "redirect:/404.html";
    }

}
