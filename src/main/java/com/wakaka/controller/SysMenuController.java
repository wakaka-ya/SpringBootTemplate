package com.wakaka.controller;

import com.wakaka.dao.mapper.SysMenuMapper;
import com.wakaka.dao.pojo.CheckedCMenu;
import com.wakaka.dao.pojo.CheckedPMenu;
import com.wakaka.dao.pojo.SysMenu;
import com.wakaka.dao.pojo.SysRole;
import com.wakaka.util.JsonUtil;
import com.wakaka.util.JsonUtils;
import com.wakaka.util.LayuiTableUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public String editHtml(Integer id, ModelMap model) {
        SysMenu sysMenu = sysMenuMapper.selectByPrimaryKey(id);
        model.put("sysMenu", sysMenu);
        return "sys_menu/edit";
    }
}
