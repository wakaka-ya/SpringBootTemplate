package com.wakaka.service;

import com.wakaka.interceptor.JwtInterceptor;
import com.wakaka.jwt.pojo.Audience;
import com.wakaka.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
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
	@Autowired
	private Audience audience;
	
	public SysUser selectByName(String loginName) {
		SysUser sysUser = sysUserMapper.selectByName(loginName);
		return sysUser;
	}

    public List<Map<String, Object>> selectallUserInfo(int offset, Integer limit) {
		return sysUserMapper.selectallUserInfo(offset,limit);
    }

	public SysUser getUserByTokem(String token){
		Claims claims = JwtTokenUtil.parseJWT(token, audience.getBase64Secret());
		String userId = claims.get("userId").toString();
		SysUser sysUser = sysUserMapper.selectByPrimaryKey(Integer.valueOf(userId));
		return sysUser;
	}

	public int countallUserInfo() {
		return sysUserMapper.countallUserInfo();
	}

	public List<Integer> selectcheckUserInfo(String roleId) {
		return sysUserMapper.selectcheckUserInfo(roleId);
	}
}
