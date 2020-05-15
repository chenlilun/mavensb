package com.hengyi.controllor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class QuckController {
    @RequestMapping("/quck")
    @ResponseBody
    public String quck(){
        return "hello,world" ;
    }
}
