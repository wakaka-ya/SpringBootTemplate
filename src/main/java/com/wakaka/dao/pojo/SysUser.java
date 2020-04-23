package com.wakaka.dao.pojo;

import java.util.Date;

public class SysUser {
    private Integer id;

    private String uid;

    private String loginName;

    private String password;

    private String cell;

    private String type;

    private String status;

    private Date gmtCreate;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell == null ? null : cell.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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
}