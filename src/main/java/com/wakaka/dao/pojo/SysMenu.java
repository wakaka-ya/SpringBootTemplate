package com.wakaka.dao.pojo;

import java.util.Date;

public class SysMenu {
    private Integer id;

    private Integer pid;

    private String title;

    private String icon;

    private String href;

    private Integer rank;

    private Date gmtCreate;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href == null ? null : href.trim();
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
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