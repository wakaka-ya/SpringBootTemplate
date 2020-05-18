package com.wakaka.controller;

import com.wakaka.dao.mapper.SysMenuMapper;
import com.wakaka.dao.pojo.SysMenu;
import com.wakaka.util.LayuiTableUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sys_menu")
public class SysMenuController {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @GetMapping("/list.html")
    public String listHtml(ModelMap model) {

        return "sys_menu/list";
    }

    @PostMapping("/list")
    @ResponseBody
    public String list(Integer page, Integer limit) {
        Map<String, Object> map = new HashMap<>();
        Integer count = sysMenuMapper.getMenuCount(map);
        map.put("ofset", (page - 1) * limit);
        map.put("limit", limit);
        List<Map<String, Object>> list = sysMenuMapper.getMenuInfoList(map);
        return LayuiTableUtil.getTableJson(list, count);
    }

    @GetMapping("/edit.html")
    public String editHtml(@RequestParam String id){
        sysMenuMapper.selectByPrimaryKey(id);
        return "sys_menu/edit.html";
    }
}
