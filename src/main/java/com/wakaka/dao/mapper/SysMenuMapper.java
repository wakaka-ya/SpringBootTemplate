package com.wakaka.dao.mapper;

import com.wakaka.dao.pojo.SysMenu;
import com.wakaka.dao.pojo.SysMenuExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface SysMenuMapper {
    int countByExample(SysMenuExample example);

    int deleteByExample(SysMenuExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    List<SysMenu> selectByExample(SysMenuExample example);

    SysMenu selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysMenu record, @Param("example") SysMenuExample example);

    int updateByExample(@Param("record") SysMenu record, @Param("example") SysMenuExample example);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);
    /**
     * @Author zfy
     * @Date 2020/1/17 8:52
     * @MethodName getMenuInfoList
     * @MethodParameter
     * @MethodReturnType
     * @declare 获取菜单数据表格
     */
    List<Map<String, Object>> getMenuInfoList(Map<String,Object> map);
    /**
     * @Author zfy
     * @Date 2020/1/17 8:53
     * @MethodName getMenuCount
     * @MethodParameter
     * @MethodReturnType
     * @declare 菜单总数（用于分页）
     */
    Integer getMenuCount(Map<String, Object> map);
}