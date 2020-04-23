package com.wakaka.dao.mapper;

import com.wakaka.dao.pojo.SysUserlogin;
import com.wakaka.dao.pojo.SysUserloginExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysUserloginMapper {
    int countByExample(SysUserloginExample example);

    int deleteByExample(SysUserloginExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysUserlogin record);

    int insertSelective(SysUserlogin record);

    List<SysUserlogin> selectByExample(SysUserloginExample example);

    SysUserlogin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysUserlogin record, @Param("example") SysUserloginExample example);

    int updateByExample(@Param("record") SysUserlogin record, @Param("example") SysUserloginExample example);

    int updateByPrimaryKeySelective(SysUserlogin record);

    int updateByPrimaryKey(SysUserlogin record);

    /**
     * 更新登录时间
     * @param uid
     */
    void updateLastLoginTime(String uid);
}