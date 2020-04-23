package com.wakaka.service;

import com.wakaka.dao.mapper.SysRoleMapper;
import com.wakaka.dao.pojo.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysroleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;

    public List<Map<String,Object>> getRoleInfoList(){

        return sysRoleMapper.getRoleInfoList();
    }

    public SysRole selectRoleListById(String id) {
        return sysRoleMapper.selectByPrimaryKey(Integer.valueOf(id));
    }

    public int delete(Integer id) {
        return sysRoleMapper.deleteByPrimaryKey(id);
    }
}
