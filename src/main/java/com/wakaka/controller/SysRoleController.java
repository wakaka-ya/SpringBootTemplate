package com.wakaka.controller;

import com.alibaba.fastjson.JSONObject;
import com.wakaka.dao.mapper.SysMenuMapper;
import com.wakaka.dao.pojo.*;
import com.wakaka.dao.pojo.SysMenuExample.Criteria;
import com.wakaka.service.SysUserService;
import com.wakaka.service.SysroleService;
import com.wakaka.util.DateUtil;
import com.wakaka.util.JsonUtil;
import com.wakaka.util.JsonUtils;
import com.wakaka.util.LayuiTableUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
/**
 * Description 角色管理
 * USER: zfy
 * Date: 2020/1/16 16:47
 */
@Controller
@RequestMapping("/sys_role")
public class SysRoleController {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysroleService sysRoleService;

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/add.html")
    public String addHtml(ModelMap model) {
        List<List<SysMenu>> lists = menuJson();
        String json = StringEscapeUtils.unescapeHtml4(JsonUtils.objectToJson(lists));
        model.put("lists", json);
        return "sys_role/add";
    }

    @GetMapping("/list.html")
    public String listHtml() {
        return "sys_role/list";
    }

    @PostMapping("/list")
    @ResponseBody
    public String list(Integer page, Integer limit) {
        List<Map<String, Object>> roleInfoList = sysRoleService.getRoleInfoList();
        return LayuiTableUtil.getLimitTableJson(roleInfoList, page, limit);
    }

    @GetMapping("/edit.html")
    public String editHtml(String id, ModelMap model) {
        List<List<SysMenu>> lists = menuJson();
        model.put("lists", JsonUtil.object2Json(lists));
        model.put("menuLong", lists.size() - 1);
        SysRole item = sysRoleService.selectRoleListById(id);
        model.put("item", item);
        String roleMenu = item.getRoleMenu();
        List<CheckedPMenu> jsonToList = JsonUtils.jsonToList(roleMenu, CheckedPMenu.class);
        List<String> titles = jsonToList.stream().filter(p -> p.getChildren() != null).flatMap(p -> {
            Stream<String> stringStream = p.getChildren().stream().map(CheckedCMenu::getTitle);
            String title = p.getTitle();
            List<String> collect1 = stringStream.collect(Collectors.toList());
            collect1.add(title);
            return collect1.stream();
        }).collect(Collectors.toList());
        /*List<String> titles = new ArrayList<String>();
        for(CheckedPMenu checkedPMenu: jsonToList) {
            titles.add(checkedPMenu.getTitle());
            if(checkedPMenu.getChildren() != null) {
                for(CheckedCMenu checkedCMenu: checkedPMenu.getChildren()) {
                    titles.add(checkedCMenu.getTitle());
                }
            }
        }*/
        model.put("titles", JsonUtils.objectToJson(titles));
        return "sys_role/edit";
    }

    private List<List<SysMenu>> menuJson() {
        SysMenuExample example = new SysMenuExample();
        example.setOrderByClause("RANK ASC");
        List<SysMenu> menuList = sysMenuMapper.selectByExample(example);
        Criteria criteria = example.createCriteria();
        criteria.andPidEqualTo(1);//父菜单
        List<SysMenu> parentMenu = sysMenuMapper.selectByExample(example);
        List<List<SysMenu>> lists = parentMenu.stream()
				.map(p -> menuList.stream().filter(c -> (c.getPid() != null ))
						.filter(c -> (c.getPid().equals(p.getId()) || c.getId().equals(p.getId())))
						.collect(Collectors.toList()))
				.collect(Collectors.toList());
		/*List<List<SysMenu>> lists = new ArrayList<List<SysMenu>>();
        for(SysMenu pmenu: parentMenu) {
            List<SysMenu> list = new ArrayList<SysMenu>();
            list.add(pmenu);
            for(SysMenu cmenu: menuList) {
                if(cmenu.getPid() != null && pmenu.getId() != null) {
                    if(cmenu.getPid().equals(pmenu.getId())) {
                        list.add(cmenu);
                    }
                }
            }
            lists.add(list);
        }*/
        return lists;
    }

    @GetMapping("/userManeger.html")
    public String userManegerHtml(String roleId, ModelMap model) {
        model.put("roleId", roleId);
        return "/sys_role/userManeger";
    }

    @PostMapping("/userManeger")
    @ResponseBody
    public String userManeger(String roleId, Integer page, Integer limit) {
        List<Map<String, Object>> userList = sysUserService.selectallUserInfo((page - 1) * limit, limit);
        int count = sysUserService.countallUserInfo();
        List<Integer> checkUserInfo = sysUserService.selectcheckUserInfo(roleId);
        for(Map<String, Object> map: userList) {
            for(Integer id: checkUserInfo) {
                if(map.get("id").equals(id)) {
                    map.put("LAY_CHECKED", true);
                    break;
                }
            }
            Date date = (Date)map.get("lastLoginTime");
            String lastLoginTime = DateUtil.date2Str(date);
            map.put("lastLoginTime", lastLoginTime);
        }
        String tableJson = LayuiTableUtil.getTableJson(userList, count);
        return tableJson;
    }

    @PostMapping("/delete")
    @ResponseBody
    public String delete(Integer id) {
        SysRole role = sysRoleService.selectRoleListById(id + "");
        String type = role.getRoleId();
        JSONObject data = new JSONObject();
        List<Integer> list = sysUserService.selectcheckUserInfo(type);
        if(CollectionUtils.isNotEmpty(list)) {
            data.put("Message", "当前角色下有关联用户,不可删除!");
            return JsonUtils.objectToJson(data);
        }
        int total = sysRoleService.delete(id);
        if(total > 0) {
            data.put("IsError", 0);
            data.put("Code", 200);
            data.put("Message", "删除角色成功!");
        } else {
            data.put("Message", "删除角色失败!");
        }

        return JsonUtils.objectToJson(data);
    }
}
