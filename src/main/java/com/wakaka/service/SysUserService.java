package com.wakaka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wakaka.dao.mapper.SysUserMapper;
import com.wakaka.dao.pojo.SysUser;

import java.util.List;
import java.util.Map;

@Service
public class SysUserService {
	
	@Autowired
	private SysUserMapper sysUserMapper;
	
	public SysUser selectByName(String loginName) {
		SysUser sysUser = sysUserMapper.selectByName(loginName);
		return sysUser;
	}

    public List<Map<String, Object>> selectallUserInfo(int offset, Integer limit) {
		return sysUserMapper.selectallUserInfo(offset,limit);
    }

	public int countallUserInfo() {
		return sysUserMapper.countallUserInfo();
	}

	public List<Integer> selectcheckUserInfo(String roleId) {
		return sysUserMapper.selectcheckUserInfo(roleId);
	}
}
