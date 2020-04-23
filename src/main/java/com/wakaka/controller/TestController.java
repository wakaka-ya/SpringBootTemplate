package com.wakaka.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class TestController {
    @GetMapping("/test/{id}")
    @ResponseBody
    public String getTest1(@PathVariable String id) {
        return "test1:" + id;
    }

    @GetMapping("/test")
    @ResponseBody
    public String getTest2(@RequestParam String id) {
        return "test2:" + id;
    }

    @DeleteMapping("/test")
    @ResponseBody
    public String getTest3(@RequestParam String id) {
        return "test3:" + id;
    }

    @DeleteMapping("/test/{id}")
    @ResponseBody
    public String getTest4(@PathVariable String id) {
        return "test4:" + id;
    }
}
