package com.wakaka.dao.mapper;

import com.wakaka.dao.pojo.SysUser;
import com.wakaka.dao.pojo.SysUserExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface SysUserMapper {
    int countByExample(SysUserExample example);

    int deleteByExample(SysUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    List<SysUser> selectByExample(SysUserExample example);

    SysUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByExample(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
    /**
     * 登录查询
     * @param loginName
     * @return
     */
	SysUser selectByName(String loginName);

    List<Map<String, Object>> selectallUserInfo(int offset, Integer limit);

    int countallUserInfo();

    List<Integer> selectcheckUserInfo(String roleId);

    SysUser selectUserByUid(String uid);
}