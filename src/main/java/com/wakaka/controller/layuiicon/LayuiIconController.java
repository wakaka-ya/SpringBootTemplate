package com.wakaka.controller.layuiicon;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sys_layuiIcon")
public class LayuiIconController {
    @GetMapping("/table.html")
    public String tableHtml() {
        return "sys_layuiIcon/table";
    }
}
