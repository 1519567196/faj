package com.resjz.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 联系我们
 */
@Controller
public class MobileAboutUsController {
    @RequestMapping("/test")
   public String recruit(Model model){
        return "mobile/test";
    }
}
