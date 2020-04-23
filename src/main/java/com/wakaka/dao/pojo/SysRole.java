package com.wakaka.dao.pojo;

import java.util.Date;

public class SysRole {
    private Integer id;

    private String roleId;

    private String roleName;

    private String roleDesc;

    private Date gmtCreate;

    private Date gmtModified;

    private String roleMenu;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc == null ? null : roleDesc.trim();
    }

    public Date getGmtCreate() {
        return (Date) gmtCreate.clone();
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = (Date) gmtCreate.clone();
    }

    public Date getGmtModified() {
        return (Date) gmtModified.clone();
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = (Date) gmtModified.clone();
    }

    public String getRoleMenu() {
        return roleMenu;
    }

    public void setRoleMenu(String roleMenu) {
        this.roleMenu = roleMenu == null ? null : roleMenu.trim();
    }
}