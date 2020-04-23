package com.wakaka.dao.mapper;

import com.wakaka.dao.pojo.SysRole;
import com.wakaka.dao.pojo.SysRoleExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface SysRoleMapper {
    int countByExample(SysRoleExample example);

    int deleteByExample(SysRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    List<SysRole> selectByExampleWithBLOBs(SysRoleExample example);

    List<SysRole> selectByExample(SysRoleExample example);

    SysRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysRole record, @Param("example") SysRoleExample example);

    int updateByExampleWithBLOBs(@Param("record") SysRole record, @Param("example") SysRoleExample example);

    int updateByExample(@Param("record") SysRole record, @Param("example") SysRoleExample example);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKeyWithBLOBs(SysRole record);

    int updateByPrimaryKey(SysRole record);

    /**
     * 查询所有的角色
     * @return
     */
    List<Map<String, Object>> getRoleInfoList();
}