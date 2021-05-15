package com.king.shiro.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @RequestMapping("/isPermission")
    @ResponseBody
    @RequiresPermissions("user:list")
    public String isPermission() {
        return "ok";
    }
}
